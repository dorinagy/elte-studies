package game.playShips;

import game.GameEngine;
import game.Planet;
import game.Player;
import game.Ship;

import javax.swing.*;
import java.awt.*;

public class TransportShip extends Ship {

    private Image image;

    private int capacity;

    public TransportShip(int x, int y, int capacity) {
        super(x, y);
        this.capacity = capacity;
        this.attack = 1;
        this.defense = 2;
        this.velocity = 3;
        this.vitality = 2;
        this.metalCost = 1;
        this.fuelConsumption = 2;
        
        try {
            this.image = new ImageIcon(ClassLoader.getSystemResource("spritePics/TransportShip.png")).getImage();
        } catch (Exception e) {  
            System.out.println("Cannot find image: TransportShip.");
        }
    }

    public TransportShip(int x, int y, int capacity, int beReady) {
        this(x,y,capacity);
        this.setBeReady(beReady);
    }
    
    public int getCapacity() {
        return this.capacity;
    }
    
    /**
     * Draw Transport Ship
     * 
     * @param g Graphics
     * 
     * @see Graphics
     */
    @Override
    public void draw(Graphics g){
        g.drawImage(this.image, x, y, width, height, null);
    }

    public boolean hasEnoughFuel(Planet from, Planet to) {
        int routeLengthBetweenShipAndPlanetStart = (int) Math.sqrt(
                (this.getX()-from.getX())*(this.getX()-from.getX()) + 
                (this.getX()-from.getY())*(this.getY()-from.getY())
                );
        int routeLengthBetweenPlanets = (int) Math.sqrt(
                (to.getX()-from.getX())*(to.getX()-from.getX()) + 
                (to.getX()-from.getY())*(to.getY()-from.getY())
                );
        int routeLengthBetweenShipAndPlanetEnd = (int) Math.sqrt(
                (this.getX()-to.getX())*(this.getX()-to.getX()) + 
                (this.getX()-to.getY())*(this.getY()-to.getY())
                );
        int routeLengthTotal = routeLengthBetweenShipAndPlanetStart + 
                routeLengthBetweenPlanets + 
                routeLengthBetweenShipAndPlanetEnd;
        
        int fuelNeed = (int) routeLengthTotal / 1500 * fuelConsumption;
        
        if (fuelNeed <= from.getOwner().totalDeuterium()) {
            return true;
        } else {
            return false;
        }
    }

    public void transportResources(Planet planetFrom, Planet planetTo, int amount, GameEngine game) {
        if ( amount >= planetFrom.getMetal() || amount >= capacity) {
            return;
        } 
        if (!hasEnoughFuel(planetFrom, planetTo)) {
            return;
        }
        
        planetFrom.metal -= amount;
        planetTo.metal += amount;
        game.getActivePlayer().reduceFuelByAmount(amount);
        
    }
}
