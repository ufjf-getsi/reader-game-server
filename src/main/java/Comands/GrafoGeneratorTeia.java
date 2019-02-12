package Comands;

import Grafo.Aresta;
import Grafo.Grafo;
import Grafo.Vertice;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igor
 */
class GrafoGeneratorTeia implements GrafoGenerator {

    public Grafo getGrafo(int qtdVertices) {

        int n = 1;
        Grafo novoGrafo = new Grafo();
        Vertice centro = new Vertice(n++);
        novoGrafo.setVertice(centro);
        List<Vertice> n1 = new ArrayList<Vertice>();

        for (int i = 0; i < 4; i++) {
            Vertice v = new Vertice(n++);
            novoGrafo.setVertice(v);
            centro.setAresta(v, new Aresta(1));
            n1.add(v);
        }
        n1.get(0).setAresta(n1.get(n1.size()-1), new Aresta(1));
        n1.get(0).setAresta(n1.get(1), new Aresta(1));
        n1.get(1).setAresta(n1.get(2), new Aresta(1));
        n1.get(2).setAresta(n1.get(3), new Aresta(1));
        for (Vertice vertice : n1) {
            for (int i = 0; i < 3; i++) {
                Vertice v = new Vertice(n++);
                novoGrafo.setVertice(v);
                vertice.setAresta(v, new Aresta(1));
            }
        }
        return novoGrafo;
    }
}
