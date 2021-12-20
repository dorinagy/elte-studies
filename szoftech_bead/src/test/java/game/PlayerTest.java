package game;

import game.playPlanets.MainPlanet;
import game.playResources.DeuteriumMine;
import game.playResources.MetalMine;
import game.playResources.SolarPowerPlant;
import game.playShips.ColonialShip;
import game.playShips.MotherShip;
import game.playShips.TransportShip;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    
    private static Player instance;
    
    private final int ACTION_POINTS = 100;
    
    @BeforeEach
    public static void setUp() {
        SolarPowerPlant solarPowerPlant = new SolarPowerPlant(100, 100, 1);
        MetalMine metalMine = new MetalMine(60, 60, 2);
        DeuteriumMine deuteriumMine = new DeuteriumMine(50, 50, 3);
        
        Planet mainPlanet = new MainPlanet(10, 10, 20, 20, 1, 80, solarPowerPlant, metalMine, deuteriumMine);
        MotherShip motherShip = new MotherShip(20, 20);
        instance = new Player(100, mainPlanet, motherShip);
    }

    /**
     * Test of reset method, of class Player.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        
        int actionPoints = instance.getActionPoints();
        assertEquals(100, actionPoints);
        
        instance.setActionPoints(300);
        assertEquals(300, instance.getActionPoints());
        
        Planet mainPlanet = instance.getPlayerPlanets().get(0);
        instance.reset(actionPoints, mainPlanet);
        
        assertEquals(ACTION_POINTS, instance.getActionPoints());
    }

    /**
     * Test of setPlayerPlanets method, of class Player.
     */
    @Test
    public void testSetPlayerPlanets() {
        System.out.println("setPlayerPlanets");
        Planet planet = new Planet(10, 10, 20, 20, 1, 80);
        instance.setPlayerPlanets(planet);
        
        assertEquals(planet, instance.getPlayerPlanets().get(1));
    }

    /**
     * Test of getPlayerPlanets method, of class Player.
     */
    @Test
    public void testGetPlayerPlanets() {
        System.out.println("getPlayerPlanets");
        List<Planet> expResult = null;
        List<Planet> result = instance.getPlayerPlanets();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerResources method, of class Player.
     */
    @Test
    public void testGetPlayerResources() {
        System.out.println("getPlayerResources");
        List<Resource> expResult = null;
        List<Resource> result = instance.getPlayerResources();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addPlayerResource method, of class Player.
     */
    @Test
    public void testAddPlayerResource() {
        System.out.println("addPlayerResource");
        Resource res = null;
        instance.addPlayerResource(res);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setMotherShip method, of class Player.
     */
    @Test
    public void testSetMotherShip() {
        System.out.println("setMotherShip");
        MotherShip motherShip = null;
        instance.setMotherShip(motherShip);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getMotherShip method, of class Player.
     */
    @Test
    public void testGetMotherShip() {
        System.out.println("getMotherShip");
        MotherShip expResult = null;
        MotherShip result = instance.getMotherShip();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setFleet method, of class Player.
     */
    @Test
    public void testSetFleet() {
        System.out.println("setFleet");
        ArrayList<Ship> fleet = null;
        instance.setFleet(fleet);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addShip method, of class Player.
     */
    @Test
    public void testAddShip() {
        System.out.println("addShip");
        Ship ship = null;
        instance.addShip(ship);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getFleet method, of class Player.
     */
    @Test
    public void testGetFleet() {
        System.out.println("getFleet");
        ArrayList<Ship> expResult = null;
        ArrayList<Ship> result = instance.getFleet();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getFleetWithBeReady method, of class Player.
     */
    @Test
    public void testGetFleetWithBeReady() {
        System.out.println("getFleetWithBeReady");
        ArrayList<Ship> expResult = null;
        ArrayList<Ship> result = instance.getFleetWithBeReady();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Player.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        int expResult = 0;
        int result = instance.getId();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getActionPoints method, of class Player.
     */
    @Test
    public void testGetActionPoints() {
        System.out.println("getActionPoints");
        int expResult = 0;
        int result = instance.getActionPoints();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setActionPoints method, of class Player.
     */
    @Test
    public void testSetActionPoints() {
        System.out.println("setActionPoints");
        int actionPoints = 0;
        instance.setActionPoints(actionPoints);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCoordinates method, of class Player.
     */
    @Test
    public void testGetCoordinates() {
        System.out.println("getCoordinates");
        List<Point2D> expResult = null;
        List<Point2D> result = instance.getCoordinates();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCoordinates method, of class Player.
     */
    @Test
    public void testSetCoordinates() {
        System.out.println("setCoordinates");
        List<Point2D> coordinates = null;
        instance.setCoordinates(coordinates);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of removeCoordinate method, of class Player.
     */
    @Test
    public void testRemoveCoordinate() {
        System.out.println("removeCoordinate");
        int index = 0;
        //instance.removeCoordinate(index);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of resetCoordinates method, of class Player.
     */
    @Test
    public void testResetCoordinates() {
        System.out.println("resetCoordinates");
        instance.resetCoordinates();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of move method, of class Player.
     */
    @Test
    public void testMove() {
        System.out.println("move");
        int x = 0;
        int y = 0;
        instance.move(x, y);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setMadeupgrade method, of class Player.
     */
    @Test
    public void testSetMadeupgrade() {
        System.out.println("setMadeupgrade");
        boolean bool = false;
        instance.setMadeupgrade(bool);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getMadeupdate method, of class Player.
     */
    @Test
    public void testGetMadeupdate() {
        System.out.println("getMadeupdate");
        boolean expResult = false;
        boolean result = instance.getMadeupdate();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of isOwnPlanet method, of class Player.
     */
    @Test
    public void testIsOwnPlanet() {
        System.out.println("isOwnPlanet");
        Planet planet = null;
        boolean expResult = false;
        boolean result = instance.isOwnPlanet(planet);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of isOwnFleet method, of class Player.
     */
    @Test
    public void testIsOwnFleet() {
        System.out.println("isOwnFleet");
        List<Ship> fleet = null;
        boolean expResult = false;
        boolean result = instance.isOwnFleet(fleet);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of colonizePlanet method, of class Player.
     */
    @Test
    public void testColonizePlanet() {
        System.out.println("colonizePlanet");
        Planet planet = null;
        instance.colonizePlanet(planet);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of conqeurPlanet method, of class Player.
     */
    @Test
    public void testConqeurPlanet() {
        System.out.println("conqeurPlanet");
        Planet planet = null;
        instance.conqeurPlanet(planet);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getColonialShip method, of class Player.
     */
    @Test
    public void testGetColonialShip() {
        System.out.println("getColonialShip");
        ColonialShip expResult = null;
        ColonialShip result = instance.getColonialShip();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of hasColonialShip method, of class Player.
     */
    @Test
    public void testHasColonialShip() {
        System.out.println("hasColonialShip");
        boolean expResult = false;
        boolean result = instance.hasColonialShip();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getTransportShip method, of class Player.
     */
    @Test
    public void testGetTransportShip() {
        System.out.println("getTransportShip");
        TransportShip expResult = null;
        TransportShip result = instance.getTransportShip();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of transportResources method, of class Player.
     */
    @Test
    public void testTransportResources() {
        System.out.println("transportResources");
        Planet planetFrom = null;
        Planet planetTo = null;
        TransportShip ship = null;
        //instance.transportResources(planetFrom, planetTo, ship);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of hasBattleShip method, of class Player.
     */
    @Test
    public void testHasBattleShip() {
        System.out.println("hasBattleShip");
        boolean expResult = false;
        boolean result = instance.hasBattleShip();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getFleetConsumption method, of class Player.
     */
    @Test
    public void testGetFleetConsumption() {
        System.out.println("getFleetConsumption");
        int expResult = 0;
        int result = instance.getFleetConsumption();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
