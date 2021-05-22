package labyrinth;

public class Tile extends GameObject {
    
    public Tile(int x, int y) {
        image = ImageLoader.getInstance().TILE;
        this.posX = x;
        this.posY = y;
    }
}
