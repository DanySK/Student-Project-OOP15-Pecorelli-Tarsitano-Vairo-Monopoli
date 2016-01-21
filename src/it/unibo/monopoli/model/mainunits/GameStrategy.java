package it.unibo.monopoli.model.mainunits;

import java.util.List;

import it.unibo.monopoli.model.table.Building;

/**
 * This interface holds the strategy for initialize the game with the right
 * {@link GameVersion}.
 *
 */
public interface GameStrategy {
    
    public int howManyBuildings();
    
    public Building getBuilding();
    
    List<Player> getPlayers();

}
