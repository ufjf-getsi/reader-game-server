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
        List<Vertice> nAtual = new ArrayList<Vertice>();
        
        if(qtdVertices <= 6){
            for (int i = 0; i < qtdVertices - 1; i++) {
                Vertice v = new Vertice(n++);
                novoGrafo.setVertice(v);
                centro.setAresta(v, new Aresta(1));
                v.setAresta(centro, new Aresta(1));
                nAtual.add(v);
            }
        }
        else{
            for (int i = 0; i < novoGrafo.getMaxArestasPorVertice(); i++) {
                Vertice v = new Vertice(n++);
                novoGrafo.setVertice(v);
                centro.setAresta(v, new Aresta(1));
                v.setAresta(centro, new Aresta(1));
                nAtual.add(v);
            }
            List<Vertice> nAux = new ArrayList<Vertice>();
        
            int sorteioQtd = 0;

            while(n <= qtdVertices + 1){
                nAux.clear();
                for(int j = 1; j < nAtual.size(); j++){
                    if(novoGrafo.numeroAleatorio(0, 1) == 1){               //Aresta para a esquerda
                        if(nAtual.get(j - 1).getArestasDesteVertice().getQtdArestas() < novoGrafo.getMaxArestasPorVertice()){
                            nAtual.get(j).setAresta(nAtual.get(j - 1), new Aresta(1));
                            nAtual.get(j - 1).setAresta(nAtual.get(j), new Aresta(1));
                        }
                    }
                }
                for(int j = 0; j < nAtual.size(); j++){
                    sorteioQtd = novoGrafo.numeroAleatorio(0, novoGrafo.getMaxArestasPorVertice() - nAtual.get(j).getArestasDesteVertice().getQtdArestas());
                    for (int k = 0; k < sorteioQtd; k++) {
                        if(n >= qtdVertices + 1){
                           break;
                        }
                        Vertice v = new Vertice(n++);
                        novoGrafo.setVertice(v);
                        nAtual.get(j).setAresta(v, new Aresta(1));
                        v.setAresta(nAtual.get(j),  new Aresta(1));
                        nAux.add(v);
                    }
                }
                if(n >= qtdVertices + 1){
                    break;
                }
                nAtual.clear();
                nAtual.addAll(nAux);
            }
        }
        //Vertice teste = new Vertice(n++);
        //novoGrafo.setVertice(teste);
        //System.out.println("novoGrafo - distancia entre \'1\' e \'" + novoGrafo.getNumeroDeVertices()+"\' eh: " + novoGrafo.algoritmoDijkstra(1, novoGrafo.getNumeroDeVertices()));
        
        
        return novoGrafo;
    }
}
