package game.playShips;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class TransportShipTest {
    
    private static TransportShip instance;
        
    private final int ATTACK = 1;
    private final int DEFENSE = 2;
    private final int VELOCITY = 3;
    private final int VITALITY = 2;
    private final int METAL_COST = 1;
    private final int FUEL_CONSUMPTION = 2;
        
    @BeforeEach
    public static void setUp() {
        instance = new TransportShip(100, 200, 50);
    }

    @Test
    public void testInitialization() {
        System.out.println("initialize");
        int posX = instance.getX();
        int posY = instance.getY();
        int capacity = instance.getCapacity();
        
        assertEquals(100, posX);
        assertEquals(200, posY);
        assertEquals(50, capacity);
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
