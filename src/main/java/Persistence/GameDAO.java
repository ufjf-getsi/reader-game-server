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
    private PreparedStatement operacaoListCurrentPlayer;
    private PreparedStatement operacaoSearchGame;
    private PreparedStatement operacaoUpdateGame;

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
    
    public Integer searchCurrentPlayer (Integer gameId) throws SQLException, ClassNotFoundException {
        operacaoListCurrentPlayer = DatabaseLocator.getInstance().getConnection().prepareStatement("select currentPlayer from game where game_identifier = ?");
        operacaoListCurrentPlayer.setInt(1, gameId);
        
        Integer currentPlayer = -1;
        
        ResultSet resultado = operacaoListCurrentPlayer.executeQuery();
        while (resultado.next())
        {
            currentPlayer = resultado.getInt("currentPlayer");
        }
        return currentPlayer;
    }
    
    public Game searchGame (Integer gameID) throws SQLException, ClassNotFoundException {
        operacaoSearchGame = DatabaseLocator.getInstance().getConnection().prepareStatement("select * from game where game_identifier = ?");
        operacaoSearchGame.setInt(1, gameID);
        
        Game game = new Game();
        ResultSet resultado = operacaoSearchGame.executeQuery();
        while (resultado.next())
        {
            game.setIdentifier(gameID);
            game.setCurrentPlayer(resultado.getInt("currentPlayer"));
            game.setTurnsLeft(resultado.getInt("turnsleft"));
            game.setPlayersOrder(resultado.getString("turnorder"));
        }
        return game;
    }
    
    public void updateGame (Game game) throws ClassNotFoundException, SQLException
    {
        operacaoUpdateGame = DatabaseLocator.getInstance().getConnection().prepareStatement("update game set currentPlayer = ?, turnsleft = ? where game_identifier = ?");
        operacaoUpdateGame.setInt(1, game.getCurrentPlayer());
        operacaoUpdateGame.setInt(2, game.getTurnsLeft());
        operacaoUpdateGame.setInt(3, game.getIdentifier());
        operacaoUpdateGame.executeUpdate();
    }
}
