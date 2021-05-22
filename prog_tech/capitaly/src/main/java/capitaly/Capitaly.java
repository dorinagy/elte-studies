package capitaly;

import capitaly.fields.Field;
import capitaly.fields.Luck;
import capitaly.fields.Service;
import capitaly.fields.Property;

import capitaly.players.Player;
import capitaly.players.Greedy;
import capitaly.players.Cautious;
import capitaly.players.Tactician;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

/*
    Programozasi Technologia - 1. beadando / 3. feladat
    Nev: Nagy Dora
    Neptun-kod: K2VZ0T
*/

public class Capitaly {
    
    private boolean end;
    private final ArrayList<Field> deck;
    private final ArrayList<Integer> rolls;
    private final ArrayList<Player> players;
    
    private int playersNum;
    private int fieldsNum;
    private int rollsNum;
    
    
    public Capitaly() {
      this.end = false;
      this.deck = new ArrayList<>();
      this.rolls = new ArrayList<>();
      this.players = new ArrayList<>();
    }
    
    /**
    * Returns a list of fields.
    * <p>
    * 
    * @see         ArrayList
    * @see         File
    * @see         Scanner
    * @see         Field
    */
    private void createDeck () {                
        try {
            File inputFile = new File("./resources/fields.txt");
        
            try (Scanner scanner = new Scanner(inputFile)) {
                this.fieldsNum = scanner.nextInt();
                
                while (scanner.hasNextLine()) {
                    String input = scanner.nextLine();
                    String fieldProps[] = input.split(" ");
                    
                    try {
                        String type = fieldProps[0];
                       
                        switch(type) {
                            case "luck" -> this.deck.add(new Luck(Integer.parseInt(fieldProps[1])));
                            case "service" -> this.deck.add(new Service(Integer.parseInt(fieldProps[1])));
                            case "property" -> this.deck.add(new Property());
                        }
                    } catch (Exception e) {
                        throw new IllegalArgumentException();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(1);
        }
    }
    
    /**
    * Returns a list of players.
    * <p>
    * 
    * @see         ArrayList
    * @see         File
    * @see         Scanner
    * @see         Field
    */
    private void initializePlayers() {
        try {
            File inputFile = new File("./resources/input.txt");
        
            try (Scanner scanner = new Scanner(inputFile)) {
                this.playersNum = scanner.nextInt();
                
                while (scanner.hasNextLine()) {
                    String input = scanner.nextLine();
                    if (input.equals("")) continue;
                    String fieldProps[] = input.split(" ");
                    
                    try {
                        String name = fieldProps[0];
                        String strategy = fieldProps[1];
                        
                        switch(strategy) {
                            case "greedy" -> this.players.add(new Greedy(name, strategy));
                            case "cautious" -> this.players.add(new Cautious(name, strategy));
                            case "tactician" -> this.players.add(new Tactician(name, strategy));
                        }
                    } catch (Exception e) {
                        throw new IllegalArgumentException();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(1);
        }
    }
    
    /**
    * Returns a list of rolls.
    * <p>.
    * 
    * @see         ArrayList
    * @see         File
    * @see         Scanner
    * @see         Field
    */
    private void initializeDeterminedDiceRolls() {
        try {
            File inputFile = new File("./resources/rolls.txt");
        
            try (Scanner scanner = new Scanner(inputFile)) {
                this.rollsNum = scanner.nextInt();
                
                while (scanner.hasNext()) {
                    this.rolls.add(scanner.nextInt());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(1);
        }
    }
    
    /**
    * Main game logic.
    * <p>
    * Reads entry data from file or user input, initialize deck 
    * and players and then run the game.
    *
    * 
    * @see         Scanner
    */
    private void run() {
        // initialize fields & players
        this.createDeck();
        this.initializePlayers();
        
        System.out.println("Capitaly game start");
        this.info(0);
        
        // determined dice rolls - TRUE for testing
        boolean isDetermined = true;
        
        if (isDetermined) {
            this.initializeDeterminedDiceRolls();
        }
              
        int count = 0;
        
        int n = 0;
        int round = 0;
        while(!this.end) {
            for (int i=0; i<this.playersNum; ++i) {
                Player actPlayer = this.players.get(i);
                
                if (actPlayer.getIsInGame()) {
                    int steps = isDetermined ? this.rolls.get(n%this.rollsNum) : actPlayer.roll();
                    int position = actPlayer.getPosition();
                    
                    int newPosition = (position+steps)%this.fieldsNum;
                    
                    actPlayer.setPosition(newPosition);
                    
                    if (position > this.fieldsNum-steps) {
                        actPlayer.startNewRound();
                    }
                                                
                    Field field = this.deck.get(newPosition);

                    boolean isInGame = actPlayer.cross(field);

                    if (!isInGame) { 
                        count++;
                        actPlayer.setIsInGame(false);
                        actPlayer.removeProperties();
                        this.removeOwnerFromProperties(actPlayer.getName());
                    }
                    
                    if (actPlayer.getRound() > round) {
                        this.info(actPlayer.getRound());
                        round++;
                    }
                    
                    if (count == this.playersNum-1) this.end = true;
                }
                n++;
            }
        }
        
        System.out.println("Game END");
    }
    
    public void info(int round) {
        System.out.println();
        System.out.println("ROUND #" + round);
        System.out.println();
        for (Player player : this.players) {
            if (player.getIsInGame()) {
                System.out.println();
                System.out.println(player.toString());
                System.out.println("---------------");
            }
        }
    }
    
    /**
    * Delete owner of properties.
    * <p>
    * If player is out of game it removes owner from his properties.
    * 
    * @param owner
    * @see         Property
    */
    public void removeOwnerFromProperties(String owner) {
        for (int i=0; i<this.fieldsNum; i++) {
            Field field = this.deck.get(i);
            if (field instanceof Property) {
                Property property = (Property) field;
                property.setOwner("");
                this.deck.set(i, property);
            }
        }
    }
    
    /**
    * Main method.
    * <p>
    * Create instance of Capitaly class.
    * Calls main game logic - run() method.
    * 
    * @param args
    * @see         Capitaly
    */
    public static void main(String[] args) {
       Capitaly capitaly = new Capitaly();
       capitaly.run();
    }
}
