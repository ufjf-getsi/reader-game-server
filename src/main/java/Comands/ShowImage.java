package Comands;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowImage implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            Properties config = new Properties();
            config.load(request.getServletContext().getResourceAsStream("/WEB-INF/properties/config.properties"));
            File uploads = new File(config.getProperty("UPLOAD_DIR"));
            String fileName = request.getParameter("nomeimagem");
            FileInputStream fis = new FileInputStream(new File(uploads, fileName));
            response.setContentType("image/gif");
            BufferedImage bi = ImageIO.read(fis);
            try (OutputStream os = response.getOutputStream()) {
                ImageIO.write(bi, "gif", os);
                fis.close();
            }
        } catch (IOException e) {
            Logger.getLogger(GetIndexCommand.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
