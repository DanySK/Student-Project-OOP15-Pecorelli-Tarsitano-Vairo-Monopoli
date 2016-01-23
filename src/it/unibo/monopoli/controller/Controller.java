package it.unibo.monopoli.controller;

import javax.swing.JTextField;
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


}
