package it.unibo.monopoli.model.mainunits;

import java.util.Set;

import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Contract;

/**
 * This interface represent the only one bank in the game. Here is were all the
 * money, {@link Building}s and {@link Contract}s are keep at the beginning.
 *
 */
public interface Bank {

    /**
     * Return a {@link Set} of the remained {@link Contract}s.
     * 
     * @return a {@link Set} of {@link Contract}s
     */
    Set<Contract> getLeftContract();

    /**
     * Return a {@link Set} of the remained {@link Building}s.
     * 
     * @return a {@link Set} of {@link Building}s
     */
    Set<Building> getLeftBuilding();

}
