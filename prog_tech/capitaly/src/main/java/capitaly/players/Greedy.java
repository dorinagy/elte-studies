package capitaly.players;

import java.util.Objects;
import capitaly.fields.Property;

public class Greedy extends Player {
    
    public Greedy(String name, String strategy) {
        super(name, strategy);
    }
    
    /**
    * Buying a house.
    * <p>
    * Sets the current player of the owner of property.
    *
    * @param property
    * 
    */
    @Override
    public void buy (Property property) {
        if (this.getBalance() >= property.getCost()) {
            property.setOwner(this.getName());
            this.addProperty(property);
        }
    }
    
    /**
    * Building a house.
    * <p>
    *
    * Sets the house property of the field to true.
    * 
    * @param property
    */
    @Override
    public void build (Property property) {
        if (this.getBalance() >= property.getCost()) {
            property.setHouse(true);
        }
    }
        
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof Greedy)) return false;
        
        Greedy that = (Greedy)obj;
        
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
                + ", balance: " + this.getBalance() + "\nproperties: " + this.getProperties().size() + properties); 
    }
}
