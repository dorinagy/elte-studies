package labyrinth;

import java.util.Random;

public class Monster extends Character {
    
    private Random random;
    private int direction;
    
    public Monster() {
      int x = (int) (Math.random() * 9);
      int y = (int) (Math.random() * 9);
      while (GameArea.isWall(x, y) || !GameArea.isEmpty(x, y)) {
        x = (int) (Math.random() * 9);
        y = (int) (Math.random() * 9);
      }
      image = ImageLoader.getInstance().MONSTER;
      posX = x;
      posY = y;
      
      random = new Random();
      direction = 0;
    }
    
    public void move() {
      int x;
      int y;
      
      if (direction == 0) {
        direction = random.nextInt((4 - 1) + 1) + 1;
      }
      
      switch (direction) {
        case 1:
          x = this.posX + 1;
          y = this.posY;
          break;
        case 2:
          x = this.posX - 1;
          y = this.posY;
          break;
        case 3:
          x = this.posX;
          y = this.posY + 1;
          break;
        default:
          x = this.posX;
          y = this.posY - 1;
          break;
      };
      
      if (
        x >= 0 && x < App.TILE_NUM && 
        y >= 0 && y < App.TILE_NUM &&
        GameArea.isEmpty(x, y) && !GameArea.isWall(x, y)
      ) {
          this.posX = x;
          this.posY = y;
      } else if (!GameArea.isEmpty(x, y)) {
          // lose - save score
          System.out.println("lose");
          App.end = true;
          App.listener.saveScore();
      } else {
          direction = 0;
          move();
      }
    }
}
