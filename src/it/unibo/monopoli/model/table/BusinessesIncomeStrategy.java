package it.unibo.monopoli.model.table;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.monopoli.model.mainunits.Player;

public abstract class BusinessesIncomeStrategy extends AbstractIncomeStartegy {

    private final Ownership ownership;

    public BusinessesIncomeStrategy(final Ownership ownership) {
        super(ownership);
        this.ownership = ownership;
    }

    @Override
    protected int getSpecificIncome(List<Ownership> allMembers) {
        final Set<Ownership> playersMembers = new HashSet<>();
        allMembers.stream()
                  .filter(m -> ((Player) this.ownership.getOwner()).getOwnerships().contains(m))
                  .forEach(m -> playersMembers.add(m));
        return this.getBusinessesIncome(playersMembers.size());
    }
    
    protected abstract int getBusinessesIncome(final int size);

}
