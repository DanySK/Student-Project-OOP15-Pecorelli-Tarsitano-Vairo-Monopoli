package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.LandGroup;
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
     * @throws IllegalArgumentException
     *             - if the {@link LandGroup} of the {@link Land} to mortgage
     *             has some {@link Building}s
     */
    public ToMortgage(final int amount, final Ownership ownership) {
        super(amount);
        if (amount <= 0) {
            throw new IllegalArgumentException("Only positive amount different of zero!");
        }
        if (ownership instanceof Land && ((LandGroup) ownership.getGroup()).getBuildings().isEmpty()) {
            throw new IllegalArgumentException("Can't mortgage a land with buildings. Before, sell all buildings");
        }
        this.ownership = ownership;
    }

    @Override
    protected void strategyOfMortgaging() {
        this.ownership.setMortgage(true);
    }

}
