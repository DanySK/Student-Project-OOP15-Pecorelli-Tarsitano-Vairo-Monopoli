package it.unibo.monopoli.model.mainunits;

import java.util.Set;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Contract;
import it.unibo.monopoli.model.table.Group;

/**
 * This is the interface that specifies witch instances are to be used depending
 * on the chosen game's version.
 *
 */
public interface GameVersion {

    /**
     * This method return all the {@link Building}s required for the specific
     * version.
     * 
     * @return a {@link Set} of {@link Building}s
     */
    Set<Building> getBuildings();

    /**
     * This method return all the {@link Box}es required for the specific
     * version. This {@link Box}es bring with them also the informations about
     * {@link Contract}s, {@link Action}s and {@link Group}s.
     * 
     * @return a {@link Set} of {@link Box}es
     */
    Set<Box> getBoxes();

    /**
     * This method return an instance of the only one {@link Bank}, specific of
     * this version.
     * 
     * @return a {@link Bank}
     */
    Bank getBank();

    /**
     * This method return all the {@link Action}s required for the specific
     * version.
     * 
     * @return a {@link Bank}
     */
    Set<Action> getAllActions();

}
