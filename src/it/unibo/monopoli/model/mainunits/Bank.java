package it.unibo.monopoli.model.mainunits;

import java.util.List;

import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Contract;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This interface represent the only one bank in the game. Here is were all the
 * money, {@link Building}s and {@link Contract}s are keep at the beginning.
 *
 */
public interface Bank extends Owner {

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
     * Returns the {@link Ownership} with the ID in
     * input and removes it from the {@link Bank}.
     * 
     * @param ownershipsIndex
     *            - the ID of the {@link Ownership}
     * @return the specific {@link Ownership}
     */
    Ownership getOwnership(int ownershipsIndex);

    /**
     * Returns a random {@link Ownership} from those belonging to the
     * {@link Bank} and removes it.
     * 
     * @return a random {@link Ownership}
     */
    Ownership getOwnership();

    /**
     * Add a new {@link Building}.
     * 
     * @param building
     *            - the one to add
     */
    void addBuilding(Building building);

    /**
     * Returns a {@link Building} of the same type as the input and removes it
     * from the {@link Bank}.
     * 
     * @param buildingsType
     *            - the type of {@link Building} to get
     * @return a {@link Bank}'s {@link Building} of the same type as the input
     */
    Building getBuilding(Building buildingsType);

}
