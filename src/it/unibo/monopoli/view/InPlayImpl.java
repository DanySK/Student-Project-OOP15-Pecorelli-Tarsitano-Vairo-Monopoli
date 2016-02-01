package it.unibo.monopoli.view;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

import it.unibo.monopoli.controller.Actions;
import it.unibo.monopoli.controller.Controller;
import it.unibo.monopoli.model.mainunits.Player;

public class InPlayImpl implements InPlay {

    private final List<Actions> buttons;

    public InPlayImpl() {
     this.buttons = new LinkedList<>();
    }

    @Override
    public void setButton(final List<Actions> name) {
        this.buttons.clear();
        this.buttons.addAll(name);

    }

    public List<Actions> getButtons(){
        return this.buttons;
    }

    @Override
    public void gameOver(final Player player) {
        new Dialog(new JFrame(), "Game over", "The player" + player.getName() + "has lost");
    }

    @Override
    public void computerTurn(final Player player) {
        new Dialog(new JFrame(), "Computer player", "Next player is " + player.getName());
    }

    @Override
    public void drawCard(final String description) {
        new Dialog(new JFrame(), "You have drew a card", description);
    }

    @Override
    public void notifyEndTurnComputer(final Player player) {
        
    }

}
