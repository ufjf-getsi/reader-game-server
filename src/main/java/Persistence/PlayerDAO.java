package Persistence;

import Mundo.Player;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PlayerDAO {

    private final static PlayerDAO instance = new PlayerDAO();
    private final String operacaoSavePlayer = "insert into player (player_identifier_in_game, name, position, team, points, fk_game_identifier) values (?, ?, ?, ?, ?, ?)";
    private final String operacaoSearchPlayer = "select * from player where player_identifier_in_game = ? and fk_game_identifier = ?";

    private PlayerDAO() {
    }

    public static PlayerDAO getInstance() {
        return instance;
    }

    public void savePlayers(List<Player> jogadores, Integer gameIdentifier) throws SQLException, ClassNotFoundException {
        PreparedStatement comando = DatabaseLocator.getInstance().getConnection().prepareStatement(operacaoSavePlayer);
        for (Player jogadore : jogadores) {
            comando.clearParameters();
            comando.setInt(1, jogadore.getIdentifier_in_game());
            comando.setString(2, jogadore.getName());
            comando.setInt(3, jogadore.getPosition());
            comando.setInt(4, jogadore.getTeam());
            comando.setInt(5, jogadore.getPontos());
            comando.setInt(6, gameIdentifier);
            comando.execute();
        }
        comando.close();
    }
    
    public Player searchPlayer (Integer player_identifier_in_game, Integer gameIdentifier) throws ClassNotFoundException, SQLException
    {
        PreparedStatement comando = DatabaseLocator.getInstance().getConnection().prepareStatement(operacaoSearchPlayer);
        comando.setInt(1, player_identifier_in_game);
        comando.setInt(2, gameIdentifier);
        
        Player player = new Player();
        
        ResultSet resultado = comando.executeQuery();
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
