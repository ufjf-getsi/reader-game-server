package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseLocator {

    private static DatabaseLocator instance = new DatabaseLocator();

    public static DatabaseLocator getInstance() {
        return instance;
    }

    private DatabaseLocator() {
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Boolean online = false;
        Connection conn;
        if (online) {
            conn = ConexaoHeroku();
        } else {
            conn = ConexaoOffline();
        }
        IniciaDatabase(conn);
        return conn;
    }
    
    private Connection ConexaoHeroku() throws SQLException {
        String jdbcurl = System.getenv("JDBC_DATABASE_URL");
        return DriverManager.getConnection(jdbcurl);
    }

    private Connection ConexaoOffline() throws SQLException, ClassNotFoundException {
        String driver = "org.postgresql.Driver";
        String user = "postgres";
        String senha = "postgres";
        String url = "jdbc:postgresql://localhost:5432/reader-game-server";
        Class.forName(driver);
        Connection conexao = (Connection) DriverManager.getConnection(url, user, senha);
        return conexao;
    }

    private void IniciaDatabase(Connection conn) throws SQLException {
        Statement operacao = conn.createStatement();
        operacao.executeUpdate("CREATE TABLE IF NOT EXISTS GAME (game_identifier serial PRIMARY KEY, name varchar(100)"
                + ", currentPlayer integer, players integer, turnsLeft integer, turnOrder varchar(100), nodes varchar(100), data varchar(100), status varchar(100));");
        operacao.close();

        Statement operacao2 = conn.createStatement();
        operacao2.executeUpdate("CREATE TABLE IF NOT EXISTS PLAYER (player_identifier serial PRIMARY KEY, player_identifier_in_game integer, "
                + "name varchar(100), position integer, team integer, points integer, fk_game_identifier integer,"
                + "CONSTRAINT fk_game_identifier_id FOREIGN KEY (fk_game_identifier) REFERENCES GAME (game_identifier) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION);");
        operacao2.close();

        Statement operacao3 = conn.createStatement();
        operacao3.executeUpdate("CREATE TABLE IF NOT EXISTS IMAGE(image_identifier serial PRIMARY KEY, path varchar (100),"
                + "fk_game_identifier integer, CONSTRAINT fk_game_identifier_id_image FOREIGN KEY (fk_game_identifier) REFERENCES GAME (game_identifier) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION);");
        operacao3.close();

        Statement operacao4 = conn.createStatement();
        operacao4.executeUpdate("CREATE TABLE IF NOT EXISTS ITEM(item_identifier serial PRIMARY KEY, data varchar (100), node int,"
                + "fk_game_identifier integer, CONSTRAINT fk_game_identifier_id_image FOREIGN KEY (fk_game_identifier) REFERENCES GAME (game_identifier) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION);");
    
        Statement operacao5 = conn.createStatement();
        operacao5.executeUpdate("CREATE TABLE IF NOT EXISTS GAME_WORDS (word_identifier serial PRIMARY KEY, word varchar (100), used int, fk_game_identifier integer, fk_player_identifier integer,"
                + " CONSTRAINT fk_game_identifier_id FOREIGN KEY (fk_game_identifier) REFERENCES GAME (game_identifier), CONSTRAINT fk_player_identifier_id FOREIGN KEY (fk_player_identifier) REFERENCES PLAYER (player_identifier)"
                + "MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION);");
        operacao5.close();
    }
}
