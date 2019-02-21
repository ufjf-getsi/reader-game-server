package Comands;

import static cern.clhep.Units.sr;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openide.util.Exceptions;

public class SVGShow implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            Properties config = new Properties();
            config.load(request.getServletContext().getResourceAsStream("/WEB-INF/properties/config.properties"));
            File uploads = new File(config.getProperty("UPLOAD_DIR"));
            String fileName = request.getParameter("nomeimagem");
            File file = new File(uploads, fileName);
            System.out.println(file.getAbsoluteFile());
            response.setContentType("image/svg+xml");
            FileInputStream fis = new FileInputStream(file);
            IOUtils.copy(fis, response.getOutputStream());
            fis.close();
        } catch (IOException e) {
            Logger.getLogger(GetIndexCommand.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
