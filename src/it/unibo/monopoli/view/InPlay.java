package it.unibo.monopoli.view;

import java.util.List;

import it.unibo.monopoli.controller.Actions;
import it.unibo.monopoli.controller.Controller;
import it.unibo.monopoli.model.mainunits.Player;

/**
 * interface through which the controller communicates with the view.
 * 
 *
 */
public interface InPlay {

    /**
     * 
     * @param name
     */
    void setButton(List<Actions> name);

    /**
     * 
     * @param player
     */
    void gameOver(Player player);

    /**
     * 
     * @param player
     */
    void computerTurn(Player player);

    /**
     * 
     * @param description
     */
    void drawCard(String description);

    /**
     * 
     * @param player
     */
    void notifyEndTurnComputer(Player player);

    /**
     * 
     * @return
     */
    List<Actions> getButtons();
    
    /**
     * 
     * @param i
     */
    void beginComputer(int i);

}
