package capitaly.players;

import capitaly.fields.Property;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    
    /**
     * Test of getName method, of class Player.
    */
    @Test
    public void testGetName() {
        Player instance = new Greedy("john", "greedy");
        String expResult = "john";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPosition method, of class Player.
     */
    @Test
    public void testGetPosition() {
        Player instance = new Greedy("john", "greedy");
        int expResult = 0;
        int result = instance.getPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPosition method, of class Player.
     */
    @Test
    public void testSetPosition() {
        int position = 4;
        Player instance = new Greedy("john", "greedy");
        instance.setPosition(position);
        assertEquals(position, instance.getPosition());
    }

    /**
     * Test of getBalance method, of class Player.
     */
    @Test
    public void testGetBalance() {
        Player instance = new Greedy("john", "greedy");
        int expResult = 10000;
        int result = instance.getBalance();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBalance method, of class Player.
     */
    @Test
    public void testSetBalance() {
        int amount = 5000;
        Player instance = new Greedy("john", "greedy");
        instance.setBalance(amount);
        assertEquals(amount, instance.getBalance());
    }

    /**
     * Test of getRound method, of class Player.
     */
    @Test
    public void testGetRound() {
        Player instance = new Greedy("john", "greedy");
        int expResult = 0;
        int result = instance.getRound();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRound method, of class Player.
     */
    @Test
    public void testSetRound() {
        int round = 5;
        Player instance = new Greedy("john", "greedy");
        instance.setRound(round);
        assertEquals(round, instance.getRound());
    }

    /**
     * Test of getIsInGame method, of class Player.
     */
    @Test
    public void testGetIsInGame() {
        Player instance = new Greedy("john", "greedy");
        boolean expResult = true;
        boolean result = instance.getIsInGame();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIsInGame method, of class Player.
     */
    @Test
    public void testSetIsInGame() {
        boolean isInGame = false;
        Player instance = new Greedy("john", "greedy");
        instance.setIsInGame(isInGame);
        assertEquals(isInGame, instance.getIsInGame());
    }

    /**
     * Test of getProperties method, of class Player.
     */
    @Test
    public void testGetProperties() {
        Player instance = new Greedy("john", "greedy");
        int expResult = 0;
        ArrayList<Property> result = instance.getProperties();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of setProperties method, of class Player.
     */
    @Test
    public void testSetProperties() {
        ArrayList<Property> properties = new ArrayList<>();
        Property property = new Property();
        properties.add(property);
        
        Player instance = new Greedy("john", "greedy");
                
        instance.setProperties(properties);
        assertEquals(1, instance.getProperties().size());
    }

    /**
     * Test of getStrategy method, of class Player.
     */
    @Test
    public void testGetStrategy() {
        Player instance = new Greedy("john", "greedy");
        String expResult = "greedy";
        String result = instance.getStrategy();
        assertEquals(expResult, result);
    }

    /**
     * Test of startNewRound method, of class Player.
     */
    @Test
    public void testStartNewRound() {
        Player instance = new Greedy("john", "greedy");
        instance.startNewRound();
        assertEquals(1, instance.getRound());
    }

    /**
     * Test of removeProperties method, of class Player.
     */
    @Test
    public void testRemoveProperties() {
        Player instance = new Greedy("john", "greedy");
        Property property = new Property();
        instance.addProperty(property);
        
        instance.removeProperties();
        
        assertEquals(0, instance.getProperties().size());
    }

    /**
     * Test of addProperty method, of class Player.
     */
    @Test
    public void testAddProperty() {
        Player instance = new Greedy("john", "greedy");
        Property property = new Property();
        instance.addProperty(property);
        
        assertEquals(1, instance.getProperties().size());
    }

    /**
     * Test of changeBalance method, of class Player.
     */
    @Test
    public void testChangeBalance() {
        int amount = -4000;
        Player instance = new Greedy("john", "greedy");
        int expResult = 6000;
        instance.changeBalance(amount);
        assertEquals(expResult, instance.getBalance());
    }
    
}
