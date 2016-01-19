package it.unibo.monopoli.model.table;

import java.util.Set;

import it.unibo.monopoli.model.actions.Action;

/**
 * This interface represent all the boxes in the game's table. Each box have a
 * name, a ID that identifies it and some {@link Action}s to accomplish.
 *
 */
public interface Box {

    /**
     * Return the name that identifies the {@link Box}.
     * 
     * @return the name of the {@link Box}
     */
    String getName();

    /**
     * Return the ID of the {@link Box}.
     * 
     * @return the ID of the {@link Box}
     */
    int getID();

    /**
     * Return a {@link Set} of allowed {@link Action}s to do in this {@link Box}
     * .
     * 
     * @return a {@link Set} of {@link Action}
     */
    Set<Action> getAllowedActions();

    /**
     * Return a {@link Set} of obligatory {@link Action}s to do in this
     * {@link Box}.
     * 
     * @return a {@link Set} of {@link Action}
     */
    Set<Action> getObligatoryActions();

}
