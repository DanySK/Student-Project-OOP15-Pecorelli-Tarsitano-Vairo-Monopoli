package it.unibo.monopoli.view;

import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoli.controller.Actions;
import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.mainunits.Player;

public class InPlayImpl implements InPlay {
    
    private final List<Actions> buttons;

    public InPlayImpl() {
     this.buttons = new LinkedList<>();
    }
    
    @Override
    public void setButton(List<Actions> name) {
        this.buttons.clear();
        this.buttons.addAll(name);

    }

    @Override
    public void gameOver(Player player) {
        // TODO Auto-generated method stub

    }
    
    public List<Actions> getButtons(){
        return this.buttons;
    }

}
