package labyrinth;

public class Hero extends Character {
    
    /**
     * @see ImageLoader
     */
    public Hero() {
        image = ImageLoader.getInstance().HERO_DOWN;
        posX = 0;
        posY = App.TILE_NUM - 1;
    }
    
    public void move(int x, int y) {
      if (!GameArea.isWall(x, y)) {
        posX = x;
        posY = y;
      }
  }
}
