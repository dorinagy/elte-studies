package capitaly.players;

import capitaly.fields.Property;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

public class CautiousTest {
    
    private static Cautious instance;

    @BeforeEach
    public static void setUp() {
        instance = new Cautious("john", "cautious");
    }

    /**
     * Test of buy method, of class Greedy.
     */
    @Test
    @DisplayName("Cautious player buys property if has budget.")
    public void testBuyCautious() {
        Property property = new Property();
        instance.setBudget(2000);
        instance.buy(property);
        
        String owner = property.getOwner();
        String expOwner = "john";
        assertEquals(expOwner, owner);
        
        ArrayList<Property> properties = instance.getProperties();
        assertEquals(1, properties.size());
    }
    
    @Test
    @DisplayName("Cautious player doesn't buy property if doesn't have the budget.")
    public void testBuyCautiousFalse() {
        Property property = new Property();
        instance.setBudget(500);
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
    @DisplayName("Cautious player builds house if has budget.")
    public void testBuild() {
        Property property = new Property();
        
        property.setOwner("john");
        instance.setBudget(5000);
        instance.addProperty(property);
        
        instance.build(property);
        
        boolean hasHouse = property.getHouse();

        assertEquals(true, hasHouse);
        
        ArrayList<Property> properties = instance.getProperties();
        assertEquals(true, properties.get(0).getHouse());
    }
    
    @Test
    @DisplayName("Cautious player doens't build house if doesn't have budget.")
    public void testBuildFalse() {
        Property property = new Property();
        
        property.setOwner("john");
        instance.setBudget(100);
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
        String expResult = "john\nstrategy: cautious, balance: 10000, budget: 5000\nproperties: 0";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Greedy.
     */
    @Test
    @DisplayName("Two cautious player equals if all properties are equal.")
    public void testEquals() {
        Object obj = new Cautious("john", "cautious");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test
    @DisplayName("Two greedy player are not equal if not all properties are equal.")
    public void testEqualsFalse() {
        Player obj = new Cautious("john", "cautious");
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
        int expResult = -1061169957;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    
}
