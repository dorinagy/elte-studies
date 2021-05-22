package capitaly.fields;

import capitaly.players.Greedy;
import capitaly.players.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class PropertyTest {
    
    private static Property instance;
        
    private final int defaultCost = 1000;
    private final int buildingCost = 4000;
    private final int propertyRental = 500;
    private final int houseRental = 2000;

    @BeforeEach
    public static void setUp() {
        instance = new Property();
    }
   
    /**
     * Test of getCost method, of class Luck.
     */
    @Test
    @DisplayName("Get cost method should return default cost value.")
    public void testGetCost() {
        int expResult = this.defaultCost;
        int result = instance.getCost();
        assertEquals(expResult, result);
    }
    
    @Test
    @DisplayName("Get cost method should return rental cost with house.")
    public void testGetCostRentalWithHouse() {
        int expResult = this.houseRental;
        instance.setOwner("bela");
        instance.setHouse(true);
        int result = instance.getCost();
        assertEquals(expResult, result);
    }
    
    @Test
    @DisplayName("Get cost method should return rental cost without house.")
    public void testGetCostRentalWithoutHouse() {
        int expResult = this.propertyRental;
        instance.setOwner("bela");
        instance.setHouse(false);
        int result = instance.getCost();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class Luck.
     */
    @Test
    @DisplayName("Get type method should return type value.")
    public void testGetType() {
        String expResult = "property";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of cross method, of class Luck.
     */
    @Test
    @DisplayName("Cross method should return true when building or buying property.")
    public void testCross() {
        Player player = new Greedy("janos", "greedy");
        player.setBalance(5000);
        boolean expResult = true;
        boolean result = instance.cross(player);
        assertEquals(expResult, result);
    }
    
    @Test
    @DisplayName("Cross method should return true if renting and has enough money.")
    public void testCrossRenting() {
        Player player = new Greedy("janos", "greedy");
        player.setBalance(5000);
        instance.setOwner("bela");
        instance.setHouse(false);
        boolean expResult = true;
        boolean result = instance.cross(player);
        assertEquals(expResult, result);
    }
    
    @Test
    @DisplayName("Cross method should return flase if renting and doesn't have enough money.")
    public void testCrossRentingFalse() {
        Player player = new Greedy("janos", "greedy");
        player.setBalance(100);
        instance.setOwner("bela");
        instance.setHouse(true);
        boolean expResult = false;
        boolean result = instance.cross(player);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Luck.
     */
    @Test
    @DisplayName("ToString method should return with the correct format and values.")
    public void testToString() {
        String expResult = "property without house";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Luck.
     */
    @Test
     @DisplayName("Equals method should return with correct value.")
    public void testEquals() {
        Object obj = new Property();
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Luck.
     */
    @Test
    @DisplayName("HashCode method should return with the correct hash.")
    public void testHashCode() {
        int expResult = -684832961;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    
}
