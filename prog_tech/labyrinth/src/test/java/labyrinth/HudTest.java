package labyrinth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HudTest {
    
    private static Hud instance;
    
    @BeforeEach
    public static void setUp() {
        instance = new Hud();
    }
    
    /**
     * Test of elapsedTime method, of class Hud.
     */
    @Test
    public void testElapsedTime() {
        System.out.println("elapsedTime");
        long expResult = System.currentTimeMillis() - instance.getStart();
        long result = instance.elapsedTime();
        assertEquals(expResult, result);
    }

}
