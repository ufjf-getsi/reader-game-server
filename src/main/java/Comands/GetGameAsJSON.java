package Comands;

import Mundo.Game;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openide.util.Exceptions;

public class GetGameAsJSON implements Comando{
    
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json");
            PrintWriter writer = (PrintWriter)response.getWriter();
            String pathInfo = request.getRequestURI(); 
            String[] parts = pathInfo.split("/");
            
            System.out.println(Arrays.toString(parts));
            if(parts.length!=4 || parts[3].isEmpty()){
                writer.println("{\"error\":\"Game room not found!\"}");
                writer.close(); 
                return ;
            }
            Game game = new Game("Jogo "+parts[3], 4, 0, 6*4, "PlayersOrder");
            String json = new ObjectMapper().writeValueAsString(game);
            writer.println(json);
            writer.close();
        } catch (JsonProcessingException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    
}
