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
        jogadores.get(0).setVertice((VerticeItem)this.mapa.getVertice(verticeJogador));         //Posicionando o primeiro jogador
        posJogadores.add(verticeJogador);
        verticesDisp.remove(verticeJogador);
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
                        jogadores.get(i).setVertice((VerticeItem)this.mapa.getVertice(buscaElemLista(verticesDisp, (int)verticeJogador)));
                        removeElemLista(verticesDisp, (int)verticeJogador);
                        break;
                    }
                }
            }
        }
        
        /*for(int i = 0; i < jogadores.size(); i++){
            System.out.println("Vertice jogadores[ " + i +"]: " + jogadores.get(i).getVertice().getIndice());
        }
        
        for(int i = 0; i < verticesDisp.size(); i++){
            System.out.println("Vertice Disp[ " + i +"]: " + verticesDisp.get(i));
        }*/
        
        List<Integer> qtd = new ArrayList<>();
        List<Integer> tipos = new ArrayList<>();
        List<Integer> qtdMaxTipo = new ArrayList<>();
        qtdMaxTipo.add(Math.round((60 * verticesDisp.size())/100));
        qtdMaxTipo.add(Math.round((40 * verticesDisp.size())/100));
        //System.out.println("qtdMax[0]: " + qtdMaxTipo.get(0) + " qtdMax[1]: " + qtdMaxTipo.get(1) + " verticesDisp: " + verticesDisp.size());
        for(int i = 0; i < qtdMaxTipo.size(); i++){
            qtd.add(0);
            tipos.add(i);
        }
        //System.out.println("Tamanho verticesDisp: " + verticesDisp.size());
        int verticeRecurso;
        int sortTipo;
        VerticeItem auxVertice;
        while(verticesDisp.size() > 0){
            verticeRecurso = this.mapa.numeroAleatorio(0, (verticesDisp.size() - 1));
            //System.out.println("Vertice Recurso: " + verticesDisp.get(verticeRecurso));
            if(qtd.size() > 0){
                sortTipo = this.mapa.numeroAleatorio(0, (qtd.size() - 1));
                //System.out.println("Vertice Recurso: " + verticesDisp.get(verticeRecurso) + " -- Tipo: " + tipos.get(sortTipo));
                auxVertice = (VerticeItem)(this.mapa.getVertice(verticesDisp.get(verticeRecurso)));
                switch(tipos.get(sortTipo)){
                    case 0:
                        auxVertice.addItem("GR");           //Ganha recurso
                        break;
                    case 1:
                        auxVertice.addItem("CR");           //Consome recurso
                        break;
                    default:
                }
                qtd.set(sortTipo, qtd.get(sortTipo) + 1);
                if(qtd.get(sortTipo) >= qtdMaxTipo.get(sortTipo)){
                    //System.out.println("qtd: "+ qtd.get(tipos.get(sortTipo)) + " - qtdMax: "+ qtdMaxTipo.get(tipos.get(sortTipo)) + " - Tipo: " + tipos.get(sortTipo));
                    qtd.remove(sortTipo);
                    qtdMaxTipo.remove(sortTipo);
                }
            }
            else{
                sortTipo = 0;       //this.mapa.numeroAleatorio(0, (tipos.size() - 1));
                auxVertice = (VerticeItem)(this.mapa.getVertice(verticesDisp.get(verticeRecurso)));
                switch(sortTipo){
                    case 0:
                        auxVertice.addItem("GR");           //Ganha recurso
                        break;
                    case 1:
                        auxVertice.addItem("CR");           //Consome recurso
                        break;
                    default:
                }
            }
            verticesDisp.remove(verticeRecurso);
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
