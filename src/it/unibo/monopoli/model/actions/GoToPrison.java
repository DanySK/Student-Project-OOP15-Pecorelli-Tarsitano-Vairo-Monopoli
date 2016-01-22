package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Pawn;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.ActionBox;
import it.unibo.monopoli.model.table.Prison;

/**
 * This class represent one of the {@link Action}s of the game. This one is for
 * send to {@link Prison} a {@link Player}.
 *
 */
public class GoToPrison implements Action {

    private final ActionBox prison;

    /**
     * Constructs a new instance of {@link GoToPrison}'s {@link Action}. The
     * {@link Prison} in input specifies where the {@link Player}'s {@link Pawn}
     * have to go.
     * 
     * @param prison
     *            - the {@link Prison}'s {@link ActionBox}
     */
    public GoToPrison(final ActionBox prison) {
        this.prison = prison;
    }

    @Override
    public void play(final Player player) {
        player.setPrison(true);
        player.getPawn().setPos(this.prison.getID());
    }

}
