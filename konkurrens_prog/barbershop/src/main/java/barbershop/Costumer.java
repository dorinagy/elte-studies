package barbershop;

import java.util.Random;

public class Costumer {

    private final Boolean wantsBeardTrim;
    private final int arrivingTime;
    private int waitEnded;

    private Costumer(Boolean wantsBeardTrim, int arrivingTime) {
        this.wantsBeardTrim = wantsBeardTrim;
        this.arrivingTime = arrivingTime;
    }
    
    public static Costumer createInstance(int arrivingTime) {
        Random random = new Random();
        
        Boolean wantsBeardTrim = random.nextBoolean();
        
        return new Costumer(wantsBeardTrim, arrivingTime);
    }

    public Boolean wantsBeardTrim() {
        return this.wantsBeardTrim;
    }

    public void setWaitEnded(int waitEnded) {
        this.waitEnded = waitEnded;
    }

    public int getWaitTime(){
        return (waitEnded - arrivingTime);
    }
}
