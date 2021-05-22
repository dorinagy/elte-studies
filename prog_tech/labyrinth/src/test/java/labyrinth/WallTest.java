package labyrinth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WallTest {
    
    private static Wall instance;
    
    private final String path = "res/wall.png";
   
    @BeforeEach
    public static void setUp() {
        instance = new Wall(0, 1);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, instance.posX);
        assertEquals(1, instance.posY);
    }
}
