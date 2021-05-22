package hunt_game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CharacterTest {

    private static Character instance;
    
    @BeforeEach
    public static void setUp() {
        instance = new Character(0, 0);
    }

    /**
     * Test of getPosX method, of class Character.
     */
    @Test
    public void testGetPosX() {
        System.out.println("getPosX");
        int expResult = 0;
        int result = instance.getPosX();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPosX method, of class Character.
     */
    @Test
    public void testSetPosX() {
        System.out.println("setPosX");
        int expResult = 5;
        instance.setPosX(expResult);
        int result = instance.getPosX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPosY method, of class Character.
     */
    @Test
    public void testGetPosY() {
        System.out.println("getPosY");
        int expResult = 0;
        int result = instance.getPosY();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPosY method, of class Character.
     */
    @Test
    public void testSetPosY() {
        System.out.println("setPosY");
        int expResult = 5;
        instance.setPosY(5);
        int result = instance.getPosY();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImage method, of class Character.
     */
    @Test
    public void testGetImage() {
        System.out.println("getImage");
        Character hero = new Hero(0, 0);
        String expResult = "assets/hero_.png";
        String result = hero.getImage();
        assertEquals(expResult, result);
    }

    /**
     * Test of setImage method, of class Character.
     */
    @Test
    public void testSetImage() {
        System.out.println("setImage");
        String image = "kiskutya.png";
        instance.setImage(image);
        String result = instance.getImage();
        assertEquals(image, result);
    }

    /**
     * Test of getName method, of class Character.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "hero";
        Character hero = new Hero(0, 0);
        String result = hero.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Character.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "kiskutya";
        instance.setName(name);
        String result = instance.getName();
        assertEquals(name, result);
    }

    /**
     * Test of equals method, of class Character.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Character hero1 = new Hero(0, 0);
        Character hero2 = new Hero(0, 0);
        boolean result = hero1.equals(hero2);
        assertTrue(result); 
    }
    
    @Test
    public void testEqualsFalse() {
        System.out.println("equals false");
        Character hero = new Hero(0, 0);
        Character monster = new Monster(0, 0);
        boolean result = hero.equals(monster);
        assertFalse(result); 
    }
    
}
