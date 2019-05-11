package Mundo;

import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemTest {
    
    public ItemTest() {
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
     * Test of getId method, of class Item.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Item instance = new Item();
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Item.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = 1;
        Item instance = new Item();
        instance.setId(id);
        assertEquals((Integer)1, instance.getId());
    }

    /**
     * Test of getData method, of class Item.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        Item instance = new Item();
        String expResult = "{}";
        String result = instance.getData();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setData method, of class Item.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        String data = "{\"name\": \"teste\"}";
        Item instance = new Item();
        instance.setData(data);
        assertNotNull(instance.getDataMap());
        assertEquals(1, instance.getDataMap().size());
        assertEquals("teste", instance.getDataMap().get("name"));
    }

    /**
     * Test of getNode method, of class Item.
     */
    @Test
    public void testGetNode() {
        System.out.println("getNode");
        Item instance = new Item();
        Integer expResult = null;
        Integer result = instance.getNode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNode method, of class Item.
     */
    @Test
    public void testSetNode() {
        System.out.println("setNode");
        Integer node = 1;
        Item instance = new Item();
        instance.setNode(node);
        assertEquals((Integer)1, instance.getNode());
    }

    /**
     * Test of getDataMap method, of class Item.
     */
    @Test
    public void testGetDataMap() {
        System.out.println("getDataMap");
        Item instance = new Item();
        Map<String, String> expResult = new HashMap<>();
        Map<String, String> result = instance.getDataMap();
        assertEquals(expResult, result);
    }
    
}
