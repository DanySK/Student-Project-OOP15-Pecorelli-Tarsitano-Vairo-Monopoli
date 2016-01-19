package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This abstract class represent the common contract of sub-classes that want to
 * mortgage or revoke a mortgage on a {@link Ownership}.
 *
 */
public abstract class ToMortgageAndRevoke implements Action {

    private final int amount;

    /**
     * This constructor serves to get the amount of the mortgage or the revoke.
     * 
     * @param amount
     *            - of the of the mortgage or the revoke
     */
    protected ToMortgageAndRevoke(final int amount) {
        this.amount = amount;
    }

    @Override
    public void play(final Player player) {
        player.setMoney(player.getMoney() + this.amount);
        this.strategyOfMortgaging();
    }

    /**
     * This is an abstract method that the specifics sub-classes have to
     * implements depending on the strategy. This method represent how to
     * operate with the mortgaging.
     */
    protected abstract void strategyOfMortgaging();

}
