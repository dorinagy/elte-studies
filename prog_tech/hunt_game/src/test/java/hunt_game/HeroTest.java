package hunt_game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeroTest {
    
    private static Hero instance;
        
    @BeforeEach
    public static void setUp() {
        instance = new Hero(0, 0);
    }
    
    String name = "hero";
    String image = "assets/hero_.png";
    
    @Test
    public void testConstructor() {
        assertEquals(name, instance.getName());
        assertEquals(image, instance.getImage());
    }
    
    /**
     * Test of canEscape method, of class Hero.
     */
    @Test
    public void testCanEscape() {
        System.out.println("canEscape");
        Board board = new Board(3);
        int boardSize = 3;
        
        boolean expResult = true;
        boolean result = instance.canEscape(board, boardSize);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCantEscape() {
        System.out.println("cantEscape");
        int boardSize = 3;
        Board board = new Board(boardSize);
        
        board.get(0, 1).setCharacter(new Monster(0, 1));
        board.get(1, 0).setCharacter(new Monster(1, 0));
        
        boolean expResult = false;
        boolean result = instance.canEscape(board, boardSize);
        assertEquals(expResult, result);
    }
}
