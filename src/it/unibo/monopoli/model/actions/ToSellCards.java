package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Deck;

/**
 * This class represent one of the {@link Action}s of the game. This one allows
 * to sell {@link Card}s.
 *
 */
public class ToSellCards extends ToBuyAndSellCards {

    private final Card card;

    /**
     * Construct a new instance of {@link ToSellCards}. The {@link Card} in
     * input is the card to sell.
     * 
     * @param card
     *            - the {@link Card} to sell
     * @param amount
     *            - the amount of the {@link Card}
     */
    public ToSellCards(final Card card, final int amount) {
        super(amount);
        this.card = card;
    }

    @Override
    protected void strategy(final Deck deck) {
        deck.removeCard(this.card);
    }

}
