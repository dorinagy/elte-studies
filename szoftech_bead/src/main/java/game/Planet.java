package game;

import game.playResources.DeuteriumMine;
import game.playResources.MetalMine;
import game.playResources.ShipYard;
import game.playResources.SolarPowerPlant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Planet extends Sprite {
    private static final int MAX_SIZE = 15;
    private static final int BUILD_SIZE_COST = 1;
    private static final int UPGRADE_SIZE_COST = 1;
    private int size;
    private int level;
    private final int temperature;
    private ShipYard shipYard;
    private List<Resource> planetResources = new ArrayList<>();
    private int solarPower;
    public int metal;
    public int deuterium;
    private final int MAX_METAL = 200;
    private final int MAX_DEUTERIUM = 200;
    private Player owner;
    
    public static int ID = 0;
    
    private int id;

    private Image image;

    public Planet(int x, int y, int width, int height, int level, int temperature, SolarPowerPlant solarPowerPlant, MetalMine metalMine, DeuteriumMine deuteriumMine) {
        super(x, y, width, height);

        try {
            this.image = new ImageIcon(ClassLoader.getSystemResource("spritePics/MainPlanet.png")).getImage();
        } catch (Exception e) {
            System.out.println("Cannot find image: MainPlanet.");
        }

        this.temperature = temperature;
        this.level = level;
        this.solarPower = 0;
        this.metal = 0;
        this.deuterium = 0;
        this.size = BUILD_SIZE_COST;
        this.size += BUILD_SIZE_COST;
        this.size += BUILD_SIZE_COST;
        
        id = ID;
        ID++;

        this.addPlanetResource(solarPowerPlant);
        this.addPlanetResource(deuteriumMine);
        this.addPlanetResource(metalMine);
    }

    public Planet(int x, int y, int width, int height, int level, int temperature) {
        super(x, y, width, height);

        try {
            this.image = new ImageIcon(ClassLoader.getSystemResource("spritePics/MainPlanet.png")).getImage();
        } catch (Exception e) {
            System.out.println("Cannot find image: MainPlanet.");
        }

        this.temperature = temperature;
        this.level = level;
        this.solarPower = 0;
        this.metal = 0;
        this.deuterium = 0;
        this.size = BUILD_SIZE_COST;
        this.size += BUILD_SIZE_COST;
        this.size += BUILD_SIZE_COST;
    }
    
    public int getId() {
        return this.id;
    }

    public List<Resource> getPlanetResources(){
        return this.planetResources;
    }

    public List<Resource> getDeuteriumMines() {
        List<Resource> ret = new ArrayList<>();
        for (int i=0; i<planetResources.size(); i++) {
            if (planetResources.get(i) instanceof DeuteriumMine) {
                ret.add(planetResources.get(i));
            }
        }
        return ret;
    }

    public List<Resource> getSolarPowerPlants() {
        List<Resource> ret = new ArrayList<>();
        for (int i=0; i<planetResources.size(); i++) {
            if (planetResources.get(i) instanceof SolarPowerPlant) {
                ret.add(planetResources.get(i));
            }
        }
        return ret;
    }
    
    public void setOwner(Player player) {
        owner = player;
    }
    
    public Player getOwner() {
        return owner;
    } 

    public List<Resource> getMetalMines() {
        List<Resource> ret = new ArrayList<>();
        for (int i=0; i<planetResources.size(); i++) {
            if (planetResources.get(i) instanceof MetalMine) {
                ret.add(planetResources.get(i));
            }
        }
        return ret;
    }

    public List<Resource> getResources() {
        return this.planetResources;
    }

    public List<Resource> getShipYards() {
        List<Resource> ret = new ArrayList<>();
        for (int i=0; i<planetResources.size(); i++) {
            if (planetResources.get(i) instanceof ShipYard) {
                ret.add(planetResources.get(i));
            }
        }
        return ret;
    }

    public boolean hasShipYard() {
        for(int i=0; i<getShipYards().size(); i++) {
            if(getShipYards().get(i) instanceof ShipYard && getShipYards().get(i).getLevel() > 0) {
                return true;
            }
        }
        return false;
    }

    public int getShipYardLevel() {
        int ret = 0;
        for(int i=0; i<getShipYards().size(); i++) {
            if(getShipYards().get(i) instanceof ShipYard && getShipYards().get(i).getLevel() > 0) {
                ret += getShipYards().get(i).getLevel();
            }
        }
        return ret;
    }

    public final void addPlanetResource(Resource res) { this.planetResources.add(res); }

    public int getSolarPower() {
        return solarPower;
    }
    
    public int getMetal() {
        return metal;
    }
    
    public int getDeuterium() {
        return deuterium;
    }
    
    private int getSolarPowerPlantLevels() {
        var list = getSolarPowerPlants();
        int levels = 0;
        for (Resource plant : list) {
            levels += plant.getLevel();
        }
        return levels;
    }
    
      private int getMetalMineLevels() {
        var list = getMetalMines();
        int levels = 0;
        for (Resource mine : list) {
            levels += mine.getLevel();
        }
        return levels;
    }
      
    private int getDeuteriumMineLevels() {
        var list = getDeuteriumMines();
        int levels = 0;
        for (Resource mine : list) {
            levels += mine.getLevel();
        }
        return levels;
    }
    
    
    public void setSolarPowerMetalDeuterium() {
        int increment = temperature*getSolarPowerPlantLevels();
    
        solarPower += increment;
        
        int metalMineLevel = getMetalMineLevels();
        int deuteriumMineLevel = getDeuteriumMineLevels();
        int metalIncrement = 0;
        int deuteriumIncrement = 0;

        if (metalMineLevel+deuteriumMineLevel != 0) {
            
            metalIncrement = 
                solarPower / (metalMineLevel+deuteriumMineLevel) * metalMineLevel < metalMineLevel * 1000 
                    ? solarPower / (metalMineLevel+deuteriumMineLevel) * metalMineLevel 
                    :  metalMineLevel * 1000;
            
            deuteriumIncrement = 
                solarPower / (metalMineLevel+deuteriumMineLevel) * deuteriumMineLevel < deuteriumMineLevel * 1000 
                    ? solarPower / (metalMineLevel+deuteriumMineLevel) * deuteriumMineLevel 
                    :  deuteriumMineLevel * 1000;
            
            setMetal(metalIncrement);
            setDeuterium(deuteriumIncrement);
        } 
    }
    
    public void setSolarPower(int n) {
        solarPower = n;
    }
    
    private void setMetal(int increment) {
        if (MAX_METAL - metal > increment) {
            metal += increment;
            solarPower -= increment;
        } else if (MAX_METAL - metal == 0 ) {
            metal = MAX_METAL;
        } else {
            metal = MAX_METAL;
            solarPower -= increment;
        }
    }
    
    private void setDeuterium(int increment) {
        if (MAX_DEUTERIUM - deuterium > increment) {
            deuterium += increment;
            solarPower -= increment;
        } else if (MAX_DEUTERIUM - deuterium == 0 ) {
            deuterium = MAX_DEUTERIUM;
        } else {
            deuterium = MAX_DEUTERIUM;
            solarPower -= increment;
        }
    }
    
    public void useDeuterium(int amount) {
        if (deuterium >= amount) {
            deuterium -= amount;
        }
    }

    public void useMetal(int amount) {
        if (metal >= amount) {
            metal -= amount;
        }
    }
    
    public int getUsedSize() { return this.size; }

    public static int getBuildSizeCost() { return BUILD_SIZE_COST; }

    public static int getUpgradeSizeCost() { return UPGRADE_SIZE_COST; }

    public static int getMaxPlanetSize() { return MAX_SIZE; }

    public void addBuildCost() { this.size += BUILD_SIZE_COST; }

    public void addUpgradeCost() { this.size += UPGRADE_SIZE_COST; }

    @Override
    public void draw(Graphics g){
        g.drawImage(this.image, x, y, width, height, null);
    }
}
