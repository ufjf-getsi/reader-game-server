package Comands;

import Grafo.GrafoGenerator;
import Grafo.GrafoGeneratorTeia;
import Grafo.gephi.Gephi;
import Grafo.Grafo;
import Grafo.graphviz.GraphViz;
import Mundo.Fase;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
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
            Properties config = new Properties();
            config.load(request.getServletContext().getResourceAsStream("/WEB-INF/properties/config.properties"));
            File uploads = new File(config.getProperty("UPLOAD_DIR"));
            File pngGraphviz = File.createTempFile("graph", ".gif", uploads);
            File svgGephi = File.createTempFile("gephi", ".svg", uploads);
            
            

            Integer quantidadeDeVertices = Integer.parseInt(request.getParameter("qtddVertices"));

            Random rand = new Random();
            int randomNum = rand.nextInt();

            Fase fase = new Fase(quantidadeDeVertices);

            //GrafoGenerator gerador = new GrafoGeneratorTeia();
            //fase.setMapa(gerador.getGrafo(quantidadeDeVertices));
            fase.geraMapa();
            /*System.out.println("Teste movimentacao do jogador");
            System.out.println("Vertice jogador 1 antes: " + fase.getJogadores().get(0).getVertice().getIndice());
            System.out.println("Movendo...");
            fase.getJogadores().get(0).mover(0);
            System.out.println("Vertice jogador 1 depois: " + fase.getJogadores().get(0).getVertice().getIndice());*/
            Grafo grafo = fase.getMapa();
            GraphViz gv = new GraphViz();
            gv.addln(gv.start_graph());
            String dotFormat = grafo.impressaoGraphViz();
            gv.add(dotFormat);
            gv.addln(gv.end_graph());

            gv.increaseDpi();   // 106 dpi

            try {
                Gephi gephi = new Gephi();
                gephi.script(grafo, svgGephi);
            } catch (TranscoderException ex) {
                Exceptions.printStackTrace(ex);
            }

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
            request.setAttribute("nomeimagem", pngGraphviz.getName());
            request.setAttribute("nomefigura", svgGephi.getName());
            RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/figura.jsp");
            despachante.forward(request, response);

        } catch (ServletException | IOException ex) {
            Logger.getLogger(GetIndexCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
