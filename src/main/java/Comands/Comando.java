package Comands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Comando {
    
    public void exec(HttpServletRequest request, HttpServletResponse response);

}
