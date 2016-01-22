package it.unibo.monopoli.model.table;

/**
 * This is an implementation of {@link Building}. It represents a normal
 * {@link Home}.
 *
 */
public class Hotel implements Building {

    private final int ID;

    /**
     * Constructs an instance of this class. The ID in input is the one to
     * identify the specific {@link Hotel}.
     * 
     * @param ID
     *            - to identify the specific {@link Hotel}
     */
    public Hotel(final int ID) {
        this.ID = ID;
    }

    @Override
    public int getID() {
        return this.ID;
    }

}
