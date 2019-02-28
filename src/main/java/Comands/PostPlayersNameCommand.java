package Comands;

import Grafo.Grafo;
import Grafo.gephi.Gephi;
import Grafo.graphviz.GraphViz;
import Mundo.Game;
import Mundo.GameGenerator;
import Mundo.Player;
import Persistence.GameDAO;
import Persistence.PlayerDAO;
import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.batik.transcoder.TranscoderException;
import org.openide.util.Exceptions;

public class PostPlayersNameCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {

        try {
            Integer gameId = Integer.parseInt(request.getParameter("idGame"));
            Integer playersNumber = Integer.parseInt(request.getParameter("playersNumber"));
            String playersNames[] = request.getParameterValues("players");

            GameGenerator game = new GameGenerator();
            Grafo grafo = game.savePlayersDataAndStartGame(playersNumber, playersNames, gameId);

            Properties config = new Properties();
            config.load(request.getServletContext().getResourceAsStream("/WEB-INF/properties/config.properties"));

            File uploads = new File(config.getProperty("UPLOAD_DIR"));
            if (!uploads.exists()) {
                uploads.mkdirs();
            }

            File pngGraphviz = File.createTempFile("graph", ".gif", uploads);
            File svgGephi = File.createTempFile("gephi", ".svg", uploads);

            GraphViz gv = new GraphViz();
            gv.addln(gv.start_graph());
            String dotFormat = grafo.impressaoGraphViz();
            gv.add(dotFormat);
            gv.addln(gv.end_graph());
            gv.increaseDpi();   // 106 dpi

            try {
                Gephi gephi = new Gephi();
                gephi.script(grafo, svgGephi);
                System.out.println(svgGephi.getAbsoluteFile());
            } catch (TranscoderException ex) {
                Exceptions.printStackTrace(ex);
            }

            try {
                String type = "gif";
                //      String type = "dot";
                //      String type = "fig";    // open with xfig
                //      String type = "pdf";
                //      String type = "ps";
                //      String type = "svg";    // open with inkscape
                //      String type = "png";
                //      String type = "plain";

                String repesentationType = "twopi";     //"dot";
                //		String repesentationType= "neato";
                //		String repesentationType= "fdp";
                //		String repesentationType= "sfdp";
                // 		String repesentationType= "twopi";
                // 		String repesentationType= "circo";

                //		File out = new File("/tmp/out"+gv.getImageDpi()+"."+ type);   // Linux
                gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type, repesentationType), pngGraphviz);
                System.out.println(pngGraphviz.getAbsoluteFile());

            } catch (Exception ex) {
                Logger.getLogger(GetIndexCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Integer currentPlayer = GameDAO.getInstance().searchCurrentPlayer(gameId);
            
            Player player = PlayerDAO.getInstance().searchPlayer(currentPlayer, gameId);
            
            request.setAttribute("gameId", gameId);
            request.setAttribute("player", player);
            request.setAttribute("nomeimagem", pngGraphviz.getName());
            request.setAttribute("nomefigura", svgGephi.getName());
            RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/figura.jsp");
            despachante.forward(request, response);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
