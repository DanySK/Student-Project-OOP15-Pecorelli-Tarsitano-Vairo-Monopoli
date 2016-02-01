package it.unibo.monopoli.view;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

import it.unibo.monopoli.controller.Actions;
import it.unibo.monopoli.controller.Controller;
import it.unibo.monopoli.model.mainunits.Player;

public class InPlayImpl implements InPlay {

    private final List<Actions> buttons;
<<<<<<< local
    private Index i;
=======
    private Index index;
>>>>>>> other

<<<<<<< local
    public InPlayImpl(Index i) {
=======
    public InPlayImpl(final Index index) {
>>>>>>> other
     this.buttons = new LinkedList<>();
<<<<<<< local
     this.i = i;
=======
     this.index = index;
>>>>>>> other
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
<<<<<<< local
        this.i.computerTurn(player);
=======
        this.index.computerTurn(player);
>>>>>>> other
    }
    
//    @Override
//    public void beginComputer(int prePos){
//        this.i.prevPos(prePos);
//    }

}
