package it.unibo.monopoli.model.cards;

import it.unibo.monopoli.model.mainunits.Player;

/**
 * This interface represent all the {@link Card}s that belongs to a
 * {@link Player}.
 *
 */
public interface CardBelongingToPlayer extends Card {

    /**
     * This method adds the specific {@link Card} to a {@link Player}.
     */
    void addItselfToPlayer();

}
