package Mundo;

import Grafo.Grafo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private Integer identifier;
    private String tittle;
    private Integer turnsLeft;
    private String nodes;
    private String data;
    private String status;

    private Integer players;
    private Integer currentPlayer;
    private String playersOrder;
    private List<Player> jogadores = new ArrayList<>();
    private List<Item> itens = new ArrayList<>();
    private Map<String, String> opcoes = new HashMap<>();

    private Grafo mapa;

    public Game() {

    }

    public Game(String name, Integer currentPlayer, Integer players, Integer turnsLeft, String playersOrder) {
        this.tittle = name;
        this.currentPlayer = currentPlayer;
        this.players = players;
        this.turnsLeft = turnsLeft;
        this.playersOrder = playersOrder;
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

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public Integer getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getJogadorAtual() {
        return this.jogadores.get(this.currentPlayer);
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

    public String getPlayersOrder() {
        return playersOrder;
    }

    public void setPlayersOrder(String playersOrder) {
        this.playersOrder = playersOrder;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Grafo getMapa() {
        return mapa;
    }

    public void setMapa(Grafo mapa) {
        this.mapa = mapa;
    }
    
    public Integer getPlayers() {
        return players;
    }

    public void setPlayers(Integer players) {
        this.players = players;
    }    

    public void start() {

    }

    public void reset() {

    }

    public void stop() {

    }

    public void doAction(String action) {

    }

    public String getAllStatus() {
        return null;
    }

    public String getCurrentPlayerStatus() {
        return null;
    }

    public Map<String, String> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(Map<String, String> opcoes) {
        this.opcoes = opcoes;
    }
    
    
}
