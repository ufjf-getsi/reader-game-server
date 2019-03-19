package Comands;

import Models.Image;
import Mundo.Game;
import Mundo.GameSteps;
import Mundo.Player;
import Persistence.GameDAO;
import Persistence.ImageDAO;
import Persistence.PlayerDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openide.util.Exceptions;

public class GetAvancarGameCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            List<Image> images = ImageDAO.getInstance().listImages(id);
            Game game = GameDAO.getInstance().searchGame(id);
            GameSteps avancarGame = new GameSteps();
            Boolean avancou = avancarGame.atualizarJogadorAtual(game);

            if (avancou) {
                GameDAO.getInstance().updateGame(game);
                Player player = PlayerDAO.getInstance().searchPlayer(game.getCurrentPlayer(), id);
                request.setAttribute("gameId", id);
                request.setAttribute("player", player);
                request.setAttribute("nomeimagem", images.get(0).getPath());
                request.setAttribute("nomefigura", images.get(1).getPath());
                RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/figura.jsp");
                despachante.forward(request, response);
            }
            else
            {
                System.out.println("Final de partida");
            }

        } catch (IOException | ServletException | SQLException | ClassNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
