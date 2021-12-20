package game;

import java.awt.*;

public class Sprite extends Component {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    
    public Sprite(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isCollide(Sprite other){
        Rectangle first = new Rectangle(x,y,width, height);
        Rectangle second = new Rectangle(other.x, other.y, other.width, other.height);
        return first.intersects(second);
    }

    /**
     * Draw element
     * 
     * @param g Graphics
     * 
     * @see Graphics
     */
    public void draw(Graphics g) {}

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }


    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
