package it.unibo.monopoli.model.table;

/**
 * This interface represent all the {@link Box}es in the game's table. Each box
 * have a name and an ID that identifies it.
 *
 */
public interface Box {

    /**
     * Return the name that identifies the {@link ActionBox}.
     * 
     * @return the name of the {@link ActionBox}
     */
    String getName();

    /**
     * Return the ID of the {@link ActionBox}.
     * 
     * @return the ID of the {@link ActionBox}
     */
    int getID();
}
