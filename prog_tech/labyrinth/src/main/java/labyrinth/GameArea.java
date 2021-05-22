package labyrinth;

import java.awt.Graphics;

public class GameArea {
    
  static int[][] map;
   
  public GameArea() {
    map = generateMap();
  }
  
  // for testing
  public static void setArea(int[][] area) {
    map = area;
  }

  /**
   *
   * @param graphics
   */
  public void drawArea(Graphics graphics) {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        if (
            i < App.hero.posX - 3 || i > App.hero.posX + 3 ||
            j < App.hero.posY - 3 || j > App.hero.posY + 3
        ) {
            Tile newTile = new Tile(i, j);
            newTile.draw(graphics);
        } else {
          if (GameArea.map[j][i] == 1) {
            Wall newWall = new Wall(i, j);
            newWall.draw(graphics);
          } else {
            Floor newFloor = new Floor(i, j);
            newFloor.draw(graphics);
          }
        }  
      }
    }
  }

  /**
   *
   * @param x
   * @param y
   * @return
   */
  public static boolean isWall(int x, int y) {
    return map[y][x] == 1;
  }

  /**
   *
   * @param x
   * @param y
   * @return
   */
  public static boolean isEmpty(int x, int y) {
    if (
      (x == App.hero.posX && y == App.hero.posY) ||
      (App.monster != null && x == App.monster.posX && y == App.monster.posY)
    ) {
      return false;
    }
    return true;
  }

  private int[][] generateMap() {
    MazeGenerator mazeGenerator = new MazeGenerator();
    
    return mazeGenerator.getMaze();
  }
}
