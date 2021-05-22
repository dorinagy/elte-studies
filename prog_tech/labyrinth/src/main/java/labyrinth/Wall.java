package labyrinth;

public class Wall extends Tile {
    
    /**
     *
     * @param x
     * @param y
     * 
     * @see ImageLoader
     */
    public Wall(int x, int y) {
        super(x, y);
        image = ImageLoader.getInstance().WALL;
    }
}
