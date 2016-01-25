package it.unibo.monopoli.model.table;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.monopoli.model.mainunits.Player;

public abstract class AbstractIncomeStartegy implements IncomeStrategy {

    private final Ownership ownership;

    public AbstractIncomeStartegy(final Ownership ownership) {
        this.ownership = ownership;
    }

    @Override
    public int getIncome() {
        final Set<Ownership> allMembers = this.ownership.getGroup().getMembers();
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
    protected abstract int getSpecificIncome(final Set<Ownership> allMembers);

}
