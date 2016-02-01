package it.unibo.monopoli.model.mainunits;

import java.util.List;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This interface represent all the players. They all have a name that
 * identifies them, a {@link Pawn} that identifies them in the game, some
 * {@link Ownership}s and some money.
 *
 */
public interface Player extends Owner {

    /**
     * Return the name of the {@link Player}.
     * 
     * @return the name of the {@link Player}
     */
    String getName();

    /**
     * Return the {@link Pawn} of the {@link Player}. Each {@link Player} can
     * have only one {@link Pawn}.
     * 
     * @return the {@link Pawn} of the {@link Player}
     */
    Pawn getPawn();

    // /**
    // * Set the {@link Pawn} that will belong to the {@link Player}.
    // */
    // void setPawn();

    /**
     * Return the {@link List} of {@link Ownership} belonging to the
     * {@link Player}.
     * 
     * @return a {@link List} of {@link Ownership}
     */
    List<Ownership>  getOwnerships();

    /**
     * Adds a {@link Card} to the {@link Player}'s {@link Deck}.
     * 
     * @param card
     *            - the {@link Card} to add
     */
    void addCard(Card card);
    
    void removeCard(Card card);

    /**
     * Return a {@link List} of player's {@link Card}s.
     * 
     * @return a {@link List} of {@link Card}s
     */
    List<Card> getCards();

    /**
     * Returns true if the {@link Player} has already rolled {@link Dice}s,
     * false if hasn't.
     * 
     * @return true if the {@link Player} has already rolled {@link Dice}s
     */
    boolean dicesAlreadyRolled();

    /**
     * Sets if the {@link Player} rolled {@link Dice}s (true) or not (false).
     * 
     * @param alreadyRolled
     *            - true if {@link Player} already rolled {@link Dice}s,
     *            otherwise, false
     */
    void setDicesRoll(boolean alreadyRolled);

    /**
     * Returns true if the {@link Player} is in prison, false if he isn't.
     * 
     * @return true if the {@link Player} is in prison
     */
    boolean isInPrison();

    /**
     * Returns the last card the the {@link Player} drew from a {@link Deck}
     * @return
     * @throws NullPointerException - if no {@link Card} was draw.
     */
    Card lastCardDrew();
    
    void setLastCardDrew(Card lastCard);
    
    List<Integer> lastDicesNumber();
    
    void setLastDicesNumber(List<Integer> numbers);
    
    boolean areDebtsPaid();
    
    void setDebts(boolean arePaid);

    /**
     * Sets if the {@link Player} is going to prison (true) or not (false).
     * 
     * @param isGoingToPrison
     *            - true if {@link Player} is going to prison, otherwise, false
     */
    void setPrison(boolean isGoingToPrison);

    // /**
    // * If the {@link Ownership} request belongs to the {@link Player},
    // * the method returns an {@link Optional} of {@link Ownership}, else return
    // * an {@link Optional} of null.
    // * @return an {@link Optional} of {@link Ownership}
    // * @param ownership - the {@link Ownership} to find the return
    // */
    // Optional<Ownership> getOwnership(Ownership ownership);

    /**
     * Add a new {@link Ownership}.
     * 
     * @param ownership
     *            - the one to add
     */
    void addOwnership(Ownership ownership);

    /**
     * Remove a {@link Ownership} from the {@link Player}.
     * 
     * @param ownership
     *            - the one to remove
     */
    void removeOwnership(Ownership ownership);

    /**
     * Return the money belonging to the {@link Player}.
     * 
     * @return the money belonging to the {@link Player}
     */
    int getMoney();

    /**
     * Set the money belonging to the {@link Player}.
     * 
     * @param amount
     *            - the amount of money to set
     */
    void setMoney(int amount);
    
    boolean isHuman();

    int howManyTurnsHasBeenInPrison();

    void incrementsTurnsInPrison();

}
