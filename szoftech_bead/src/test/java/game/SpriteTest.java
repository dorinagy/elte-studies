package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpriteTest {
    
    private static Sprite instance;
        
    @BeforeEach
    public static void setUp() {
        instance = new Sprite(100, 200, 50, 50);
    }

    /**
     * Test of getX method, of class Sprite.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        int expResult = 100;
        int result = instance.getX();
        assertEquals(expResult, result);
    }

    /**
     * Test of setX method, of class Sprite.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        int x = 0;
        instance.setX(x);
        int result = instance.getX();
        assertEquals(x, result);
    }

    /**
     * Test of getY method, of class Sprite.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        int expResult = 200;
        int result = instance.getY();
        assertEquals(expResult, result);
    }

    /**
     * Test of setY method, of class Sprite.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        int y = 0;
        instance.setY(y);
        int result = instance.getY();
        assertEquals(y, result);
    }

    /**
     * Test of getWidth method, of class Sprite.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        int expResult = 50;
        int result = instance.getWidth();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWidth method, of class Sprite.
     */
    @Test
    public void testSetWidth() {
        System.out.println("setWidth");
        int width = 0;
        instance.setWidth(width);
        int result = instance.getWidth();
        assertEquals(width, result);
    }

    /**
     * Test of getHeight method, of class Sprite.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        int expResult = 50;
        int result = instance.getHeight();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHeight method, of class Sprite.
     */
    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        int height = 0;
        instance.setHeight(height);
        int result = instance.getHeight();
        assertEquals(height, result);
    }
    
}
