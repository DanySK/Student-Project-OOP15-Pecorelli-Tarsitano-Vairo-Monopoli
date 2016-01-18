package it.unibo.monopoli.model.cards;

import it.unibo.monopoli.model.actions.Action;

/**
 * This interface represent all the cards in that game. 
 * They all have a name, a description and an {@link Action} to execute.
 *
 */
public interface Card {

    /**
     * Return the name of the {@link Card}.
     * 
     * @return {@link Card}'s name
     */
    String getName();

    /**
     * Return the description of the specific {@link Card}.
     * 
     * @return {@link Card}'s description
     */
    String getDescription();

    /**
     * Return the {@link Action} of the specific {@link Card}.
     * 
     * @return {@link Card}'s {@link Action}
     */
    Action getAction();

}
