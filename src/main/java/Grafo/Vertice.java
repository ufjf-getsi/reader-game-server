package Grafo;

public class Vertice {

    private Vertice proximo;
    private int indice;
    private ListaEncadArestas arestasDesteVertice = new ListaEncadArestas();

    public Vertice(int indice) {
        this.indice = indice;
    }

    public ListaEncadArestas getArestasDesteVertice() {
        return arestasDesteVertice;
    }
    
    public void setIndice(int indice) {
        this.indice = indice;
    }

    public void setProximo(Vertice proximo) {
        this.proximo = proximo;
    }

    public int getIndice() {
        return indice;
    }

    public Vertice getProximo() {
        return proximo;
    }

    public void setAresta(Vertice destino, Aresta novaAresta) {
        this.arestasDesteVertice.setAresta(destino, this.indice, novaAresta);
    }

    public Aresta getPrimAresta() {
        return this.arestasDesteVertice.getPrimeira();
    }

}
