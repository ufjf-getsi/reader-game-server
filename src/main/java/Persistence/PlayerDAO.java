package Persistence;

import Mundo.Player;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PlayerDAO {

    private final static PlayerDAO instance = new PlayerDAO();
    private PreparedStatement operacaoSavePlayer;
    private PreparedStatement operacaoSearchPlayer;

    private PlayerDAO() {
    }

    public static PlayerDAO getInstance() {
        return instance;
    }

    public void savePlayers(List<Player> jogadores, Integer gameIdentifier) throws SQLException, ClassNotFoundException {
        operacaoSavePlayer = DatabaseLocator.getInstance().getConnection().prepareStatement("insert into player (player_identifier_in_game, name, position, team, points, fk_game_identifier) values (?, ?, ?, ?, ?, ?)");
        for (Player jogadore : jogadores) {
            operacaoSavePlayer.clearParameters();
            operacaoSavePlayer.setInt(1, jogadore.getIdentifier_in_game());
            operacaoSavePlayer.setString(2, jogadore.getName());
            operacaoSavePlayer.setInt(3, jogadore.getPosition());
            operacaoSavePlayer.setInt(4, jogadore.getTeam());
            operacaoSavePlayer.setInt(5, jogadore.getPontos());
            operacaoSavePlayer.setInt(6, gameIdentifier);
            operacaoSavePlayer.execute();
        }
    }
    
    public Player searchPlayer (Integer player_identifier_in_game, Integer gameIdentifier) throws ClassNotFoundException, SQLException
    {
        operacaoSearchPlayer = DatabaseLocator.getInstance().getConnection().prepareStatement("select * from player where player_identifier_in_game = ? and fk_game_identifier = ?");
        operacaoSearchPlayer.setInt(1, player_identifier_in_game);
        operacaoSearchPlayer.setInt(2, gameIdentifier);
        
        Player player = new Player();
        
        ResultSet resultado = operacaoSearchPlayer.executeQuery();
        while(resultado.next())
        {
            player.setIdentifier_in_game(player_identifier_in_game);
            player.setName(resultado.getString("name"));
            player.setPontos(resultado.getInt("points"));
            player.setPosition(resultado.getInt("position"));
            player.setTeam(resultado.getInt("team"));
            player.setIdentifier(resultado.getInt("player_identifier"));
        }
        
        return player;
    }
}
