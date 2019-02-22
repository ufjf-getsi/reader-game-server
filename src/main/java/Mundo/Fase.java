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
        GrafoGenerator gerador = new GrafoGeneratorTeia();
        this.mapa = gerador.getGrafo(this.fatorMutltMapa * this.jogadores.size());
        List<Integer> verticesDisp = new ArrayList<Integer>();
        List<Integer> posJogadores = new ArrayList<Integer>();                          //Lista de posições dos jogadores já escolhidas que deverá ser verificado se a distancia do vertice escolhido é compativel com todos eles
        for(int i = 1; i <= (this.fatorMutltMapa * this.jogadores.size());i++){
            verticesDisp.add(i);
        }
        Integer verticeJogador = this.mapa.numeroAleatorio(verticesDisp.get(0), verticesDisp.get(verticesDisp.size() - 1));
        int sortTipoVertice;
        jogadores.get(0).setVertice((VerticeItem)this.mapa.getVertice(verticeJogador));         //Posicionando o primeiro jogador
        posJogadores.add(verticeJogador);
        verticesDisp.remove(verticeJogador);
        System.out.println(posJogadores.get(0) + "  TESTE");
        boolean testePossibilidade = true;                                                      //Possibilidade de adicionar um jogador
        for(int i = 1; i < jogadores.size(); i++){
            for(int j = 0; j < mapa.getNumeroDeVertices(); j++){
                if(mapa.getMatrizDistancias()[verticeJogador - 1][j] >= 2){
                    testePossibilidade = true;
                    for (int k = 0; k < posJogadores.size(); k++) {         //Verifica se o jogador tera distancia padronizada dentre todos os outros que já foram posicionados
                        if(mapa.getMatrizDistancias()[j][posJogadores.get(k) - 1] < 2){
                            testePossibilidade = false;
                            break;
                        }
                    }
                    if(testePossibilidade){
                        verticeJogador = j + 1;
                        posJogadores.add(verticeJogador);
                        jogadores.get(i).setVertice((VerticeItem)this.mapa.getVertice(buscaElemLista(verticesDisp, verticeJogador)));
                        removeElemLista(verticesDisp, verticeJogador);
                        break;
                    }
                }
            }
        }
    }
    
    private void removeElemLista(List<Integer> lista, int elemento){
        for(int i = 0; i < lista.size(); i++){
            if(lista.get(i) == elemento){
                lista.remove(i);
                break;
            }
        }
    }
    
    private Integer buscaElemLista(List<Integer> lista, int elemento){
        for(int i = 0; i < lista.size(); i++){
            if(lista.get(i) == elemento){
                return lista.get(i);
            }
        }
        return -1;
    }
}
