package Mundo;

import Grafo.VerticeItem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    public PlayerTest() {
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
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Player instance = new Player();
        String result = instance.getName();
        assertNotNull(result);
    }

    /**
     * Test of setName method, of class Player.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Test name";
        Player instance = new Player();
        Player result = instance.setName(name);
        String expResult = instance.getName();
        assertSame(instance, result);
        assertEquals(expResult, name);
    }

    /**
     * Test of getPosition method, of class Player.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Player instance = new Player();
        Integer expResult = null;
        Integer result = instance.getPosition();
        assertNull(result);
    }

    /**
     * Test of setPosition method, of class Player.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        Integer position = 1;
        Player instance = new Player();
        Player result = instance.setPosition(position);
        Integer respos = instance.getPosition();
        assertSame(instance, result);
        assertEquals(respos, position);
    }

    /**
     * Test of getVertice method, of class Player.
     */
    @Test
    public void testGetVertice() {
        System.out.println("getVertice");
        Player instance = new Player();
        VerticeItem expResult = null;
        VerticeItem result = instance.getVertice();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVertice method, of class Player.
     */
    @Test
    public void testSetVertice() {
        System.out.println("setVertice");
        VerticeItem vertice = new VerticeItem(0);
        Player instance = new Player();
        Player result = instance.setVertice(vertice);
        VerticeItem result2 = instance.getVertice();
        assertSame(result, instance);
        assertEquals(vertice, result2);
        assertEquals((Integer)vertice.getIndice(), result.getPosition());
    }


    /**
     * Test of getIdentifier method, of class Player.
     */
    @Test
    public void testGetIdentifier() {
        System.out.println("getIdentifier");
        Player instance = new Player();
        Integer expResult = null;
        Integer result = instance.getIdentifier();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdentifier method, of class Player.
     */
    @Test
    public void testSetIdentifier() {
        System.out.println("setIdentifier");
        Integer identifier = null;
        Player instance = new Player();
        Player expResult = null;
        Player result = instance.setIdentifier(identifier);
        Integer result2 = instance.getIdentifier();
        assertSame(instance, result);
        assertEquals(identifier, result2);
    }

    /**
     * Test of getTeam method, of class Player.
     */
    @Test
    public void testGetTeam() {
        System.out.println("getTeam");
        Player instance = new Player();
        Integer expResult = 0;
        Integer result = instance.getTeam();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTeam method, of class Player.
     */
    @Test
    public void testSetTeam() {
        System.out.println("setTeam");
        Integer team = 1;
        Player instance = new Player();
        Integer expResult = 1;
        Player result = instance.setTeam(team);
        Integer result2 = instance.getTeam();
        assertSame(instance, result);
        assertEquals(expResult, result2);
    }

    /**
     * Test of getPontos method, of class Player.
     */
    @Test
    public void testGetPontos() {
        System.out.println("getPontos");
        Player instance = new Player();
        Integer expResult = 0;
        Integer result = instance.getPontos();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPontos method, of class Player.
     */
    @Test
    public void testSetPontos() {
        System.out.println("setPontos");
        Integer pontos = 8000;
        Player instance = new Player();
        Integer expResult = 8000;
        Player result = instance.setPontos(pontos);
        Integer result2 = instance.getPontos();
        assertSame(instance, result);
        assertEquals(expResult, result2);
    }

    /**
     * Test of getIdentifier_in_game method, of class Player.
     */
    @Test
    public void testGetIdentifier_in_game() {
        System.out.println("getIdentifier_in_game");
        Player instance = new Player();
        Integer expResult = null;
        Integer result = instance.getIdentifier_in_game();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdentifier_in_game method, of class Player.
     */
    @Test
    public void testSetIdentifier_in_game() {
        System.out.println("setIdentifier_in_game");
        Integer identifier_in_game = 123;
        Player instance = new Player();
        Player expResult = null;
        Player result = instance.setIdentifier_in_game(identifier_in_game);
        Integer result2 = instance.getIdentifier_in_game();
        assertSame(instance, result);
        assertEquals(identifier_in_game, result2);
    }

}
