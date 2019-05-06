package Mundo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {
    
    public NodeTest() {
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
     * Test of getId method, of class Node.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Node instance = new Node();
        Integer expResult = null;
        Integer result = instance.getNode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Node.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = 1;
        Node instance = new Node();
        instance.setNode(id);
    }

    /**
     * Test of getX method, of class Node.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Node instance = new Node();
        Integer expResult = null;
        Integer result = instance.getX();
        assertEquals(expResult, result);
    }

    /**
     * Test of setX method, of class Node.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        Integer x = 10;
        Node instance = new Node();
        instance.setX(x);
        assertEquals(x, instance.getX());
    }

    /**
     * Test of getY method, of class Node.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Node instance = new Node();
        Integer expResult = null;
        Integer result = instance.getY();
        assertEquals(expResult, result);
    }

    /**
     * Test of setY method, of class Node.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        Integer y = 20;
        Node instance = new Node();
        instance.setY(y);
        assertEquals((Integer)20, instance.getY());
    }
    
}
