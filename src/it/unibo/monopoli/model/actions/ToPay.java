package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;

/**
 * This class represent one of the {@link MoneyAction}s of the game. This one is
 * for paying.
 *
 */
public class ToPay extends ToPayAndBePaid {

    /**
     * Construct a new instance of {@link ToPay}. The amount in input is the
     * amount to pay.
     * 
     * @param amount
     *            - the amount to pay
     * @throws IllegalArgumentException
     *             - if the player's moneys are less than the amount to pay
     */
	public ToPay(final int amount){
		super(amount);
	}
	
    public ToPay(final int amount, final Player player) {
        super(-amount);
        if (amount > player.getMoney()) {
            throw new IllegalArgumentException();
        }
    }

}
