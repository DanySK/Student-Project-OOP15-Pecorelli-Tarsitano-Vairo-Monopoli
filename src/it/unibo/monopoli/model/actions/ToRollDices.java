package it.unibo.monopoli.model.actions;

import java.util.List;

import it.unibo.monopoli.model.mainunits.Dice;
import it.unibo.monopoli.model.mainunits.Player;

/**
 * This class represent one of the {@link Action}s of the game. This one is for
 * rolling dices.
 *
 */
public class ToRollDices implements Action {

    private final DicesStrategy strategy;
    private final List<Dice> dices;

    /**
     * Constructs a new instance of {@link ToRollDices}'s {@link Action}. The
     * {@link DicesStrategy} in input specifies how to use this dices.
     * 
     * @param strategy
     *            - the {@link DicesStrategy} to use
     */
    public ToRollDices(final DicesStrategy strategy) {
        this.strategy = strategy;
        this.dices = strategy.getDices();
    }

    @Override
    public void play(final Player player) {
        this.dices.stream().forEach(d -> d.roll());
        player.setDicesRoll(true);
        final int index = this.dices.get(0).getLastNumberObtained() + this.dices.get(1).getLastNumberObtained();
        player.getPawn().setPos(player.getPawn().getActualPos() + index);
        this.strategy.strategy(this.dices, player);
    }

}
