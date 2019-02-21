/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;

import Grafo.Grafo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class Fase {
    private Grafo mapa;
    private List<Jogador> jogadores = new ArrayList<Jogador>();
    private int fatorMutltMapa = 10;                             //Fator que multiplicado pelo num de jogadores ira gerar o grafo
    
    public Fase(){
    
    }
    
    public Fase(int numJogadores){
        this.mapa = new Grafo();
        for(int i = 1; i <= numJogadores; i++){
            this.jogadores.add(new Jogador(i));
        }
    }
    
    public void setMapa(Grafo mapa){
        this.mapa = mapa;
    }
    
    public Grafo getMapa(){
        return mapa;
    }
    
    public int getNumJogadores(){
        return this.jogadores.size();
    }
    
    public int getFatorMutiMapa(){
        return this.fatorMutltMapa;
    }
    
    public List<Jogador> getJogadores(){
        return this.jogadores;
    }
    
    public void setJogadores(List<Jogador> jogadores){
        this.jogadores = jogadores;
    }
    
    public void geraMapa(){
        int posJogador = mapa.numeroAleatorio(1, mapa.getNumeroDeVertices());
        //jogadores.get(0).getVertice() = mapa.getVertice(posJogador);
        //jogadores.get(0).getVertice().addItem("J"+this.indice);
    }
    
}
