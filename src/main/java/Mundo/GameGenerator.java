package Mundo;

import Grafo.Grafo;
import Persistence.GameDAO;
import java.sql.SQLException;
import java.util.List;
import org.openide.util.Exceptions;

public class GameGenerator {

    public Integer saveInitialData(String name, Integer players, Integer turnsLeft) {
        try {
            OrdemGenerator ordem = new OrdemGenerator(players);
            Game game = new Game(name, players, ordem.getOrdem().get(0), (turnsLeft * players), ordem.getOrdem(players));
            Integer game_id = GameDAO.getInstance().saveGame(game);
            return game_id;
        } catch (ClassNotFoundException | SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    public Grafo savePlayersDataAndStartGame(Integer playersNumber, String []playersName) {
        Game fase = new Game(playersNumber, playersName);
        //GrafoGenerator gerador = new GrafoGeneratorTeia();
        //fase.setMapa(gerador.getGrafo(quantidadeDeVertices));
        fase.geraMapa();
        List<Player> jogadores = fase.getJogadores();
        /*System.out.println("Teste movimentacao do jogador");
            System.out.println("Vertice jogador 1 antes: " + fase.getJogadores().get(0).getVertice().getIndice());
            System.out.println("Movendo...");
            fase.getJogadores().get(0).mover(0);
            System.out.println("Vertice jogador 1 depois: " + fase.getJogadores().get(0).getVertice().getIndice());*/
        Grafo grafo = fase.getMapa();
        return grafo;
    }
}
