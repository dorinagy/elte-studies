package game;

import helper.Wrapper;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class GameGUI extends JPanel implements GameListener {

    final static int X_SHIFT = 130; // a bal oldali sáv miatt
    final static int Y_SHIFT = 50;  // a fenti sáv miatt
    final static int SPRITE_SHIFT = 20;

    private int time;
    private Timer counter;
    private Timer moveListener;
    private Timer battleListener;
    private JPanel timer;
    private JLabel statusLabel;
    private JPanel mainPanel;
    public GamePanel gamePanel;
    
    //game stats
    private JPanel playerPanel;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel roundInfo;
    private JLabel player1Name;
    private JLabel player1ActionPoints;
    private JLabel player2Name;
    private JLabel player2ActionPoints;
    private JButton switchActivePlayerButton;
    
    //planet resource info
    private JLabel metalMineLabel;
    private String metalMineImageIcon = "spritePics/MetalMine.png";
    private JLabel metalMineDataLabel;

    private JLabel shipYardLabel;
    private String shipYardImageIcon = "spritePics/ShipYard.png";
    private JLabel shipYardDataLabel;

    private JLabel solarPowerPlantLabel;
    private String solarPowerPlantImageIcon = "spritePics/SolarPowerPlant.png";
    private JLabel solarPowerPlantDataLabel;

    private JLabel deuteriumMineLabel;
    private String deuteriumMineImageIcon = "spritePics/DeuteriumMine.png";
    private JLabel deuteriumMineDataLabel;

    //battle results info
    private JLabel attackerFleetLabel;
    private JLabel attackerFleetDataLabel;

    private JLabel defenderFleetLabel;
    private JLabel defenderFleetDataLabel;

    private List<JLabel> planetLabels = new ArrayList<>();
    private List<JLabel> solarPowerLabels = new ArrayList<>();
    private List<JLabel> metalLabels = new ArrayList<>();
    private List<JLabel> deuteriumLabels = new ArrayList<>();

    private List<JLabel> resourcesLabels = new ArrayList<>();
    private JLabel actionLabel = new JLabel();
    
    //constants
    String playerImageIcon = "backgroundPics/playerImage.jpg";
    final static int SIZE = 70;
    Color green = new Color(0,255,0);
    Color grey = new Color(50,50,50);

    private JFrame frame;
    private GameEngine gameEngine;
          
    public GameGUI() {
        this.frame = new JFrame("GAME");
        this.createGUI();
        this.frame.setVisible(true);
        
        this.setGameListener();
        
        frame.pack();
    }
    
    private void setGameListener() {
        gameEngine.setListener(this);
    }

    /**
     * Create Game General User Interface
     * 
     * @see JFrame
     */
    private void createGUI() {

        frame.setSize(new Dimension(1362, 770));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.createMenu();

        addGamePanel();
        addMainPanel();
        addPlanetPanel();
        addPlayerStats();
        
        createTimer();
        
        frame.addMouseListener(gameEngine);
    }
    
    /**
     * Create Game Menu
     * 
     * @see JMenu
     * @see JMenuBar
     * @see ActionEvent
     */
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenu gameMenu = new JMenu("Menü");
        menuBar.add(gameMenu);
        
        JMenuItem newGameMenuItem = new JMenuItem("Új játék");
        gameMenu.add(newGameMenuItem);
        
        newGameMenuItem.addActionListener((ActionEvent ae) -> {
            startNewGame();
        });
        
        JMenuItem exitMenuItem = new JMenuItem("Kilépés");
        gameMenu.add(exitMenuItem);
        
        exitMenuItem.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });
    }
    
    /**
     * Add buttons
     * 
     * @see JPanel
     * @see Dimension
     * @see BorderLayout
     * @see ActionListener
     * @see ActionEvent
     * @see JButton
     */
    private void addMainPanel() {
        this.mainPanel = new JPanel();
        this.mainPanel.setPreferredSize(new Dimension(130,700));
        this.frame.add(this.mainPanel, BorderLayout.WEST);
    }

    private void addGamePanel() {
        gameEngine = new GameEngine(this);
        gamePanel = new GamePanel(this, gameEngine);
        frame.add(gamePanel);
        
    }

    /**
     * Add planet resources panel
     * 
     * @see JPanel
     */

    private void addPlanetPanel() {

        //show icons of resources on the left hand side panel
        ImageIcon metalMineImage = new ImageIcon(ClassLoader.getSystemResource(metalMineImageIcon));
        metalMineLabel = new JLabel(metalMineImage);
        metalMineLabel.setIcon(new ImageIcon(metalMineImage.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        mainPanel.add(metalMineLabel);

        metalMineDataLabel = new JLabel(Wrapper.wrapText("Metalmine data:<br><hr>"));
        mainPanel.add(metalMineDataLabel);

        ImageIcon shipYardImage = new ImageIcon(ClassLoader.getSystemResource(shipYardImageIcon));
        shipYardLabel = new JLabel(shipYardImage);
        shipYardLabel.setIcon(new ImageIcon(shipYardImage.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        mainPanel.add(shipYardLabel);

        shipYardDataLabel = new JLabel(Wrapper.wrapText("Shipyard data:<br><hr>"));
        mainPanel.add(shipYardDataLabel);

        ImageIcon solarPowerPlantImage = new ImageIcon(ClassLoader.getSystemResource(solarPowerPlantImageIcon));
        solarPowerPlantLabel = new JLabel(solarPowerPlantImage);
        solarPowerPlantLabel.setIcon(new ImageIcon(solarPowerPlantImage.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        mainPanel.add(solarPowerPlantLabel);

        solarPowerPlantDataLabel = new JLabel(Wrapper.wrapText("Solarplant data:<br><hr>"));
        mainPanel.add(solarPowerPlantDataLabel);

        ImageIcon deuteriumMineImage = new ImageIcon(ClassLoader.getSystemResource(deuteriumMineImageIcon));
        deuteriumMineLabel = new JLabel(deuteriumMineImage);
        deuteriumMineLabel.setIcon(new ImageIcon(deuteriumMineImage.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        mainPanel.add(deuteriumMineLabel);

        deuteriumMineDataLabel = new JLabel(Wrapper.wrapText("Deuterium data:<br> <hr>"));
        mainPanel.add(deuteriumMineDataLabel);

        Planet planet = gameEngine.getActivePlanet();

        if (planet != null) {
            planetLabels.add(new JLabel(Wrapper.wrapText("Planet " + planet.getId())));
            mainPanel.add(planetLabels.get(0));
            solarPowerLabels.add(new JLabel("Solar Power: " + planet.getSolarPower()));
            mainPanel.add(solarPowerLabels.get(0));
            metalLabels.add(new JLabel("Metal: " + planet.getMetal()));
            mainPanel.add(metalLabels.get(0));
            deuteriumLabels.add(new JLabel("Deuterium: " + planet.getDeuterium()));
            mainPanel.add(deuteriumLabels.get(0));
        } else {
            planetLabels.add(new JLabel());
            mainPanel.add(planetLabels.get(0));
            solarPowerLabels.add(new JLabel());
            mainPanel.add(solarPowerLabels.get(0));
            metalLabels.add(new JLabel());
            mainPanel.add(metalLabels.get(0));
            deuteriumLabels.add(new JLabel());
            mainPanel.add(deuteriumLabels.get(0));
        }

        //panel for displaying actions
        actionLabel.add(new JLabel("action happened"));
        mainPanel.add(actionLabel);
    }

    public JLabel getAttackerFleetDataLabel() {
        return attackerFleetDataLabel;
    }

    public void setAttackerFleetDataLabel(JLabel attackerFleetDataLabel) {
        this.attackerFleetDataLabel = attackerFleetDataLabel;
    }

    public JLabel getDefenderFleetDataLabel() {
        return defenderFleetDataLabel;
    }

    public void setDefenderFleetDataLabel(JLabel defenderFleetDataLabel) {
        this.defenderFleetDataLabel = defenderFleetDataLabel;
    }

    /**
     * Add player information
     * 
     * @see JPanel
     * @see Dimension
     * @see BorderLayout
     * @see JLabel
     * @see ImageIcon
     * @see ClassLoader
     */
    private void addPlayerStats() {
        //create panel on the right
        playerPanel = new JPanel();
        playerPanel.setPreferredSize(new Dimension(250,700));
        frame.add(this.playerPanel, BorderLayout.EAST);
        
        //round text
        roundInfo = new JLabel("Round " + gameEngine.getRound());
        playerPanel.add(roundInfo);
        playerPanel.add(Box.createRigidArea(new Dimension(10, 50)));
        
        //separator
        //playerPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        
        // player1
        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource(playerImageIcon));
        player1Label = new JLabel(image);
        player1Label.setIcon(new ImageIcon(image.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        player1Label.setBorder(BorderFactory.createLineBorder(green));
        playerPanel.add(player1Label);
       
        player1Name = new JLabel("Player" + gameEngine.getPlayer1().getId());
        player1Name.setHorizontalAlignment(SwingConstants.LEFT);
        player1ActionPoints = new JLabel("Action points: " + gameEngine.getPlayer1().getActionPoints());
        playerPanel.add(player1Name);
        playerPanel.add(player1ActionPoints);
        playerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
       
        //player2
        player2Label = new JLabel(image);
        player2Label.setIcon(new ImageIcon(image.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        player2Label.setBorder(BorderFactory.createLineBorder(grey));
        playerPanel.add(player2Label);
       
        player2Name = new JLabel("Player" + gameEngine.getPlayer2().getId());
        player2ActionPoints = new JLabel("Action points: " + gameEngine.getPlayer2().getActionPoints());
        playerPanel.add(player2Name);
        playerPanel.add(player2ActionPoints);
        playerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        
        //button to finish actions
        switchActivePlayerButton = new JButton("Finish my round");
        playerPanel.add(switchActivePlayerButton);
        this.switchActivePlayerButton.setPreferredSize(new Dimension(100,100));
        this.switchActivePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEngine.switchActivePlayerButtonPressed();
                changeBorderColor();
                changeRoundText();
                refreshSolarPower();
                refreshMetal();
                refreshDeuterium();
                
            }
        });

        defenderFleetLabel= new JLabel(Wrapper.wrapText("Defending fleet data: <hr>"));
        playerPanel.add(defenderFleetLabel);
        defenderFleetDataLabel = new JLabel(" ");
        playerPanel.add(defenderFleetDataLabel);

        BoxLayout boxLayout = new BoxLayout(playerPanel, BoxLayout.Y_AXIS);
        playerPanel.setLayout(boxLayout);

        attackerFleetLabel = new JLabel(Wrapper.wrapText("Attacking fleet data: <hr>"));
        playerPanel.add(attackerFleetLabel);
        attackerFleetDataLabel = new JLabel(" ");
        playerPanel.add(attackerFleetDataLabel);

    }
    
    /**
     * Change border color
     * 
     */
    private void changeBorderColor() {
        if (gameEngine.getActivePlayer().getId() == 1) {
            player1Label.setBorder(BorderFactory.createLineBorder(green));
            player2Label.setBorder(BorderFactory.createLineBorder(grey));
        } else {
            player1Label.setBorder(BorderFactory.createLineBorder(grey));
            player2Label.setBorder(BorderFactory.createLineBorder(green));
        }
    }
        
    private void changeRoundText() {
        roundInfo.setText(Wrapper.wrapText("ROUND" + gameEngine.getRound()));
    }
    
    
    /**
     * Create Timer
     * 
     * @see JPanel
     * @see BorderFactory
     * @see Dimension
     * @see BoxLayout
     * @see BorderLayout
     * @see Timer
     * @see ActionListener
     * @see Color
     * @see SwingConstants
     * @see ActionEvent
     */
    private void createTimer() {
        if (this.counter != null) {
            this.counter.stop();
        }
        
        if (this.statusLabel != null) {
            this.frame.remove(this.timer);
            this.frame.remove(this.statusLabel);
        }
        
        this.timer = new JPanel();
        timer.setBorder(BorderFactory.createLineBorder(Color.black));
        this.frame.add(timer, BorderLayout.SOUTH);
        timer.setPreferredSize(new Dimension(frame.getWidth(), 16));
        timer.setLayout(new BoxLayout(timer, BoxLayout.X_AXIS));
        this.statusLabel = new JLabel("00:00");
        this.statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        timer.add(this.statusLabel);
        time = 0;
        
        this.counter = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(gameEngine.isBattle){
                    gameEngine.battleCheck();
                    gameEngine.isBattle = false;
                }

                time++;
                int minute = time/60;
                int second = time-(time/60)*60;
                if (minute == 0 && time < 10) {
                    statusLabel.setText("00:0" + time);
                } else if(minute == 0) {
                    statusLabel.setText("00:" + time);
                } else if(minute > 0 && minute < 10 && second < 10) {
                    statusLabel.setText("0" + minute + ":0" + second);
                } else if(minute > 0 && minute < 10 && second >= 10) {
                    statusLabel.setText("0" + minute + ":" + second);
                } else if(minute >= 10 && second < 10) {
                    statusLabel.setText(minute + ":0" + second);
                } else {
                    statusLabel.setText(minute + ":" + second);
                }
                
                refreshSolarPower();
                refreshMetal();
                refreshDeuterium();
                
            }
        });
        this.counter.start();
        
        this.moveListener = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshPlayerStatus();
            }
        });
        this.moveListener.start();


        this.battleListener = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (
                    GameEngine.refreshBattleList && 
                    GameEngine.batCounter < GameEngine.battleData.size()
                ){
                    attackerFleetDataLabel.setText(Wrapper.wrapText(GameEngine.battleData.get(GameEngine.batCounter)));
                    GameEngine.batCounter++;
                    defenderFleetDataLabel.setText(Wrapper.wrapText(GameEngine.battleData.get(GameEngine.batCounter)));
                    GameEngine.batCounter++;
                } else if (
                    GameEngine.batCounter >= GameEngine.battleData.size()
                ){
                    GameEngine.refreshBattleList = false;
                    GameEngine.batCounter = 0;
                    GameEngine.battleData.clear();
                    boolean isOver = gameEngine.isGameOver();
                    if (isOver) {
                        JOptionPane.showMessageDialog(frame, "Game over. Winner is Player" + gameEngine.getWinner().getId());
                        gameEngine.newGame();
                        attackerFleetDataLabel.setText("");
                        defenderFleetDataLabel.setText("");
                        refreshPlayerStats();
                    }
                }
            }
        });
        this.battleListener.start();
    }
    
    private void refreshPlayerStatus() {
        Player player = gameEngine.getActivePlayer();
        List<Point2D> coordinates = player.getCoordinates();
        if (coordinates.size() > 0) {
            int x = (int)coordinates.get(0).getX();
            int y = (int)coordinates.get(0).getY();
            player.move(x, y);
            gamePanel.repaint();
            player.removeCoordinate(0);
        }
    }
    
    private void refreshSolarPower() {
        Planet planet = gameEngine.getActivePlanet();
        if (planet != null) {
            String text = "Solar Power: " + planet.getSolarPower();
            solarPowerLabels.get(0).setText(text);
        } else {
            solarPowerLabels.get(0).setText("");
        }
    }
    
    private void refreshMetal() {
        Planet planet = gameEngine.getActivePlanet();
        if (planet != null) {
            String text = "Metal: " + planet.getMetal();
            metalLabels.get(0).setText(text);
        } else {
            metalLabels.get(0).setText("");
        }
    }
        
    private void refreshDeuterium(){
        Planet planet = gameEngine.getActivePlanet();
        if (planet != null) {
            String text = "Deuterium: " + planet.getDeuterium();
            deuteriumLabels.get(0).setText(text);
        } else {
            deuteriumLabels.get(0).setText("");
        }
    }

    /**
     * Start New Game
     * 
     */
    private void startNewGame() {
        createGUI();
        frame.pack();
    }

    @Override
    public void refreshPlayerStats() {
        this.addPlayerStats();  
        gamePanel.repaint();
    }

    //setters and getters for labels
    public JLabel getMetalMineDataLabel() {
        return metalMineDataLabel;
    }

    public void setMetalMineDataLabel(JLabel metalMineDataLabel) {
        this.metalMineDataLabel = metalMineDataLabel;
    }

    public JLabel getShipYardDataLabel() {
        return shipYardDataLabel;
    }

    public void setShipYardDataLabel(JLabel shipYardDataLabel) {
        this.shipYardDataLabel = shipYardDataLabel;
    }

    public JLabel getSolarPowerPlantDataLabel() {
        return solarPowerPlantDataLabel;
    }

    public void setSolarPowerPlantDataLabel(JLabel solarPowerPlantDataLabel) {
        this.solarPowerPlantDataLabel = solarPowerPlantDataLabel;
    }

    public JLabel getDeuteriumMineDataLabel() {
        return deuteriumMineDataLabel;
    }

    public void setDeuteriumMineDataLabel(JLabel deuteriumMineDataLabel) {
        this.deuteriumMineDataLabel = deuteriumMineDataLabel;
    }

    public JLabel getActionLabel() {
        return actionLabel;
    }

    public void setActionLabel(JLabel actionLabel) {
        this.actionLabel = actionLabel;
    }
    
    public void createInput() {
        var max = gameEngine.getTransportAmountMaxValue();
        gamePanel.createInputForTransport(max);
    }
            
}
