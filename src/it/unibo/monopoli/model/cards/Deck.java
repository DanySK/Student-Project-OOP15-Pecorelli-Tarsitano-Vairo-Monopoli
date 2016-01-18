package it.unibo.monopoli.model.cards;

import java.util.Set;

/**
 * This interface represent all the decks in the game. 
 * They are composed of several cards.
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

}
