package it.unibo.monopoli.model.mainunits;

public class ClassicPawn implements Pawn {

    private final int id;
    private int actualPos;
    private int previousPos;

    public ClassicPawn(final int id) {
        this.id = id;
        this.actualPos = 0;
        this.previousPos = 0;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public int getActualPos() {
        return this.actualPos;
    }

    @Override
    public int getPreviousPos() {
        return this.previousPos;
    }

    @Override
    public void setPos(final int newPosizion) {
        this.previousPos = this.actualPos;
        this.actualPos = newPosizion;
    }

}
