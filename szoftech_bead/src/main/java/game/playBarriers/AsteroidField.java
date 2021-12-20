package game.playBarriers;

import game.Barrier;

import javax.swing.*;
import java.awt.*;

public class AsteroidField extends Barrier {
    
    private Image image;

    public AsteroidField(int x, int y) {
        super(x, y);
        
        try {
            this.image = new ImageIcon(ClassLoader.getSystemResource("spritePics/AsteroidField.png")).getImage();
        } catch (Exception e) {
            System.out.println("Cannot find image: AsteroidField.");
        }
    }

    /**
     * Draw asteroid field to map
     * 
     * @param g Graphics
     * 
     * @see Graphics
     */
    @Override
    public void draw(Graphics g){
        g.drawImage(this.image, x, y, width, height, null);
    }
}
