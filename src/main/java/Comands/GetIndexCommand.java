package Comands;

import Grafo.Aresta;
import Grafo.Grafo;
import Grafo.Vertice;
import GraphViz.GraphViz;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetIndexCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            
            Grafo grafo = new Grafo();
            Vertice vertice = new Vertice(1);
            Vertice vertice2 = new Vertice(2);
            grafo.setVertice(vertice);
            grafo.setVertice(vertice2);
            Aresta aresta = new Aresta(0);
            Aresta aresta2 = new Aresta(0);
            grafo.setAresta(aresta, 1, 2);
            grafo.setAresta(aresta2, 2, 1);
            
            GraphViz gv = new GraphViz();
            gv.addln(gv.start_graph());
            String dotFormat = grafo.impressaoGraphViz();
            gv.add(dotFormat);
            gv.addln(gv.end_graph());

            gv.increaseDpi();   // 106 dpi

            String type = "gif";
            //      String type = "dot";
            //      String type = "fig";    // open with xfig
            //      String type = "pdf";
            //      String type = "ps";
            //      String type = "svg";    // open with inkscape
            //      String type = "png";
            //      String type = "plain";

            String repesentationType = "dot";
            //		String repesentationType= "neato";
            //		String repesentationType= "fdp";
            //		String repesentationType= "sfdp";
            // 		String repesentationType= "twopi";
            // 		String repesentationType= "circo";

            //		File out = new File("/tmp/out"+gv.getImageDpi()+"."+ type);   // Linux
            File out = new File("C:\\Users\\Mateu\\Documents\\temp\\out." + type);    // Windows
            gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type, repesentationType), out);
            RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/index.jsp");
            despachante.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(GetIndexCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
