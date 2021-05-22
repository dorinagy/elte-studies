package hunt_game;

import java.awt.Point;

public class Board {

    private Field[][] board;
    private final int boardSize;
    private final int maxSteps;

    private Character prevPlayer;
    private Character actPlayer;
    private int currentSteps;

    /**
     *
     * @param boardSize
     */
    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.maxSteps = 4 * boardSize;
        this.board = new Field[this.boardSize][this.boardSize];
        for (int i = 0; i < this.boardSize; ++i) {
            for (int j = 0; j < this.boardSize; ++j) {
                this.board[i][j] = new Field();
            }
        }
    }

    /**
     * Returns if maximum number of steps is reached.
     * 
     * @param x
     * @param y
     * @return boolean
     */
    public boolean isOver() {
        if (this.currentSteps == this.maxSteps) return true;
        
        return false;
    }

    /**
     *
     * @param x
     * @param y
     * @return Field
     */
    public Field get(int x, int y) {
        return this.board[x][y];
    }

    /**
     *
     * @param point
     * @return Field
     */
    public Field get(Point point) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        return get(x, y);
    }

    /**
     *
     * @return boardSize
     */
    public int getBoardSize() {
        return this.boardSize;
    }

    /**
     *
     * @return maxSteps
     */
    public int getMaxSteps() {
        return this.maxSteps;
    }

    /**
     *
     * @return currentSteps
     */
    public int getCurrentSteps() {
        return this.currentSteps;
    }

    /**
     *
     * @param currentSteps
     */
    public void setCurrentSteps(int currentSteps) {
        this.currentSteps = currentSteps;
    }

    /**
     *
     * @return prevPlayer
     */
    public Character getPrevPlayer() {
        return this.prevPlayer;
    }

    /**
     *
     * @param prevPlayer
     */
    public void setPrevPlayer(Character prevPlayer) {
        this.prevPlayer = prevPlayer;
    }

    /**
     *
     * @return actPlayer
     */
    public Character getActPlayer() {
        return this.actPlayer;
    }

    /**
     *
     * @param actPlayer
     */
    public void setActPlayer(Character actPlayer) {
        this.actPlayer = actPlayer;
    }
}
