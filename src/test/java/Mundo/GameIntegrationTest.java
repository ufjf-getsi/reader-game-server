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
        assertEquals((Integer)0, game.getCurrentPlayer());
        assertEquals((Integer)8, game.getPlayers());
    }
    
    @Test
    public void testGetOptions() {
        game.getOpcoes();
    }
    
    
    @Test
    public void testNodeMapShouldNotBeNull() {
        Map<Integer, Node> nodes = game.getNodeMap();
        assertNotNull(nodes);
        assertEquals(6, nodes.size());
        assertEquals((Integer)0, nodes.get(0).getNode());
    }
    
    
    
}
