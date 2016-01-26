package it.unibo.monopoli.controller;

import java.util.List;

import javax.swing.JTextField;

import it.unibo.monopoli.model.table.Box;
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
    void setStrategy();
    /**
     * This method allow to get all Boxes
     * @return {@link List} of {@link Boxes}
     */
    List<Box> getAllBoxes() ;


}
