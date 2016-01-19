package it.unibo.monopoli.model.cards;

import java.util.Set;

/**
 * This interface represent all the decks in the game. They are composed of
 * several cards.
 *
 */
public interface Deck {

    /**
     * Return the name of the {@link Deck}.
     * 
     * @return {@link Deck}'s name
     */
    String getName();

    /**
     * Return a {@link Set} of {@link Deck}'s {@link Card}s.
     * 
     * @return a {@link Set} of {@link Card}s
     */
    Set<Card> getCards();

    /**
     * This method add to the {@link Deck} a new {@link Card}.
     * 
     * @param card
     *            - the {@link Card} to add
     */
    void addCard(Card card);

    /**
     * This method remove from the {@link Deck} this {@link Card}.
     * 
     * @param card
     *            - the {@link Card} to remove
     */
    void removeCard(Card card);

}
