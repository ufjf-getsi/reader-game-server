package Persistence;

public class PlayerDAO {

    private final static PlayerDAO instance = new PlayerDAO();

    private PlayerDAO() {
    }

    public static PlayerDAO getInstance() {
        return instance;
    }

}
