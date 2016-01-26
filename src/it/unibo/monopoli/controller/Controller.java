package it.unibo.monopoli.controller;

import java.util.List;

import javax.swing.JTextField;

import it.unibo.monopoli.model.actions.ToBuyProperties;
import it.unibo.monopoli.model.actions.ToMortgage;
import it.unibo.monopoli.model.actions.ToRevokeMortgage;
import it.unibo.monopoli.model.actions.ToSellProperties;
import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.mainunits.Bank;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.Ownership;
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
    //void setStrategy();
    /**
     * This method allow to get all Boxes
     * @return {@link List} of {@link Boxes}
     */

    void addPlayer(final JTextField name, final int idPawn, final int typePlayer);

    /**
     * This method allow to get set of all Box.
     * 
     * @return Set of {@link Box}
     */
    List<Box> getAllBoxes() ;

    /**
     * This method allow to get Bank.
     * 
     * @return {@link Bank}
     */
    Bank getBank();

    /**
     * Remove player from list.
     * 
     * @param name
     *            .
     */
    void removePlayer(final JTextField name);

    /**
     * This method get the list of player {@link Player}.
     * 
     * @return the list <@link List> of player <@link Player>
     */
    List<Player> getListPlayer();

    /**
     * Return the id of the actual player.
     * 
     * @return actual player .
     */
    Player getActualPlayer();

    /**
     * Method for buy a property by id.
     * 
     * @param amount
     *            .
     * @param ownership
     *            .
     */
    void buyOwnership(final int amount, final Ownership ownership);

    /**
     * method for sell a property by id.
     * 
     * @param amount
     *            .
     * @param ownership
     *            .
     */
    void sellOwnership(final int amount, final Ownership ownership) ;

    /**
     * This method allow to build house or hotel on {@link Land}.
     * 
     * @param amount
     *            -cost of building
     * @param land
     *            .
     * @param building
     *            -house to add
     */
    void buyBuilding(final int amount, final Land land, final Building building);

    /**
     * This method allow to mortgage a property by id.
     * 
     * @param amount
     *            .
     * @param ownership
     *            .
     */
    void mortgageOwnership(final int amount, final Ownership ownership);

    /**
     * This method allow to revoke mortgage a property by id.
     * 
     * @param id
     *            .
     */
    void revokeMortgageOwnership(final int amount, final Ownership ownership) ;

    /**
     * this method allow to accept trade between two player.
     * 
     * @param amount
     *            - amount of trade
     * @param ownership
     *            - the {@link Ownership} to trade
     * @param tradePlayer
     *            - the {@link Player} for the trade
     */
    void acceptTrade(final int amount, final Ownership ownership, final Player tradePlayer);

    /**
     * This method allow to get the new position.
     * 
     * @return new position
     */
    int getNewPosition();

    /**
     * go To next player.
     * 
     * @return the integer for next player
     */
    Player endTurn();
    /**
     * This method allow to roll dice.
     * 
     * @return the list of {@link Dices} rolled;
     */
    List<Integer> toRollDices();
    /**
     * This method allow to get the result of dices .
     * 
     * @return the result of rolling dices .
     */
    int getDicesResult();


}
