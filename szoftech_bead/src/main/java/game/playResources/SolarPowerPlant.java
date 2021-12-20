package game.playResources;

import game.Resource;

import javax.swing.*;
import java.awt.*;

public class SolarPowerPlant extends Resource {
    
    private Image image;

    public SolarPowerPlant(int x, int y, int level) {
        super(x, y, level);
        
        try {
            this.image = new ImageIcon(ClassLoader.getSystemResource("spritePics/SolarPowerPlant.png")).getImage();
        } catch (Exception e) {  
            System.out.println("Cannot find image: SolarPowerPlant.");
        }
    }

    /**
     * Define level as 1 at initialization
     */
    public SolarPowerPlant(int x, int y){
        super(x, y);
    }

    public SolarPowerPlant() { super(0,0,0); }

    /**
     * Draw Main Planet
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
