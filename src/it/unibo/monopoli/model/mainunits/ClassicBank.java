package it.unibo.monopoli.model.mainunits;

import java.util.List;
import java.util.Set;

import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Contract;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This class implements the contract of {@link Bank} and represents the classic Bank of Monopoly
 *
 */
public class ClassicBank implements Bank {

    private final List<Ownership> ownerships;

    /**
     * Constructs an instance of a {@link ClassicBank}
     * @param ownerships
     */
    public ClassicBank(final List<Ownership> ownerships) {
        this.ownerships = ownerships;
    }

    @Override
    public Set<Contract> getLeftContract() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Building> getLeftBuilding() {
        // TODO Auto-generated method stub
        return null;
    }

}
