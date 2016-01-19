package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.table.Ownership;

/**
 * This class represent one of the {@link MoneyAction}s of the game. This one
 * allows to mortgage on {@link Ownership}s.
 *
 */
public class ToMortgage extends ToMortgageAndRevoke {

    private final Ownership ownership;

    /**
     * Construct a new instance of {@link ToMortgage}. The {@link Ownership} in
     * input is the {@link Ownership} to mortgage.
     * 
     * @param ownership
     *            - the {@link Ownership} to mortgage
     * @param amount
     *            - the amount of the mortgage
     * @throws IllegalArgumentException
     *             - if the amount is less than or equal to zero
     */
    public ToMortgage(final int amount, final Ownership ownership) {
        super(amount);
        if (amount <= 0) {
            throw new IllegalArgumentException("Only positive amount different of zero!");
        }
        this.ownership = ownership;
    }

    @Override
    protected void strategyOfMortgaging() {
        this.ownership.setMortgage(true);
    }

}
