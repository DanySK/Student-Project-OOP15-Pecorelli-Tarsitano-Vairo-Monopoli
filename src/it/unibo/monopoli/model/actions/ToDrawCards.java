package it.unibo.monopoli.model.actions;

import java.util.Random;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.mainunits.Player;

/**
 * This class represent one of the {@link Action}s of the game. This one is
 * for drawing {@link Card}s.
 *
 */
public class ToDrawCards implements Action {
    
    private final Deck deck;
    
    public ToDrawCards(final Deck deck) {
        this.deck = deck;
    }

    @Override
    public void play(Player player) {
        Random r = new Random();
        r.nextInt(this.deck.getCards().size());
    }

}
