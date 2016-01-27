package it.unibo.monopoli.model.cards;

import java.util.List;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.mainunits.Player;

/**
 * This interface represent all the {@link Card}s in that game. They all have a name, a
 * description and an {@link Action} to execute.
 *
 */
public interface Card {

    // /**
    // * Return the {@link Deck} to which the {@link Card} belongs.
    // *
    // * @return {@link Card}'s {@link Deck}
    // */
    // Deck getDeck();
    
    int getID();

    /**
     * Return the description of the specific {@link Card}.
     * 
     * @return {@link Card}'s description
     */
    String getDescription();

//    /**
//     * Return a {@link List} of {@link Action}s of the specific {@link Card}.
//     * 
//     * @return {@link Card}'s {@link Action}s
//     */
//    List<Action> getActions();
//
//    /**
//     * Returns the {@link Player} who draw the {@link Card}.
//     * 
//     * @return the {@link Player} who draw the {@link Card}
//     */
//    Player getPlayer();
//
//    /**
//     * Sets the {@link Player} who draw the {@link Card}.
//     * 
//     * @param player
//     *            - the {@link Player} who draw the {@link Card}.
//     */
//    void setPlayer(Player player);

}
