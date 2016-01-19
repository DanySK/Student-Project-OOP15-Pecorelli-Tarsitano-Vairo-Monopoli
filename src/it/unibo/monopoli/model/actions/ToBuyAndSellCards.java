package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.mainunits.Player;

/**
 * This abstract class represent the common contract of classes
 * {@link ToBuyCards} and {@link ToSellCards}.
 *
 */
public abstract class ToBuyAndSellCards implements Action {

    private final int amount;

    /**
     * This constructor serves to get the amount of the {@link Card} to
     * buy/sell.
     * 
     * @param amount
     *            - of the {@link Card}
     */
    public ToBuyAndSellCards(final int amount) {
        this.amount = amount;
    }

    @Override
    public void play(final Player player) {
        player.setMoney(player.getMoney() + this.amount);
        player.getCards().ifPresent(d -> {
            this.strategy(d);
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
    protected abstract void strategy(final Deck deck);

}
