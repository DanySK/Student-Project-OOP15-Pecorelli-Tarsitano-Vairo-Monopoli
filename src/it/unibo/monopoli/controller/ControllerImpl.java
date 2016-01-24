package it.unibo.monopoli.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import it.unibo.monopoli.model.actions.ToBuyAndSellProperties;
import it.unibo.monopoli.model.actions.ToBuyProperties;
import it.unibo.monopoli.model.actions.ToPay;
import it.unibo.monopoli.model.actions.ToSellProperties;
import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.mainunits.ClassicStrategy;
import it.unibo.monopoli.model.mainunits.GameStrategy;
import it.unibo.monopoli.model.mainunits.Pawn;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Ownership;

public class ControllerImpl implements Controller {
	final List<Player> player=new ArrayList<>();
	int numberOfPlayer;
	int actualPlayer=0;
    /**
    * Set the initial strategy of the game.
    * @param strategy
    *            - set a strategy {@link JTextField}s
    */
    public void setStrategy() {
    	final GameStrategy strategy = new ClassicStrategy(this.player);
    }
    /**
    * add player in a list.
    * 
    * @param name
    *            .
    * @param idPawn
    *            .
    * @param typePlayer 
    *            .
    */
    public void addPlayer(final JTextField name, final int idPawn, final int typePlayer) {
    	final Player p=new Player(name.getText(),idPawn,typePlayer);
    	this.player.add(p);

    }

    /**
    * Remove player from list.
    * 
    * @param name
    *            .
    */
    public void removePlayer(final JTextField name) {
        for(Player p: this.player){
        	if (p.getName().equals(name.getText())){
        		this.player.remove(p);
        	}
        }
    }

    /**
     * This method get the list of player {@link Player}.
     * @return the list <@link List> of player <@link Player>
     */
     public List<Player> getListPlayer(){
    	 return this.player;
     }
     /**
      * Return the id of the actual player.
      * @return actual player
      */
     public Player getActualPlayer(List<Player> players) {
         return this.player.get(this.actualPlayer);
     }
     /**
      * Method for buy a property by id.
      * @param id .
      */
     void buyOwnership(int amount,Ownership ownership){

    	 Player p=this.player.get(this.actualPlayer);
    	 final ToBuyAndSellProperties buy= ToBuyProperties.buyAOwnership(amount, ownership);
    	 
     }
     /**
      * method for sell a property by id.
      * @param id .
      */
     void sellOwnership(int amount,Ownership ownership){

         Player p=this.player.get(this.actualPlayer);

    	 final ToBuyAndSellProperties buy= ToSellProperties.buyAOwnership(amount, ownership);
        
         	
     }
     /** 
      * This method allow to buy building for a properties
      */
     void BuyBuilding(int amount, Ownership){
    	 
     }

     /**
      * This method allow to mortgage a property by id.
      * @param id .
      */
     void mortgageOwnership(int id);
     /**
      * This method allow to unmortgage a property by id.
      * @param id .
      */
     void unmortgageOwnership(int id);
     /**
      *this method is used for do a trade with other player.
      */
     void trade();
     /*trade action*/
     /**
      * This method allow to counter a trade.
      */
     void counter();
     /**
      * This method allow to accept a trade.
      */
     void accept();
     /**
      * This method allow to reject a trade.
      */
     void reject();

     
     

     /**
     * Get pawn.
     * @return pawn
     */
     Pawn getPawn(){

         Player p=this.player.get(this.actualPlayer);
    	 return p.getPawn();
     }

     /**
      * Method for throwing dice.
      * @return the number of throwing dice
      */
     int diceThrow();

     

     /**
     * add property on actual player.
     * @param property .
     */
     void addOwnership(Ownership ownership){
    	 
     }

     /**
     * go To next player.
     * @return the integer for next player
     */
     public int endTurn() {
        if (this.actualPlayer < this.numberOfPlayer) {
            this.actualPlayer++;
        } else {
            this.actualPlayer = 0;
        }
        return this.actualPlayer;
     }

     /**
     * do auction for property.
     * @param property .
     * @return {@link Card}.
     */
     Card auction(JTextField property);
     /**
      * Method for choose the winner.
      * @return winner Player {@link Player}
      */
     /**
     *
     */
     void pay(int amount){
    	 final ToPay pay=new ToPay(amount);
     }
     
     /*prison action*/
     /*dice pair throw*/
     /*prison pay*/
     /**
      * this method allow to use card for Get out of Jail.
      */
     void usePrisonCard();
     /**/
     /*prison action*/
     /*dice pair throw*/
     /*prison pay*/
     /**
      * this method allow to use card for Get out of Jail.
      */
     void usePrisonCard();

    public static void main(String[] args){
    	
    }
    
    
}
