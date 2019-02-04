package Comands;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetIndexCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/index.jsp");
            despachante.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(GetIndexCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
