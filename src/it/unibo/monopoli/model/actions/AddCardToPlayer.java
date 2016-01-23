package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.mainunits.Player;

/**
 * This class represent one of the {@link Action}s of the game. This one is for
 * adding a {@link Card} to a {@link Player}.
 *
 */
public class AddCardToPlayer implements Action {

    private final Card card;

    /**
     * Constructs an instance of this {@link Action} that allowed to add a
     * {@link Card} in {@link Player}'s {@link Deck}.
     * 
     * @param card
     *            - the {@link Card} to add
     */
    public AddCardToPlayer(final Card card) {
        this.card = card;
    }

    @Override
    public void play(final Player player) {
        player.addCard(this.card);
    }

}
