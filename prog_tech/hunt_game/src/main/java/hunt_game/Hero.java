package hunt_game;

public class Hero extends Character {
    
    /**
     *
     * @param x
     * @param y
     */
    public Hero(int x, int y) {
        super(x, y);
        this.setName("hero");
        this.setImage(ImageLoader.getInstance().HERO);
    }
    
    /**
     * Returns if hero can escape.
     * 
     * @param board
     * @param boardSize
     * @return boolean
     */
    public boolean canEscape(Board board, int boardSize) {
        int x = this.getPosX();
        int y = this.getPosY();
        if (
            (x == boardSize-1 || board.get(x+1, y).getCharacter() != null) &&
            (y == boardSize-1 || board.get(x, y+1).getCharacter() != null) &&
            (y == 0 || board.get(x, y-1).getCharacter() != null) &&
            (x == 0 || board.get(x-1, y).getCharacter() != null)
        ) {
            return false;
        }
        return true;
    }
}
