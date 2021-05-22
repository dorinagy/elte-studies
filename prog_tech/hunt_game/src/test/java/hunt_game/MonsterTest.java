package hunt_game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MonsterTest {
    
    String name = "monster";
    String image = "assets/boss_.png";
    
    @Test
    public void testConstructor() {
        Monster monster = new Monster(2, 4);
        assertEquals(name, monster.getName());
        assertEquals(image, monster.getImage());
    }
}
