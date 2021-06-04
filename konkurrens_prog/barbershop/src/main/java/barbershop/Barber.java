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
            Costumer costumer = this.callNextCostumer();
            
            if (costumer != null) {
                // haircut time: between 20 - 200 ms
                int hairCutTime = (int) (Math.random() * ((200 - 20) + 1));
                
                try {
                    Thread.sleep(hairCutTime);
                } catch(InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Returns costumer.
     * 
     * Get next costumer in line and serve.
     * 
     * @see Costumer
     * @return Costumer
     */
    public synchronized Costumer callNextCostumer() {
        
        Costumer costumer = barberShop.getNextInQueue();

        if (costumer == null) return null;
        
        System.out.println("Barber calls next costumer.");
        
        if (costumer.wantsBeardTrim()) {
            System.out.println("Costumer wants beard trim.");
            if (this.doesBeardTrim()) {
                System.out.println("Barber works on costumer's beard.");
                return this.serveCostumer();
            } else {
                System.out.println("Barber does not do beard trim.");
               return null;
            }
        } else {
            System.out.println("Barber works on costumer's hair.");
            return this.serveCostumer();
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
    private Costumer serveCostumer() {
        try {
            Costumer next = barberShop.removeFromQueue();
            next.setWaitEnded(barberShop.getCurrentTime());
            barberShop.setServiceTime(next);
            return next;
        } catch (Exception e) {
            return null;
        }
    }
}
