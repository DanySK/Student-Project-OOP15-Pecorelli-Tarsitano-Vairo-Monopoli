package it.unibo.monopoli.model.table;

import java.util.List;

public abstract class AbstractIncomeStartegy implements IncomeStrategy {

    private final Ownership ownership;

    public AbstractIncomeStartegy(final Ownership ownership) {
        this.ownership = ownership;
    }

    @Override
    public int getIncome() {
        final List<Ownership> allMembers = this.ownership.getGroup().getMembers();
        return this.getSpecificIncome(allMembers);
    }

    /**
     * Return the income of the specific {@link Ownership} according to the
     * tactics of the game.
     * 
     * @param allMembers
     *            - all the members owned to the specific {@link Ownership}'s
     *            {@link Group}.
     * @return the income of the {@link Ownership}
     */
    protected abstract int getSpecificIncome(final List<Ownership> allMembers);

}
