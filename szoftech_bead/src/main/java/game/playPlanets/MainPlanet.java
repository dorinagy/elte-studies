package game.playPlanets;

import game.Planet;
import game.Ship;
import game.playResources.DeuteriumMine;
import game.playResources.MetalMine;
import game.playResources.SolarPowerPlant;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
//depricated
public class MainPlanet extends Planet {
   
    private Image image;

    private ArrayList<Ship> fleet;

    public MainPlanet(int x, int y, int width, int height, int level, int temperature, SolarPowerPlant solarPowerPlant, MetalMine metalMine, DeuteriumMine deuteriumMine) {
        super(x, y, width, height, level, temperature, solarPowerPlant, metalMine, deuteriumMine);
        
        try {
            this.image = new ImageIcon(ClassLoader.getSystemResource("spritePics/MainPlanet.png")).getImage();
        } catch (Exception e) {  
            System.out.println("Cannot find image: MainPlanet.");
        }
    }
    
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
