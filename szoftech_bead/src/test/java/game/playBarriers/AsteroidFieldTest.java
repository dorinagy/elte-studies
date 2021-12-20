package game.playBarriers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AsteroidFieldTest {
    
    private static AsteroidField instance;
        
    @BeforeEach
    public static void setUp() {
        instance = new AsteroidField(100, 200);
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
