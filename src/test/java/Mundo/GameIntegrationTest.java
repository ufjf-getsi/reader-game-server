package Mundo;

import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameIntegrationTest {

    Game game;

    public GameIntegrationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        game = GameGenerator.getGameStub();
    }

    @After
    public void tearDown() {
        game = null;
    }

    /**
     * @Description Deve haver um stub antes
     */
    @Test
    public void testNewStub() {
        assertNotNull(game);
        assertEquals((Integer) 0, game.getCurrentPlayer());
        assertEquals((Integer) 8, game.getPlayers());
    }

    @Test
    public void testGetOptions() {
        game.endTurn();
        assertEquals((Integer) 1, game.getCurrentPlayer());
        Node actual = game.getCurrentPlayerNode();

        Map<String, String> opcoes = game.getOpcoes();
        assertEquals(4, opcoes.size());
        assertEquals((Integer) 4, actual.getNode());
        assertEquals((Integer) 1, actual.getX());
        assertEquals((Integer) 1, actual.getY());
        assertEquals("Mover para [1, 0]", opcoes.get(Game.UP));
        assertEquals("Mover para [0, 1]", opcoes.get(Game.LEFT));
        assertEquals("Mover para [2, 1]", opcoes.get(Game.RIGHT));
        assertEquals("Mover para [1, 2]", opcoes.get(Game.DOWN));
    }

    @Test
    public void testNodeMapShouldNotBeNull() {
        Map<Integer, Node> nodes = game.getNodeMap();
        assertNotNull(nodes);
        assertEquals(13, nodes.size());
        assertEquals((Integer) 0, nodes.get(0).getNode());
    }

    @Test
    public void testPassTurn() {
        Player p1 = game.getJogadorAtual();
        assertNotNull(p1);
        game.endTurn();
        Player p2 = game.getJogadorAtual();
        assertNotNull(p2);
        assertNotSame(p1, p2);
        for (int i = 2; i < game.getJogadores().size(); i++) {
            game.endTurn();
        }
        Player p3 = game.getJogadorAtual();
        assertNotSame(p1, p3);
    }

    @Test
    public void testGetCurrentPlayerNode() {
        Node currentNode = game.getCurrentPlayerNode();
        assertNotNull(currentNode);
        assertEquals((Integer) 0, currentNode.getNode());
    }

    @Test
    public void testGetNeitghbors() {
        Node currentNode = game.getNodeMap().get(0);
        assertNotNull(currentNode);
        List<Node> neighbors = game.getNeighbors(currentNode);
        assertNotNull(neighbors);
        assertEquals(2, neighbors.size());
        currentNode = game.getNodeMap().get(4);
        neighbors = game.getNeighbors(currentNode);
        assertEquals(4, neighbors.size());
        currentNode = game.getNodeMap().get(9);
        neighbors = game.getNeighbors(currentNode);
        assertEquals(3, neighbors.size());
        currentNode = game.getNodeMap().get(10);
        neighbors = game.getNeighbors(currentNode);
        assertEquals(2, neighbors.size());
        currentNode = game.getNodeMap().get(12);
        neighbors = game.getNeighbors(currentNode);
        assertEquals(0, neighbors.size());
    }

    @Test
    public void testMove() {
        Node p1n1 = game.getCurrentPlayerNode();
        assertEquals((Integer) 0, p1n1.getX());
        assertEquals((Integer) 0, p1n1.getY());
        game.move(Game.MOVE_DOWN);
        Node p1n2 = game.getCurrentPlayerNode();
        assertEquals((Integer) 0, p1n2.getX());
        assertEquals((Integer) 1, p1n2.getY());

        game.endTurn();
        Node p2n1 = game.getCurrentPlayerNode();
        assertEquals((Integer) 1, p2n1.getX());
        assertEquals((Integer) 1, p2n1.getY());
        game.move(Game.MOVE_RIGHT);
        Node p2n2 = game.getCurrentPlayerNode();
        assertEquals((Integer) 2, p2n2.getX());
        assertEquals((Integer) 1, p2n2.getY());
        game.move(Game.MOVE_LEFT);
        Node p2n4 = game.getCurrentPlayerNode();
        assertEquals((Integer) 1, p2n4.getX());
        assertEquals((Integer) 1, p2n4.getY());
        game.move(Game.MOVE_UP);
        Node p2n3 = game.getCurrentPlayerNode();
        assertEquals((Integer) 1, p2n3.getX());
        assertEquals((Integer) 0, p2n3.getY());
        game.move(Game.MOVE_UP);
        Node p2n5 = game.getCurrentPlayerNode();
        assertEquals((Integer) 1, p2n5.getX());
        assertEquals((Integer) 0, p2n5.getY());

    }

    @Test
    public void testStubSaphireOnNode04() {
        Item saphire = game.getItens().get(5);
        assertNotNull(saphire);
        assertNotNull(saphire.getData());
        assertEquals((Integer)4, saphire.getNode());
        assertEquals("{\"name\": \"saphire\", \"type\":\"good\", \"color\":\"blue\"}", saphire.getData());
        assertNotNull(saphire.getDataMap());
        System.out.println(saphire.getData());
        System.out.println(saphire.getDataMap());
        assertEquals(3, saphire.getDataMap().size());
        assertEquals("saphire", saphire.getDataMap().get("name"));
        assertEquals("good"   , saphire.getDataMap().get("type"));
        assertEquals("blue"   ,saphire.getDataMap().get("color"));
    }
    
    @Test
    public void testStubItensOnNode04() {
        Node node4 = game.getNodeMap().get(4);
        assertNotNull(node4);
        List<Item> itens  = game.getItemsOnNode(node4);
        assertNotNull(itens);
        assertEquals(1, itens.size());
        assertEquals("saphire", itens.get(0).getDataMap().get("name"));
    }
    
    @Test
    public void testPickUpAllItensOnNode03() {
        Node node3 = game.getNodeMap().get(3);
        Player player = game.getJogadorAtual();
        assertNotNull(node3);
        List<Item> itensOnNode3  = game.getItemsOnNode(node3);
        assertNotNull(itensOnNode3);
        assertEquals(1, itensOnNode3.size());
        assertEquals("ruby", itensOnNode3.get(0).getDataMap().get(Game.NAME));
        game.move(Game.MOVE_DOWN);
        itensOnNode3  = game.getItemsOnNode(node3);
        assertEquals(0, itensOnNode3.size());
        assertEquals("1", player.getDataMap().get("ruby"));
        
    }
    
    @Test
    public void testDeliverAllItensOnNode00() {
        Player player = game.getJogadorAtual();
        Node node6 = game.getNodeMap().get(6);
        List<Item> itensOnNode6  = game.getItemsOnNode(node6);
        assertEquals(1, itensOnNode6.size());
        assertEquals("ruby", itensOnNode6.get(0).getDataMap().get(Game.NAME));
        assertEquals(Game.DEMAND, itensOnNode6.get(0).getDataMap().get(Game.TYPE));
        assertEquals(null, player.getDataMap().get("ruby"));
        game.move(Game.MOVE_DOWN);
        assertEquals("1", player.getDataMap().get("ruby"));
        game.move(Game.MOVE_DOWN);
        itensOnNode6  = game.getItemsOnNode(node6);
        assertEquals("0", player.getDataMap().get("ruby"));
        assertEquals(0, itensOnNode6.size());
    }
    
    @Test
    public void testScoreAfterDeliverOnNode00() {
        Player player = game.getJogadorAtual();
        assertEquals((Integer)0, player.getPontos());
        assertEquals(0, player.getGoodQuantity("emerald"));
        game.move(Game.MOVE_RIGHT);
        assertEquals(1, player.getGoodQuantity("emerald"));
        game.move(Game.MOVE_LEFT);
        assertEquals(0, player.getGoodQuantity("emerald"));
        assertEquals((Integer)20, player.getPontos());
        assertEquals(0, player.getGoodQuantity("ruby"));
        game.move(Game.MOVE_DOWN);
        assertEquals(1, player.getGoodQuantity("ruby"));
        assertEquals((Integer)20, player.getPontos());
        game.move(Game.MOVE_DOWN);
        assertEquals(0, player.getGoodQuantity("ruby"));
        assertEquals((Integer)50, player.getPontos());
        game.move(Game.MOVE_RIGHT);
        game.move(Game.MOVE_UP);
        game.move(Game.MOVE_LEFT);
        assertEquals(1, player.getGoodQuantity("saphire"));
        game.move(Game.MOVE_UP);
        assertEquals(0, player.getGoodQuantity("saphire"));
        assertEquals((Integer)60, player.getPontos());
        
    }
    
}
