package hunt_game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {
    
    private static Field instance;
        
    @BeforeEach
    public static void setUp() {
        instance = new Field();
    }
    
    /**
     * Test of getCharacter method, of class Field.
     */
    @Test
    public void testGetCharacter() {
        System.out.println("getCharacter");
        
        Character expResult = null;
        Character result = instance.getCharacter();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCharacter method, of class Field.
     */
    @Test
    public void testSetCharacter() {
        System.out.println("setCharacter");
        Character character = new Hero(0, 0);

        instance.setCharacter(character);
        Character result = instance.getCharacter();
        
        assertEquals(character, result);
    }

    /**
     * Test of getImage method, of class Field.
     */
    @Test
    public void testGetImage() {
        System.out.println("getImage");
        
        String expResult = "assets/floor.png";
        String result = instance.getImage();
        assertEquals(expResult, result);
    }

    /**
     * Test of setImage method, of class Field.
     */
    @Test
    public void testSetImage() {
        System.out.println("setImage");
        String image = "kiskutya";
        
        instance.setImage(image);
        String result = instance.getImage();
        
        assertEquals("kiskutya", result);
    }
    
}
