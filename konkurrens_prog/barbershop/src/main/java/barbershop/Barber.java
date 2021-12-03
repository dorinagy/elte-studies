package barbershop;

public class Barber implements Runnable {
    
    private final Boolean doesBeardTrim;
    private final BarberShop barberShop;

    public Barber(Boolean doesBeardTrim){
        this.doesBeardTrim = doesBeardTrim;
        this.barberShop = BarberShop.getInstance();
    }
    
    public Boolean doesBeardTrim() {
        return this.doesBeardTrim;
    }

    @Override
    public void run() {
        while (
            barberShop.getDaysCount() < BarberShop.SIMULATION_TIME
        ) { 
            this.serveCostumer(); 
        }
    }

    /**
     * Returns costumer.
     * 
     * Remove served costumer from queue & set service time.
     * 
     * @see Costumer
     * @return Costumer next
     */
    private void serveCostumer() {
        
        Costumer costumer = barberShop.getNextInQueue(this.doesBeardTrim);
        
        if (costumer != null) {
            
            // haircut time: between 20 - 200 ms
            int hairCutTime = (int) (Math.random() * ((200 - 20) + 1));
                
            try {
                Thread.sleep(hairCutTime);
            } catch(InterruptedException e){
                System.out.println(e.getMessage());
            }

            costumer.setWaitEnded(barberShop.getCurrentTime());
            barberShop.setServiceTime(costumer);
        }
    }
}
