package labyrinth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeroTest {
    
    private static Hero instance;
    
    @BeforeEach
    public static void setUp() {
        instance = new Hero(); 
    }

    @Test
    public void testConstructor() {
        assertEquals(0, instance.posX);
        assertEquals(9, instance.posY);
    }
}
