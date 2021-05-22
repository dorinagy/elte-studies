package labyrinth;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Hud {

    private String status;
    private Timer timer;
    private String time;
    private long start;
    
    public Hud() {
        time = "0 ms";
        setStatus();
        
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time = elapsedTime() + " ms";
            }
        });
        
        start = System.currentTimeMillis();
        
        timer.start();
    }
    
    public long getStart() {
        return this.start;
    }
    
    public long elapsedTime() {
        return System.currentTimeMillis() - start;
    }
    
    private void setStatus() {
        status = "Labyrinth count: " + App.labyrinthCount;
    }

    /**
     *
     * @param graphics
     * @param x
     * @param y
     */
    public void draw(Graphics graphics, int x, int y) {
      setStatus();
      graphics.drawString(status, x-100, y);
      graphics.drawString(time, x+100, y);
    }
}
