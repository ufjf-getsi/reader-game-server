package Mundo;

import Grafo.Aresta;
import Grafo.VerticeItem;

public class Player {

    private Integer identificator;
    private String name;
    private Integer team;
    private Integer position;
    private Integer pontos;
    private VerticeItem vertice;

    public Player() {
    }
    
    public Player(Integer identificator, String nome) {
        this.identificator = identificator;
        this.name = nome;
        this.pontos = 0;
        this.vertice = null;
        this.team = 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public VerticeItem getVertice() {
        return vertice;
    }

    public void setVertice(VerticeItem vertice) {
        this.vertice = vertice;
        setPosition(vertice.getIndice());
        vertice.addItem(this.getName());
    }

    public void mover(int opcao) {
        vertice.removeItem(this.getName());
        Aresta auxAresta = vertice.getPrimAresta();
        for (int i = 0; i < opcao; i++) {
            auxAresta = auxAresta.getProxima();
        }
        vertice = (VerticeItem) auxAresta.getVerticeDestino();
        setVertice(vertice);
    }

    public Integer getIdentificator() {
        return identificator;
    }

    public void setIdentificator(Integer identificator) {
        this.identificator = identificator;
    }

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }
}
