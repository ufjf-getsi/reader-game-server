package Comands;

import Grafo.Grafo;
import Grafo.gephi.Gephi;
import Grafo.graphviz.GraphViz;
import Mundo.GameGenerator;
import Mundo.Player;
import Persistence.GameDAO;
import Persistence.PlayerDAO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.batik.transcoder.TranscoderException;
import org.openide.util.Exceptions;

public class PostIndexCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("matchName");
            Integer turnsLeft = Integer.parseInt(request.getParameter("turns"));
            Integer players = Integer.parseInt(request.getParameter("players"));
            GameGenerator generator = new GameGenerator();
            response.sendRedirect("alunos.html?id=" + generator.saveInitialData(name, players, turnsLeft));
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
