package it.unibo.monopoli.view;

import java.util.List;

import it.unibo.monopoli.controller.Actions;
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
     *            button's name
     */
    void setButton(List<Actions> name);

    /**
     * 
     * @param player
     *            Player
     */
    void gameOver(Player player);

    /**
     * 
     * @param player
     *            Player
     */
    void computerTurn(Player player);

    /**
     * 
     * @param description
     *            description of the card
     */
    void drawCard(String description);

    /**
     * 
     * @param player
     * Player
     */
    void notifyEndTurnComputer(Player player);

    /**
     * 
     * returns a list of buttons to activate.
     * @return List<Action>
     */
    List<Actions> getButtons();

    /**
     * 
     * @param i
     */
    void beginComputer(int i);
    
    void finish(final Player player);

}
