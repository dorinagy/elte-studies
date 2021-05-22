package labyrinth;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GameObject {
    
    final static int SIZE = 72;
    BufferedImage image;
    int posX, posY;

    public GameObject() {}

    /**
     *
     * @param graphics
     */
    public void draw(Graphics graphics) {
      if (image != null) {
        graphics.drawImage(image, posX * SIZE, posY * SIZE, null);
      }
    }

    /**
     *
     * @param image
     */
    public void setImage(BufferedImage image) {
      this.image = image;
    }
}
