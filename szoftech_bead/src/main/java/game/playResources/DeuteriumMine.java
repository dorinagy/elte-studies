package game.playResources;

import game.Resource;

import javax.swing.*;
import java.awt.*;

public class DeuteriumMine extends Resource {
    
    private Image image;

    public DeuteriumMine(int x, int y, int level) {
        super(x, y, level);
        
        try {
            this.image = new ImageIcon(ClassLoader.getSystemResource("spritePics/DeuteriumMine.png")).getImage();
        } catch (Exception e) {  
            System.out.println("Cannot find image: DeuteriumMine.");
        }
    }

    public DeuteriumMine(int x, int y){
        super(x, y);
    }

    public DeuteriumMine() { super(0,0,0); }

    /**
     * Draw Game Elements
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
