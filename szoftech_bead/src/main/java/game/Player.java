package game;

import game.playShips.BattleShip;
import game.playShips.ColonialShip;
import game.playShips.CruiserShip;
import game.playShips.HuntingShip;
import game.playShips.MotherShip;
import game.playShips.TransportShip;
import java.awt.geom.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Player {
    
    public static int ID = 1;
    
    private int actionPoints;
    private boolean madeupgrade = false;
    
    private List<Point2D> coordinates;

    private List<Planet> playerPlanets = new ArrayList<>();
    private List<Resource> playerResources = new ArrayList<>();

    private MotherShip motherShip;

    private ArrayList<Ship> fleet = new ArrayList<>();

    private int id;

    public Player(int actionPoints, Planet mainPlanet, MotherShip motherShip) {

        this.motherShip = motherShip;
        this.actionPoints = actionPoints;
        this.playerPlanets.add(mainPlanet);
        id = ID;
        ID++;
        
        coordinates = new ArrayList<>();

        fleet.add(motherShip);
    }
    
    public void reset(int actionPoints, Planet mainPlanet) {
        madeupgrade = false;
        playerPlanets = new ArrayList<>();
        playerResources = new ArrayList<>();
        
        this.actionPoints = actionPoints;
        this.playerPlanets.add(mainPlanet);
    }
    
    // setters & getters
    public void setPlayerPlanets(Planet planet){
        this.playerPlanets.add(planet);
    }

    public List<Planet> getPlayerPlanets(){
        return this.playerPlanets;
    }

    public List<Resource> getPlayerResources(){
        return this.playerResources;
    }

    public void addPlayerResource(Resource res) { this.playerResources.add(res); }

    public void setMotherShip(MotherShip motherShip) {
        this.motherShip = motherShip;
    }

    public MotherShip getMotherShip(){
        return this.motherShip;
    }

    public void setFleet(ArrayList<Ship> fleet) {
        this.fleet = fleet;
    }

    public void addShip(Ship ship) { this.fleet.add(ship); }

    public ArrayList<Ship> getFleet(){
        ArrayList<Ship> ret = new ArrayList<>();
        for(int i=0; i<this.fleet.size(); i++) {
            if(this.fleet.get(i).getBeReady() == 0) {
                ret.add(this.fleet.get(i));
            }
        }
        return ret;
    }

    public ArrayList<Ship> getFleetWithBeReady() {
        return this.fleet;
    }

    public int getId() {
        return id;
    }
    
    public int getActionPoints() {
        return actionPoints;
    }

    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }
    
    public List<Point2D> getCoordinates() {
        return this.coordinates;
    }
    
    public void setCoordinates(List<Point2D> coordinates) {
        this.coordinates = coordinates;
    }
    
    public void removeCoordinate(int index) {
        this.coordinates.remove(index);
    }
    
    public void resetCoordinates() {
        this.coordinates.clear();
    }
    
    /**
     * Move somewhere
     * 
     * @param x
     * @param y
     */
    public void move(int x, int y) {
        for (Ship ship : getFleet()) {
            ship.moveShip(x, y);
        }
    }

    public void setMadeupgrade(boolean bool){
        this.madeupgrade = bool;
    }

    public boolean getMadeupdate(){
        return this.madeupgrade;
    }
    
    /**
     * return if planet is own planet
     * 
     * @param planet
     * 
     * @return boolean
     */
    public boolean isOwnPlanet(Planet planet) {
        if (
            planet == null || planet.getOwner() == null
        ) {
            return false;
        }
        
        return planet.getOwner().equals(this);
    }
    
    /**
     * return if planet is own planet
     * 
     * @param fleet
     * 
     * @return boolean
     */
    public boolean isOwnFleet(List<Ship> fleet) {
        if (fleet == null) return false;
        
        return fleet.equals(this.getFleet());
    }

    public void colonizePlanet(Planet planet) {
        if (planet == null) return;
        
        planet.setOwner(this);
        
        ColonialShip colonialShip = getColonialShip();
        
        if (colonialShip != null) {
            colonialShip.addResourcesToPlanet(planet);
            this.playerPlanets.add(planet);
        }
    }
    
    public void conqeurPlanet(Planet planet) {
        if (planet == null) return;
        
        planet.setOwner(this);     
        this.playerPlanets.add(planet);
    }
    
    public ColonialShip getColonialShip() {
        ColonialShip colonialShip = null;
        for (int i=0; i<fleet.size(); i++) {         
            Ship ship = fleet.get(i);
            if (ship instanceof ColonialShip) {
                colonialShip = (ColonialShip)ship;
            }
        }
        return colonialShip;
    }

    public boolean hasColonialShip() {
        boolean hasColonialShip = false;
        for (int i=0; i<fleet.size(); i++) {
            Ship ship = fleet.get(i);
            if (ship instanceof ColonialShip) {
                hasColonialShip = true;
            }
        }
        return hasColonialShip;
    }

    public TransportShip getTransportShip() {
        TransportShip transportShip = null;
        for (int i=0; i<fleet.size(); i++) {         
            Ship ship = fleet.get(i); 
            if (ship instanceof TransportShip) {
                transportShip = (TransportShip)ship;
            }
        }
        return transportShip;
    }

    /**
     * Handle transport resources
     * 
     * @param planetFrom
     * @param planetTo
     * @param ship
     */
    public void transportResources(Planet planetFrom, Planet planetTo, TransportShip ship, int amount, GameEngine game) {
        ship.transportResources(planetFrom, planetTo, amount, game);
    }

    boolean hasBattleShip() {
        boolean hasBattleShip = false;
        for (int i=0; i<fleet.size(); i++) {
            Ship ship = fleet.get(i);
            if (
                ship instanceof CruiserShip ||
                ship instanceof HuntingShip ||
                ship instanceof BattleShip ||
                ship instanceof MotherShip
            ) {
                hasBattleShip = true;
                break;
            }
        }
        return hasBattleShip;
    }
    
    public int getFleetConsumption() {
        int consumption = 0;
        for (int i=0; i<fleet.size(); i++) {
            Ship ship = fleet.get(i);
            consumption+=ship.getFuelConsumption();
        }
        return consumption;
    }
    
    public int totalDeuterium () {
        int total = 0;
        for (Planet planet : playerPlanets) {
            total += planet.getDeuterium();
        }
        
        return total;
    }
    
    public void useDeuterium(int amount) {
        for (Planet planet : playerPlanets) {
            if (planet.getDeuterium() >= amount) {
                planet.useDeuterium(amount);
                return;
            }
        }
    }
    
    private int getFuelNeeded(int routeLength) {
        return (int) routeLength * getFleetConsumption() / 150;
    }
    
    private int getPointsNeeded(int routeLength) {
        return getFleet().size() * getFleetConsumption() * routeLength / 150;
    }
        
    public boolean hasEnoughFuel(int routeLength) {
        return getFuelNeeded(routeLength) <= totalDeuterium();
    }
    
    public boolean hasEnoughPoints(int routeLength) {        
        return getActionPoints() > getPointsNeeded(routeLength);
    }
    
    public void reduceFuel(int routeLength) {
        int amount = getFuelNeeded(routeLength);
        int cost = getPointsNeeded(routeLength);
        
        int points = getActionPoints() - cost;
        
        useDeuterium(amount);
        setActionPoints(points);
    }
    
    public void reduceFuelByAmount(int amount) {
        useDeuterium(amount);
    }
 }
