package labyrinth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameAreaTest {
    
    private static GameArea instance;
    
    private static int[][] map = {
        {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
        {0, 1, 1, 1, 0, 1, 0, 1, 1, 0},
        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
        {1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
        {0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
        {0, 1, 0, 1, 0, 1, 1, 0, 1, 0},
        {0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
        {0, 0, 0, 0, 0, 1, 1, 0, 1, 0},
        {0, 1, 0, 0, 0, 1, 0, 0, 0, 0}
    };
    
    
    @BeforeEach
    public static void setUp() {
        instance = new GameArea();
        GameArea.setArea(map);
    }

    /**
     * Test of isWall method, of class GameArea.
     */
    @Test
    public void testIsNotWall() {
        System.out.println("isNotWall");
        int x = 5;
        int y = 5;
        boolean expResult = false;
        // tile at (5,5) is not wall (0)
        boolean result = GameArea.isWall(x, y);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsWall() {
        System.out.println("isWall");
        int x = 3;
        int y = 0;
        boolean expResult = true;
        // tile at (3,0) is wall (1)
        boolean result = GameArea.isWall(x, y);
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class GameArea.
     */
    @Test
    public void testIsNotEmpty() {
        System.out.println("isEmpty");
        int x = 0;
        int y = 9; // hero start position
        
        App.hero = new Hero();
        
        boolean expResult = false;
        boolean result = GameArea.isEmpty(x, y);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsEmpty() {
        System.out.println("isNotEmpty");
        int x = 0;
        int y = 4; // wall tile - no character
        boolean expResult = true;
        boolean result = GameArea.isEmpty(x, y);
        assertEquals(expResult, result);
    }
}
