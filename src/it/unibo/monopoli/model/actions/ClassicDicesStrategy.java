package it.unibo.monopoli.model.actions;

import java.util.List;

import it.unibo.monopoli.model.mainunits.Player;

public class ClassicDicesStrategy implements DicesStrategy {

    @Override
    public int howManyDices() {
        return 2;
    }

    @Override
    public int whatToDoWhitDicesNumbers(final List<Integer> numbers, final Player player) {
        if (numbers.get(0).equals(numbers.get(1))) {
            player.setDicesRoll(false);
        }
        return numbers.get(0) + numbers.get(1);
    }

}
