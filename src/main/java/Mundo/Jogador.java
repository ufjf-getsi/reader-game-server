/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;

import Grafo.Aresta;
import Grafo.Vertice;
import Grafo.VerticeItem;

/**
 *
 * @author lucas
 */
public class Jogador {
    private int indice;
    private String nome;
    private int pontos;
    private VerticeItem vertice;
    
    public Jogador(int indice){
        this.indice = indice;
        this.nome = "Padrao";
        this.pontos = 0;
        this.vertice = null;
    }
    
    public Jogador(int indice, String nome){
        this.indice = indice;
        this.nome = nome;
        this.pontos = 0;
        this.vertice = null;
    }
    
    public int getIndice(){
        return this.indice;
    }
    
    public void setIndice(int indice){
        this.indice = indice;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public VerticeItem getVertice(){
        return vertice;
    }
    
    public void setVertice(VerticeItem vertice){
        this.vertice = vertice;
    }
    
    public void mover(int opcao){
        vertice.removeItem("J" + this.indice);
        Aresta auxAresta = vertice.getPrimAresta();
        for(int i = 0; i < opcao; i++){
            auxAresta = auxAresta.getProxima();
        }
        vertice = (VerticeItem) auxAresta.getVerticeDestino();
        vertice.addItem("J"+this.indice);
    }
    
    
}
