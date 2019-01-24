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
public class Aresta {

    private Vertice verticeDestino;
    private Aresta proxima;
    int peso;
    int pai;

    public Aresta(int peso) {
        this.peso = peso;
    }

    public Vertice getVerticeDestino() {
        return verticeDestino;
    }

    public Aresta getProxima() {
        return proxima;
    }

    public int getPeso() {
        return peso;
    }

    public int getPai() {
        return pai;
    }

    public void setVerticeDestino(Vertice verticeDestino) {
        this.verticeDestino = verticeDestino;
    }

    public void setProxima(Aresta proxima) {
        this.proxima = proxima;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setPai(int pai) {
        this.pai = pai;
    }
}
