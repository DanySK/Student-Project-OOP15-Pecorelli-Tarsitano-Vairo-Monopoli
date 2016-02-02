package it.unibo.monopoli.model.mainunits;

import java.util.Random;

/**
 * This is an implementation of the classic {@link Dice} with 6 faces.
 *
 */
public class ClassicDice implements Dice {

    private static final int FACES = 6;
    private static final Random RANDOM = new Random();

    @Override
    public int roll() {
        return RANDOM.nextInt(FACES) + 1;
    }

}
