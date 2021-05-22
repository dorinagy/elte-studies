package labyrinth;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;

public class App extends JComponent implements KeyListener {

  final static int MAP_SIZE = 720;
  final static int TILE_NUM = 10;
  final static int HUD = 100;
    
  static Hero hero;
  static Monster monster; 
  static int labyrinthCount;
  
  public static boolean end;
  public static GameListener listener;
    
  Hud hud;
  GameArea area;
    
  public App() {
    setPreferredSize(new Dimension(MAP_SIZE, MAP_SIZE + HUD));
    setVisible(true);
    
    end = false;
    
    hero = new Hero();
    area = new GameArea();
    
    hud = new Hud();
    monster = new Monster();
    
    labyrinthCount = 1;
  }
    
  public void drawHud(Graphics graphics) {
    hud.draw(graphics, 340, MAP_SIZE + 20);
  }
  
  public void setListener(GameListener listener) {
    App.listener = listener;
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    area.drawArea(graphics);
    drawMonster(graphics);
    hero.draw(graphics);
    drawHud(graphics);
    
    timer.start();
  }
  
  public void drawMonster(Graphics graphics) {
      if (
        monster.posX >= App.hero.posX - 3 && monster.posX <= App.hero.posX + 3 &&
        monster.posY >= App.hero.posY - 3 && monster.posY <= App.hero.posY + 3
      ) {
        monster.draw(graphics);
      }
  }
  
  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {}

  @Override
  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_W:
      case KeyEvent.VK_UP:
        if (hero.posY > 0) {
          hero.move(hero.posX, hero.posY - 1);
        } else {
          area = new GameArea();
          labyrinthCount++;
          hero.posY = TILE_NUM - 1;
          invalidate();
          repaint();
        }
        hero.setImage(ImageLoader.getInstance().HERO_UP);
        break;
      case KeyEvent.VK_S:
      case KeyEvent.VK_DOWN:
        if (hero.posY < TILE_NUM - 1) {
          hero.move(hero.posX, hero.posY + 1);
        } else {
          area = new GameArea();
          labyrinthCount++;
          hero.posY = 0;
          invalidate();
          repaint();
        }
        hero.setImage(ImageLoader.getInstance().HERO_DOWN);
        break;
      case KeyEvent.VK_A:
      case KeyEvent.VK_LEFT:
        if (hero.posX > 0) {
          hero.move(hero.posX - 1, hero.posY);
        } else {
          area = new GameArea();
          labyrinthCount++;
          hero.posX = TILE_NUM - 1;
          invalidate();
          repaint();
        }
        hero.setImage(ImageLoader.getInstance().HERO_LEFT);
        break;
      case KeyEvent.VK_D:
      case KeyEvent.VK_RIGHT:
        if (hero.posX < TILE_NUM - 1) {
          hero.move(hero.posX + 1, hero.posY);
        } else {
          area = new GameArea();
          labyrinthCount++;
          hero.posX = 0;
          invalidate();
          repaint();
        }
        hero.setImage(ImageLoader.getInstance().HERO_RIGHT);
        break;
      default:
        break;
    }
    
    if (hero.posX == TILE_NUM - 1 && hero.posY == 0) {
        // win - save score
        System.out.println("win");
        App.listener.saveScore();
        end = true;
    }
    
    invalidate();
    repaint();
  }

  javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      if (!end) {
        monster.move();
        repaint();
      }
    }
  });
}
