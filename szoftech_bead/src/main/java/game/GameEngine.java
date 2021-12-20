package game;

import game.playBarriers.AsteroidField;
import game.playBarriers.BlackHole;
import game.playPlanets.MainPlanet;
import game.playResources.DeuteriumMine;
import game.playResources.MetalMine;
import game.playResources.ShipYard;
import game.playResources.SolarPowerPlant;
import game.playShips.*;
import helper.Wrapper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class GameEngine implements MouseListener {

    final static int ACTION_POINTS = 100;
    private static final int NEW_TRANSPORT_SHIP_CAPACITY = 20;

    private GameGUI gameGUI;

    private Player player1;
    private Player player2;

    private static GameListener listener;

    public static boolean isBattle = false;
    public static boolean refreshBattleList = false;

    private static Player activePlayer;
    private static Planet activePlanet;
    public static List<Ship> activeFleet;
    public static List<Ship> enemyFleet;
    private static Planet targetPlanet;
    private static TransportShip transportShipSelected;

    public static List<Planet> planetList = new ArrayList<>();
    private static List<List<Ship>> fleetList = new ArrayList<>();

    public static ArrayList<String> battleData = new ArrayList<>();
    public static int batCounter = 0;
    private boolean endOfGame = true;

    private int round = 1;
    Timer timer;

    private JPopupMenu optionsMenu;

    public GameEngine(GameGUI gameGUI) {

        this.gameGUI = gameGUI;

        this.newGame();
    }

    //setters and getters for players
    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    /**
     * Start new game
     * 
     */
    public void newGame() {
        
        planetList.clear();
        fleetList.clear();

        MetalMine metalMine1 = new MetalMine(75, 495, 1);
        MetalMine metalMine2 = new MetalMine(775, 75, 1);
        SolarPowerPlant solarPowerPlant1 = new SolarPowerPlant(145, 565, 1);
        SolarPowerPlant solarPowerPlant2 = new SolarPowerPlant(845, 145, 1);
       
        planetList.add(new MainPlanet(71, 420, 140, 140, 1, 30, solarPowerPlant1 , metalMine1, new DeuteriumMine(70,500, 1)));
        planetList.add(new MainPlanet(702, 0, 140, 140, 1, 30, solarPowerPlant2, metalMine2, new DeuteriumMine(770,50, 1)));

        planetList.add(new Planet(71, 71, 70, 70, 1, 90));
        planetList.add(new Planet(250, 300, 50, 50, 1, 120));
        planetList.add(new Planet(720, 400, 60, 60, 1, 100));
        planetList.add(new Planet(800, 600, 70, 70, 1, 120));

        player1 = new Player(ACTION_POINTS, planetList.get(0), new MotherShip(280, 580));
        player2 = new Player(ACTION_POINTS, planetList.get(1), new MotherShip(630, 90));

        
        planetList.get(0).setOwner(player1);
        planetList.get(1).setOwner(player2);
        
        fleetList.add(player1.getFleet());
        fleetList.add(player2.getFleet());

        activePlayer = player1;
        round = 1;
    }

    /**
     * Set game listener
     * 
     * @param listener
     */
    public void setListener(GameListener listener) {
        GameEngine.listener = listener;
    }

    /**
     * Get round
     * @return int round
     */
    public int getRound() {
        return round;
    }

    public int getNumOfPlanets() {
        return planetList.size();
    }

    public Planet getPlanet(int n) {
        return planetList.get(n);
    }

    public void increaseSolarPowerMetalDeuterium() {
        for (var planet : planetList) {
            planet.setSolarPowerMetalDeuterium();
        }
    }

    /**
     * Increment round
     */
    private void incrementRound() {
        round++;
        this.buildResources();
        this.buildShips();
        this.clearSolarPower();
    }
    
    private void clearSolarPower() {
        for (var planet : planetList) {
            planet.setSolarPower(0);
        }
    }

    /**
     * Switch active player
     */
    public void switchActivePlayerButtonPressed() {
        this.changeActivePlayer();
    }

    /**
     * Change active player
     */
    private void changeActivePlayer() {
        activePlayer.setMadeupgrade(false);
        if (activePlayer.getId() == 1) {
            activePlayer = player2;
        } else {
            activePlayer = player1;
            incrementRound();
            increaseSolarPowerMetalDeuterium();
        }
    }

    /**
     * Get active player
     *
     * @return Player
     */
    public Player getActivePlayer() {
        return activePlayer;
    }

    public Planet getActivePlanet() {
        return activePlanet;
    }

    /**
     * Handle mouse click event
     * <p>
     * set active planet
     *
     * @param e MouseEvent
     * @see MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1) { // kijelölés csak bal egérgombbal választásnál

            // ha új objektumot választunk ki, a többi null lesz
            activePlanet = null;
            activeFleet = null;
            targetPlanet = null;

            activePlanet = getPlanet(e);
            activeFleet = getFleet(e);


            if (activePlanet != null) { // bolygó
                showPlanetResources(activePlanet);

                if (activePlanet.getOwner() == null) { // üres bolygó
                    gameGUI.getActionLabel().setText(Wrapper.wrapText("Empty planet <br> selected!"));
                } else if (activePlayer.isOwnPlanet(activePlanet)) { // saját bolygó
                    gameGUI.getActionLabel().setText(Wrapper.wrapText("Own planet <br> selected!"));
                } else if (!activePlayer.isOwnPlanet(activePlanet)) { // ellenséges bolygó
                    gameGUI.getActionLabel().setText(Wrapper.wrapText("Opponent planet <br> selected!"));
                }

                showPlanetResources(activePlanet);

            } else if (activeFleet != null) { // flotta
                resetResourceData();
                if (activePlayer.isOwnFleet(activeFleet)) { // saját flotta
                    gameGUI.getActionLabel().setText(Wrapper.wrapText("Own fleet <br> selected."));
                } else {
                    // ellenséges flotta ?
                }
            } else { // nincs kiválasztás
                gameGUI.getActionLabel().setText(Wrapper.wrapText("Nothing <br> selected!"));
                resetResourceData();
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) { // action
            if (activePlanet != null) {
                planetAction(e); // bolygó - bolygó
            } else if (activeFleet != null) {
                fleetAction(e); // flotta - bolygó / (flotta - flotta)?
            }
        }
    }

    private void planetAction(MouseEvent e) {

        targetPlanet = getPlanet(e);

        if (targetPlanet != null) {
            if (
                activePlanet.equals(targetPlanet) &&
                activePlayer.isOwnPlanet(activePlanet)
            ) {
                // építkezés / fejlesztés
                buildOrUpgrade(e);
            } else if (
                !activePlanet.equals(targetPlanet) && 
                activePlayer.isOwnPlanet(activePlanet) && 
                activePlayer.isOwnPlanet(targetPlanet)
            )
            {
                gameGUI.getActionLabel().setText(Wrapper.wrapText("Target <br> selected!"));
                // szállítás két bolygó között
                transport(e, targetPlanet);
            }
            else {
                gameGUI.getActionLabel().setText(Wrapper.wrapText("Target not <br> selected!"));
            }
        }
    }

    /**
     * Move action options
     *
     * @param e MouseEvent
     * @see MouseEvent
     */
    private void fleetAction(MouseEvent e) {

        targetPlanet = getPlanet(e);
        List<Ship> targetFleet = getFleet(e);

        if (targetPlanet != null) {
            if (targetPlanet.getOwner() == null) {
                // gyarmatosítás
                this.showPlanetOptions(e, false, targetPlanet);
            } else if (activePlayer.isOwnPlanet(targetPlanet)) {
                // landolás
                this.showPlanetOptions(e, true, targetPlanet);
            } else {
               // támadás
               this.showAttackOptions(e, targetPlanet);
            }
        } else if (targetFleet.size() > 0 && !targetFleet.equals(activeFleet)) {
            this.showBattleOptions(e, targetFleet);
        } else { // mozgás
            showMoveOptions(e);
        }
    }
            
    private void showMoveOptions(MouseEvent e) {

        int x = e.getX() - GameGUI.X_SHIFT;
        int y = e.getY() - GameGUI.Y_SHIFT;
            
        this.optionsMenu = new JPopupMenu();
        JMenu move = new JMenu("Move");
        JMenuItem moveHere = new JMenuItem("Move fleet here");
        move.add(moveHere);

        moveHere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveFleet(x, y);
            }
        });

        this.optionsMenu.add(move);
        this.optionsMenu.show(e.getComponent(), e.getX(), e.getY());
    }
    
    private void moveFleet(int x, int y) {  
        List<Point2D> coordinates = new ArrayList<>();

        int posX = activePlayer.getMotherShip().getX();
        int posY = activePlayer.getMotherShip().getY();
        int routeLength = (int) Math.sqrt((x-posX)*(x-posX) + (y-posY)*(y-posY));
        
        if (!activePlayer.hasEnoughPoints(routeLength)) {
            gameGUI.getActionLabel().setText(Wrapper.wrapText("Not enough <br> action points."));
            return;
        } else if (!activePlayer.hasEnoughFuel(routeLength)) {
            gameGUI.getActionLabel().setText(Wrapper.wrapText("Not enough <br> fuel."));
            return;
        } else if (isBlackHole(x, y) || isAsteroidField(x, y) || isPlanet(x, y)) {
            gameGUI.getActionLabel().setText(Wrapper.wrapText("Cannot move <br> to destination."));
            return;
        }
        
        float ratio = (float)(y - posY) / (float)(x - posX);
        int width = Math.abs(x - posX);

        int currX = posX;
        int currY = posY;
        for (int i=0; i < width; i+=GameGUI.SPRITE_SHIFT) {
            currX = x > posX ? currX+i : currX-i;
            currY = x < posX ? currY - (int)(ratio * i)
                             : currY + (int)(ratio * i);

            if ((posX < x && currX >= x) || (posX > x && currX <= x) ||
                (posY < y && currY >= y) || (posY > y && currY <= y)) {
                break;
            }

            if (isBlackHole(currX, currY) || isAsteroidField(currX, currY) || isPlanet(currX, currY)) {
                gameGUI.getActionLabel().setText(Wrapper.wrapText("Barrier <br> on the way."));
                activePlayer.resetCoordinates();
                return;
            }
            coordinates.add(new Point2D.Double(currX, currY));
        }
        coordinates.add(new Point2D.Double(x, y));

        activePlayer.reduceFuel(routeLength);
        activePlayer.setCoordinates(coordinates);
        gameGUI.getActionLabel().setText(Wrapper.wrapText("Moving <br> was successful."));
        GameEngine.listener.refreshPlayerStats();
    }

    public void battleCheck() {
        enemyFleet = activePlayer.equals(player1) ? player2.getFleet() : player1.getFleet();
        boolean attackerWon = battleAction(activePlayer.getFleet(), enemyFleet);
        
        Player winner = attackerWon 
                            ? activePlayer 
                            : activePlayer.equals(player1) 
                                ? player2
                                : player1;
        
        for (Planet planet : planetList) {
            if (winner != null && winner.getId() != planet.getOwner().getId()) {
                winner.conqeurPlanet(planet);
            }
        }
    }
    

    //one ship attack an other
    private void battleOfTwo(Ship attacker, Ship defender){

        int defLife;
        int attackPower;
        int defendPower;

        int hitValue;

        //battle action
        defLife = defender.getVitality();
        attackPower = attacker.getAttack();
        defendPower = defender.getDefense();

        if(attackPower<=defendPower){
            defender.setDefense(defendPower-attackPower);
            hitValue = 0;
        } else {
            hitValue = attackPower-defendPower;
        }

        defender.setVitality(defLife-hitValue);
    }

    //battle of two fleets
    private boolean battleAction(List<Ship> attackingFleet, List<Ship> defendingFleet){

        boolean attackerWon = false;
        
        boolean battling = true;
        
        while (battling){
            Random rnd = new Random();
            int target;

            StringBuilder attackerInfo = new StringBuilder();
            //generate string data of the fleet
            if(!attackingFleet.isEmpty()) {
                for (int i = 0; i < attackingFleet.size(); i++) {
                    attackerInfo.append(attackingFleet.get(i).getClass().getSimpleName() + " life: " + attackingFleet.get(i).getVitality() + "<br>");
                }
            } else {
                attackerInfo.append("No ship in fleet <br>");
                //TODO remove fleet graphics!
            }

            battleData.add(attackerInfo.toString());

            if (!attackingFleet.isEmpty()) {
                for (int i = 0; i < attackingFleet.size(); i++) {
                    target = rnd.nextInt(defendingFleet.size());

                    battleOfTwo(attackingFleet.get(i), defendingFleet.get(target));
                    if (defendingFleet.get(target).getVitality() <= 0) {
                        defendingFleet.remove(target);
                    }
                }

                StringBuilder defenderInfo = new StringBuilder();
                //generate string data of the fleet
                if(!defendingFleet.isEmpty()) {
                    for (int i = 0; i < defendingFleet.size(); i++) {
                        defenderInfo.append(defendingFleet.get(i).getClass().getSimpleName() + " life: " + defendingFleet.get(i).getVitality() + "<br>");
                    }
                } else{
                    defenderInfo.append("No ship in fleet <br>");
                    //TODO remove fleet graphics!
                }

                battleData.add(defenderInfo.toString());

                if (!defendingFleet.isEmpty()) {
                    for (int i = 0; i < defendingFleet.size(); i++) {
                        target = rnd.nextInt(attackingFleet.size());
                        battleOfTwo(defendingFleet.get(i), attackingFleet.get(target));
                        if (attackingFleet.get(target).getVitality() <= 0) {
                            attackingFleet.remove(target);
                        }
                    }
                } else {
                    battling = false;
                }
            } else {
                battling = false;
            }
        }
        
        if (!attackingFleet.isEmpty()) {
            attackerWon = true;

            StringBuilder attackerResults = new StringBuilder();
            attackerResults.append("Attacking fleet won!");
            battleData.add(attackerResults.toString());

            StringBuilder defenderResults = new StringBuilder();
            defenderResults.append("Defending fleet lost!");
            battleData.add(defenderResults.toString());

        }
        else if (!defendingFleet.isEmpty()) {
            attackerWon = false;

            StringBuilder attackerResults = new StringBuilder();
            attackerResults.append("Attacking fleet lost!");
            battleData.add(attackerResults.toString());

            StringBuilder defenderResults = new StringBuilder();
            defenderResults.append("Defending fleet won!");
            battleData.add(defenderResults.toString());

        }
        refreshBattleList = true;
        endOfGame = false;
        return attackerWon;
    }
    
    /**
     * Reset resource data
     * 
     * @see GameGUI
     */
    private void resetResourceData(){
        gameGUI.getMetalMineDataLabel().setText(Wrapper.wrapText("Metalmine data:<br><hr>"));
        gameGUI.getShipYardDataLabel().setText(Wrapper.wrapText("Shipyard data:<br><hr>"));
        gameGUI.getSolarPowerPlantDataLabel().setText(Wrapper.wrapText("Solarplant data:<br><hr>"));
        gameGUI.getDeuteriumMineDataLabel().setText(Wrapper.wrapText("Deuterium data:<br><hr>"));
    }

    /**
     * Build or upgrade resources on planet
     * 
     * set menu functions
     * 
     * @param MouseEvent
     * 
     * @see JMenu
     * @see JMenuItem
     * @see ActionListener
     * @see ActionEvent
     */
    private void buildOrUpgrade(MouseEvent e) {
        // építés, fejlesztés
        gameGUI.gamePanel.createBuildUpgradeMenu();

        for (int i=0; i<activePlanet.getPlanetResources().size(); i++) {
            Resource upgradable = activePlanet.getPlanetResources().get(i);
            JMenuItem curr = this.getUpgradableResources().get(i);
            curr.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    upgradeOnPlanet(upgradable);
                }
            });
            gameGUI.gamePanel.upgrade.add(curr);
        }

        GamePanel.ownPlanetMenu.add(gameGUI.gamePanel.build);
        GamePanel.ownPlanetMenu.add(gameGUI.gamePanel.upgrade);
        if(getActivePlanet().hasShipYard()) { GamePanel.ownPlanetMenu.add(gameGUI.gamePanel.shipBuild); }
        GamePanel.ownPlanetMenu.show(e.getComponent(), e.getX(), e.getY());
    }
    
    /**
     * Show planet attack options available
     * 
     * @param MouseEvent e
     */
    private void showAttackOptions(MouseEvent e, Planet target) {
        this.optionsMenu = new JPopupMenu();
        JMenu attack = new JMenu("Attack");
        
        JMenuItem attackPlanet = new JMenuItem("Attack planet");
        attack.add(attackPlanet);
        
        attackPlanet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // csata csak ha van vadász/cirkáló/csata hajó vagy mother ship
                if (activePlayer.hasBattleShip()) {
                    enemyFleet = activePlayer.equals(player1) ? player2.getFleet() : player1.getFleet();
                    boolean attackerWon = battleAction(activePlayer.getFleet(), enemyFleet);
                    
                    if (attackerWon) {
                        activePlayer.conqeurPlanet(target);
                    }
                } else {
                    gameGUI.getActionLabel().setText(Wrapper.wrapText("Attack <br> not possible!"));
                }
            }
        });
        
        this.optionsMenu.add(attack);
        this.optionsMenu.show(e.getComponent(), e.getX(), e.getY());
    }
    
    /**
     * Show planet attack options available
     * 
     * @param MouseEvent e
     * @param target
     */
    private void showBattleOptions(MouseEvent e, List<Ship> target) {
        this.optionsMenu = new JPopupMenu();
        JMenu attack = new JMenu("Battle");
        
        JMenuItem attackPlanet = new JMenuItem("Attack fleet");
        attack.add(attackPlanet);
        
        attackPlanet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // csata csak ha van vadász/cirkáló/csata hajó vagy mother ship
                if (true) {
                    enemyFleet = activePlayer.equals(player1) ? player2.getFleet() : player1.getFleet();
                    isBattle = true;
                } else {
                    gameGUI.getActionLabel().setText(Wrapper.wrapText("Attack <br> not possible!"));
                }
            }
        });
        this.optionsMenu.add(attack);
        this.optionsMenu.show(e.getComponent(), e.getX(), e.getY());
    }
    
    /**
     * Show conquer options available
     * 
     * @param MouseEvent e
     * @param boolean isOwn
     */
    private void showPlanetOptions(MouseEvent e, boolean isOwn, Planet planet) {
        this.optionsMenu = new JPopupMenu();
        
        JMenu land = new JMenu("Land");
        
        if (!isOwn) {
            JMenu colonize = new JMenu("Colonize");
            
            JMenuItem colonizePlanet = new JMenuItem("Colonize planet");
            colonize.add(colonizePlanet);
            
            colonizePlanet.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!activePlayer.hasColonialShip()) {
                        gameGUI.getActionLabel().setText(Wrapper.wrapText("No colonialship <br> in fleet."));
                        return;
                    }
                    activePlayer.colonizePlanet(planet);
                    showPlanetResources(planet);
                    gameGUI.getActionLabel().setText(Wrapper.wrapText("Planet <br> colonizeed!"));
                }
            });
            
            this.optionsMenu.add(colonize);
        }

        JMenuItem landOnPlanet = new JMenuItem("Land on planet");
        land.add(landOnPlanet);
        
        landOnPlanet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!activePlayer.isOwnPlanet(planet)) {
                    gameGUI.getActionLabel().setText(Wrapper.wrapText("Not own <br> planet."));
                    return;
                } else if (!activePlayer.hasColonialShip()) {
                    gameGUI.getActionLabel().setText(Wrapper.wrapText("No colonialship - <br> cannot land."));
                    return;
                }
                activePlayer.move(planet.getX()-gameGUI.SPRITE_SHIFT, planet.getY()-gameGUI.SPRITE_SHIFT);
                showPlanetResources(planet);
                gameGUI.gamePanel.repaint();
                gameGUI.getActionLabel().setText(Wrapper.wrapText("Landing <br> was successful."));
            }
        });

        this.optionsMenu.add(land);
        this.optionsMenu.show(e.getComponent(), e.getX(), e.getY());
    }
    
    /**
     * Show transport menu
     * 
     * @param e
     * @param planetTo
     */
    private void transport(MouseEvent e, Planet planetTo) {
        this.optionsMenu = new JPopupMenu();
        JMenu ship = new JMenu("Transport");
        JMenuItem shipToPlanet = new JMenuItem("Transport resources");
        
        shipToPlanet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransportShip transportShip= activePlayer.getTransportShip();
               transportShipSelected = transportShip;

                if (transportShip == null) {
                    gameGUI.getActionLabel().setText(Wrapper.wrapText("Transport is <br> not possible."));
                } else {
                    gameGUI.createInput();
                    
                }
            }
        });

        ship.add(shipToPlanet);
        this.optionsMenu.add(ship);
        
        this.optionsMenu.show(e.getComponent(), e.getX(), e.getY());
    }
    
    public void setTransportAmountFromInput(int value) {
        activePlayer.transportResources(activePlanet, targetPlanet, transportShipSelected, value, this);
    }
    
    public int getTransportAmountMaxValue() {
        int planetRestriction = activePlanet.getMetal();
        int shipRestriction = activePlayer.getTransportShip().getCapacity();
        if(planetRestriction < shipRestriction) {
            return planetRestriction;
        }
        else {
            return shipRestriction;
        }
    }

    /**
     * Get planet
     */
    private Planet getPlanet(MouseEvent e) {
        
        int x = e.getX()-GameGUI.X_SHIFT;
        int y = e.getY()-GameGUI.Y_SHIFT;

        Planet planet = null;
        for (int i=0; i<planetList.size(); i++) {
            Planet current = planetList.get(i);
            if (
                (x >= current.getX() && x <= (current.getX()+current.getWidth())) &&
                (y >= current.getY() && y <= (current.getY()+current.getHeight()))
            ) {
                planet = planetList.get(i);
            }
        }

        return planet;
    }

    private List<Ship> getFleet(MouseEvent e) {

        List<Ship> retFleet = new ArrayList<>();

        int x = e.getX()-GameGUI.X_SHIFT;
        int y = e.getY()-GameGUI.Y_SHIFT;

        for (int i=0; i<fleetList.size(); i++) {

            List<Ship> fleet = fleetList.get(i);

            Ship ship = fleet.get(0);

            if (x >= ship.getX() && x <= (ship.getX()+ship.getWidth()) &&
                y >= ship.getY() && y <= (ship.getY()+ship.getHeight())
            ) {
                retFleet = fleet;
            }
        }
        return retFleet;
    }

    /**
     * Get resource level
     */
    private String getResourceLevel(List<Resource> resList){
        if (!resList.isEmpty()){
            if(resList.size() == 1) {
                return ("Level: " + String.valueOf(resList.get(0).getLevel()) + ((resList.get(0).getBeReady() != 0) ? "(" + String.valueOf(resList.get(0).getBeReady()-this.round) + ")" : ""));
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i=0; i<resList.size(); i++) {
                    sb.append("Level: ").append(String.valueOf(resList.get(i).getLevel())).append((resList.get(i).getBeReady() != 0) ?"(" + String.valueOf(resList.get(i).getBeReady()-this.round) + ")" : "").append("<br>");
                }
                return sb.toString();
            }
        } else {
            return "Not built yet";
        }
    }
    
    /**
     * Show planet resources
     */
    private void showPlanetResources(Planet selected) {
        gameGUI.getMetalMineDataLabel().setText(Wrapper.wrapText("Metalmine data:<br>" + getResourceLevel(selected.getMetalMines())));
        gameGUI.getShipYardDataLabel().setText(Wrapper.wrapText("Shipyard data:<br>" + getResourceLevel(selected.getShipYards())));
        gameGUI.getSolarPowerPlantDataLabel().setText(Wrapper.wrapText("Solarplant data:<br>" + getResourceLevel(selected.getSolarPowerPlants())));
        gameGUI.getDeuteriumMineDataLabel().setText(Wrapper.wrapText("Deuterium data:<br>" + getResourceLevel(selected.getDeuteriumMines())));
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    /**
     * Build on planet
     * @param res
     */
    public boolean buildOnPlanet(Resource res) {
        if (activePlayer.getMadeupdate()) {
            gameGUI.getActionLabel().setText(Wrapper.wrapText("No more action <br> in this round!"));
            return false;
        } else {
            if(!hasResourceOnPlanet(res)) {
                if (activePlanet.getUsedSize() + Planet.getBuildSizeCost() <= Planet.getMaxPlanetSize()) {
                    activePlayer.addPlayerResource(res);
                    activePlanet.addPlanetResource(res);
                    res.setBeReady(this.round + Resource.BUILD_ROUNDS);
                    showPlanetResources(activePlanet);
                    gameGUI.getActionLabel().setText(Wrapper.wrapText(res.getClass().getSimpleName() + "<br> is built <br> to main planet."));
                    activePlayer.setMadeupgrade(true);
                    activePlanet.addBuildCost();
                    return true;
                } else {
                    gameGUI.getActionLabel().setText(Wrapper.wrapText("No more space <br> left on planet."));
                    return false;
                }
            } else {
                gameGUI.getActionLabel().setText(Wrapper.wrapText(res.getClass().getSimpleName() + " has<br>been already<br>built."));
                return false;
            }
        }
    }

    public boolean hasResourceOnPlanet(Resource res) {
        return (res instanceof DeuteriumMine && !activePlanet.getDeuteriumMines().isEmpty() ||
                res instanceof MetalMine && !activePlanet.getMetalMines().isEmpty() ||
                res instanceof SolarPowerPlant && !activePlanet.getSolarPowerPlants().isEmpty() ||
                res instanceof ShipYard && !activePlanet.getShipYards().isEmpty());
    }

    /**
     * Upgrade on planet
     */
    private boolean upgradeOnPlanet(Resource res) {

        if (activePlayer.getMadeupdate()) {
            gameGUI.getActionLabel().setText(Wrapper.wrapText("No more action <br> in this round!"));
            return false;
        } else {
            if(activePlanet.getUsedSize()+Planet.getUpgradeSizeCost() <= Planet.getMaxPlanetSize()) {
                if (res.getBeReady() == 0) {
                    res.setBeReady(this.round + Resource.UPDATE_ROUNDS);
                    showPlanetResources(activePlanet);
                    gameGUI.getActionLabel().setText(Wrapper.wrapText(res.getClass().getSimpleName() + "<br> is upgraded <br> to level " + res.getLevel()));
                    activePlayer.setMadeupgrade(true);
                    activePlanet.addUpgradeCost();
                    return true;
                } else {
                    gameGUI.getActionLabel().setText(Wrapper.wrapText("This building is <br> under construction."));
                    return false;
                }
            } else {
                gameGUI.getActionLabel().setText(Wrapper.wrapText("No more space <br> left on planet."));
                return false;
            }
        }
    }
    
    /**
     * Get upgradeable resources
     */
    private List<JMenuItem> getUpgradableResources() {
        List<JMenuItem> ret = new ArrayList<>();
        for (int i=0; i<activePlanet.getPlanetResources().size(); i++) {
            Resource currentRes = activePlanet.getPlanetResources().get(i);
            ret.add(new JMenuItem(currentRes.getClass().getSimpleName() + ", level: " + currentRes.getLevel()));
        }
        return ret;
    }

    private void buildResources() {
        for(int i=0; i<planetList.size(); i++) {
            List<Resource> planetRes = planetList.get(i).getResources();
            for(int j=0; j<planetRes.size(); j++) {
                if(planetRes.get(j).getBeReady() != 0) {
                    if(this.round == planetRes.get(j).getBeReady()) {
                        planetRes.get(j).setBeReady(0);
                        planetRes.get(j).setLevel(planetRes.get(j).getLevel()+1);
                    }
                }
            }

        }
    }

    private void buildShips() {
        List<Ship> fleetLst = player1.getFleetWithBeReady();
        for(int j=0; j<fleetLst.size(); j++) {
            if(fleetLst.get(j).getBeReady() != 0) {
                if(this.round == fleetLst.get(j).getBeReady()) {
                    fleetLst.get(j).setBeReady(0);
                }
            }
        }
        fleetLst = player2.getFleetWithBeReady();
        for(int j=0; j<fleetLst.size(); j++) {
            if(fleetLst.get(j).getBeReady() != 0) {
                if(this.round == fleetLst.get(j).getBeReady()) {
                    fleetLst.get(j).setBeReady(0);
                }
            }
        }
        refreshFleetList();
    }

    public void buildShip() {
        if(gameGUI.gamePanel.getTransportShip().isSelected()) {
            if(getActivePlanet().getMetal() < Ship.BUILD_COST) { return; }
            int beReady = round + calcBeReady();
            getActivePlayer().addShip(new TransportShip(0,0,NEW_TRANSPORT_SHIP_CAPACITY, beReady));
            getActivePlanet().useMetal(Ship.BUILD_COST);
        } else if(gameGUI.gamePanel.getHuntingShip().isSelected()) {
            if(getActivePlanet().getMetal() < Ship.BUILD_COST*2) { return; }
            int beReady = round + calcBeReady();
            getActivePlayer().addShip(new HuntingShip(0,0,beReady));
            getActivePlanet().useMetal(Ship.BUILD_COST*2);
        } else if(gameGUI.gamePanel.getCruiserShip().isSelected()) {
            if(getActivePlanet().getMetal() < Ship.BUILD_COST*3) { return; }
            int beReady = round + calcBeReady();
            getActivePlayer().addShip(new CruiserShip(0,0,beReady));
            getActivePlanet().useMetal(Ship.BUILD_COST*3);
        } else if(gameGUI.gamePanel.getBattleShip().isSelected()) {
            if(getActivePlanet().getMetal() < Ship.BUILD_COST*4) { return; }
            int beReady = round + calcBeReady();
            getActivePlayer().addShip(new BattleShip(0,0,beReady));
            getActivePlanet().useMetal(Ship.BUILD_COST*4);
        } else if(gameGUI.gamePanel.getColonialShip().isSelected()) {
            if(getActivePlanet().getMetal() < Ship.BUILD_COST*5) { return; }
            int beReady = round + calcBeReady();
            getActivePlayer().addShip(new ColonialShip(0,0,beReady));
            getActivePlanet().useMetal(Ship.BUILD_COST*5);
        }
    }

    public void refreshFleetList() {
        fleetList = new ArrayList<>();
        fleetList.add(player1.getFleet());
        fleetList.add(player2.getFleet());
    }

    public int calcBeReady() {
        int ret = 0;
        if(activePlanet.getShipYardLevel() <= 5) {
            ret += 3;
        } else {
            if(activePlanet.getShipYardLevel() < 8) {
                ret += (8 - activePlanet.getShipYardLevel());
            } else ret += 1;
        }
        return ret;
    }
    
    public boolean isGameOver() {
     if (
            !endOfGame &&
            checkGameOver() && 
            getWinner() != null
        ) {
           endOfGame = true;
           return true;
        }
     return false;
    }

    /**
     * Check if game is over
     *
     * @return boolean
     */
    public boolean checkGameOver() {
        int player1owned = 0;
        int player2owned = 0;
        for (Planet planet : planetList) {
            if (planet.getOwner().getId() == 1) {
                player1owned++;
            } else if (planet.getOwner().getId() == 2) {
                player2owned++;
            }

            if (player1owned == 0 || player2owned == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get winner
     *
     * @return Player
     */
    public Player getWinner() {
        int player1owned = 0;
        int player2owned = 0;
        for (Planet planet : planetList) {
            if (planet.getOwner() == null){
                return null;
            } else if (planet.getOwner().getId() == 1) {
                player1owned++;
            } else if (planet.getOwner().getId()==2){
                player2owned++;
            }
        }

        if (player1owned == 0) return player2;
        if (player2owned == 0) return player1;

        return null;
    }

    private boolean isBlackHole(int x, int y) {

        BlackHole blackHole = gameGUI.gamePanel.blackHole;

        if (
            x >= blackHole.getX()-gameGUI.SPRITE_SHIFT && x <= blackHole.getX()+gameGUI.SPRITE_SHIFT*2 &&
            y >= blackHole.getY()-gameGUI.SPRITE_SHIFT && y <= blackHole.getY()+gameGUI.SPRITE_SHIFT*2
        ) { // black hole
            return true;
        }
        return false;
    }

    private boolean isAsteroidField(int x, int y) {
        AsteroidField asteroidField = gameGUI.gamePanel.asteroidField;
        if (
            x >= asteroidField.getX()-gameGUI.SPRITE_SHIFT && x <= asteroidField.getX()+gameGUI.SPRITE_SHIFT*2 &&
            y >= asteroidField.getY()-gameGUI.SPRITE_SHIFT && y <= asteroidField.getY()+gameGUI.SPRITE_SHIFT*2
        ) { // asteroid field
            return true;
        }
        return false;
    }

    private boolean isPlanet(int x, int y) {
        for (int i=0; i<planetList.size(); i++) {
            Planet current = planetList.get(i);
            if (
                (x >= current.getX() && x <= (current.getX()+current.getWidth())) &&
                (y >= current.getY() && y <= (current.getY()+current.getHeight()))
            ) {
                return true;
            }
        }
        return false;
    }
}
