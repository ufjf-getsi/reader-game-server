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

    public Player(Integer identificator, String nome) {
        this.identificator = identificator;
        this.name = nome;
        this.pontos = 0;
        this.vertice = null;
    }

    public int getIndice() {
        return this.identificator;
    }

    public void setIndice(int indice) {
        this.identificator = indice;
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
//        vertice.addItem("J" + this.identificator);
        vertice.addItem(this.getName());
    }

    public void mover(int opcao) {
        vertice.removeItem("J" + this.identificator);
        Aresta auxAresta = vertice.getPrimAresta();
        for (int i = 0; i < opcao; i++) {
            auxAresta = auxAresta.getProxima();
        }
        vertice = (VerticeItem) auxAresta.getVerticeDestino();
        vertice.addItem("J" + this.identificator);
    }
}
