package labyrinth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MonsterTest {
    
    private static App app;
    private static GameArea area;
    private static Monster instance;
    
    @BeforeEach
    public static void setUp() {
        app = new App();
        area = new GameArea();
        instance = new Monster();
    }
    
    /**
     * Test of move method, of class Monster.
     */
    @Test
    public void testMove() {
        System.out.println("move");
        
        int x = instance.posX;
        int y = instance.posY;
        
        instance.move();
        
        //assertFalse(x == instance.posX && y == instance.posY);
    }
}
