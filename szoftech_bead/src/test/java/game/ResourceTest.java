package game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class ResourceTest {
    
    private static Resource instance;
        
    @BeforeEach
    public static void setUp() {
        instance = new Resource(10, 20, 0);
    }
    
    /**
     * Test of getLevel method, of class Resource.
     */
    @Test
    public void testGetLevel() {
        System.out.println("getLevel");
        int expResult = 0;
        int result = instance.getLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLevel method, of class Resource.
     */
    @Test
    public void testSetLevel() {
        System.out.println("setLevel");
        int level = 10;
        instance.setLevel(level);
        int result = instance.getLevel();
        assertEquals(level, result);
    }
    
}
