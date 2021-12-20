package game;

import game.playBarriers.AsteroidField;
import game.playBarriers.BlackHole;
import game.playPlanets.MainPlanet;
import game.playResources.DeuteriumMine;
import game.playResources.MetalMine;
import game.playResources.ShipYard;
import game.playResources.SolarPowerPlant;
import game.playShips.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JComponent {

    private static final int TRANSPORT_SHIP_MIN_LEVEL = 1;
    private static final int HUNTING_SHIP_MIN_LEVEL = 2;
    private static final int CRUISER_SHIP_MIN_LEVEL = 3;
    private static final int BATTLE_SHIP_MIN_LEVEL = 4;
    private static final int COLONIAL_SHIP_MIN_LEVEL = 5;

    private GameGUI gameGUI;
    private GameEngine gameEngine;

    private Image backgroundImage = new ImageIcon(ClassLoader.getSystemResource("backgroundPics/backgroundSpace.jpg")).getImage();

    public BlackHole blackHole;
    public AsteroidField asteroidField;

    public static JPopupMenu ownPlanetMenu;

    public JMenu build;
    public JMenu upgrade;
    public JMenuItem shipBuild;

    public JPanel shipYardMenuPanel;
    public ButtonGroup shipYardMenuButtonGroup;
    public GridBagConstraints shipYardMenuGridBag;
    public JRadioButton cruiserShip;
    public JRadioButton colonialShip;
    public JRadioButton transportShip;
    public JRadioButton battleShip;
    public JRadioButton huntingShip;
    private JPanel transportPanel;

    public GamePanel(GameGUI gameGUI, GameEngine gameEngine) {

        this.gameGUI = gameGUI;
        this.gameEngine = gameEngine;
        this.initializeMap();
    }

    /**
     * Initialize map
     * <p>
     * draw game elements
     *
     * @see MainPlanet
     * @see MetalMine
     * @see SolarPowerPlant
     * @see MotherShip
     * @see ShipYard
     */
    private void initializeMap() {
        blackHole = new BlackHole(560, 350);
        asteroidField = new AsteroidField(350, 210);
    }

    public void createShipYardPane() {
        shipYardMenuPanel = new JPanel(new GridBagLayout());
        shipYardMenuButtonGroup = new ButtonGroup();
        shipYardMenuGridBag = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0);
        cruiserShip = new JRadioButton("Cruiser ship");
        colonialShip = new JRadioButton("Colonial ship");
        transportShip = new JRadioButton("Transport ship");
        battleShip = new JRadioButton("Battle ship");
        huntingShip = new JRadioButton("Hunting ship");

        addShipToPanel(transportShip,0,0,TRANSPORT_SHIP_MIN_LEVEL);
        addShipToPanel(huntingShip,1,0,HUNTING_SHIP_MIN_LEVEL);
        addShipToPanel(cruiserShip,0,1,CRUISER_SHIP_MIN_LEVEL);
        addShipToPanel(battleShip,1,1,BATTLE_SHIP_MIN_LEVEL);
        addShipToPanel(colonialShip,0,2,COLONIAL_SHIP_MIN_LEVEL);

        int reply = JOptionPane.showConfirmDialog(null, shipYardMenuPanel, "ShipYard menu",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (reply == JOptionPane.OK_OPTION) {
            gameEngine.buildShip();
        }
    }

    
        public void createInputForTransport(int max) {
        transportPanel = new JPanel(new GridBagLayout());
        SpinnerModel model = new SpinnerNumberModel(10, 0, max, 10);     
        JSpinner spinner = new JSpinner(model);
        
        transportPanel.add(spinner);
 

        int reply = JOptionPane.showConfirmDialog(null, transportPanel, "Select amount",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (reply == JOptionPane.OK_OPTION) {
            int value = (Integer) spinner.getValue();
            gameEngine.setTransportAmountFromInput(value);
        }
    }
    public void addShipToPanel(JRadioButton ship, int x, int y, int level) {
        if(gameEngine.getActivePlanet().getShipYardLevel() >= level) {
            setGridX(x);
            setGridY(y);
            shipYardMenuPanel.add(ship,shipYardMenuGridBag);
            shipYardMenuButtonGroup.add(ship);
        }
    }

    public void createBuildUpgradeMenu() {

        ownPlanetMenu = new JPopupMenu();
        build = new JMenu("Build");
        upgrade = new JMenu("Upgrade");
        JMenuItem buildDeuteriumMine = new JMenuItem("DeuteriumMine");
        JMenuItem buildSolarPlant = new JMenuItem("Solarplant");
        JMenuItem buildMetalMine = new JMenuItem("MetalMine");
        JMenuItem buildShipYard = new JMenuItem("ShipYard");
        build.add(buildDeuteriumMine);
        build.add(buildSolarPlant);
        build.add(buildMetalMine);
        build.add(buildShipYard);
        shipBuild = new JMenuItem("ShipYard");

        buildDeuteriumMine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEngine.buildOnPlanet(new DeuteriumMine());
            }
        });

        buildSolarPlant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEngine.buildOnPlanet(new SolarPowerPlant());
            }
        });

        buildMetalMine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEngine.buildOnPlanet(new MetalMine());
            }
        });

        buildShipYard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEngine.buildOnPlanet(new ShipYard());
            }
        });

        shipBuild.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createShipYardPane();
            }
        });
    }

    @Override
    public void paint(Graphics graphics) {

        graphics.drawImage(backgroundImage, 0, 0, 980, 700, null);
        super.paintComponents(graphics);

        for (int i=0;i<gameEngine.planetList.size(); i++) {
            gameEngine.planetList.get(i).draw(graphics);
        }

        gameEngine.getPlayer1().getMotherShip().draw(graphics);
        gameEngine.getPlayer2().getMotherShip().draw(graphics);

        blackHole.draw(graphics);
        asteroidField.draw(graphics);

        drawArea(graphics);

    }

    private void drawArea(Graphics graphics) {
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 10; j++) {
                graphics.drawRect(i * GameGUI.SIZE, j * GameGUI.SIZE, GameGUI.SIZE, GameGUI.SIZE);
            }
        }
    }
    

    public void setGridX(int x) { this.shipYardMenuGridBag.gridx = x; }

    public void setGridY(int y) { this.shipYardMenuGridBag.gridy = y; }

    public JRadioButton getCruiserShip() { return this.cruiserShip; }

    public JRadioButton getColonialShip() { return this.colonialShip; }

    public JRadioButton getTransportShip() { return this.transportShip; }

    public JRadioButton getBattleShip() { return this.battleShip; }

    public JRadioButton getHuntingShip() { return this.huntingShip; }
}
