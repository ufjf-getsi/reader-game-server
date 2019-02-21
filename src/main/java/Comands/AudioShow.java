package Comands;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AudioShow implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        OutputStream os = null;
        BufferedInputStream bis = null;

        try {
            Properties config = new Properties();
            config.load(request.getServletContext().getResourceAsStream("/WEB-INF/properties/config.properties"));
            File uploads = new File(config.getProperty("UPLOAD_DIR"));
            String fileName = request.getParameter("nomeaudio");
            File audio = new File(uploads, fileName);
            os = response.getOutputStream();

            response.setContentType("audio/webm");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentLength((int) audio.length());
            FileInputStream input = new FileInputStream(audio);
            bis = new BufferedInputStream(input);
            int readBytes = 0;
            while ((readBytes = bis.read()) != -1) {
                os.write(readBytes);
            }
            os.close();
            bis.close();
        } catch (IOException e) {
            Logger.getLogger(GetIndexCommand.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
