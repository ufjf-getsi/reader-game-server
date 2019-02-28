package Servlets;

import Comands.Comando;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PrincipalServlet", urlPatterns = {"/index.html", "/imagem", "/audio", "/", "/alunos.html", "/grafo.html"})
public class PrincipalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            enableCORS(response);
            Map<String, String> rotas;
            rotas = new HashMap<>();
            rotas.put("/", "Comands.GetIndexCommand");
            rotas.put("/index.html", "Comands.GetIndexCommand");
            rotas.put("/imagem", "Comands.ShowImage");
            rotas.put("/audio", "Comands.AudioShow");
            rotas.put("/audios", "Comands.AudioListCommand");
            rotas.put("/svg", "Comands.SVGShow");
            rotas.put("/alunos.html", "Comands.GetPlayersNameCommand");
            rotas.put("/grafo.html", "Comands.GetGrafoCommand");
            processRequest(rotas, request, response);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            response.sendRedirect("/index.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            enableCORS(response);

            Map<String, String> rotas;
            rotas = new HashMap<>();
            rotas.put("/", "Comands.PostIndexCommand");
            rotas.put("/index.html", "Comands.PostIndexCommand");
            rotas.put("/audio", "Comands.PostAudioCommand");
            rotas.put("/alunos.html", "Comands.PostPlayersNameCommand");
            rotas.put("/grafo.html", "Comands.PostGrafoCommand");
            
            processRequest(rotas, request, response);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            response.sendRedirect("index.html");
        }

    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        enableCORS(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void enableCORS(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "OPTIONS, HEADER, GET,POST");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    }

    private boolean processRequest(Map<String, String> rotas, HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {
        String clazzName = rotas.get(request.getServletPath());
        if (clazzName == null) {
            System.out.println("404: "+request.getServletPath()+" "+clazzName);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return false;
        }
        Class clazz = Class.forName(clazzName);
        Comando comando = (Comando) clazz.newInstance();
        comando.exec(request, response);
        return true;
    }

}
