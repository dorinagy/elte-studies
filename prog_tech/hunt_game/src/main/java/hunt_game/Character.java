package hunt_game;

public class Character {

    private String name;
    private String image;
    private int posX;
    private int posY;

    /**
     *
     * @param x
     * @param y
     */
    public Character(int x, int y) {
        this.posX = x;
        this.posY = y;
    }
    
    /**
     *
     * @return int
     */
    public int getPosX() {
        return this.posX;
    }

    /**
     *
     * @param x
     */
    public void setPosX(int x) {
        this.posX = x;
    }

    /**
     *
     * @return int
     */
    public int getPosY() {
        return this.posY;
    }

    /**
     *
     * @param y
     */
    public void setPosY(int y) {
        this.posY = y;
    }
    
    /**
     *
     * @return String
     */
    public String getImage() {
        return this.image;
    }

    /**
     *
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }
    
    /**
     *
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Character)) return false;
        
        Character that = (Character)obj;
        
        return this.getImage().equals(that.getImage());
    }
}
