package it.unibo.monopoli.model.actions;

import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoli.model.mainunits.ClassicDice;
import it.unibo.monopoli.model.mainunits.Dice;
import it.unibo.monopoli.model.mainunits.GameVersion;
import it.unibo.monopoli.model.mainunits.Player;

/**
 * This is an implementation of a classic {@link DicesStrategy} for this game.
 *
 */
public class ClassicDicesStrategy implements DicesStrategy {
    
    private final GameVersion version;
    
    public ClassicDicesStrategy(final GameVersion version) {
        this.version = version;
    }

    @Override
    public List<Dice> getDices() {
        final List<Dice> dices = new LinkedList<>();
        dices.add(new ClassicDice());
        dices.add(new ClassicDice());
        return dices;
    }

    @Override
    public void nowPlay(final List<Dice> dices, final Player player) {
        if (dices.size() != 2) {
            throw new IllegalArgumentException("This version needs two dices");
        }
        if (player.isInPrison() && !this.twice(player)) {
            this.version.endOfTurnAndNextPlayer(player);
        } else {
            player.setPrison(false);
            MoveUpTo.takeSteps(player.lastDicesNumber().get(0) + player.lastDicesNumber().get(1));
        }
    }

    private boolean twice(final Player player) {
        return player.lastDicesNumber().get(0) == player.lastDicesNumber().get(1); 
    }

}
