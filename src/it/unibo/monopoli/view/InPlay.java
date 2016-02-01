package it.unibo.monopoli.view;

import java.util.List;

import it.unibo.monopoli.controller.Actions;
import it.unibo.monopoli.controller.Controller;
import it.unibo.monopoli.model.mainunits.Player;

public interface InPlay {

    void setButton(List<Actions> name);

    void gameOver(Player player);
    
    void computerTurn(Player player);
    
    void drawCard(String description);
    
    void notifyEndTurnComputer(Player player);
    
    List<Actions> getButtons();

}
