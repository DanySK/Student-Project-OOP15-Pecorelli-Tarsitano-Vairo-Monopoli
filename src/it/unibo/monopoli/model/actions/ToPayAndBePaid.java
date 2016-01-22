package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;

/**
 * This abstract class represent the common contract of sub-classes that
 * represent who want to pay or to be paid.
 *
 */
public abstract class ToPayAndBePaid extends MoneyAction {

    /**
     * This constructor serves to get the amount to pay or with which you will
     * be paid.
     * 
     * @param amount
     *            - to pay or with which you will be paid
     */
    protected ToPayAndBePaid(final int amount) {
        super(amount);
    }

    @Override
    protected void strategy(final Player player) {
    };

}
