package Persistence;

import Mundo.Game;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDAO {

    private final static GameDAO instance = new GameDAO();
    private final String operacaoSaveGame = "insert into game (name, turnsleft, players, currentPlayer, turnOrder) values (?, ?, ?, ?, ?)";
    private final String operacaoListGame = "select * from game";
    private final String operacaoListPlayersNumber = "select players from game where game_identifier = ?";
    private final String operacaoListCurrentPlayer = "select currentPlayer from game where game_identifier = ?";
    private final String operacaoSearchGame = "select * from game where game_identifier = ?";
    private final String operacaoUpdateGame = "update game set currentPlayer = ?, turnsleft = ? where game_identifier = ?";

    private GameDAO() {
    }

    public static GameDAO getInstance() {
        return instance;
    }

    public Integer saveGame (Game game) throws SQLException, ClassNotFoundException {
        PreparedStatement comando = DatabaseLocator.getInstance().getConnection().prepareStatement(operacaoSaveGame);
        comando.clearParameters();
        comando.setString(1, game.getTittle());
        comando.setInt(2, game.getTurnsLeft());
        comando.setInt(3, game.getPlayers());
        comando.setInt(4, game.getCurrentPlayer());
        comando.setString(5, game.getPlayersOrder());
        comando.execute();
        comando.close();
        Integer idCriado = 1;
        comando = DatabaseLocator.getInstance().getConnection().prepareStatement(operacaoListGame);
        comando.clearParameters();
        ResultSet resultado = comando.executeQuery();
        while (resultado.next())
        {
            idCriado =  resultado.getInt("game_identifier");
        }
        comando.close();
        return idCriado;
    }
    
    public Integer searchPlayersNumber (Integer id) throws SQLException, ClassNotFoundException {
        PreparedStatement comando = DatabaseLocator.getInstance().getConnection().prepareStatement(operacaoListPlayersNumber);
        comando.setInt(1, id);
        ResultSet resultado = comando.executeQuery();
        while (resultado.next()) {
            return resultado.getInt("players");
        }
        comando.close();
        return 0;
    }
    
    public Integer searchCurrentPlayer (Integer gameId) throws SQLException, ClassNotFoundException {
        PreparedStatement comando = DatabaseLocator.getInstance().getConnection().prepareStatement(operacaoListCurrentPlayer);
        comando.setInt(1, gameId);
        
        Integer currentPlayer = -1;
        
        ResultSet resultado = comando.executeQuery();
        while (resultado.next())
        {
            currentPlayer = resultado.getInt("currentPlayer");
        }
        comando.close();
        return currentPlayer;
    }
    
    public Game searchGame (Integer gameID) throws SQLException, ClassNotFoundException {
        PreparedStatement comando = DatabaseLocator.getInstance().getConnection().prepareStatement(operacaoSearchGame);
        comando.setInt(1, gameID);
        
        Game game = new Game();
        ResultSet resultado = comando.executeQuery();
        while (resultado.next())
        {
            game.setIdentifier(gameID);
            game.setCurrentPlayer(resultado.getInt("currentPlayer"));
            game.setTurnsLeft(resultado.getInt("turnsleft"));
            game.setPlayersOrder(resultado.getString("turnorder"));
        }
        comando.close();
        return game;
    }
    
    public void updateGame (Game game) throws ClassNotFoundException, SQLException
    {
        PreparedStatement comando = DatabaseLocator.getInstance().getConnection().prepareStatement(operacaoUpdateGame);
        comando.setInt(1, game.getCurrentPlayer());
        comando.setInt(2, game.getTurnsLeft());
        comando.setInt(3, game.getIdentifier());
        comando.executeUpdate();
        comando.close();
    }
}
