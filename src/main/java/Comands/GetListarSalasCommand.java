package Comands;

import Persistence.GameDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetListarSalasCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
       try {
            request.setAttribute("games", GameDAO.getInstance().searchAllGames());
            RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/listar-salas.jsp");
            despachante.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(GetListarSalasCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
