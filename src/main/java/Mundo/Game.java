package Mundo;

import Grafo.Grafo;
import Grafo.GrafoGenerator;
import Grafo.GrafoGeneratorTeia;
import Grafo.VerticeItem;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private Integer identifier;
    private String name;
    private Integer players;
    private Integer currentPlayer;
    private Integer turnsLeft;
    private String playersOrder;
    
    private Grafo map;
    private List<Player> jogadores = new ArrayList<>();
    private int fatorMutltMapa = 4;                             //Fator que multiplicado pelo num de jogadores ira gerar o grafo

    public Game() {

    }

    public Game(Integer numJogadores, String []playersName) {
        this.map = new Grafo();
        for (int i = 1; i <= numJogadores; i++) {
            this.jogadores.add(new Player(i, playersName[i - 1]));
        }
    }

    public Game(String name, Integer players, Integer currentPlayer, Integer turnsLeft, String playersOrder) {
        this.name = name;
        this.players = players;
        this.currentPlayer = currentPlayer;
        this.turnsLeft = turnsLeft;
        this.playersOrder = playersOrder;
    }

    public void setMapa(Grafo mapa) {
        this.map = mapa;
    }

    public Grafo getMapa() {
        return map;
    }

    public List<Player> getJogadores() {
        return this.jogadores;
    }

    public void setJogadores(List<Player> players) {
        this.jogadores = players;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlayers() {
        return players;
    }

    public void setPlayers(Integer players) {
        this.players = players;
    }

    public Integer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Integer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Integer getTurnsLeft() {
        return turnsLeft;
    }

    public void setTurnsLeft(Integer turnsLeft) {
        this.turnsLeft = turnsLeft;
    }

    public void geraMapa() {
        GrafoGenerator gerador = new GrafoGeneratorTeia();
        this.map = gerador.getGrafo(this.fatorMutltMapa * this.jogadores.size());
        
        List<Integer> verticesDisp = new ArrayList<>();                                         
        List<Integer> posJogadores = new ArrayList<>();                         //Lista de posições dos jogadores já escolhidas que deverá ser verificado se a distancia 
                                                                                //do vertice escolhido é compativel com todos eles
        for (int i = 1; i <= (this.fatorMutltMapa * this.jogadores.size()); i++) {
            verticesDisp.add(i);
        }

        Integer verticeJogador = this.map.numeroAleatorio(verticesDisp.get(0), verticesDisp.get(verticesDisp.size() - 1));      //Sorteia uma posição na lista de vertices disponíveis
        jogadores.get(0).setVertice((VerticeItem) this.map.getVertice(verticeJogador));         //Posicionando o primeiro jogador
        jogadores.get(0).setIdentifier_in_game(verticeJogador);
        posJogadores.add(verticeJogador);
        verticesDisp.remove(verticeJogador);
        boolean testePossibilidade;  
        for (int i = 1; i < jogadores.size(); i++) {
            for (int j = 0; j < map.getNumeroDeVertices(); j++) {
                if (this.map.getMatrizDistancias()[verticeJogador - 1][j] >= 2) {
                    testePossibilidade = true;
                    for (int k = 0; k < posJogadores.size(); k++) {                 //Compara a distancia com todos os jogadores inseridos e verifica se o vértice é válido
                        if (this.map.getMatrizDistancias()[j][posJogadores.get(k) - 1] < 2) {
                            testePossibilidade = false;
                            break;
                        }
                    }
                    if (testePossibilidade) {
                        verticeJogador = j + 1;
                        posJogadores.add(verticeJogador);
                        jogadores.get(i).setVertice((VerticeItem) this.map.getVertice(verticeJogador));
                        jogadores.get(i).setIdentifier_in_game(verticeJogador);
                        removeElemLista(verticesDisp, verticeJogador);
                        break;
                    }
                }
            }
        }

        List<Integer> qtd = new ArrayList<>();
        List<Integer> tipos = new ArrayList<>();
        List<Integer> qtdMaxTipo = new ArrayList<>();
        qtdMaxTipo.add(Math.round((60 * verticesDisp.size()) / 100));           //Estabelece uma porcentagem para os elementos que deverá ter no mapa
        qtdMaxTipo.add(Math.round((40 * verticesDisp.size()) / 100));
        for (int i = 0; i < qtdMaxTipo.size(); i++) {
            qtd.add(0);
            tipos.add(i);
        }
        int verticeRecurso;
        int sortTipo;
        VerticeItem auxVertice;
        while (verticesDisp.size() > 0) {
            verticeRecurso = this.map.numeroAleatorio(0, (verticesDisp.size() - 1));
            if (qtd.size() > 0) {                           //Se a lista de quantidade ainda teme elementos, logo os tipos de vertices ainda não estão na sua quantidade máxima
                sortTipo = this.map.numeroAleatorio(0, (qtd.size() - 1));
                auxVertice = (VerticeItem) (this.map.getVertice(verticesDisp.get(verticeRecurso)));
                switch (tipos.get(sortTipo)) {
                    case 0:
                        auxVertice.addItem("GR");           //Ganha recurso
                        break;
                    case 1:
                        auxVertice.addItem("CR");           //Consome recurso
                        break;
                    default:
                }
                qtd.set(sortTipo, qtd.get(sortTipo) + 1);               ///Incrementa quantidade presente no grafo de determinado tipo de vértice
                if (qtd.get(sortTipo) >= qtdMaxTipo.get(sortTipo)) {
                    qtd.remove(sortTipo);
                    qtdMaxTipo.remove(sortTipo);
                }
            } else {                                                ///Atingiu as porcentagens dos vertices mas ainda não terminou de preencher o mapa
                sortTipo = 0;
                auxVertice = (VerticeItem) (this.map.getVertice(verticesDisp.get(verticeRecurso)));
                switch (sortTipo) {
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

    private void removeElemLista(List<Integer> lista, int elemento) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i) == elemento) {
                lista.remove(i);
                break;
            }
        }
    }

    public String getPlayersOrder() {
        return playersOrder;
    }

    public void setPlayersOrder(String playersOrder) {
        this.playersOrder = playersOrder;
    }

}
