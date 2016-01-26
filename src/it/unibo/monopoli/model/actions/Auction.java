package it.unibo.monopoli.model.actions;

import java.util.List;

import it.unibo.monopoli.model.mainunits.Player;

public interface Auction {
    
    void setPlayersAndTheFirstOne(List<Player> players, Player firstPlayer);

    void increment();

    void decrement();

    int getValue();

    Player nextPlayer();

    void removePlayer();

    /**
     * Returns true if this {@link Player} is able to do the {@link Auction},
     * otherwise returns false.
     * 
     * @param player
     *            - the {@link Player} participating in auction
     * @return true if this {@link Player} is able to do the {@link Auction},
     *         false if he isn't able to
     */
    boolean isAbleToDoIt(Player player);

}
