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

    private static final int FIRST_USEFUL_POSITION = 28;
    private static final int LAST_USEFUL_POSITION = 11;

    @Override
    public List<Dice> getDices() {
        final List<Dice> dices = new LinkedList<>();
        dices.add(new ClassicDice());
        dices.add(new ClassicDice());
        return dices;
    }

    @Override
    public void nowPlay(final Player player) {
        if (!player.isInPrison()) {
            player.getPawn().setPos(player.getPawn().getActualPos() + player.lastDicesNumber().get(0) + player.lastDicesNumber().get(1));
        }
        if (this.twice(player)) {
            if (player.isInPrison()) {
                player.setPrison(false);
                player.getPawn().setPos(player.getPawn().getActualPos() + player.lastDicesNumber().get(0) + player.lastDicesNumber().get(1));
            } else {
                player.setDicesRoll(false);
            }
        } else {
            if (player.isInPrison() && player.howManyTurnsHasBeenInPrison() < 3) {
                player.incrementsTurnsInPrison();
            }
        }
        if (this.isPassedFromStartBox(player)) {
            new PassFromStar().play(player);
        }
    }

    private boolean twice(final Player player) {
        return player.lastDicesNumber().get(0) == player.lastDicesNumber().get(1); 
    }

    private boolean isPassedFromStartBox(final Player player) {
        return player.getPawn().getPreviousPos() >= FIRST_USEFUL_POSITION
                && player.getPawn().getActualPos() <= LAST_USEFUL_POSITION;
    }

}
