package Comands;

import Models.Image;
import Mundo.Game;
import Mundo.GameSteps;
import Mundo.Player;
import Persistence.GameDAO;
import Persistence.ImageDAO;
import Persistence.PlayerDAO;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.FileList;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.openide.util.Exceptions;

public class GetAvancarGameCommand implements Comando {

    List<String> sons = new ArrayList<String>() {
        {
            add("palito.wav");
            add("pálido.wav");
            add("patins.wav");
            add("carroça.wav");
            add("chaveiro.wav");
            add("camisa.wav");
            add("piolho.wav");
            add("calça.wav");
            add("comida.wav");
            add("casamento.wav");
        }
    };

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            List<Image> images = ImageDAO.getInstance().listImages(id);
            Game game = GameDAO.getInstance().searchGame(id);
            Player playerAtual = PlayerDAO.getInstance().searchPlayer(game.getCurrentPlayer(), id);
            GameSteps avancarGame = new GameSteps();
            Boolean avancou = avancarGame.atualizarJogadorAtual(game);

            if (avancou) {
                File som = avaliaAudio(request, playerAtual);
                uploadGoogleDrive(game, playerAtual, som);

                GameDAO.getInstance().updateGame(game);
                Player proximoPlayer = PlayerDAO.getInstance().searchPlayer(game.getCurrentPlayer(), id);
                //request.setAttribute("nomeimagem", images.get(0).getPath());
                //request.setAttribute("nomefigura", images.get(1).getPath());
                request.setAttribute("gameId", id);
                request.setAttribute("player", proximoPlayer);
                RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/figura.jsp");
                despachante.forward(request, response);
            } else {
                System.out.println("Final de partida");
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private File avaliaAudio(HttpServletRequest request, Player player) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS).
                writeTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build();

        Properties config = new Properties();
        config.load(request.getServletContext().getResourceAsStream("/WEB-INF/properties/config.properties"));

        File uploads = new File(config.getProperty("UPLOAD_DIR"));
        if (!uploads.exists()) {
            uploads.mkdirs();
        }

        File som = null;
        File[] sons = uploads.listFiles();
        for (File f : sons) {
            if (f.getAbsoluteFile().getName().equals(this.sons.get(player.getIdentifier_in_game()))) {
                som = f;
            }
        }

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("text", this.sons.get(player.getIdentifier_in_game()))
                .addFormDataPart("audio", "audio.wav",
                        RequestBody.create(MediaType.get("audio/wav"), som))
                .build();

        Request request2 = new Request.Builder()
                .url("http://200.131.219.35:8085/")
                .post(requestBody)
                .build();
        Response response2 = client.newCall(request2).execute();
        System.out.println(response2.body().string());
        return som;
    }

    private void uploadGoogleDrive(Game game, Player player, File som) throws Exception {
        com.google.api.services.drive.model.File googleFile;
        com.google.api.services.drive.model.File fileAux = getGoogleRootFolderByName(game.getIdentifier() + "-" + game.getTittle()); // pasta geral
        if (fileAux != null) { // Se existir a pasta do jogo
            com.google.api.services.drive.model.File fileAux2 = getGoogleRootPlayerFoldersByName(fileAux, player.getName()); // pasta do jogador dentro da pasta do jogo
            if (fileAux2 == null) { //Se não existir a pasta do jogador
                com.google.api.services.drive.model.File folder = createGoogleFolder(fileAux.getId(), player.getName());
                fileAux2 = folder;
            }
            googleFile = createGoogleFile(fileAux2.getId(), "audio/wav", player.getName() + "-" + this.sons.get(player.getIdentifier_in_game()), som);
        } else { // Cria a pasta geral e a do primeiro jogador
            com.google.api.services.drive.model.File folder = createGoogleFolder(null, game.getIdentifier() + "-" + game.getTittle());
            com.google.api.services.drive.model.File fileAux2 = createGoogleFolder(folder.getId(), player.getName());
            googleFile = createGoogleFile(fileAux2.getId(), "audio/wav", player.getName() + "-" + this.sons.get(player.getIdentifier_in_game()), som);
        }
        System.out.println("Created Google file!");
        System.out.println("WebContentLink: " + googleFile.getWebContentLink());
        System.out.println("WebViewLink: " + googleFile.getWebViewLink());
        System.out.println("Done!");
    }

    private com.google.api.services.drive.model.File getGoogleRootPlayerFoldersByName(com.google.api.services.drive.model.File fileAux, String subFolderName) throws IOException, Exception {
        return getGoogleSubFolderByName(fileAux.getId(), subFolderName);
    }

    private com.google.api.services.drive.model.File getGoogleRootFolderByName(String subFolderName) throws IOException, Exception {
        return getGoogleSubFolderByName(null, subFolderName);
    }

    private com.google.api.services.drive.model.File getGoogleSubFolderByName(String googleFolderIdParent, String subFolderName)
            throws IOException, Exception {

        Drive driveService = GoogleDriveUtils.getDriveService();

        String pageToken = null;
        List<com.google.api.services.drive.model.File> list = new ArrayList<>();

        String query = null;
        if (googleFolderIdParent == null) {
            query = " name = '" + subFolderName + "' " //
                    + " and mimeType = 'application/vnd.google-apps.folder' " //
                    + " and 'root' in parents";
        } else {
            query = " name = '" + subFolderName + "' " //
                    + " and mimeType = 'application/vnd.google-apps.folder' " //
                    + " and '" + googleFolderIdParent + "' in parents";
        }

        do {
            FileList result = driveService.files().list().setQ(query).setSpaces("drive") //
                    .setFields("nextPageToken, files(id, name, createdTime)")//
                    .setPageToken(pageToken).execute();
            for (com.google.api.services.drive.model.File file : result.getFiles()) {
                list.add(file);
            }
            pageToken = result.getNextPageToken();
        } while (pageToken != null);
        //
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private com.google.api.services.drive.model.File createGoogleFile(String googleFolderIdParent, String contentType, //
            String customFileName, java.io.File uploadFile) throws IOException, Exception {

        //
        AbstractInputStreamContent uploadStreamContent = new FileContent(contentType, uploadFile);
        //
        return _createGoogleFile(googleFolderIdParent, contentType, customFileName, uploadStreamContent);
    }

    private com.google.api.services.drive.model.File _createGoogleFile(String googleFolderIdParent, String contentType, //
            String customFileName, AbstractInputStreamContent uploadStreamContent) throws IOException, Exception {

        com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
        fileMetadata.setName(customFileName);

        List<String> parents = Arrays.asList(googleFolderIdParent);
        fileMetadata.setParents(parents);
        //
        Drive driveService = GoogleDriveUtils.getDriveService();

        com.google.api.services.drive.model.File file = driveService.files().create(fileMetadata, uploadStreamContent)
                .setFields("id, webContentLink, webViewLink, parents").execute();

        return file;
    }

    private com.google.api.services.drive.model.File createGoogleFolder(String folderIdParent, String folderName) throws IOException, Exception {

        com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();

        fileMetadata.setName(folderName);
        fileMetadata.setMimeType("application/vnd.google-apps.folder");

        if (folderIdParent != null) {
            List<String> parents = Arrays.asList(folderIdParent);

            fileMetadata.setParents(parents);
        }
        Drive driveService = GoogleDriveUtils.getDriveService();

        // Create a Folder.
        // Returns File object with id & name fields will be assigned values
        com.google.api.services.drive.model.File file = driveService.files().create(fileMetadata).setFields("id, name").execute();

        return file;
    }
}
