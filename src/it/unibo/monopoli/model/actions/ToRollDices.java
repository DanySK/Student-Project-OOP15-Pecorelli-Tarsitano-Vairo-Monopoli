package it.unibo.monopoli.model.actions;

import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoli.model.mainunits.Dice;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;

/**
 * This class represent one of the {@link Action}s of the game. This one is for
 * rolling dices.
 *
 */
public class ToRollDices implements Action {

    private static final int POLICE_POSITION = 30;

    private final DicesStrategy strategy;
    private final List<Dice> dices;
    private final Box prison;

    /**
     * Constructs a new instance of {@link ToRollDices}'s {@link Action}. The
     * {@link DicesStrategy} in input specifies how to use this dices.
     * 
     * @param strategy
     *            - the {@link DicesStrategy} to use
     */
    public ToRollDices(final DicesStrategy strategy, final Box prison) {
        this.strategy = strategy;
        this.dices = strategy.getDices();
        this.prison = prison;
    }

    @Override
    public void play(final Player player) {
        final List<Integer> dicesNumbers = new LinkedList<>();
        this.dices.stream()
                  .forEach(d -> {
                      dicesNumbers.add(d.roll());
                  });
        player.setLastDicesNumber(dicesNumbers);
        player.setDicesRoll(true);
        this.strategy.nowPlay(player);
        if (this.policePos(player)) {
            new GoToPrison(this.prison);
        }
    }

    private boolean policePos(final Player player) {
        return player.getPawn().getActualPos() == POLICE_POSITION; 
    }

}
