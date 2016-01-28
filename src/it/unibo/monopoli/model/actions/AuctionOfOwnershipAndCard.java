package it.unibo.monopoli.model.actions;

import java.util.List;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This is the interface that represents the auction about {@link Ownership}s
 * and {@link Card}s.
 *
 */
public interface AuctionOfOwnershipAndCard {

    /**
     * This method initializes all the main components of the auction. This one
     * need a list of the {@link Player}s that takes part to the auction, the
     * {@link Player} who started the auction (so the first one) and the
     * {@link Ownership} to auction.
     * 
     * @param players
     *            - a list of the {@link Player}s that takes part to the auction
     * @param firstPlayer
     *            - the {@link Player} who started the auction (so the first
     *            one)
     * @param ownership
     *            - the {@link Ownership} to auction
     */
    void initializedOwnershipsAuction(List<Player> players, Player firstPlayer, Ownership ownership);

    /**
     * This method initializes all the main components of the auction. This one
     * need a list of the {@link Player}s that takes part to the auction, the
     * {@link Player} who started the auction (so the first one) and the
     * {@link Card} to auction.
     * 
     * @param players
     *            - a list of the {@link Player}s that takes part to the auction
     * @param firstPlayer
     *            - the {@link Player} who started the auction (so the first
     *            one)
     * @param card
     *            - the {@link Card} to auction
     */
    void initializedCardsAuction(List<Player> players, Player firstPlayer, Card card);

    /**
     * This method increments the amount of money to invest in the auction.
     */
    void increment();

    /**
     * This method decrements the amount of money to invest in the auction.
     */
    void decrement();

    /**
     * Returns the actual value of the auction.
     * @return the value of the auction
     */
    int getValue();

    /**
     * Returns the next {@link Player} that have to play in the auction.
     * @return the next {@link Player} that have to play
     */
    Player nextPlayer();

    /**
     * Removes the {@link Player} that is playing in the auction from it.
     */
    void removePlayer();

    /**
     * Stops the auction and sell the {@link Ownership} or the {@link Card} to the winer of the auction.
     * 
     * @param player - the {@link Player} who win the auction
     */
    void stopAuction(Player player);

    /**
     * Returns true if this {@link Player} is able to do the
     * {@link AuctionOfOwnershipAndCard}, otherwise returns false.
     * 
     * @param player
     *            - the {@link Player} participating in auction
     * @return true if this {@link Player} is able to do the
     *         {@link AuctionOfOwnershipAndCard}, false if he isn't able to
     */
    boolean isAbleToDoIt(Player player);

}
