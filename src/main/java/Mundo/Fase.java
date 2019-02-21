/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;

import Grafo.Grafo;
import Grafo.GrafoGenerator;
import Grafo.GrafoGeneratorTeia;
import Grafo.VerticeItem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class Fase {
    private Grafo mapa;
    private List<Jogador> jogadores = new ArrayList<Jogador>();
    private int fatorMutltMapa = 4;                             //Fator que multiplicado pelo num de jogadores ira gerar o grafo
    
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
        //int posJogador = mapa.numeroAleatorio(1, mapa.getNumeroDeVertices());
        GrafoGenerator gerador = new GrafoGeneratorTeia();
        this.mapa = gerador.getGrafo(this.fatorMutltMapa * this.jogadores.size());
        List<Integer> verticesDisp = new ArrayList<Integer>();
        for(int i = 1; i <= (this.fatorMutltMapa * this.jogadores.size());i++){
            verticesDisp.add(i);
        }
        int verticeJogador = this.mapa.numeroAleatorio(verticesDisp.get(0), verticesDisp.get(verticesDisp.size() - 1));
        int sortTipoVertice;
        jogadores.get(0).setVertice((VerticeItem)this.mapa.getVertice(verticeJogador));         //Posicionando o primeiro jogador
        verticesDisp.remove((Integer)verticeJogador);
        for(int i = 1; i < jogadores.size(); i++){
            //if(verticesDisp.contains())
            for(int j = 0; j < mapa.getNumeroDeVertices(); j++){
                if(mapa.getMatrizDistancias()[verticeJogador - 1][j] >= 2){
                    if(verticesDisp.contains((Integer)(j + 1))){
                        jogadores.get(i).setVertice((VerticeItem)this.mapa.getVertice(verticesDisp.get((Integer)(j + 1))));
                        verticesDisp.remove((Integer)(j + 1));
                        verticeJogador = j + 1;
                        break;
                    }
                }
            }
        }
        
        //jogadores.get(0).getVertice() = mapa.getVertice(posJogador);
        //jogadores.get(0).getVertice().addItem("J"+this.indice);
    }
    
}
