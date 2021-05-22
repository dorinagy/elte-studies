package capitaly.fields;

import capitaly.players.Greedy;
import capitaly.players.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

public class ServiceTest {
    
    private static Service instance;

    @BeforeAll
    public static void setUp() {
        instance = new Service(1000);
    }
   
    /**
     * Test of getCost method, of class Luck.
     */
    @Test
    @DisplayName("Get cost method should return cost value.")
    public void testGetCost() {
        int expResult = 1000;
        int result = instance.getCost();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class Luck.
     */
    @Test
    @DisplayName("Get type method should return type value.")
    public void testGetType() {
        String expResult = "service";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of cross method, of class Luck.
     */
    @Test
    @DisplayName("Cross method should return true if player has enough money.")
    public void testCross() {
        Player player = new Greedy("janos", "greedy");
        player.setBalance(2000);
        boolean expResult = true;
        boolean result = instance.cross(player);
        assertEquals(expResult, result);
    }
    
        @Test
    @DisplayName("Cross method should return false if player hasn't got enough money.")
    public void testCrossFalse() {
        Player player = new Greedy("janos", "greedy");
        player.setBalance(500);
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
        String expResult = "service field, value: 1000";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Luck.
     */
    @Test
     @DisplayName("Equals method should return with correct value.")
    public void testEquals() {
        Object obj = new Service(1000);
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
        int expResult = 1984185230;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    
}
