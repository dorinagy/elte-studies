package game.playShips;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class MotherShipTest {

    private static MotherShip instance;
    
    private final int ATTACK = 5;
    private final int DEFENSE = 4;
    private final int VELOCITY = 3;
    private final int VITALITY = 5;
    private final int METAL_COST = 4;
    private final int FUEL_CONSUMPTION = 4;
        
    @BeforeEach
    public static void setUp() {
        instance = new MotherShip(100, 200);
    }

    @Test
    public void testInitialization() {
        System.out.println("initialize");
        int posX = instance.getX();
        int posY = instance.getY();
        
        assertEquals(100, posX);
        assertEquals(200, posY);
    }
    
    /**
     * Test of moveShip method, of class MotherShip.
     */
    @Test
    public void testMoveShip() {
        System.out.println("moveShip");
        int x = 0;
        int y = 0;
        instance.moveShip(x, y);
        
        int posX = instance.getX();
        int posY = instance.getY();
        assertEquals(0, posX);
        assertEquals(0, posY);
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
