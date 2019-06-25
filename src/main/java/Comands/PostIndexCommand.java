package Comands;

import Mundo.GameGenerator;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openide.util.Exceptions;

public class PostIndexCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("matchName");
            Integer turnsLeft = Integer.parseInt(request.getParameter("turns"));
            Integer players = Integer.parseInt(request.getParameter("players"));
            String words = request.getParameter("words");
            GameGenerator generator = new GameGenerator();
            response.sendRedirect("alunos.html?id=" + generator.saveInitialData(name, players, turnsLeft, words));
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
