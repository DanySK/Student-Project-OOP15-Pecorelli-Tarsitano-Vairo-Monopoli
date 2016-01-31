package it.unibo.monopoli.view;

import java.util.List;

import it.unibo.monopoli.controller.Actions;

public interface InPlay {

    void setButton(List<Actions> name);

    void gameOver(it.unibo.monopoli.model.mainunits.Player player);
    
    List<Actions> getButtons();

}
