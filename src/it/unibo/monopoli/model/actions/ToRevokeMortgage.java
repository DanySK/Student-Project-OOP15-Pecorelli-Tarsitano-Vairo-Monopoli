package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.table.Ownership;

/**
 * This class represent one of the {@link MoneyAction}s of the game. This one
 * allows to revoke mortgage on {@link Ownership}s.
 *
 */
public class ToRevokeMortgage extends ToMortgageAndRevoke {

    private final Ownership ownership;

    /**
     * Construct a new instance of {@link ToRevokeMortgage}. The
     * {@link Ownership} in input is the {@link Ownership} with the mortgage to
     * revoke.
     * 
     * @param ownership
     *            - the {@link Ownership} with the mortgage to revoke
     * @param amount
     *            - the amount of the revoke
     * @throws IllegalArgumentException
     *             - if the amount is less than or equal to zero
     */
    public ToRevokeMortgage(final int amount, final Ownership ownership) {
        super(-amount);
        if (amount <= 0) {
            throw new IllegalArgumentException("Only positive amount different of zero!");
        }
        this.ownership = ownership;
    }

    @Override
    protected void strategyOfMortgaging() {
        this.ownership.setMortgage(false);
    }

}
