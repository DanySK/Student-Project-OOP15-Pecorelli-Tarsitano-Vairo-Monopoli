package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.mainunits.Player;

/**
 * This abstract class represent the common contract of all classes that want to
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
    protected abstract void strategy(Player player);

//    /**
//     * This is an abstract method that the sub-classes have to implements
//     * depending on the strategy. This method represent how to operate with the
//     * {@link Card} to buy/sell
//     * 
//     * @param player
//     *            - the {@link Player} who want to buy/sell the {@link Card}
//     */
//    protected abstract void cardsStrategy(final Player player);

}
