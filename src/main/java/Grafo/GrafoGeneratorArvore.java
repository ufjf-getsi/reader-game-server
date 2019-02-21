package Grafo;

public class GrafoGeneratorArvore implements GrafoGenerator {
    public Grafo getGrafo(int qtdVertices){
        Grafo novoGrafo = new Grafo(qtdVertices);
        novoGrafo.criarArestasAutomaticamente();
        return novoGrafo;
    }
}
