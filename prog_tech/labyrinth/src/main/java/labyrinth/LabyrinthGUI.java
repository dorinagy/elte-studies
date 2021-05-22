package labyrinth;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class LabyrinthGUI implements GameListener {
    private JFrame frame;
    private App app;

    public LabyrinthGUI() {
        this.createGUI();
    }

    /**
     * Create Game UI
     * 
     * @see JFrame
     * @see App
     * @see JMenuBar
     * @see JMenu
     * @see ActionListener
     * @see ActionEvent
     */
    private void createGUI() {
        frame = new JFrame("LABYRINTH");
        app = new App();
        frame.add(app);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        addMenu();
        
        this.setGameListener();
        
        frame.setVisible(true);
        frame.pack();
        frame.addKeyListener(app);
    }
    
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        
        JMenuItem newMenuItem = new JMenuItem("New Game");
        gameMenu.add(newMenuItem);
        
        
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                startNewGame();
            }
        });
        
        JMenuItem scoresMenuItem = new JMenuItem("High Scores");
        gameMenu.add(scoresMenuItem);
        
        scoresMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    showHighScores();
                } catch (SQLException ex) {
                    Logger.getLogger(LabyrinthGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        gameMenu.add(exitMenuItem);
        
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
    }
    
    /**
     * Set Game Listener
     */
    private void setGameListener() {
        app.setListener(this);
    }

    /**
     * Start new Game
     */
    @Override
    public void startNewGame() {
       frame.removeAll();
       createGUI();
    }

    /**
     * Save Score
     */
    @Override
    public void saveScore() {
        String name = JOptionPane.showInputDialog(null, "Enter your name:", "Save Highscore", 1);
        try {
            HighScoreManager highScores = new HighScoreManager(10);
            highScores.putHighScore(name, App.labyrinthCount);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
       startNewGame();
    }
    
    private void showHighScores() throws SQLException {
        frame.removeAll();
        
        HighScoreManager highScores = new HighScoreManager(10);
        List<HighScore> scores = highScores.getSortedHighScores();
        
        JList list = new JList(new Vector<HighScore>(scores));
        
        frame = new JFrame("LABYRINTH");
        
        frame.add(list);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        addMenu();
        
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(App.MAP_SIZE, App.MAP_SIZE + App.HUD));
        frame.pack();
    }
}
