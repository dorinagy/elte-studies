package game.playResources;

import game.Resource;

import javax.swing.*;
import java.awt.*;

public class ShipYard extends Resource {
        
    private Image image;

    public ShipYard(int x, int y, int level) {
        super(x, y, level);
        
        try {
            this.image = new ImageIcon(ClassLoader.getSystemResource("spritePics/ShipYard.png")).getImage();
        } catch (Exception e) {  
            System.out.println("Cannot find image: ShipYard.");
        }
    }

    public ShipYard(int x, int y){
        super(x, y);
    }

    public ShipYard() { super(0,0,0); }

    /**
     * Draw Metal Mine
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
