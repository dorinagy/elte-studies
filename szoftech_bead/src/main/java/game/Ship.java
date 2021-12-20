package game;

public class Ship extends Sprite {

    protected static final int WIDTH = 70;
    protected static final int HEIGHT = 35;
    protected static final int BUILD_COST = 10;

    protected int beReady;
    
    // ship attributes
    protected int attack;
    protected int defense;
    protected int velocity;
    protected int vitality;
    protected int metalCost;
    protected int fuelConsumption;

    public Ship(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
        beReady = 0;
    }

    public Ship(int x, int y, int beReady) {
        super(x,y,WIDTH,HEIGHT);
        this.beReady = beReady;
    }

    public int getBeReady() {
        return this.beReady;
    }

    public void setBeReady(int beReady) {
        this.beReady = beReady;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }
    
    public int getFuelConsumption() {
        return this.fuelConsumption;
    }
    
    /**
     * Move ship
     * 
     * @param x
     * @param y
     */
    public void moveShip(int x, int y) {
        this.setX(x);
        this.setY(y);
    }
}


