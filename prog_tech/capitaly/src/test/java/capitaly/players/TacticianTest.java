package capitaly.players;

import capitaly.fields.Property;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

public class TacticianTest {
    
    private static Tactician instance;

    @BeforeEach
    public static void setUp() {
        instance = new Tactician("john", "tactician");
    }

    /**
     * Test of buy method, of class Greedy.
     */
    @Test
    @DisplayName("Tactician player buys property every second possibility.")
    public void testBuyCautious() {
        Property property = new Property();
        instance.setPurchases(0);
        instance.buy(property);
        
        String owner = property.getOwner();
        String expOwner = "john";
        assertEquals(expOwner, owner);
        
        ArrayList<Property> properties = instance.getProperties();
        assertEquals(1, properties.size());
    }
    
    @Test
    @DisplayName("Tactician player doesn't buy property if not second purchase.")
    public void testBuyCautiousFalse() {
        Property property = new Property();
        instance.setPurchases(1);
        instance.buy(property);
        
        String owner = property.getOwner();
        String expOwner = "";
        assertEquals(expOwner, owner);
        
        ArrayList<Property> properties = instance.getProperties();
        assertEquals(0, properties.size());
    }
    @Test
    @DisplayName("Tactician player doesn't buy property if doesn't have the budget.")
    public void testBuyCautiousFalseBudget() {
        Property property = new Property();
        instance.setBalance(100);
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
    @DisplayName("Tactician player builds house on every second purchase.")
    public void testBuild() {
        Property property = new Property();
        instance.setPurchases(2);
        property.setOwner("john");
        instance.addProperty(property);
        
        instance.build(property);
        
        boolean hasHouse = property.getHouse();

        assertEquals(true, hasHouse);
        
        ArrayList<Property> properties = instance.getProperties();
        assertEquals(true, properties.get(0).getHouse());
    }
    
    @Test
    @DisplayName("Tactician player doens't build house if not second purchase.")
    public void testBuildFalse() {
        Property property = new Property();
        instance.setPurchases(3);
        property.setOwner("john");
        instance.setBalance(100);
        instance.addProperty(property);
        
        instance.build(property);
        
        boolean hasHouse = property.getHouse();

        assertEquals(false, hasHouse);
        
        ArrayList<Property> properties = instance.getProperties();
        assertEquals(false, properties.get(0).getHouse());
    }
    
    @Test
    @DisplayName("Tactician player doens't build house if doesn't have budget.")
    public void testBuildFalseBalance() {
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
        String expResult = "john\nstrategy: tactician, balance: 10000\nproperties: 0";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Greedy.
     */
    @Test
    @DisplayName("Two cautious player equals if all properties are equal.")
    public void testEquals() {
        Object obj = new Tactician("john", "tactician");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test
    @DisplayName("Two greedy player are not equal if not all properties are equal.")
    public void testEqualsFalse() {
        Player obj = new Cautious("john", "tactician");
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
        int expResult = -2022076488;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    
}
