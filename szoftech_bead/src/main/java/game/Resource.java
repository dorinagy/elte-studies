package game;

public class Resource extends Sprite {

    private int level;

    protected static final int RES_WIDTH = 60;
    protected static final int RES_HEIGHT = 60;

    private int beReady;
    protected static final int BUILD_ROUNDS = 2;
    protected static final int UPDATE_ROUNDS = 3;

    public Resource(int x, int y, int level) {
        super(x, y, RES_WIDTH, RES_HEIGHT);
        this.level = level;
    }

    /**
     * Define initial resource level
     * 
     * @param x
     * @param y
     */
    public Resource(int x, int y){
        super(x, y, RES_WIDTH, RES_HEIGHT);
        this.level = 1;
    }
    
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBeReady() {
        return this.beReady;
    }

    public void setBeReady(int round) { this.beReady = round; }
}
