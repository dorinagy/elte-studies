package game.playResources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SolarPowerPlantTest {
    
    private static SolarPowerPlant instance;
        
    @BeforeEach
    public static void setUp() {
        instance = new SolarPowerPlant(100, 200);
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
