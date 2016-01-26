package it.unibo.monopoli.model.actions;

import java.util.List;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Ownership;

public interface Auction {
    
    void setPlayersTheFirstOneAndOwnership(List<Player> players, Player firstPlayer, Ownership ownership);

    void setPlayersTheFirstOneAndCard(List<Player> players, Player firstPlayer, Card card);

    void increment();

    void decrement();

    int getValue();

    Player nextPlayer();

    void removePlayer();

    /**
     * Stops the {@link Auction} and 
     * @param player
     */
    void stopAuction(Player player);

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
