package Comands;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openide.util.Exceptions;

public class AudioListCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            Properties config = new Properties();
            config.load(request.getServletContext().getResourceAsStream("/WEB-INF/properties/config.properties"));
            File folder = new File(config.getProperty("UPLOAD_DIR")+"/audio");
            
            File[] files = folder.listFiles();
            request.setAttribute("audios", files);
            RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/audiolist.jsp");
            despachante.forward(request, response);
        } catch (IOException | ServletException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
