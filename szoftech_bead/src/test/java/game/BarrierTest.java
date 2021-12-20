package game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class BarrierTest {
    
    private static Barrier instance;
        
    @BeforeEach
    public static void setUp() {
        instance = new Barrier(100, 200);
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
