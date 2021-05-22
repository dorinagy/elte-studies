package labyrinth;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageLoader {
  private static ImageLoader imageLoader;
  public BufferedImage FLOOR, WALL, HERO_DOWN, HERO_LEFT, HERO_RIGHT, HERO_UP, MONSTER, TILE;

  ImageLoader() {
    try {
      FLOOR = ImageIO.read(new File("res/floor.png"));
      WALL = ImageIO.read(new File("res/wall.png"));
      HERO_DOWN = ImageIO.read(new File("res/hero-down.png"));
      HERO_LEFT = ImageIO.read(new File("res/hero-left.png"));
      HERO_RIGHT = ImageIO.read(new File("res/hero-right.png"));
      HERO_UP = ImageIO.read(new File("res/hero-up.png"));
      MONSTER = ImageIO.read(new File("res/skeleton.png"));
      TILE = ImageIO.read(new File("res/dark.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

    /**
     *
     * @return ImageLoader
     */
    public static ImageLoader getInstance() {
    if (imageLoader == null) {
      imageLoader = new ImageLoader();
    }
    return imageLoader;
  }
}
