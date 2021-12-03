import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.atomic.AtomicInteger;
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
      System.out.println("Horde created: " + size);
      new Thread(() -> {autoMove();}).start();
    }

    private void autoMove() {
      while(!valley.breach && !merged && size > 0) {
        doNothing(getRandom(100, 300));
        valley.register(pos, ++pos, this);
      }
    }
  }

  private static class Valley {
    private static class Field {
      public ZombieHorde occupant = null;
      public ReentrantLock re     = new ReentrantLock();
      public Condition empty      = re.newCondition();
    }

    public volatile Boolean breach;
    private Field[] fields;
    private AtomicInteger corpses;

    public Valley() {
      this.breach  = false;
      this.fields  = Stream.generate(() -> new Field()).limit(10).toArray(Field[]::new);
      this.corpses = new AtomicInteger(0);
      Stream.iterate(0, i -> i+1).limit(10).forEach(i -> new Soldier(i, this));
      new Thread(() -> {generateZombies();}).start();
    }

    public void register(int from, int to, ZombieHorde zh) {
      if (zh.size <= 0) return;
      Boolean fullyLocked = false;
      while (!fullyLocked) {
        if (from != -1) fields[from].re.lock();
        if (to >= 10 || fields[to].re.tryLock()) fullyLocked = true;
        else if (from != -1) fields[from].re.unlock();
      }
      if (to >= 10) {
        breach = true;
        for (Field field : fields) {
          field.re.lock();
          field.empty.signal();
          field.re.unlock();
        }
        System.out.println("Kills: " + corpses.get());
      }
      else {
        if (from != -1) fields[from].occupant = null;
        if (fields[to].occupant == null) fields[to].occupant = zh;
        else {zh.merged = true; fields[to].occupant.size += zh.size;}
        fields[to].empty.signal();
      }
      if (from != -1) fields[from].re.unlock();
      if (to < 10) fields[to].re.unlock();
    }

    public Boolean fireAtField(int idx, int bullets) {
      Boolean success = false;
      fields[idx].re.lock();
        if (fields[idx].occupant != null) {
          corpses.addAndGet(bullets);
          fields[idx].occupant.size -= bullets;
          if (fields[idx].occupant.size <= 0) fields[idx].occupant = null;
          success = true;
        }
      fields[idx].re.unlock();
      return success;
    }

    public void waitForUpdate(int pos) {
      fields[pos].re.lock();
      try {fields[pos].empty.await();} catch (Exception e) {e.printStackTrace();}
      fields[pos].re.unlock();
    }

    private void generateZombies() {
      int speed = 200;
      while(!this.breach) {
        new ZombieHorde(this);
        doNothing(speed);
        speed = Math.max((speed * 95) / 100, 10);
      }
    }
  }

  private static class Soldier {
    private int pos;
    private Valley valley;

    public Soldier(int pos, Valley valley) {
      this.pos    = pos;
      this.valley = valley;
      new Thread(() -> {autoFire();}).start();
    }

    private void autoFire() {
      while(!valley.breach) {
        if (valley.fireAtField(pos, 10)) doNothing(50);
        else valley.waitForUpdate(pos);
      }
    }
  }

  public static void main(String[] args) {
    Valley valley = new Valley();
  }
}
