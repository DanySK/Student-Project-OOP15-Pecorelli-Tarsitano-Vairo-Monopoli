package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Deck;

/**
 * This class represent one of the {@link Action}s of the game. This one allows
 * to buy {@link Card}s.
 *
 */
public class ToBuyCards extends ToBuyAndSellCards {

    private final Card card;

    /**
     * Construct a new instance of {@link ToBuyCards}. The {@link Card} in input
     * is the card to buy.
     * 
     * @param card
     *            - the {@link Card} to buy
     * @param amount
     *            - the amount of the {@link Card}
     */
    public ToBuyCards(final Card card, final int amount) {
        super(-amount);
        this.card = card;
    }

    @Override
    protected void strategy(final Deck deck) {
        deck.addCard(this.card);
    }

}
