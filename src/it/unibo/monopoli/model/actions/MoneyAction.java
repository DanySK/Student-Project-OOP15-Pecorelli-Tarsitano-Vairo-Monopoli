package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;

/**
 * This abstract class represent the common contract for all the {@link Action}
 * that uses money.
 *
 */
public abstract class MoneyAction implements Action {

    private final int amount;

    /**
     * This constructor serves to get the amount of the {@link Action}.
     * 
     * @param amount
     *            - of the of the {@link Action}
     */
    protected MoneyAction(final int amount) {
        this.amount = amount;
    }

    @Override
    public void play(final Player player) {
        player.setMoney(player.getMoney() + this.amount);
        this.strategy(player);
    }

    /**
     * This is an abstract method that the specifics sub-classes have to
     * implements depending on the strategy. This method represent how to
     * operate with {@link Action}s with money.
     * 
     * @param player
     *            - the {@link Player} who performs the {@link MoneyAction}
     */
    protected abstract void strategy(final Player player);
}
