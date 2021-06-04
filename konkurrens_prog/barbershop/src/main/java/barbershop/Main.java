package barbershop;

public class Main {
    
    private static final int SIMULATION_HOUR = 400; // one hour of simulation: 400 ms
    private static final int SIMULATION_TIME = 5; // time of simulations: 5 days
    
    public static void main(String[] args) {

        // set simulation time & hour
        BarberShop barberShop = BarberShop.createInstance(SIMULATION_HOUR, SIMULATION_TIME);
        
        try {
            // start simulation
            barberShop.startWorking();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
