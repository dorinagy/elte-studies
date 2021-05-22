package capitaly.players;

import capitaly.fields.Property;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

public class GreedyTest {
    
    private static Greedy instance;

    @BeforeEach
    public static void setUp() {
        instance = new Greedy("john", "greedy");
    }

    /**
     * Test of buy method, of class Greedy.
     */
    @Test
    @DisplayName("Greedy player buys property if has enough money.")
    public void testBuyGreedy() {
        Property property = new Property();
        instance.setBalance(2000);
        instance.buy(property);
        
        String owner = property.getOwner();
        String expOwner = "john";
        assertEquals(expOwner, owner);
        
        ArrayList<Property> properties = instance.getProperties();
        assertEquals(1, properties.size());
    }
    
    @Test
    @DisplayName("Greedy player doesn't buy property if doesn't have enough money.")
    public void testBuyGreedyFalse() {
        Property property = new Property();
        instance.setBalance(500);
        instance.buy(property);
        
        String owner = property.getOwner();
        String expOwner = "";
        assertEquals(expOwner, owner);
        
        ArrayList<Property> properties = instance.getProperties();
        assertEquals(0, properties.size());
    }

    /**
     * Test of build method, of class Greedy.
     */
    @Test
    @DisplayName("Greedy player builds house if has enough money.")
    public void testBuild() {
        Property property = new Property();
        
        property.setOwner("john");
        instance.setBalance(5000);
        instance.addProperty(property);
        
        instance.build(property);
        
        boolean hasHouse = property.getHouse();

        assertEquals(true, hasHouse);
        
        ArrayList<Property> properties = instance.getProperties();
        assertEquals(true, properties.get(0).getHouse());
    }
    
    @Test
    @DisplayName("Greedy player doens't build house if doesn't have enough money.")
    public void testBuildFalse() {
        Property property = new Property();
        
        property.setOwner("john");
        instance.setBalance(100);
        instance.addProperty(property);
        
        instance.build(property);
        
        boolean hasHouse = property.getHouse();

        assertEquals(false, hasHouse);
        
        ArrayList<Property> properties = instance.getProperties();
        assertEquals(false, properties.get(0).getHouse());
    }

    /**
     * Test of toString method, of class Greedy.
     */
    @Test
    @DisplayName("ToString should return correct string with correct values.")
    public void testToString() {
        String expResult = "john\nstrategy: greedy, balance: 10000\nproperties: 0";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Greedy.
     */
    @Test
    @DisplayName("Two greedy player equals if all properties are equal.")
    public void testEquals() {
        Object obj = new Greedy("john", "greedy");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test
    @DisplayName("Two greedy player are not equal if not all properties are equal.")
    public void testEqualsFalse() {
        Player obj = new Greedy("john", "greedy");
        obj.setBalance(2000);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Greedy.
     */
    @Test
    @DisplayName("Hashcode method should return correct hashcode.")
    public void testHashCode() {
        int expResult = 1902970426;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    
}
