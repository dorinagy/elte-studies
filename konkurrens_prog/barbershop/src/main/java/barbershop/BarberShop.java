package barbershop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.Executors;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

public class BarberShop {
    
    public static int SIMULATION_HOUR;
    public static int SIMULATION_TIME;
    
    private final int OPENING_TIME;
    private final int CLOSING_TIME;
    
    private static BarberShop instance;
    
    private final List<Integer> costumersServed;
    private final BlockingQueue<Costumer> costumersWaiting;
    
    private final List<Integer> waitingTime;
    
    // CONSTANT VALUES - Capacity of waiting room + number of barbers
    private static final int CAPACITY = 5;
    private static final int BARBERS_COUNT = 2;
    
    private final Random random;
    private int dayCount;
    private int currentTime;
    
    private int notServedDuringClose;
    private int notServedDuringOpen;
    
    private static final ExecutorService executor = Executors.newFixedThreadPool(BARBERS_COUNT);


    /**
     * Create single instance of the barber shop class
     * 
     * @param simulationHour one hour of simulation
     * @param simulationTime time of simulation
     * 
     * @return BarberShop instance
     */
    public static BarberShop createInstance(int simulationHour, int simulationTime){
        if (instance == null) {
            instance = new BarberShop(simulationHour, simulationTime);
        }
        
        return instance;
    }

    /**
     * Getter for BarberShop instance
     * 
     * @return BarberShop instance
     */
    public static BarberShop getInstance(){
        return instance;
    }

    private BarberShop(int simulationHour, int simulationTime) {
        SIMULATION_HOUR = simulationHour;
        SIMULATION_TIME = simulationTime;
        
        this.OPENING_TIME = simulationHour * 9; // working starts at 9:00
        this.CLOSING_TIME = simulationHour * 17; // working ends at 17:00
        
        this.costumersServed = new ArrayList<>(SIMULATION_TIME);
        this.costumersWaiting = new ArrayBlockingQueue<>(CAPACITY);
        
        this.waitingTime = new ArrayList<>();
        
        random = new Random();
    }

    /**
     * Start weekly routine.
     * 
     * @throws java.lang.InterruptedException
     * @see Barber
     */
    public void startWorking() throws InterruptedException {
        
        executor.execute(new Barber(false)); // one barber does only haircut
        executor.execute(new Barber(true)); // one barber does haircut + beard trim

        while (this.dayCount < SIMULATION_TIME) {
            
            System.out.println();
            System.out.println("Day "+ this.dayCount+1);
            
            int count = this.startWorkDay();
            
            this.costumersServed.add(this.dayCount, count);
            Thread.sleep(1000);
            this.dayCount++;
        }

        writeStatistics(); // write statistics on end of simulation
        executor.shutdown();
    }

    /**
     * Simulate daily routine.
     * 
     * @throws java.lang.InterruptedException
     * @see int number of costumers served
     */
    private int startWorkDay() throws InterruptedException {
        this.currentTime = 0; // start day
        int servedCostumers = 0;
        int hoursInADay = SIMULATION_HOUR * 24;
        
        while (this.currentTime <= hoursInADay) {
            
            Boolean newCostumer = random.nextBoolean();
            
            if (newCostumer) {
                System.out.println("New costumer arrives.");
                Costumer costumer = Costumer.createInstance(this.currentTime);
                if (
                    this.currentTime >= this.OPENING_TIME && 
                    this.currentTime <= this.CLOSING_TIME
                ) { // costumer arrives during working hours
                    if (!newCostumerCanEnter(costumer)) {
                        System.out.println("Not enough space.");
                        this.notServedDuringOpen++;
                    } else { // serve costumer during working hour
                        System.out.println("Costumer enters barber shop.");
                        servedCostumers++;
                    }
                } else { // costumer arrives before/after working hours
                    System.out.println("Barber shop is closed.");
                    this.notServedDuringClose++;
                }
            }

            Thread.sleep(SIMULATION_HOUR / 8);
            this.currentTime += SIMULATION_HOUR / 8;
        }
        System.out.println(servedCostumers);
        return servedCostumers;
    }
    
    
    /**
     * Write statistics.
     * 
     */
    private void writeStatistics() {
        System.out.println("\n---------- STATISTICS ----------- \n");
        System.out.println("\nNumber of working days: " + SIMULATION_TIME + "\n");
        System.out.println("\nCostumers served: " + countCostumers());
        System.out.println("\nCostumers served each day: ");
        for (int i = 0 ; i < SIMULATION_TIME; i++){
            System.out.println("\n" + (i+1) + ". day: " + this.costumersServed.get(i) + " costumer.");
        }
        System.out.println("\nAverage waiting time was " + this.getAverageWaitingTime() + "milliseconds.");
        
        System.out.println("\nCostumers not served after closing hour: " + this.notServedDuringClose);
        System.out.println("\nCostumers not served because lacking space: " + this.notServedDuringOpen);
    }
    
    private double getAverageWaitingTime() {
        int sum = 0;
        for(int i=0; i<this.waitingTime.size(); i++){
            sum += this.waitingTime.get(i);
        }
        double average = sum / this.waitingTime.size();
        
        return average;
    }

    /**
     * Get number of costumers who were served.
     * 
     * @return int sum
     */
    private int countCostumers() {
        int sum = 0;
        for (int i=0; i<this.costumersServed.size(); i++) {
            sum+= this.costumersServed.get(i);
        }
        return sum;
    }

    /**
     * Returns if new costumer can enter the barber shop.
     * 
     * Costumer can enter is there is enough space.
     * 
     * @return Boolean
     */
    private Boolean newCostumerCanEnter(Costumer person){
        if (this.costumersWaiting.remainingCapacity() > 0) {
            this.costumersWaiting.add(person);
            return true;
        }
        return false;
    }
    
    public int getDaysCount() {
        return this.dayCount;
    }

    public synchronized int getCurrentTime() { 
        return this.currentTime; 
    }
    
    public void setServiceTime(Costumer costumer){
        this.waitingTime.add(costumer.getWaitTime());
    }

    public synchronized Costumer getNextInQueue(Boolean doesBeardTrim) {
        Costumer costumer = this.costumersWaiting.peek();
        
        if (costumer == null) return null;
        
        if (costumer.wantsBeardTrim()) {
            System.out.println("Costumer wants beard trim.");
            if (doesBeardTrim) {
                System.out.println("Barber works on costumer's beard.");
                return this.costumersWaiting.remove();
            } else {
                System.out.println("Barber does not do beard trim.");
                return null;
            }
        }
        
        System.out.println("Barber works on costumer's hair.");
        return this.costumersWaiting.remove();
    }
}
