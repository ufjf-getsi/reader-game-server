package Persistence;

import Mundo.Game;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDAO {

    private final static GameDAO instance = new GameDAO();
    private PreparedStatement operacaoSaveGame;
    private PreparedStatement operacaoListGame;
    private PreparedStatement operacaoListPlayersNumber;

    private GameDAO() {
    }

    public static GameDAO getInstance() {
        return instance;
    }

    public Integer saveGame (Game game) throws SQLException, ClassNotFoundException {
        operacaoSaveGame = DatabaseLocator.getInstance().getConnection().prepareStatement("insert into game (name, players, turnsleft, currentPlayer, turnOrder) values (?, ?, ?, ?, ?)");
        operacaoSaveGame.clearParameters();
        operacaoSaveGame.setString(1, game.getName());
        operacaoSaveGame.setInt(2, game.getPlayers());
        operacaoSaveGame.setInt(3, game.getTurnsLeft());
        operacaoSaveGame.setInt(4, game.getCurrentPlayer());
        operacaoSaveGame.setString(5, game.getPlayersOrder());
        operacaoSaveGame.execute();
        Integer idCriado = 1;
        operacaoListGame = DatabaseLocator.getInstance().getConnection().prepareStatement("select * from game");
        operacaoListGame.clearParameters();
        ResultSet resultado = operacaoListGame.executeQuery();
        while (resultado.next())
        {
            idCriado =  resultado.getInt("game_identifier");
        }
        return idCriado;
    }
    
    public Integer searchPlayersNumber (Integer id) throws SQLException, ClassNotFoundException {
        operacaoListPlayersNumber = DatabaseLocator.getInstance().getConnection().prepareStatement("select players from game where game_identifier = ?");
        operacaoListPlayersNumber.setInt(1, id);
        ResultSet resultado = operacaoListPlayersNumber.executeQuery();
        while (resultado.next()) {
            return resultado.getInt("players");
        }
        return 0;
    }
}
