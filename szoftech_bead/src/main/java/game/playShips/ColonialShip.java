package game.playShips;

import game.Planet;
import game.Ship;
import game.playResources.DeuteriumMine;
import game.playResources.MetalMine;
import game.playResources.SolarPowerPlant;

import javax.swing.*;
import java.awt.*;

public class ColonialShip extends Ship {
    
    private Image image;

    public ColonialShip(int x, int y) {
        super(x, y);
        this.attack = 2;
        this.defense = 3;
        this.velocity = 3;
        this.vitality = 4;
        this.metalCost = 3;
        this.fuelConsumption = 3;
        
        try {
            this.image = new ImageIcon(ClassLoader.getSystemResource("spritePics/ColonialShip.png")).getImage();
        } catch (Exception e) {  
            System.out.println("Cannot find image: ColonialShip.");
        }
    }

    public ColonialShip(int x, int y, int beReady) {
        this(x,y);
        this.setBeReady(beReady);
    }

    /**
     * Draw Colonial Ship
     * 
     * @param g Graphics
     * 
     * @see Graphics
     */
    @Override
    public void draw(Graphics g){
        g.drawImage(this.image, x, y, width, height, null);
    }

    public void addResourcesToPlanet(Planet planet) {
        planet.addPlanetResource(new SolarPowerPlant());
        planet.addPlanetResource(new MetalMine());
        planet.addPlanetResource(new DeuteriumMine());
    }
}
