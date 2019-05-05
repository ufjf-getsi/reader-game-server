package Comands;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openide.util.Exceptions;

public class GetGrafoCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/grafo.jsp");
            despachante.forward(request, response);
        } catch (IOException | ServletException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
