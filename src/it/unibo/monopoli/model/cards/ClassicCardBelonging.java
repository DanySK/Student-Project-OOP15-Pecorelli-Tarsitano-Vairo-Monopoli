package it.unibo.monopoli.model.cards;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.actions.AddCardToPlayer;
import it.unibo.monopoli.model.mainunits.Player;

/**
 * This is an implementation of {@link CardBelongingToPlayer}. This type of
 * {@link Card}s are different from the others because they can belong to
 * {@link Player}s.
 *
 */
public class ClassicCardBelonging extends ClassicCard implements CardBelongingToPlayer {

    /**
     * Constructs a {@link ClassicCardBelonging}. This {@link Card} needs a
     * description and the {@link Action} that is able to fulfill.
     * 
     * @param description
     *            - the {@link Card}'s description
     * @param action
     *            - the {@link Card}'s {@link Action}
     */
    public ClassicCardBelonging(final String description, final Action... action) {
        super(description, action);
    }

    @Override
    public void addItselfToPlayer() {
        new AddCardToPlayer(this).play(this.getPlayer());
    }

}
