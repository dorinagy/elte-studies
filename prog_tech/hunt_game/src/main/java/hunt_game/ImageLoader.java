package hunt_game;

public class ImageLoader {
  private static ImageLoader imageLoader;
  public String FLOOR, HERO, MONSTER;

  ImageLoader() {
      FLOOR = "assets/floor.png";
      HERO = "assets/hero_.png";
      MONSTER = "assets/boss_.png";
  }

    /**
     * Returns instance of ImageLoader class.
     * 
     * @return instance
     */
    public static ImageLoader getInstance() {
    if (imageLoader == null) {
      imageLoader = new ImageLoader();
    }
    return imageLoader;
  }
}
