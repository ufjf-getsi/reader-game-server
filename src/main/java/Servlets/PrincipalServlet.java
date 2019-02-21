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

@WebServlet (name = "PrincipalServlet", urlPatterns = {"/index.html", "/imagem", "/audio","/"})
//@WebServlet(name = "PrincipalServlet", urlPatterns = {"/index.html", "/imagem", "/audio", ""})
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
            rotas.put("/audio", "Comands.ShowAudio");

            System.out.println(request.getServletPath());
            String clazzName = rotas.get(request.getServletPath());
            Class clazz = Class.forName(clazzName);
            Comando comando = (Comando) clazz.newInstance();
            comando.exec(request, response);
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
            rotas.put("/index.html", "Comands.PostIndexCommand");
            rotas.put("/audio", "Comands.PostAudioCommand");

            String clazzName = rotas.get(request.getServletPath());
            Comando comando;
            comando = (Comando) Class.forName(clazzName).newInstance();
            comando.exec(request, response);
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

}
