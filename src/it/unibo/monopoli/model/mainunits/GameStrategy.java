package it.unibo.monopoli.model.mainunits;

import java.util.List;

import it.unibo.monopoli.model.table.Box;

/**
 * This interface holds the strategy for initialize the game with the right
 * {@link GameVersion}.
 *
 */
public interface GameStrategy {

    /**
     * Returns a {@link List} of the game's {@link Player}s.
     * 
     * @return a {@link List} of the {@link Player}s
     */
    List<Player> getPlayers();

    /**
     * Return the game's {@link Bank}.
     * 
     * @return the {@link Bank}
     */
    Bank getBank();

    /**
     * Returns a {@link List} with all the game's table's {@link Box}es.
     * 
     * @return a {@link List} of all {@link Box}es
     */
    List<Box> getBoxes();

}
