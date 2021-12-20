package game.playBarriers;

import game.Barrier;

import javax.swing.*;
import java.awt.*;

public class BlackHole extends Barrier {
    
    private Image image;

    public BlackHole(int x, int y) {
        super(x, y);
        
        try {
            this.image = new ImageIcon(ClassLoader.getSystemResource("spritePics/BlackHole.png")).getImage();
        } catch (Exception e) {
            System.out.println("Cannot find image: BlackHole.");
        }
    }

    /**
     * Draw black hole barrier to map
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
