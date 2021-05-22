package capitaly.players;

import java.util.Objects;
import capitaly.fields.Property;

public class Tactician extends Player {
    
    private int purchases;
    
    public Tactician(String name, String strategy) {
        super(name, strategy);
        this.purchases = 0;
    }
    
    public void setPurchases(int purchases) {
        this.purchases = purchases;
    }
    
    public int getPurchases() {
        return this.purchases;
    }
    
    /**
    * Buying a house on every 2nd opportunity.
    * <p>
    * Sets the current player of the owner of property.
    *
    * @param property
    * 
    */
    @Override
    public void buy (Property property) {
        if (
            this.purchases % 2 == 0 &&
            this.getBalance() >= property.getCost()
        ) {
            property.setOwner(this.getName());
            this.addProperty(property);
            this.purchases++;
        }
    }
    
    /**
    * Building a house on every 2nd opportunity.
    * <p>
    *
    * Sets the house property of the field to true.
    * 
    * @param property
    */
    @Override
    public void build (Property property) {
        if (
            this.purchases % 2 == 0 &&
            this.getBalance() >= property.getCost()
        ) {
            property.setHouse(true);
            this.purchases++;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof Tactician)) return false;
        
        Tactician that = (Tactician)obj;
        
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
                + ", balance: " + this.getBalance() + "\nproperties: "  + this.getProperties().size() + properties); 
    }
}
