package Comands;

import Models.Image;
import Mundo.Game;
import Mundo.GameSteps;
import Mundo.Player;
import Persistence.FirestoreLocator;
import Persistence.GameDAO;
import Persistence.ImageDAO;
import Persistence.PlayerDAO;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
                salvarComFireStore(game, playerAtual, som);

                GameDAO.getInstance().updateGame(game);
                Player proximoPlayer = PlayerDAO.getInstance().searchPlayer(game.getCurrentPlayer(), id);
                request.setAttribute("nomeimagem", images.get(0).getPath());
                request.setAttribute("nomefigura", images.get(1).getPath());
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
//        Response response2 = client.newCall(request2).execute();
//        System.out.println(response2.body().string());
        return som;
    }

    private void salvarComFireStore(Game game, Player player, File som) throws FileNotFoundException, IOException {
        FirestoreLocator.getInstance().getEscrita();
        Bucket bucket = StorageClient.getInstance().bucket();

        int len = (int) som.length();
        byte[] sendBuf = new byte[len];
        FileInputStream inFile = null;
        try {
            inFile = new FileInputStream(som);
            inFile.read(sendBuf, 0, len);
        } catch (FileNotFoundException fnfex) {
        } catch (IOException ioex) {
        }

        BlobId blobId = BlobId.of("readergameserver.appspot.com", "game" + game.getIdentifier() + "/" + player.getName() + "/" + som.getName());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("audio/wav").build();
        Blob blob = bucket.getStorage().create(blobInfo, sendBuf);
    }
}
