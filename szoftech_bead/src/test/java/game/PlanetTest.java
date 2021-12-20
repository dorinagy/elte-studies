package game;

import game.playResources.DeuteriumMine;
import game.playResources.MetalMine;
import game.playResources.SolarPowerPlant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlanetTest {
    
    private static Planet instance;
        
    @BeforeEach
    public static void setUp() {
        SolarPowerPlant solarPowerPlant = new SolarPowerPlant(100, 100, 1);
        MetalMine metalMine = new MetalMine(60, 60, 2);
        DeuteriumMine deuteriumMine = new DeuteriumMine(50, 50, 3);
        
        instance = new Planet(10, 10, 20, 20, 1, 80, solarPowerPlant, metalMine, deuteriumMine);
    }

    /**
     * Test of getSolarPower method, of class Planet.
     */
    @Test
    public void testGetSolarPower() {
        System.out.println("getSolarPower");
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getSolarPower());
    }

    /**
     * Test of getMetal method, of class Planet.
     */
    @Test
    public void testGetMetal() {
        System.out.println("getMetal");
        int expectedValue = 0;
        assertEquals(expectedValue, instance.getMetal());
    }
}
