package Comands;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostAudioCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {

            Properties config = new Properties();
            config.load(request.getServletContext().getResourceAsStream("/WEB-INF/properties/config.properties"));
            File uploads = new File(config.getProperty("UPLOAD_DIR")+"/audio/");
            File out = File.createTempFile("audio", ".webm", uploads);
            
            byte[] buffer = new byte[1024 * 1024];
            BufferedOutputStream buffOut = new BufferedOutputStream(new FileOutputStream(out));
            int bytesRead;
            ServletInputStream input = request.getInputStream();
            while ((bytesRead = input.read(buffer)) != -1) {
                buffOut.write(buffer, 0, bytesRead);
            }
            buffOut.flush();
            request.setAttribute("nomeaudio", out.getName());
            response.setStatus(HttpServletResponse.SC_OK);
            
            //RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/audio.jsp");
            //despachante.forward(request, response);

        } catch (IOException ex) {
            Logger.getLogger(GetIndexCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
