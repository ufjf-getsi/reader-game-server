/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

/**
 *
 * @author ferna
 */
public class Grafo {

    private ListEncadVertices verticesDesteGrafo = new ListEncadVertices();
    private int numeroDeVertices;
    private int numeroDeArestas;

    public Grafo() {
        this.numeroDeArestas = 0;
        this.numeroDeVertices = 0;
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
}
