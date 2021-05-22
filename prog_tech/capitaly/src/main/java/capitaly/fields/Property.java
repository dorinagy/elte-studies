package capitaly.fields;

import capitaly.players.Player;
import java.util.Objects;


public class Property implements Field {
    
    private final int cost;
    private final String type;
    
    private String owner;
    private boolean house;
    
    private final int propertyRental;
    private final int houseRental;
    private final int buildingCost;
    
    public Property() {
        this.cost = 1000;
        this.buildingCost = 4000;
        this.propertyRental = 500;
        this.houseRental = 2000;
        this.house = false;
        this.type = "property";
        this.owner = "";
    }
    
    @Override
    public int getCost() {
        return !this.owner.equals("")
                ? this.house 
                    ? houseRental 
                    : propertyRental
                : this.cost;
    }
    
    @Override
    public String getType() {
        return this.type;
    }
    
    public String getOwner() {
        return this.owner;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    public boolean getHouse() {
        return this.house;
    }
    
    public void setHouse(boolean house) {
        this.house = house;
    }
    
    /**
    * Returns if user is still in the game.
    * <p>
    * 
    * Buying/Building property on field.
    *
    * @param player
    * 
    * @return      player.balance > 0
    */
    @Override
    public boolean cross(Player player) {
        if (this.owner.equals("")) {
            player.buy(this);
        } else if (
                this.owner.equals(player.getName()) &&
                !this.house
        ) {
            player.build(this);
        } else {
            int rent = this.getHouse() ? this.houseRental : this.propertyRental;
            return player.changeBalance(-rent);
        }
        return true;
    }

    @Override
    public String toString() { 
        String houses = this.house ? " with house" : " without house";
        return String.format(this.getType() + houses); 
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof Property)) return false;
        
        Property that = (Property)obj;
        
        return this.house == that.house && 
                this.owner.equals(that.owner) &&
                this.type.equals(that.type) &&
                this.cost == that.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.house, this.owner, this.type, this.cost);
    }
}
