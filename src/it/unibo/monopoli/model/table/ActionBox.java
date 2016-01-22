package it.unibo.monopoli.model.table;

import java.util.Set;

import it.unibo.monopoli.model.actions.Action;

/**
 * This interface represent all the {@link ActionBox}es in the game's table.
 * Each box has some {@link Action}s to accomplish.
 *
 */
public interface ActionBox extends Box {

    /**
     * Return a {@link Set} of allowed {@link Action}s to do in this
     * {@link ActionBox} .
     * 
     * @return a {@link Set} of {@link Action}
     */
    Set<Action> getAllowedActions();

    /**
     * Sets the allowed {@link Action}s of the {@link ActionBox}.
     * 
     * @param actions
     *            - the allowed {@link Action}s
     */
    void setAllowedActions(Set<Action> actions);

    /**
     * Return a {@link Set} of obligatory {@link Action}s to do in this
     * {@link ActionBox}.
     * 
     * @return a {@link Set} of {@link Action}
     */
    Set<Action> getObligatoryActions();

    /**
     * Sets the obligatory {@link Action}s of the {@link ActionBox}.
     * 
     * @param actions
     *            - the obligatory {@link Action}s
     */
    void setObligatoryActions(Set<Action> actions);

}
