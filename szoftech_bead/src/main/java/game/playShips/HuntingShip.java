package game.playShips;

import game.Ship;

import javax.swing.*;
import java.awt.*;

public class HuntingShip extends Ship {

    private Image image;

    public HuntingShip(int x, int y) {
        super(x, y);
        this.attack = 3;
        this.defense = 2;
        this.velocity = 4;
        this.vitality = 2;
        this.metalCost = 2;
        this.fuelConsumption = 2;
        
        try {
            this.image = new ImageIcon(ClassLoader.getSystemResource("spritePics/HuntingShip.png")).getImage();
        } catch (Exception e) {  
            System.out.println("Cannot find image: HuntingShip.");
        }
    }

    public HuntingShip(int x, int y, int beReady) {
        this(x,y);
        this.setBeReady(beReady);
    }
    
    /**
     * Draw Hunting Ship
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
