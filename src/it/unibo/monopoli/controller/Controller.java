package it.unibo.monopoli.controller;

import java.util.List;

import javax.swing.JTextField;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.mainunits.Pawn;
import it.unibo.monopoli.model.mainunits.Player;
/**
 * 
 *
 *
 */
public interface Controller {
    /**
    * Set the initial strategy of the game.
    * @param strategy
    * - set a strategy {@link JTextField}s
    */
    void setStrategy(JTextField strategy);

    /**
    * add player in a list.
    * @param name .
    * @param idPawn .
    * @param typePlayer .
    */
    void addPlayer(final JTextField name, final int idPawn, final int typePlayer);

    /**
    * Remove player from list.
    * @param name .
    */
    void removePlayer(final JTextField name);

    /**
    * Return the id of the actual player.
    * @return actual player
    */
    int actualPlayer();

    /**
    * Set pawn for player.
    * 
    * @param id .
    */
    void setPawn(int id);

    /**
    * Get pawn.
    * @return pawn
    */
    Pawn getPawn();

    /**
     * Method for throwing dice.
     * @return the number of throwing dice
     */
    int diceThrow();

    /**
    * This method get the list of player {@link Player}.
    * @return the list <@link List> of player <@link Player>
    */
    List<Player> getListPlayer();

    /**
    * add property on actual player.
    * @param property .
    */
    void addProprerty(JTextField property);

    /**
    * go To next player.
    * @return the integer for next player
    */
    int endTurn();

    /**
    * do auction for property.
    * @param property .
    * @return {@link Card}.
    */
    Card auction(JTextField property);

    /**
    *
    */
    void pay();

}
