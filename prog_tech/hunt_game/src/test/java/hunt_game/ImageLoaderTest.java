package hunt_game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImageLoaderTest {

    /**
     * Test of getInstance method, of class ImageLoader.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        ImageLoader result = ImageLoader.getInstance();
        assertTrue(result instanceof ImageLoader);
    }
    
}
