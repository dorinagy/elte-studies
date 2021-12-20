package game.playShips;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ColonialShipTest {

    private static ColonialShip instance;
    
    private final int ATTACK = 2;
    private final int DEFENSE = 3;
    private final int VELOCITY = 3;
    private final int VITALITY = 4;
    private final int METAL_COST = 3;
    private final int FUEL_CONSUMPTION = 3;
        
    @BeforeEach
    public static void setUp() {
        instance = new ColonialShip(100, 200);
    }

    @Test
    public void testInitialization() {
        System.out.println("initialize");
        int posX = instance.getX();
        int posY = instance.getY();
        
        assertEquals(100, posX);
        assertEquals(200, posY);
    }
    
    @Test
    public void testGetAttack() {
        System.out.println("getAttack");
        
        assertEquals(ATTACK, instance.getAttack());
    }
    
    @Test
    public void testGetDefense() {
        System.out.println("getDefense");
        
        assertEquals(DEFENSE, instance.getDefense());
    }

    @Test
    public void testGetVitality() {
        System.out.println("getVitality");
        
        assertEquals(VITALITY, instance.getVitality());
    }
    
    @Test
    public void testGetFuelConsumption() {
        System.out.println("getFuelConsumption");
        
        assertEquals(FUEL_CONSUMPTION, instance.getFuelConsumption());
    }
    
}
