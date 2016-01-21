package it.unibo.monopoli.model.actions;

import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoli.model.mainunits.ClassicDice;
import it.unibo.monopoli.model.mainunits.Dice;
import it.unibo.monopoli.model.mainunits.Player;

/**
 * This is an implementation of a classic {@link DicesStrategy} for this game.
 *
 */
public class ClassicDicesStrategy implements DicesStrategy {

    @Override
    public List<Dice> getDices() {
        final List<Dice> dices = new LinkedList<>();
        dices.add(new ClassicDice());
        dices.add(new ClassicDice());
        return dices;
    }

    @Override
    public void strategy(final List<Dice> dices, final Player player) {
        if (dices.size() != 2) {
            throw new IllegalArgumentException("This version needs two dices");
        }
        player.isInPrison() 
            ? this.prisonStrategy(this.twice(dices), player) 
            : player.getPawn().setPos(player.getPawn().getActualPos() + this.dices.get(0).getLastNumberObtained() + this.dices.get(1).getLastNumberObtained());
    }

    private void prisonStrategy(final boolean isTwiceNumber, final Player player) {
        if (isTwiceNumber) {
            player.setDicesRoll(false);
            player.setPrison(false);
        }
    }

    private boolean twice(final List<Dice> dices) {
        if (dices.get(0).getLastNumberObtained() == dices.get(1).getLastNumberObtained()) {
            return true;
        }
        return false;
    }

}
