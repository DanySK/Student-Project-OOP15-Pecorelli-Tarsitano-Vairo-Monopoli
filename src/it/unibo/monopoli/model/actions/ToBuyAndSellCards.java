package it.unibo.monopoli.model.actions;

import java.util.List;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.mainunits.Player;

/**
 * This abstract class represent the common contract of sub-classes that want to
 * buy or sell {@link Card}s.
 *
 */
public abstract class ToBuyAndSellCards extends MoneyAction {

    /**
     * This constructor serves to get the amount of the {@link Card} to
     * buy/sell.
     * 
     * @param amount
     *            - of the {@link Card}
     */
    protected ToBuyAndSellCards(final int amount) {
        super(amount);
    }

    @Override
    protected void strategy(final Player player) {
        player.getCards().ifPresent(d -> {
            this.cardsStrategy(player);
        });
    }

    /**
     * This is an abstract method that the specifics sub-classes have to
     * implements depending on the strategy. This method represent how to
     * operate with the {@link Card} to buy/sell
     * 
     * @param deck
     *            - the {@link Deck} where the {@link Card} have to be add or
     *            remove
     */
    protected abstract void cardsStrategy(final Player player);

}
