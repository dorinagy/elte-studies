package labyrinth;

import java.awt.image.BufferedImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameObjectTest {
    
    private static GameObject instance;
    
    @BeforeEach
    public static void setUp() {
        instance = new GameObject();
    }
    
    /**
     * Test of setImage method, of class GameObject.
     */
    @Test
    public void testSetImage() {
        System.out.println("setImage");
        BufferedImage image = null;
        instance.setImage(image);
        
        assertEquals(image, instance.image);
    }
    
}
