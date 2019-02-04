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

@WebServlet(name = "PrincipalServlet", urlPatterns = {"/index.html","/imagem"})
public class PrincipalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Map<String, String> rotas;
            rotas = new HashMap<>();
            rotas.put("/index.html", "Comands.GetIndexCommand");
            rotas.put("/imagem", "Comands.ShowImage");

            String clazzName = rotas.get(request.getServletPath());
            Comando comando;
            comando = (Comando) Class.forName(clazzName).newInstance();
            comando.exec(request, response);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            response.sendRedirect("index.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            
            Map<String, String> rotas;
            rotas = new HashMap<>();
            rotas.put("/index.html", "Comands.PostIndexCommand");

            String clazzName = rotas.get(request.getServletPath());
            Comando comando;
            comando = (Comando) Class.forName(clazzName).newInstance();
            comando.exec(request, response);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            response.sendRedirect("index.html");
        }

    }

}
