package it.unibo.monopoli.model.table;

/**
 * This is an implementation of {@link Building}. It represents a normal
 * {@link Home}.
 *
 */
public class Home implements Building {

    private final int ID;

    /**
     * Constructs an instance of this class. The ID in input is the one to
     * identify the specific {@link Home}.
     * 
     * @param ID
     *            - to identify the specific {@link Home}
     */
    public Home(final int ID) {
        this.ID = ID;
    }

    @Override
    public int getID() {
        return this.ID;
    }

}
