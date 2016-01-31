package it.unibo.monopoli.model.table;

public class ItalianNeutralArea implements Box {

    private final String name;
    private final int id;
    private int dirtyMoney;

    /**
     * Constructs an implementation of {@link NeutralArea} that needs a name and
     * an ID. This one is for the Italian version of the game.
     * 
     * @param name
     *            - of this {@link Box}
     * @param id
     *            - of this {@link Box}
     */
    public ItalianNeutralArea(final String name, final int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getID() {
        return this.id;
    }

    public int getDirtyMoney() {
        return this.dirtyMoney;
    }

    public void setDirtyMoney(final int dirtyMoney) {
        this.dirtyMoney = dirtyMoney;
    }

    public void resetMoney() {
        this.dirtyMoney = 0;
    }

}
