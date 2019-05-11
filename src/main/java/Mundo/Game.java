package Mundo;

import Grafo.Grafo;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openide.util.Exceptions;

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
    private List<Player> jogadores;
    private List<Item> itens;
    private Map<String, String> opcoes;

    private Grafo mapa;
    private Map<Integer, Node> nodesMap;

    public static final int MOVE_UP = 1;
    public static final int MOVE_RIGHT = 2;
    public static final int MOVE_DOWN = 3;
    public static final int MOVE_LEFT = 4;
    public static final String UP = "↑";
    public static final String LEFT = "←";
    public static final String RIGHT = "→";
    public static final String DOWN = "↓";

    public Game() {
        this("", 0, 0, 0, "");
    }

    public Game(String name, Integer currentPlayer, Integer players, Integer turnsLeft, String playersOrder) {
        this.tittle = name;
        this.currentPlayer = currentPlayer;
        this.players = players;
        this.turnsLeft = turnsLeft;
        this.playersOrder = playersOrder;
        this.nodes = "";
        this.jogadores = new ArrayList<>();
        this.itens = new ArrayList<>();
        this.opcoes = new HashMap<>();
        this.nodesMap = new HashMap<>();
        this.data = "";
        this.status = "";

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
        if (this.currentPlayer != null && this.currentPlayer < this.jogadores.size()) {
            return this.jogadores.get(this.currentPlayer);
        } else {
            return null;
        }
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
        this.nodesMap = new HashMap<Integer, Node>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Node> nodeList = Arrays.asList(mapper.readValue(this.nodes, Node[].class));
            for (Node node : nodeList) {
                this.nodesMap.put(node.getNode(), node);
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
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
        opcoes.clear();
        Node actual = getCurrentPlayerNode();
        List<Node> nodes = getNeighbors(actual);
        char l = 65;
        for (Node node : nodes) {
            opcoes.put(getMovementTo(actual, node), String.format("Mover para [%d, %d]", node.getX(), node.getY()));
        }
        return opcoes;
    }
    
    private String getMovementTo(Node actual, Node destiny){
        if(destiny.getX()<actual.getX() && destiny.getY()==actual.getY()) return Game.LEFT;
        if(destiny.getX()>actual.getX() && destiny.getY()==actual.getY()) return Game.RIGHT;
        if(destiny.getX()==actual.getX() && destiny.getY()<actual.getY()) return Game.UP;
        if(destiny.getX()==actual.getX() && destiny.getY()>actual.getY()) return Game.DOWN;
        return "?";
    }

    public void setOpcoes(Map<String, String> opcoes) {
        this.opcoes = opcoes;
    }

    Map<Integer, Node> getNodeMap() {
        return this.nodesMap;
    }

    Node getCurrentPlayerNode() {
        if (getJogadorAtual() == null) {
            return null;
        }
        return this.nodesMap.get(this.getJogadorAtual().getPosition());
    }

    void endTurn() {
        currentPlayer = (currentPlayer + 1 < this.jogadores.size()) ? currentPlayer + 1 : 0;
    }

    void move(int direction) {
        Node actual = this.getCurrentPlayerNode();
        List<Node> neighbors = this.getNeighbors(actual);
        switch (direction) {
            case MOVE_RIGHT:
                for (Node neighbor : neighbors) {
                    if (neighbor.getX() == actual.getX() + 1 && neighbor.getY() == actual.getY()) {
                        this.getJogadorAtual().setPosition(neighbor.getNode());
                    }
                }

                break;
            case MOVE_DOWN:
                for (Node neighbor : neighbors) {
                    if (neighbor.getX() == actual.getX() && neighbor.getY() == actual.getY() + 1) {
                        this.getJogadorAtual().setPosition(neighbor.getNode());
                    }
                }

                break;
            case MOVE_LEFT:
                for (Node neighbor : neighbors) {
                    if (neighbor.getX() == actual.getX() - 1 && neighbor.getY() == actual.getY()) {
                        this.getJogadorAtual().setPosition(neighbor.getNode());
                    }
                }

                break;
            case MOVE_UP:
                for (Node neighbor : neighbors) {
                    if (neighbor.getX() == actual.getX() && neighbor.getY() == actual.getY() - 1) {
                        this.getJogadorAtual().setPosition(neighbor.getNode());
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    public List<Node> getNeighbors(Node actual) {
        List<Node> neighbors = new ArrayList<>();
        if (actual == null) {
            return neighbors;
        }
        Integer[][] pos = {
            {actual.getX(), actual.getY() - 1},
            {actual.getX() + 1, actual.getY()},
            {actual.getX(), actual.getY() + 1},
            {actual.getX() - 1, actual.getY()},};
        for (Map.Entry<Integer, Node> entry : nodesMap.entrySet()) {
            Integer key = entry.getKey();
            Node node = entry.getValue();
            if ((pos[0][0] == node.getX() && pos[0][1] == node.getY())
                    || (pos[1][0] == node.getX() && pos[1][1] == node.getY())
                    || (pos[2][0] == node.getX() && pos[2][1] == node.getY())
                    || (pos[3][0] == node.getX() && pos[3][1] == node.getY())) {
                neighbors.add(node);
            }
        }
        return neighbors;
    }
    
    public List<Item> getItemsOnNode(Node node){
        List<Item> itens = new ArrayList<>();
        for (Item item : this.itens) {
            if(item.getNode().equals(node.getNode())){
                itens.add(item);
            }
        }
        return itens;
    }
}
