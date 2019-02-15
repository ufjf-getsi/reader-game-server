package Grafo;

import java.util.Random;

public class Grafo {

    private ListEncadVertices verticesDesteGrafo = new ListEncadVertices();
    private int numeroDeVertices;
    private int numeroDeArestas;
    private int maxArestasPorVertice;

    public Grafo() {
        this.numeroDeArestas = 0;
        this.numeroDeVertices = 0;
        this.maxArestasPorVertice = 5;
    }

    public Grafo(int quantidadeVertices) {
        this.numeroDeArestas = 0;
        this.numeroDeVertices = 0;
        this.maxArestasPorVertice = 5;
        for (int i = 1; i <= quantidadeVertices; i++) {
            System.out.println(i);
            Vertice novoVertice = new Vertice(i);
            this.setVertice(novoVertice);
        }
    }

    public int getNumeroDeVertices() {
        return numeroDeVertices;
    }
    
    public int getMaxArestasPorVertice(){
        return maxArestasPorVertice;
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
    
    public void setMaxArestasPorVertice(int valorMaximo){
        maxArestasPorVertice = valorMaximo;
    }

    public void printGrafo() {
        for (Vertice v = this.verticesDesteGrafo.getPrimeiro(); v != null; v = v.getProximo()) {
            System.out.println("Vértice " + v.getIndice() + ": ");
            v.getArestasDesteVertice().printArestas();
        }
        System.out.println();
    }
    
    public int[][] getMatrizAdjacencia(){
        int[][] matrizAdjacencia = new int[this.numeroDeVertices][this.numeroDeVertices];
        for(int i = 0; i < this.numeroDeVertices; i++){             ///Limpa a matriz
            for(int j = 0; j < this.numeroDeVertices; j++){
                matrizAdjacencia[i][j] = 0;
            }
        }
        Vertice auxVertice = verticesDesteGrafo.getPrimeiro();
        Aresta auxAresta;
        while(auxVertice != null){
            auxAresta = auxVertice.getArestasDesteVertice().getPrimeira();
            while(auxAresta != null){
                matrizAdjacencia[auxVertice.getIndice() - 1][auxAresta.getVerticeDestino().getIndice() - 1] = auxAresta.getPeso();
                auxAresta = auxAresta.getProxima();
            }
            auxVertice = auxVertice.getProximo();
        }
        return matrizAdjacencia;
    }
    
    public int minDistance(int dist[], boolean visitados[]){ 
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index = -1; 

        for (int v = 0; v < this.numeroDeVertices; v++){
            if (visitados[v] == false && dist[v] <= min) 
            { 
                min = dist[v]; 
                min_index = v; 
            } 
        }   

        return min_index; 
    } 
    
    // Calcula a distancia entre dois vertices através do algoritmo de Dijkstra 
    // aplicado em uma matriz de adjacência
    public int algoritmoDijkstra(int origem, int destino){
        int[][] matAdj = new int[this.numeroDeVertices][this.numeroDeVertices];
        matAdj = getMatrizAdjacencia();
        /*for(int i = 0; i < this.numeroDeVertices; i++){
            for(int j = 0; j < this.numeroDeVertices; j++){
                System.out.println("matAdj["+i+"]"+j+"]: "+ matAdj[i][j]);
            }
        }*/
        int[] dist = new int[this.numeroDeVertices];
        boolean[] visitados = new boolean[this.numeroDeVertices];
        int infinito = Integer.MAX_VALUE;                               ///Valor bem grande para representar quando não há aresta de um vertice para o outro
        for(int i = 0; i < this.numeroDeVertices; i++){
            dist[i] = infinito;                            
            visitados[i] = false;
        }
                            
        dist[origem - 1] = 0;               // Distancia da fonte pra ela mesma é sempre 0
        
        for(int i = 0; i < this.numeroDeVertices - 1; i++){
            
            int u = minDistance(dist, visitados);
            
            visitados[u] = true;
            
            for(int j = 0; j < this.numeroDeVertices; j++){
                if (!visitados[j] && matAdj[u][j] != 0 && dist[u] != infinito && dist[u] + matAdj[u][j] < dist[j]){
                    dist[j] = dist[u] + matAdj[u][j]; 
                }
            }
        }
        
        /*for(int j = 0; j < this.numeroDeVertices; j++){
            System.out.println("dist["+j+"]: "+ dist[j]);
        }*/
        return dist[destino - 1];
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
