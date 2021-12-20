package game.playShips;

import game.Ship;

import javax.swing.*;
import java.awt.*;

public class BattleShip extends Ship {

    private Image image;

    public BattleShip(int x, int y){
        super(x, y);
        this.attack = 4;
        this.defense = 4;
        this.velocity = 3;
        this.vitality = 4;
        this.metalCost = 4;
        this.fuelConsumption = 4;
        
        try {
            this.image = new ImageIcon(ClassLoader.getSystemResource("spritePics/BattleShip.png")).getImage();
        } catch (Exception e) {  
            System.out.println("Cannot find image: BattleShip.");
        }
    }

    public BattleShip(int x, int y, int beReady) {
        this(x,y);
        this.setBeReady(beReady);
    }
    
    /**
     * Draw Battle Ship
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
