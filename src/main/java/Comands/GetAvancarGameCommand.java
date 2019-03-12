package Comands;

import Models.Image;
import Persistence.ImageDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openide.util.Exceptions;

public class GetAvancarGameCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));

            List<Image> images = ImageDAO.getInstance().listImages(id);

            request.setAttribute("gameId", id);
            request.setAttribute("nomeimagem", images.get(0).getPath());
            request.setAttribute("nomefigura", images.get(1).getPath());
            RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/figura.jsp");
            despachante.forward(request, response);
        } catch (IOException | ServletException | SQLException | ClassNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
