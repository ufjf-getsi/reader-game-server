package Mundo;

import Grafo.Grafo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getJogadores method, of class Game.
     */
    @Test
    public void testGetJogadores() {
        System.out.println("getJogadores");
        Game instance = new Game();
        List<Player> expResult = null;
        List<Player> result = instance.getJogadores();
        assertNotNull(result);
    }

    /**
     * Test of setJogadores method, of class Game.
     */
    @Test
    public void testSetJogadores() {
        System.out.println("setJogadores");
        List<Player> players = null;
        Game instance = new Game();
        instance.setJogadores(players);
        assertSame(players, instance.getJogadores());
    }

    /**
     * Test of getIdentifier method, of class Game.
     */
    @Test
    public void testGetIdentifier() {
        System.out.println("getIdentifier");
        Game instance = new Game();
        Integer expResult = null;
        Integer result = instance.getIdentifier();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdentifier method, of class Game.
     */
    @Test
    public void testSetIdentifier() {
        System.out.println("setIdentifier");
        Integer identifier = 1;
        Game instance = new Game();
        instance.setIdentifier(identifier);
        assertEquals(identifier, instance.getIdentifier());
    }

    /**
     * Test of getTittle method, of class Game.
     */
    @Test
    public void testGetTittle() {
        System.out.println("getTittle");
        Game instance = new Game();
        String expResult = "";
        String result = instance.getTittle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTittle method, of class Game.
     */
    @Test
    public void testSetTittle() {
        System.out.println("setTittle");
        String tittle = "Teste";
        Game instance = new Game();
        instance.setTittle(tittle);
        assertEquals(tittle, instance.getTittle());
    }

    /**
     * Test of getCurrentPlayer method, of class Game.
     */
    @Test
    public void testGetCurrentPlayer() {
        System.out.println("getCurrentPlayer");
        Game instance = new Game();
        Integer expResult = 0;
        Integer result = instance.getCurrentPlayer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJogadorAtual method, of class Game.
     */
    @Test
    public void testGetJogadorAtual() {
        System.out.println("getJogadorAtual");
        Game instance = new Game();
        Player expResult = null;
        Player result = instance.getJogadorAtual();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCurrentPlayer method, of class Game.
     */
    @Test
    public void testSetCurrentPlayer() {
        System.out.println("setCurrentPlayer");
        Integer currentPlayer = 0;
        Game instance = new Game();
        instance.setCurrentPlayer(currentPlayer);
    }

    /**
     * Test of getTurnsLeft method, of class Game.
     */
    @Test
    public void testGetTurnsLeft() {
        System.out.println("getTurnsLeft");
        Game instance = new Game();
        Integer expResult = 0;
        Integer result = instance.getTurnsLeft();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTurnsLeft method, of class Game.
     */
    @Test
    public void testSetTurnsLeft() {
        System.out.println("setTurnsLeft");
        Integer turnsLeft = null;
        Game instance = new Game();
        instance.setTurnsLeft(turnsLeft);
    }

    /**
     * Test of getPlayersOrder method, of class Game.
     */
    @Test
    public void testGetPlayersOrder() {
        System.out.println("getPlayersOrder");
        Game instance = new Game();
        String expResult = "";
        String result = instance.getPlayersOrder();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPlayersOrder method, of class Game.
     */
    @Test
    public void testSetPlayersOrder() {
        System.out.println("setPlayersOrder");
        String playersOrder = "";
        Game instance = new Game();
        instance.setPlayersOrder(playersOrder);
    }

    /**
     * Test of getNodes method, of class Game.
     */
    @Test
    public void testGetNodes() {
        System.out.println("getNodes");
        Game instance = new Game();
        String expResult = "";
        String result = instance.getNodes();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNodes method, of class Game.
     */
    @Test
    public void testSetNodes() {
        System.out.println("setNodes");
        String nodes = "[]";
        Game instance = new Game();
        instance.setNodes(nodes);
    }

    /**
     * Test of getData method, of class Game.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        Game instance = new Game();
        String expResult = "";
        String result = instance.getData();
        assertEquals(expResult, result);
    }

    /**
     * Test of setData method, of class Game.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        String data = "";
        Game instance = new Game();
        instance.setData(data);
    }

    /**
     * Test of getStatus method, of class Game.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Game instance = new Game();
        String expResult = "";
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStatus method, of class Game.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        String status = "";
        Game instance = new Game();
        instance.setStatus(status);
    }

    /**
     * Test of getItens method, of class Game.
     */
    @Test
    public void testGetItens() {
        System.out.println("getItens");
        Game instance = new Game();
        List<Item> expResult = new ArrayList<>();
        List<Item> result = instance.getItens();
        assertNotNull(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of setItens method, of class Game.
     */
    @Test
    public void testSetItens() {
        System.out.println("setItens");
        List<Item> itens = null;
        Game instance = new Game();
        instance.setItens(itens);
    }

    /**
     * Test of getMapa method, of class Game.
     */
    @Test
    public void testGetMapa() {
        System.out.println("getMapa");
        Game instance = new Game();
        Grafo expResult = null;
        Grafo result = instance.getMapa();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMapa method, of class Game.
     */
    @Test
    public void testSetMapa() {
        System.out.println("setMapa");
        Grafo mapa = null;
        Game instance = new Game();
        instance.setMapa(mapa);
    }

    /**
     * Test of getPlayers method, of class Game.
     */
    @Test
    public void testGetPlayers() {
        System.out.println("getPlayers");
        Game instance = new Game();
        Integer expResult = 0;
        Integer result = instance.getPlayers();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPlayers method, of class Game.
     */
    @Test
    public void testSetPlayers() {
        System.out.println("setPlayers");
        Integer players = null;
        Game instance = new Game();
        instance.setPlayers(players);
    }

    /**
     * Test of start method, of class Game.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        Game instance = new Game();
        instance.start();
    }

    /**
     * Test of reset method, of class Game.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        Game instance = new Game();
        instance.reset();
    }

    /**
     * Test of stop method, of class Game.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        Game instance = new Game();
        instance.stop();
    }

    /**
     * Test of doAction method, of class Game.
     */
    @Test
    public void testDoAction() {
        System.out.println("doAction");
        String action = "";
        Game instance = new Game();
        instance.doAction(action);
    }

    /**
     * Test of getAllStatus method, of class Game.
     */
    @Test
    public void testGetAllStatus() {
        System.out.println("getAllStatus");
        Game instance = new Game();
        String expResult = null;
        String result = instance.getAllStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentPlayerStatus method, of class Game.
     */
    @Test
    public void testGetCurrentPlayerStatus() {
        System.out.println("getCurrentPlayerStatus");
        Game instance = new Game();
        String expResult = null;
        String result = instance.getCurrentPlayerStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOpcoes method, of class Game.
     */
    @Test
    public void testGetOpcoes() {
        System.out.println("getOpcoes");
        Game instance = new Game();
        Map<String, String> expResult = new HashMap<>();
        Map<String, String> result = instance.getOpcoes();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOpcoes method, of class Game.
     */
    @Test
    public void testSetOpcoes() {
        System.out.println("setOpcoes");
        Map<String, String> opcoes = null;
        Game instance = new Game();
        instance.setOpcoes(opcoes);
    }
    
}
