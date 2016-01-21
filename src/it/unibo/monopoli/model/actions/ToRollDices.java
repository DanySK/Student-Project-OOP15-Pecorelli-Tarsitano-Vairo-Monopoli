package it.unibo.monopoli.model.actions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import it.unibo.monopoli.model.mainunits.Player;

/**
 * This class represent one of the {@link Action}s of the game. This one is for
 * rolling dices.
 *
 */
public class ToRollDices implements Action {
    
    private final DicesStrategy strategy;
    private final Set<Random> dices;
    
    public ToRollDices(final DicesStrategy strategy) {
        this.strategy = strategy;
        this.dices = new HashSet<>();
    }

    @Override
    public void play(Player player) {
        final List<Integer> numbers = new LinkedList<>();
        for (int i = 0; i < this.strategy.howManyDices(); i++) {
            this.dices.add(new Random());
        }
        this.dices.stream()
                  .forEach(r -> {
                      numbers.add(r.nextInt(6) + 1);
                  });
        player.setDicesRoll(true);
        final int index = this.strategy.whatToDoWhitDicesNumbers(numbers, player);
        player.getPawn().setPos(player.getPawn().getActualPos() + index);
    }

}
