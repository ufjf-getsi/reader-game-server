package Mundo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrdemGenerator {

    private List<Integer> ordem;

    public OrdemGenerator(Integer numPlayers) {
        ordem = new ArrayList<>();
        for (Integer i = 0; i < numPlayers; i++) {
            ordem.add(i);
        }
        Collections.shuffle(ordem);
    }

    public String getOrdem(Integer numPlayers) {
        StringBuilder ordemJogadores = new StringBuilder();
        for (Integer i = 0; i < numPlayers-1; i++) {
            ordemJogadores.append(ordem.get(i) + ";");
        }
        ordemJogadores.append(ordem.get(numPlayers-1));
        return ordemJogadores.toString();
    }

    public List<Integer> getOrdem() {
        return ordem;
    }

    public void setOrdem(List<Integer> ordem) {
        this.ordem = ordem;
    }

}
