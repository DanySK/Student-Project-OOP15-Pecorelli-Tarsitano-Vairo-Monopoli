package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;

public interface Action {
    
    /**
     * This is the main method for every {@link Action}: it explains witch action 
     * the specific class represent.
     * 
     * @param player - the {@link Player} that have to execute this {@link Action}
     */
    void play(Player player);

}
