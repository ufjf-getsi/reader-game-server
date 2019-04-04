package Comands;

import Mundo.Game;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openide.util.Exceptions;

public class GetGameAsJSON implements Comando{
    
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json");
            Game game = new Game("Jogo1", 4, 0, 6*4, "PlayersOrder");
            String json = new ObjectMapper().writeValueAsString(game);
            PrintWriter writer = (PrintWriter)response.getWriter();
            writer.println(json);
            writer.close();
        } catch (JsonProcessingException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    
}
