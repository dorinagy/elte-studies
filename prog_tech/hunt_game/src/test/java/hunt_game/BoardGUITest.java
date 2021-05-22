package hunt_game;

import javax.swing.JLabel;
import javax.swing.JPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardGUITest {
    
    private static BoardGUI instance;
    
    @BeforeEach
    public static void setUp() {
        instance = new BoardGUI(5);
    }

    /**
     * Test of getBoardPanel method, of class BoardGUI.
     */
    @Test
    public void testGetBoardPanel() {
        System.out.println("getBoardPanel");
        JPanel result = instance.getBoardPanel();
        assertTrue(result instanceof JPanel);
    }

    /**
     * Test of getCounterLabel method, of class BoardGUI.
     */
    @Test
    public void testGetCounterLabel() {
        System.out.println("getCounterLabel");
        String expResult = "0/20 steps";
        JLabel result = instance.getCounterLabel();
        assertEquals(expResult, result.getText());
    }
    
}
