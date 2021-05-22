package capitaly.players;

import capitaly.fields.Field;
import capitaly.fields.Property;
import java.util.*;

public abstract class Player {
    
    private int round;
    private String name;
    private int balance;
    private int position;
    private String strategy;
    private boolean isInGame;
    private ArrayList<Property> properties;
    
    public Player(String name, String strategy) {
        this.round = 0;
        this.name = name;
        this.position = 0;
        this.balance = 10000;
        this.strategy = strategy;
        this.isInGame = true;
        this.properties = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }
    
     public void setName(String name) {
        this.name = name;
    }
     
    public int getPosition() {
        return this.position;
    }
    
     public void setPosition(int position) {
        this.position = position;
    }

    public int getBalance() {
        return this.balance;
    }
    
    public void setBalance(int amount) {
        this.balance = amount;
    }

    public int getRound() {
        return this.round;
    }
    
    public void setRound(int round) {
        this.round = round;
    }
    
    public boolean getIsInGame() {
        return this.isInGame;
    }
    
    public void setIsInGame(boolean isInGame) {
        this.isInGame = isInGame;
    }
    
    public ArrayList<Property> getProperties() {
        return this.properties;
    }
    
    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }
    
    public String getStrategy() {
        return this.strategy;
    }
    
    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
          
    public abstract void buy(Property property);
    public abstract void build(Property property);
    
    public void startNewRound() {
        this.setRound(this.round+1);
    }
    
        
    /**
    * Removes all properties of player.
    * <p>
    * If player is out of game he loses all his properties.
    *
    */
    public void removeProperties() {
        this.setProperties(new ArrayList<>());
    }
    
    /**
    * Returns if user is still in game after crossing field.
    * <p>
    * If field cost is bigger than player balance, the player is out of game.
    *
    * @param field
    * 
    * @return           player is still in game
    */
    public boolean cross(Field field) {
        this.isInGame = field.cross(this);
        return this.isInGame;
    }
    
    /**
    * Adds property to player property list.
    * <p>
    * Adds new property to player's properties.
    * 
    * @param property
    */
    public void addProperty(Property property) {
        this.properties.add(property);
    }
    
    /**
    * Sets balance of player.
    * <p>
    * If user balance would be below zero, the balance is set to zero.
    * 
    * @param amount
    * @return               if balance > 0
    */
    public boolean changeBalance(int amount) {
        int newBalance = this.getBalance() + amount;
        
        if (newBalance < 0) {
            return false;
        }
       
        this.setBalance(newBalance);
        return true;
    }
    
    /**
    * Returns random number.
    * <p>
    * Generate random number between 1 and 6.
    *
    * @see         Random
    * 
    * @return      random number between 1 and 6
    */
    public int roll() {
        Random rand = new Random();
        return rand.nextInt(7);
    }
}
