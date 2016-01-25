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
public final class MoveUpTo implements Action {

    private static final int FIRST_STATION = 5;
    private static final int SECOND_STATION = 15;
    private static final int THIRD_STATION = 25;
    private static final int FOURTH_STATION = 35;
    private static final int LAST_BOX = 39;

    private final int stepsToTake;
    private final Box box;

    private MoveUpTo() {
        this.stepsToTake = 0;
        this.box = null;
    }

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
     * @return an instance of {@link MoveUpTo}
     */
    public static MoveUpTo takeSteps(final int stepsToTake) {
        if (stepsToTake == 0) {
            throw new IllegalArgumentException("The steps to take can't be zero");
        }
        return new MoveUpTo(stepsToTake);
    }

    /**
     * Constructs an instance of this specific {@link Action}. It needs the
     * {@link Box} to which that the {@link Player}'s {@link Pawn} has to go.
     * 
     * @param box
     *            - the {@link Box} to which to go
     * @throws IllegalArgumentException
     *             - if the {@link Box} in input is null
     * @return an instance of {@link MoveUpTo}
     */
    public static MoveUpTo moveUpToBox(final Box box) {
        if (box == null) {
            throw new IllegalArgumentException("The box in input can't be null");
        }
        return new MoveUpTo(box);
    }

    /**
     * Constructs an instance of this specific {@link Action}. It needs the
     * {@link Box} to which that the {@link Player}'s {@link Pawn} has to go.
     * 
     * @return an instance of {@link MoveUpTo}
     */
    public static MoveUpTo theNearestStation() {
        return new MoveUpTo();
    }

    @Override
    public void play(final Player player) {
        if (this.box == null && this.stepsToTake == 0) {
            final int i = player.getPawn().getActualPos();
            if ((i >= 0 && i <= FIRST_STATION) || (i > FOURTH_STATION && i <= LAST_BOX)) {
                player.getPawn().setPos(FIRST_STATION);
            }
            if (i > FIRST_STATION && i <= SECOND_STATION) {
                player.getPawn().setPos(SECOND_STATION);
            }
            if (i > SECOND_STATION && i <= THIRD_STATION) {
                player.getPawn().setPos(THIRD_STATION);
            }
            if (i > THIRD_STATION && i <= FOURTH_STATION) {
                player.getPawn().setPos(FOURTH_STATION);
            }
        }
        if (this.box == null) {
            player.getPawn().setPos(player.getPawn().getActualPos() + this.stepsToTake);
        } else {
            player.getPawn().setPos(this.box.getID());
        }
    }

}
