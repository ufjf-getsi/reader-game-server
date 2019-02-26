package Comands;

import Persistence.GameDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openide.util.Exceptions;

public class GetPlayersNameCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));

            Integer numAlunos = GameDAO.getInstance().searchPlayersNumber(id);
            request.setAttribute("numAlunos", numAlunos);
            request.setAttribute("id", id);
            RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/players.jsp");
            despachante.forward(request, response);
        } catch (IOException | ServletException |ClassNotFoundException | SQLException ex) {
            Exceptions.printStackTrace(ex);
        } 
    }

}
