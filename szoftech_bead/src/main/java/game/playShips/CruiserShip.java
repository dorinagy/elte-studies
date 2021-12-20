package game.playShips;

import game.Ship;

import javax.swing.*;
import java.awt.*;

public class CruiserShip extends Ship {

    private Image image;

    public CruiserShip(int x, int y) {
        super(x, y);
        this.attack = 4;
        this.defense = 3;
        this.velocity = 5;
        this.vitality = 3;
        this.metalCost = 3;
        this.fuelConsumption = 3;
        
        try {
            this.image = new ImageIcon(ClassLoader.getSystemResource("spritePics/CruiserShip.png")).getImage();
        } catch (Exception e) {  
            System.out.println("Cannot find image: CruiserShip.");
        }
    }

    public CruiserShip(int x, int y, int beReady) {
        this(x,y);
        this.setBeReady(beReady);
    }
    
    /**
     * Draw Cruise Ship
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
