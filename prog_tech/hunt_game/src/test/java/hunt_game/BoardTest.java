package hunt_game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    
    private static Board instance; 
    
    @BeforeEach
    public static void setUp() {
        instance = new Board(3);
    }

    /**
     * Test of isOver method, of class Board.
     */
    @Test
    public void testIsOver() {
        System.out.println("isOver");
        instance.setCurrentSteps(12);
        
        boolean expResult = true;
        boolean result = instance.isOver();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsOverFalse() {
        System.out.println("isOverFalse");
        instance.setCurrentSteps(8);
        
        boolean expResult = false;
        boolean result = instance.isOver();
        assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class Board.
     */
    @Test
    public void testGet_int_int() {
        System.out.println("get");
        int x = 0;
        int y = 0;
        Field result = instance.get(x, y);
        assertTrue(result instanceof Field);
    }

    /**
     * Test of getBoardSize method, of class Board.
     */
    @Test
    public void testGetBoardSize() {
        System.out.println("getBoardSize");
        int expResult = 3;
        int result = instance.getBoardSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxSteps method, of class Board.
     */
    @Test
    public void testGetMaxSteps() {
        System.out.println("getMaxSteps");
        int expResult = 12;
        int result = instance.getMaxSteps();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentSteps method, of class Board.
     */
    @Test
    public void testGetCurrentSteps() {
        System.out.println("getCurrentSteps");
        int expResult = 0;
        int result = instance.getCurrentSteps();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCurrentSteps method, of class Board.
     */
    @Test
    public void testSetCurrentSteps() {
        System.out.println("setCurrentSteps");
        int currentSteps = 4;
        instance.setCurrentSteps(currentSteps);
        int result = instance.getCurrentSteps();
        assertEquals(currentSteps, result);
    }

    /**
     * Test of getPrevPlayer method, of class Board.
     */
    @Test
    public void testGetPrevPlayer() {
        System.out.println("getPrevPlayer");
        Character monster = new Monster(0, 1);
        
        instance.setPrevPlayer(monster);
        
        Character result = instance.getPrevPlayer();
        assertEquals(monster, result);
    }

    /**
     * Test of setPrevPlayer method, of class Board.
     */
    @Test
    public void testSetPrevPlayer() {
        System.out.println("setPrevPlayer");
        Character prevPlayer = new Monster(0, 1);
        instance.setPrevPlayer(prevPlayer);
        
        Character result = instance.getPrevPlayer();
        
        assertEquals(prevPlayer, result);
    }

    /**
     * Test of getActPlayer method, of class Board.
     */
    @Test
    public void testGetActPlayer() {
        System.out.println("getActPlayer");
        Character expResult = new Hero(3, 3);
        instance.setActPlayer(expResult);
        Character result = instance.getActPlayer();
        assertEquals(expResult, result);
    }

    /**
     * Test of setActPlayer method, of class Board.
     */
    @Test
    public void testSetActPlayer() {
        System.out.println("setActPlayer");
        Character expResult = new Hero(3, 3);
        instance.setActPlayer(expResult);
        Character result = instance.getActPlayer();
        assertEquals(expResult, result);
    }
    
}
