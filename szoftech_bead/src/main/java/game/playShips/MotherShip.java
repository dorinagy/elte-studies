package game.playShips;

import game.Ship;

import javax.swing.*;
import java.awt.*;

public class MotherShip extends Ship {
    
    private Image image;
    
    public MotherShip(int x, int y) {
        super(x, y);
        this.attack = 5;
        this.defense = 4;
        this.velocity = 3;
        this.vitality = 5;
        this.metalCost = 4;
        this.fuelConsumption = 4;
        
        try {
            this.image = new ImageIcon(ClassLoader.getSystemResource("spritePics/MotherShip.png")).getImage();
        } catch (Exception e) {  
            System.out.println("Cannot find image: MotherShip.");
        }
    }

    /**
     * Draw Mother Ship
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
