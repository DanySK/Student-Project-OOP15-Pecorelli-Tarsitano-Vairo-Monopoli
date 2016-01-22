package it.unibo.monopoli.model.mainunits;

import java.util.Random;

/**
 * This is an implementation of the classic {@link Dice} with 6 faces.
 *
 */
public class ClassicDice implements Dice {

    private static final int FACES = 6;

    private int lastNumber;

    @Override
    public void roll() {
        this.lastNumber = new Random().nextInt(FACES) + 1;
    }

    @Override
    public int getLastNumberObtained() {
        return this.lastNumber;
    }

}
