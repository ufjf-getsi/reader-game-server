package Comands;

import Mundo.Game;
import Mundo.OrdemGenerator;
import Persistence.GameDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openide.util.Exceptions;

public class PostIndexCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("matchName");
            Integer turnsLeft = Integer.parseInt(request.getParameter("turns"));
            Integer players = Integer.parseInt(request.getParameter("players"));
            
            OrdemGenerator ordem = new OrdemGenerator(players);
            
            Game game = new Game(name, players, ordem.getOrdem().get(0), (turnsLeft*players), ordem.getOrdem(players));
            Integer game_id = GameDAO.getInstance().saveGame(game);
            response.sendRedirect("alunos.html?id="+game_id);
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Exceptions.printStackTrace(ex);
        } 
    }

}
