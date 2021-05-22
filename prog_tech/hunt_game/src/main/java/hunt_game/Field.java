package hunt_game;

public class Field {

    private Character character;
    private String image;

    /**
     *
     */
    public Field() {
        character = null;
        image = ImageLoader.getInstance().FLOOR;
    }

    /**
     *
     * @return Character
     */
    public Character getCharacter() {
        return this.character;
    }

    /**
     *
     * @param character
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    /**
     *
     * @return String
     */
    public String getImage() {
        return this.image;
    }

    /**
     *
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }
}
