package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShipTest {
    
    private static Ship instance;
        
    @BeforeEach
    public static void setUp() {
        instance = new Ship(100, 200);
    }

    @Test
    public void testInitialization() {
        System.out.println("initialize");
        int posX = instance.getX();
        int posY = instance.getY();
        
        assertEquals(100, posX);
        assertEquals(200, posY);
    }
    
}
