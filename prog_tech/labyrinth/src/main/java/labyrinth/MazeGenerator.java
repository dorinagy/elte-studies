package labyrinth;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MazeGenerator {
    
    private Random random;
    
    private final int SIZE = 10;
    
    private int[][] maze;
    
    private final String PATH = "res/labyrinth/lab_";
    
    public MazeGenerator() {
        this.random = new Random();
        maze = new int[SIZE][SIZE];
    }
    
    /**
     *
     * @return int[][]
     */
    public int[][] getMaze() {
        try {
            int index = random.nextInt(10);
            
            String filePath = PATH + index + ".txt";
            File file = new File(filePath);
            
            Scanner input = new Scanner(file);
            
            while (input.hasNextInt()){
                for (int i=0; i<SIZE; i++){
                    for (int j=0; j<SIZE; j++) {
                        maze[i][j] = input.nextInt();
                    }
                }
            }   
            return maze;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MazeGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maze;
    }
}