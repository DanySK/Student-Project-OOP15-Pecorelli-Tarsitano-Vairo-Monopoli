package it.unibo.monopoli.model.actions;

import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoli.model.mainunits.ClassicDice;
import it.unibo.monopoli.model.mainunits.Dice;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;

public class ItalianDicesStrategy implements DicesStrategy {

    private Player player;
    private final Box prison;

    public ItalianDicesStrategy(final Box prison) {
        this.prison = prison;
    }

    @Override
    public List<Dice> getDices() {
        final List<Dice> dices = new LinkedList<>();
        dices.add(new ClassicDice());
        dices.add(new ClassicDice());
        dices.add(new ClassicDice());
        return dices;
    }

    @Override
    public void nowPlay(final Player player) {
        this.player = player;
        if (!player.isInPrison() && !this.triple()) {
            player.getPawn().setPos(player.getPawn().getActualPos() + this.sum());
        }
        if (this.triple()) {
            new GoToPrison(this.prison).play(player);
        } else if (this.twice()) {
            if (player.isInPrison()) {
                player.setPrison(false);
                player.getPawn().setPos(player.getPawn().getActualPos() + this.sum());
            } else {
                player.setDicesRoll(false);
            }
        } else {
            if (player.isInPrison() && player.howManyTurnsHasBeenInPrison() < 3) {
                player.incrementsTurnsInPrison();
            }
        }
    }

    private boolean twice() {
        return this.player.lastDicesNumber().get(0) == this.player.lastDicesNumber().get(1)
                || this.player.lastDicesNumber().get(0) == this.player.lastDicesNumber().get(2)
                || this.player.lastDicesNumber().get(1) == this.player.lastDicesNumber().get(2);
    }

    private boolean triple() {
        return this.player.lastDicesNumber().get(0) == this.player.lastDicesNumber().get(1)
                && this.player.lastDicesNumber().get(1) == this.player.lastDicesNumber().get(2);
    }

    private int sum() {
        return this.player.lastDicesNumber().get(0) + this.player.lastDicesNumber().get(1) + this.player.lastDicesNumber().get(2);
    }

}
