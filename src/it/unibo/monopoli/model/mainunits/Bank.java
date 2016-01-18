package it.unibo.monopoli.model.mainunits;

import java.util.Set;

import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Contract;

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
