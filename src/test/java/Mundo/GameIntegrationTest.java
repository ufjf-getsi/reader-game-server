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
        game.getOpcoes();
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

}
