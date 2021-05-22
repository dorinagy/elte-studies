package labyrinth;

public class Floor extends Tile {
 
    /**
     *
     * @param x
     * @param y
     */
    public Floor(int x, int y) {
        super(x, y);
        image = ImageLoader.getInstance().FLOOR;
    }
}
