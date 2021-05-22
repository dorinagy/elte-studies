package hunt_game;

public class Monster extends Character {
    
    /**
     *
     * @param x
     * @param y
     */
    public Monster(int x, int y) {
        super(x, y);
        this.setName("monster");
        this.setImage(ImageLoader.getInstance().MONSTER);
    }
}
