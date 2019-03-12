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
    private PreparedStatement operacaoSaveImage;
    private PreparedStatement operacaoListImage;

    private ImageDAO() {
    }

    public static ImageDAO getInstance() {
        return instance;
    }

    public void saveImage(String pathOne, String pathTwo, Integer game_identifier) throws SQLException, ClassNotFoundException {
        operacaoSaveImage = DatabaseLocator.getInstance().getConnection().prepareStatement("insert into image (path, fk_game_identifier) values (?, ?)");

        operacaoSaveImage.clearParameters();
        operacaoSaveImage.setString(1, pathOne);
        operacaoSaveImage.setInt(2, game_identifier);
        operacaoSaveImage.execute();

        operacaoSaveImage.clearParameters();
        operacaoSaveImage.setString(1, pathTwo);
        operacaoSaveImage.setInt(2, game_identifier);
        operacaoSaveImage.execute();
    }

    public List<Image> listImages(Integer game_identifier) throws SQLException, ClassNotFoundException {
        List<Image> images = new ArrayList<>();
        operacaoListImage = DatabaseLocator.getInstance().getConnection().prepareStatement("select * from image where fk_game_identifier = ?");
        operacaoListImage.setInt(1, game_identifier);
        ResultSet resultado = operacaoListImage.executeQuery();
        while (resultado.next()) {
            Image image = new Image();
            image.setIdentifier(resultado.getInt("image_identifier"));
            image.setPath(resultado.getString("path"));
            images.add(image);
        }
        return images;
    }

}
