import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.*;

public class ZombieTD {

  private static int getRandom(int min, int max) {
    return ThreadLocalRandom.current().nextInt(min, max);
  }

  private static void doNothing(int ms) {
    try {Thread.sleep(ms);}
    catch (InterruptedException ie) {ie.printStackTrace();}
  }

  private static class ZombieHorde {
    public volatile int size;
    public volatile Boolean merged;
    private int pos;
    private Valley valley;
    public ZombieHorde(Valley valley) {
      this.size   = getRandom(50, 150);
      this.merged = false;
      this.pos    = 0;
      this.valley = valley;
      valley.register(-1, 0, this);
      new Thread(() -> {autoMove();}).start();
      System.out.println("Horde created: " + size);
    }
    private void autoMove() {
      while(!valley.breach && !merged) {
        doNothing(getRandom(100, 300));
        valley.register(pos, ++pos, this);
      }
    }
  }

  private static class Valley {
    public volatile Boolean breach;
    private static class Field {
      public ZombieHorde occupant = null;
    }
    private Field[] fields;
    private void generateZombies() {
      int speed = 200;
      while(!this.breach) {
        doNothing(speed);
        new ZombieHorde(this);
        speed = Math.max((speed * 95) / 100, 1);
      }
    }
    public Valley() {
      this.fields = Stream.generate(() -> new Field()).limit(10).toArray(Field[]::new);
      this.breach = false;
      new Thread(() -> {generateZombies();}).start();
    }
    public synchronized void register(int from, int to, ZombieHorde zh) {
      if (from != -1) fields[from].occupant = null;
      if (to >= 10) breach = true;
      else {
        if (fields[to].occupant == null) fields[to].occupant = zh;
        else {zh.merged = true; fields[to].occupant.size += zh.size;}
      }
    }
  }

  public static void main(String[] args) {
    Valley valley = new Valley();
  }
}
