package it.unibo.monopoli.controller;

import java.util.List;

import javax.swing.JTextField;

import it.unibo.monopoli.model.actions.AuctionOfOwnership;
import it.unibo.monopoli.model.mainunits.Bank;
import it.unibo.monopoli.model.mainunits.ClassicPawn;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.Ownership;
import it.unibo.monopoli.view.EVersion;
import it.unibo.monopoli.view.InPlay;

/**
 * 
 *
 *
 */
public interface Controller {

    /**
     * a.
     * 
     * @param name
     *            .
     * @param pawn
     *            .
     * @param isHuman
     *            .
     */
    /**
     * This method add {@link Player} in a list.
     * @param name
     *              -name of the {@link Player}
     * @param pawn
     *              -{@link Pawn} of the {@link Player}
     * @param isHuman
     *              -boolean for type of {@link Player}
     */
    void addPlayer(String name, ClassicPawn pawn, boolean isHuman);
    
    
    void initializedVersion(EVersion versionEnum);

    void addView(InPlay view);

    List<Actions> getButtons();

    /**
     * This method allow to get set of all Box.
     * 
     * @return Set of {@link Box}
     */
    List<Box> getAllBoxes();

    /**
     * This method allow to get Bank.
     * 
     * @return {@link Bank}
     */
    Bank getBank();

    // /**
    // * Remove player from list.
    // *
    // * @param name
    // * .
    // */
    // void removePlayer(final JTextField name);
    //
    /**
     * This method get the list of player {@link Player}.
     * 
     * @return the list <@link List> of player <@link Player>
     */
    List<Player> getPlayers();

    /**
     * This method allow to roll dice.
     * 
     * @return the list of {@link Dices} rolled;
     */
    int toRollDices();

    /**
     * go To next player.
     * 
     * @return the integer for next player
     */
    void endTurn();

    Box getActualBox();
    // /**
    // * Return the id of the actual player.
    // *
    // * @return actual player .
    // */
    // Player getActualPlayer();
    //
    /**
     * Method for buy a property by id. .
     * 
     * @param ownership
     *            .
     */
    void buyOwnership();

    /**
     * method for sell a property by id. .
     * 
     * @param ownership
     *            .
     */
    void sellOwnership();

    AuctionOfOwnership auction();

    /**
     * This method allow to build house or hotel on {@link Land}.
     * 
     * @param land
     */
    void build();

    void sellBuilding();

    /**
     * This method allow to mortgage a property by id. .
     * 
     * @param ownership
     *            .
     */
    void mortgageOwnership();

    /**
     * This method allow to revoke mortgage a property by id. .
     */
    void revokeMortgageOwnership();

//    /**
//     * this method allow to accept trade between two player.
//     */
//    void trade(Ownership firstOwnership, Ownership seconfOwnership, Player firstPlayer, Player secondPlayer);

    /**
     * Returns the winner!
     * 
     * @return - the ordered {@link List} of {@link Player}s from the richest to the poorest 
     */
    List<Player> endGame();
    //
    // /**
    // * This method allow to get the new position.
    // *
    // * @return new position
    // */
    // int getNewPosition();

}
