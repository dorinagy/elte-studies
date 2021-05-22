package capitaly.fields;

import capitaly.players.Player;
import java.util.Objects;

public class Service implements Field {
    
    private final int cost;
    private final String type;
    
    public Service(int cost) {
        this.cost = cost;
        this.type = "service";
    }
    
    @Override
    public int getCost() {
        return this.cost;
    }
    @Override
    public String getType() {
        return this.type;
    }
    
    /**
    * Returns if user is still in the game.
    * <p>
    * Decrease player balance by the cost of the field.
    *
    * @param player
    * 
    * @return      balance > 0
    */
    @Override
    public boolean cross(Player player) {
        return player.changeBalance(-this.cost);
    }
    
    @Override
    public String toString() { 
        return String.format(this.getType() + " field, value: " + this.getCost()); 
    }
        
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof Service)) return false;
        
        Service that = (Service)obj;
        
        return this.cost == that.cost && 
                this.type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.cost, this.type);
    }
}
