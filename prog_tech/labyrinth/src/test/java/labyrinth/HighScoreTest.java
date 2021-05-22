package labyrinth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HighScoreTest {
    
    private static HighScore instance;
    
    @BeforeEach
    public static void setUp() {
        instance = new HighScore("alma", 1);
    }

    /**
     * Test of getName method, of class HighScore.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "alma";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScore method, of class HighScore.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        int expResult = 1;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class HighScore.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "HighScore{name=alma, score=1}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
