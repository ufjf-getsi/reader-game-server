package Comands;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
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

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {

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
                if (f.getAbsoluteFile().getName().equals("palito.wav")) {
                    som = f;
                }
            }

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("text", "palitó")
                    .addFormDataPart("audio", "audio.wav",
                            RequestBody.create(MediaType.get("audio/wav"), som))
                    .build();

            Request request2 = new Request.Builder()
                    .url("http://200.131.219.35:8085/")
                    .post(requestBody)
                    .build();
            Response response2 = client.newCall(request2).execute();
            System.out.println(response2.body().string());

            /*Integer id = Integer.parseInt(request.getParameter("id"));
            List<Image> images = ImageDAO.getInstance().listImages(id);
            Game game = GameDAO.getInstance().searchGame(id);
            GameSteps avancarGame = new GameSteps();
            Boolean avancou = avancarGame.atualizarJogadorAtual(game);

            if (avancou) {
                GameDAO.getInstance().updateGame(game);
                Player player = PlayerDAO.getInstance().searchPlayer(game.getCurrentPlayer(), id);
                request.setAttribute("gameId", id);
                request.setAttribute("player", player);
                request.setAttribute("nomeimagem", images.get(0).getPath());
                request.setAttribute("nomefigura", images.get(1).getPath());
                RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/figura.jsp");
                despachante.forward(request, response);
            }
            else
            {
                System.out.println("Final de partida");
            }*/
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
