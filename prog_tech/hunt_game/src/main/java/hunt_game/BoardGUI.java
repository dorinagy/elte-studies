package hunt_game;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BoardGUI {

    private Hero hero;
    private final Board board;
    private final JPanel boardPanel;
    private final JButton[][] buttons;
    private final JLabel counterLabel;
    
    private static GameListener listener;
    
    /**
     *
     * @param boardSize
     */
    public BoardGUI(int boardSize) {
        board = new Board(boardSize);
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(board.getBoardSize(), board.getBoardSize()));
        buttons = new JButton[board.getBoardSize()][board.getBoardSize()];
        
        this.initializePlayers(boardSize);

        counterLabel = new JLabel("");
        counterLabel.setHorizontalAlignment(JLabel.RIGHT);
        counterLabel.setText("0/" + board.getMaxSteps() + " steps");
    }
    
    private void initializePlayers(int boardSize) {
        for (int i = 0; i < board.getBoardSize(); ++i) {
            for (int j = 0; j < board.getBoardSize(); ++j) {

                JButton button = new JButton();
                Field field = board.get(i, j);
                
                if (
                    (i == 0 && j == 0) ||
                    (i == 0 && j == boardSize-1) ||
                    (i == boardSize-1 && j == 0) ||
                    (i == boardSize-1 && j == boardSize-1)
                ) {
                    Character monster = new Monster(i, j);
                    field.setCharacter(monster);
                    button.setIcon(new ImageIcon(monster.getImage()));
                } else if (i == boardSize/2 && j == boardSize/2) {
                    hero = new Hero(i, j);
                    field.setCharacter(hero);
                    button.setIcon(new ImageIcon(hero.getImage()));
                } else {
                    button.setIcon(new ImageIcon(ImageLoader.getInstance().FLOOR));
                }

                button.addActionListener(new ButtonListener(i, j));
                button.setPreferredSize(new Dimension(60, 57));
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }
    }
    
    public void setListener(GameListener listener) {
        BoardGUI.listener = listener;
    }

    /**
     * Refresh button image.
     * 
     * @param x
     * @param y
     * @param image
     */
    public void refresh(int x, int y, String image) {
        JButton button = buttons[x][y];
        button.setIcon(new ImageIcon(image));
    }

    /**
     *
     * @return JPanel
     */
    public JPanel getBoardPanel() {
        return boardPanel;
    }

    class ButtonListener implements ActionListener {

        private int x, y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Field field = board.get(x, y);
            Character prevPlayer = board.getPrevPlayer();
            Character actPlayer = board.getActPlayer();
            
            if (
                field.getCharacter() == null &&
                actPlayer != null &&
                !actPlayer.equals(prevPlayer)
            ) {
                
                if (
                    (Math.abs(x - actPlayer.getPosX()) == 1 && Math.abs(y - actPlayer.getPosY()) == 0) ||
                    (Math.abs(x - actPlayer.getPosX()) == 0 && Math.abs(y - actPlayer.getPosY()) == 1)
                ) {

                    if (actPlayer instanceof Monster) {
                        board.setCurrentSteps(board.getCurrentSteps()+1);
                        counterLabel.setText(board.getCurrentSteps() + "/" + board.getMaxSteps() + " steps");
                    }

                    refresh(x, y, actPlayer.getImage());
                    refresh(actPlayer.getPosX(), actPlayer.getPosY(), field.getImage());
                    board.get(actPlayer.getPosX(), actPlayer.getPosY()).setCharacter(null);

                    actPlayer.setPosX(x);
                    actPlayer.setPosY(y);
                    field.setCharacter(actPlayer);

                    if (board.isOver() && hero.canEscape(board, board.getBoardSize())) {
                        JOptionPane.showMessageDialog(boardPanel, "Winner is: " + hero.getName(), 
                            "Congrats!", JOptionPane.PLAIN_MESSAGE);
                        BoardGUI.listener.startNewGame(board.getBoardSize());
                    } else if (!hero.canEscape(board, board.getBoardSize())) {
                        JOptionPane.showMessageDialog(boardPanel, "Winner is: " + actPlayer.getName(), 
                            "Congrats!", JOptionPane.PLAIN_MESSAGE);

                        BoardGUI.listener.startNewGame(board.getBoardSize());
                        
                    }

                    board.setPrevPlayer(actPlayer);
                    board.setActPlayer(null);
                }

            } else if (
                field.getCharacter() != null &&
                actPlayer == null &&
                (prevPlayer == null || !field.getCharacter().equals(prevPlayer))
            ) {
                board.setActPlayer(board.get(x, y).getCharacter());
            }
        }
    }

    /**
     *
     * @return JLabel
     */
    public JLabel getCounterLabel() {
        return counterLabel;
    }
}
