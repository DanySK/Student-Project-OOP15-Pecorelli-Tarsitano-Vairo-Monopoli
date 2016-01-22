package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Pawn;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;

/**
 * This class represent one of the {@link Action}s of the game. This one is for
 * moving up to a specific {@link Box}. It uses the actual position of the
 * {@link Player}'s {@link Pawn} and the number of steps that it has to take.
 *
 */
public class MoveUpTo implements Action {

    private final int stepsToTake;
    private final Box box;

    private MoveUpTo(final int stepsToTake) {
        this.stepsToTake = stepsToTake;
        this.box = null;
    }

    private MoveUpTo(final Box box) {
        this.stepsToTake = 0;
        this.box = box;
    }

    /**
     * Constructs an instance of this specific {@link Action}. It needs the
     * number of the steps that the {@link Player}'s {@link Pawn} has to take.
     * This number can be positive (if it has to go forward) or negative (if it
     * has to go back).
     * 
     * @param stepsToTake
     *            - the number of the steps to take
     * @throws IllegalArgumentException
     *             - if the number in input is zero
     */
    public static MoveUpTo takeSteps(final int stepsToTake) {
        if (stepsToTake == 0) {
            throw new IllegalArgumentException("The steps to take can't be zero");
        }
        new MoveUpTo(stepsToTake);
    }

    /**
     * Constructs an instance of this specific {@link Action}. It needs the
     * {@link Box} to which that the {@link Player}'s {@link Pawn} has to go.
     * 
     * @param box
     *            - the {@link Box} to which to go
     * @throws IllegalArgumentException
     *             - if the {@link Box} in input is null
     */
    public static MoveUpTo moveUpToBox(final Box box) {
        if (box == null) {
            throw new NullPointerException();
        }
        new MoveUpTo(box);
    }

    @Override
    public void play(final Player player) {
        (this.box == null) 
                ? player.getPawn().setPos(player.getPawn().getActualPos() + this.stepsToTake);
                : player.getPawn().setPos(this.box.getID());
    }

}
