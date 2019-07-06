package Comands;

import Mundo.Game;
import Mundo.Player;
import Persistence.GameDAO;
import Persistence.PlayerDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetDetalhesPartidaCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer gameId = Integer.parseInt(request.getParameter("codigo"));
            Game game = GameDAO.getInstance().searchGame(gameId);
            request.setAttribute("titulo", game.getTittle());
            Player playerAtual = PlayerDAO.getInstance().searchPlayer(game.getCurrentPlayer(), gameId);
            request.setAttribute("rodadas", game.getTurnsLeft());
            request.setAttribute("jogadores", PlayerDAO.getInstance().searchPlayersGame(gameId));
            request.setAttribute("jogadoratual", playerAtual);
            request.setAttribute("codigopartida", game.getTittle() + gameId);
            request.setAttribute("palavras", GameDAO.getInstance().searchWords(gameId));
            RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/detalhe-partida.jsp");
            despachante.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(GetDetalhesPartidaCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
