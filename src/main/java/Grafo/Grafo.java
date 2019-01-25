package Grafo;

import java.util.Random;

public class Grafo {

    private ListEncadVertices verticesDesteGrafo = new ListEncadVertices();
    private int numeroDeVertices;
    private int numeroDeArestas;

    public Grafo() {
        this.numeroDeArestas = 0;
        this.numeroDeVertices = 0;
    }

    public Grafo(int quantidadeVertices) {
        for (int i = 1; i <= quantidadeVertices; i++) {
            Vertice novoVertice = new Vertice(i);
            this.setVertice(novoVertice);
        }
    }

    public int getNumeroDeVertices() {
        return numeroDeVertices;
    }

    public boolean verificaExistenciaDoVertice(int indice) {

        for (Vertice v = this.verticesDesteGrafo.getPrimeiro(); v != null; v = v.getProximo()) {
            if (v.getIndice() == indice) {
                return true;
            }
        }
        return false;
    }

    public void setVertice(Vertice novoVertice) {

        if (!verificaExistenciaDoVertice(novoVertice.getIndice())) {
            this.verticesDesteGrafo.setVertice(novoVertice);
            this.numeroDeVertices += 1;
        } else {
            System.out.println("Vértice já existe. Não foi adicionado.");
        }
    }

    public void setAresta(Aresta novaAresta, int verticeBase, int verticeDestino) {

        if (verificaExistenciaDoVertice(verticeBase) && verificaExistenciaDoVertice(verticeDestino)) {
            Vertice base = null;
            Vertice destino = null;
            for (Vertice v = this.verticesDesteGrafo.getPrimeiro(); v != null; v = v.getProximo()) {
                if (v.getIndice() == verticeBase) {
                    base = v;
                }

                if (v.getIndice() == verticeDestino) {
                    destino = v;
                }

                if (base != null && destino != null) {
                    break;
                }
            }

            base.setAresta(destino, novaAresta);

        } else {
            System.out.println("Algum dos vértices não existem.");
        }

    }

    public void printGrafo() {
        for (Vertice v = this.verticesDesteGrafo.getPrimeiro(); v != null; v = v.getProximo()) {
            System.out.println("Vértice " + v.getIndice() + ": ");
            v.getArestasDesteVertice().printArestas();
        }
        System.out.println();
    }

    // essa função verifica se tem uma aresta orientada saindo do vertice base para o vertice destino
    public boolean verificaExistenciaAresta(int verticeBas, int verticeDest) {
        if (verificaExistenciaDoVertice(verticeBas) && verificaExistenciaDoVertice(verticeDest)) {

            Vertice verticeBase = new Vertice();

            for (Vertice v = this.verticesDesteGrafo.getPrimeiro(); v != null; v = v.getProximo()) {
                if (v.getIndice() == verticeBas) {
                    verticeBase = v;
                }

            }

            for (Aresta a = verticeBase.getArestasDesteVertice().getPrimeira(); a != null; a = a.getProxima()) {
                if (a.getVerticeDestino().getIndice() == verticeDest) {
                    return true;
                }
            }
        }
        return false;
    }

    private void auxiliarEhConexo(Vertice visitando) {
        visitando.setVisitado(true);
        for (Aresta a = visitando.getPrimAresta(); a != null; a = a.getProxima()) {
            if (a.getVerticeDestino().getVisitado() == false) {
                auxiliarEhConexo(a.getVerticeDestino());
            }
        }
    }

    // retorna true se existe pelo menos um caminho entre todos os pares de vértices do grafo
    // busca de profundidade utilizada
    public boolean ehConexo() {

        //escolhe o vértice inicial da busca em profundidade
        for (Vertice a = this.verticesDesteGrafo.getPrimeiro(); a != null; a = a.getProximo()) {

            // marca todos os vértices como "não visitados"
            for (Vertice v = this.verticesDesteGrafo.getPrimeiro(); v != null; v = v.getProximo()) {
                v.setVisitado(false);
            }

            // percorre o grafo para verificar se existe um caminho entre todos os pares de vértices
            // marca os vértices como "visitado" se ele foi visitado
            auxiliarEhConexo(a);

            // verifica se todos os vértices foram visitados
            for (Vertice v = this.verticesDesteGrafo.getPrimeiro(); v != null; v = v.getProximo()) {
                if (v.getVisitado() == false) {
                    return false;
                }
            }
        }

        return true;
    }

    public int numeroAleatorio(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public void criarArestasAutomaticamente() {

        while (!ehConexo()) {
            int primeiroVertice = numeroAleatorio(1, numeroDeVertices);
            int segundoVertice = numeroAleatorio(1, numeroDeVertices);

            if (primeiroVertice != segundoVertice) {
                if (!this.verificaExistenciaAresta(primeiroVertice, segundoVertice)) {
                    Aresta ida = new Aresta(1);
                    Aresta volta = new Aresta(2);

                    this.setAresta(ida, primeiroVertice, segundoVertice);
                    this.setAresta(volta, segundoVertice, primeiroVertice);
                }

            }

        }
    }

    public String impressaoGraphViz() {
        StringBuilder dotFormat = new StringBuilder();
        Vertice vertice = verticesDesteGrafo.getPrimeiro();
        while (vertice != null) {
            Aresta aresta = vertice.getArestasDesteVertice().getPrimeira();
            while (aresta != null) {
                dotFormat.append(vertice.getIndice() + "->" + aresta.getVerticeDestino().getIndice() + ";");
                aresta = aresta.getProxima();
            }
            vertice = vertice.getProximo();
        }
        return dotFormat.toString();
    }
}
