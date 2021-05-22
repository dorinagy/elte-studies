package capitaly.fields;

import capitaly.players.Player;

public interface Field {
        
    int getCost();
    String getType();
    @Override
    String toString();
    public boolean cross(Player player);
}
