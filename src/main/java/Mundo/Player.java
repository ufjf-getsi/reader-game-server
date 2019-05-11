package Mundo;

import Grafo.Aresta;
import Grafo.VerticeItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.openide.util.Exceptions;

public class Player {

    private Integer identifier;
    private Integer identifier_in_game;
    private String name;
    private Integer team;
    private Integer position;
    private Integer pontos;
    private VerticeItem vertice;
    private String data;
    private Map<String, String> dataMap;

    public Player() {
        this(null, UUID.randomUUID().toString());
    }

    public Player(Integer identifier_in_game, String nome) {
        this(null, identifier_in_game, nome, 0, null, 0);
    }

    public Player(Integer identifier, Integer identifier_in_game, String name, Integer team, Integer position, Integer pontos) {
        this.identifier = identifier;
        this.identifier_in_game = identifier_in_game;
        this.name = name;
        this.team = team;
        this.vertice = null;
        this.position = position;
        this.pontos = pontos;
        this.data = "{}";
        this.dataMap = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;

    }

    public Integer getPosition() {
        return position;
    }

    public Player setPosition(Integer position) {
        this.position = position;
        return this;

    }

    public VerticeItem getVertice() {
        return vertice;
    }

    public Player setVertice(VerticeItem vertice) {
        this.vertice = vertice;
        setPosition(vertice.getIndice());
        vertice.addItem(this.getName());
        return this;

    }

    public void mover(int opcao) {
        vertice.removeItem(this.getName());
        Aresta auxAresta = vertice.getPrimAresta();
        for (int i = 0; i < opcao; i++) {
            auxAresta = auxAresta.getProxima();
        }
        vertice = (VerticeItem) auxAresta.getVerticeDestino();
        setVertice(vertice);
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public Player setIdentifier(Integer identifier) {
        this.identifier = identifier;
        return this;

    }

    public Integer getTeam() {
        return team;
    }

    public Player setTeam(Integer team) {
        this.team = team;
        return this;
    }

    public Integer getPontos() {
        return pontos;
    }

    public Player setPontos(Integer pontos) {
        this.pontos = pontos;
        return this;
    }

    public Integer getIdentifier_in_game() {
        return identifier_in_game;
    }

    public Player setIdentifier_in_game(Integer identifier_in_game) {
        this.identifier_in_game = identifier_in_game;
        return this;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        this.dataMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.dataMap = mapper.readValue(this.data, Map.class);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public Map<String, String> getDataMap() {
        return dataMap;
    }

    public Integer pickUp(String item) {
        addGoodQuantity(item, 1);
        return getGoodQuantity(item);
    }

    public Integer deliver(String item) {

        return addGoodQuantity(item, -1);
    }

    public Integer deliver(Item item) {
        final String itemName = item.getDataMap().get(Game.NAME);
        int q = addGoodQuantity(itemName, -1);
        if (q == -1) {
            int p;
            try {
                p = Integer.parseInt(item.getDataMap().get(Game.POINTS));
            } catch (NumberFormatException e) {
                p = 10;
            }
            setPontos(getPontos() + p);
        }
        return q;
    }

    public int getGoodQuantity(String good) {
        Integer qty = 0;
        String qtyStr = dataMap.get(good);
        if (qtyStr != null) {
            try {
                qty = Integer.parseInt(qtyStr);
            } catch (NumberFormatException e) {
                qty = -1;
            }
        }
        return qty;
    }

    public int addGoodQuantity(String item, int i) {
        int qty = getGoodQuantity(item);
        if (qty >= 0 && i > 0) {
            qty = qty + i;
        } else if (qty > 0 && i < 0) {
            i = Math.max(-qty, i);
            qty = qty + i;
        } else if (qty <= 0 && i > 0) {
            qty = i;
        } else if (qty <= 0 && i < 0) {
            qty = 0;
            i = 0;
        }
        dataMap.put(item, Integer.toString(qty));
        return i;
    }

}
