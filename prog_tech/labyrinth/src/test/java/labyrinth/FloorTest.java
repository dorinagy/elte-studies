package labyrinth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FloorTest {
    
    private static Floor instance;
    
    private final String path = "res/floor.png";
   
    @BeforeEach
    public static void setUp() {
        instance = new Floor(0, 1);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, instance.posX);
        assertEquals(1, instance.posY);
    }
}
