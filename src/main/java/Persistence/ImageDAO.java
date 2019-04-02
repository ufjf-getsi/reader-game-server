package Persistence;

import Models.Image;
import Mundo.Game;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO {

    private final static ImageDAO instance = new ImageDAO();
    private final String operacaoSaveImage = "insert into image (path, fk_game_identifier) values (?, ?)";
    private final String operacaoListImage = "select * from image where fk_game_identifier = ?";

    private ImageDAO() {
    }

    public static ImageDAO getInstance() {
        return instance;
    }

    public void saveImage(String pathOne, String pathTwo, Integer game_identifier) throws SQLException, ClassNotFoundException {
        PreparedStatement comando = DatabaseLocator.getInstance().getConnection().prepareStatement(operacaoSaveImage);

        comando.clearParameters();
        comando.setString(1, pathOne);
        comando.setInt(2, game_identifier);
        comando.execute();

        comando.clearParameters();
        comando.setString(1, pathTwo);
        comando.setInt(2, game_identifier);
        comando.execute();
        
        comando.close();
    }

    public List<Image> listImages(Integer game_identifier) throws SQLException, ClassNotFoundException {
        List<Image> images = new ArrayList<>();
        PreparedStatement comando = DatabaseLocator.getInstance().getConnection().prepareStatement(operacaoListImage);
        comando.setInt(1, game_identifier);
        ResultSet resultado = comando.executeQuery();
        while (resultado.next()) {
            Image image = new Image();
            image.setIdentifier(resultado.getInt("image_identifier"));
            image.setPath(resultado.getString("path"));
            images.add(image);
        }
        return images;
    }

}
