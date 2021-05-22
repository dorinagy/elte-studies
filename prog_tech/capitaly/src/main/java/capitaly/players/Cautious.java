package capitaly.players;

import java.util.Objects;
import capitaly.fields.Property;

public class Cautious extends Player {
    
    private int budget;
    
    public Cautious(String name, String strategy) {
        super(name, strategy);
        this.budget = this.getBalance()/2;
    }
    
    public void setBudget(int budget) {
        this.budget = budget;
    }
    
    public int getBudget() {
        return this.budget;
    }
    
    @Override
    public void startNewRound() {
        this.setRound(this.getRound()+1);
        this.budget = this.getBalance()/2;

    }
    
    /**
    * Buys house.
    * <p>
    * Only buys house if his budget is greater than the cost of the property if budget allows it.
    * 
    * @param property
    * 
    */
    @Override
    public void buy (Property property) {
        if (this.budget >= property.getCost()) {
            property.setOwner(this.getName());
            this.addProperty(property);
        }
    }
    
    /**
    * Buying a house.
    * <p>
    * Sets the current player of the owner of property if budget allows it.
    *
    * @param property
    * 
    */
    @Override
    public void build (Property property) {
        if (this.budget >= property.getCost()) {
            property.setHouse(true);
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof Cautious)) return false;
        
        Cautious that = (Cautious)obj;
        
        return this.getName().equals(that.getName()) && 
                this.getBalance() == that.getBalance() &&
                this.getStrategy().equals(that.getStrategy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getBalance(), this.getStrategy());
    }
    
    @Override
    public String toString() { 
        String properties = "";
        if (this.getProperties().size() > 0) {
            for (Property p : this.getProperties()) {
                properties += "\n - " + p.toString();
            }
        }
        
        return String.format(this.getName() + "\nstrategy: " + this.getStrategy() 
                + ", balance: " + this.getBalance() + ", budget: " + this.getBudget()
                + "\nproperties: " + this.getProperties().size() + properties); 
    }
}
