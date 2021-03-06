package Comands;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetConfigurarCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
       try {
            RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/configurar.jsp");
            despachante.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(GetConfigurarCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
