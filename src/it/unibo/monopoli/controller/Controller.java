package it.unibo.monopoli.controller;

import java.util.List;

import javax.swing.JTextField;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.mainunits.Pawn;
import it.unibo.monopoli.model.mainunits.Player;

public interface Controller {
	
	/**
	 * Method for throwing dice 
	 */
	int diceThrow();
	/* Inerente al giocatore 
	 * get pedina
	 * set pedina
	 * 
	 */
	Pawn getPawn();

	/**
	 * Add player in a list 
	 *  @param name
	 *  @param id_pawn
	 *  @param type_player
	 * */
	
	
	void addPlayer(final JTextField name,int id_pawn,int type_player);
	/**
	 * Remove player from list
	 * @param name
	 */
	void removePlayer(final JTextField name);
	/**
     * This method get the list of player {@link Player}.
     */
	List<Player> getListPlayer();
	/**
	 * Set type of game
	 * @param game_type
	 * 
	 * */
	void setGameType(JTextField game_type);
	/**
	 * 
	 * 
	 */
	int actualPlayer();
	/**
	 * add property on actual player
	 * */
	void addProprerty(JTextField property);
	/**
	 * go To next player
	 */
	int endTurn();
	/**
	 * do auction for property
	 * */
	 Card auction(JTextField property);
	/**
	 * 
	 * 
	 */
	void pay();
	
}
