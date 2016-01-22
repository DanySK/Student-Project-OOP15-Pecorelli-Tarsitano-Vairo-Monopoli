package it.unibo.monopoli.model.mainunits;

import java.util.List;

import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Contract;

/**
 * This interface represent the only one bank in the game. Here is were all the
 * money, {@link Building}s and {@link Contract}s are keep at the beginning.
 *
 */
public interface Bank {

    /**
     * Return a {@link List} of the remained {@link Contract}s.
     * 
     * @return a {@link List} of {@link Contract}s
     */
    List<Contract> getLeftContract();

    /**
     * Return a {@link List} of the remained {@link Building}s.
     * 
     * @return a {@link List} of {@link Building}s
     */
    List<Building> getLeftBuilding();

    /**
     * Add a new {@link Contract}.
     * 
     * @param contract
     *            - the one to add
     */
    void addContract(Contract contract);

    /**
     * Remove a {@link Contract} from the {@link Bank}.
     * 
     * @param contract
     *            - the one to remove
     */
    void removeContract(Contract contract);

    /**
     * Add a new {@link Building}.
     * 
     * @param building
     *            - the one to add
     */
    void addBuilding(Building building);

    /**
     * Remove a {@link Building} from the {@link Bank}.
     * 
     * @param building
     *            - the one to remove
     */
    void removeBuilding(Building building);

}
