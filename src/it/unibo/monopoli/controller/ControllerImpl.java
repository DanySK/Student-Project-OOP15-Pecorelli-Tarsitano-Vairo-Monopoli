package it.unibo.monopoli.controller;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextField;

import it.unibo.monopoli.model.actions.ToBuyProperties;
import it.unibo.monopoli.model.actions.ToMortgage;
import it.unibo.monopoli.model.actions.ToRevokeMortgage;
import it.unibo.monopoli.model.actions.ToSellProperties;
import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.mainunits.Bank;
import it.unibo.monopoli.model.mainunits.ClassicPlayer;
import it.unibo.monopoli.model.mainunits.ClassicStrategy;
import it.unibo.monopoli.model.mainunits.GameStrategy;
import it.unibo.monopoli.model.mainunits.GameVersion;
import it.unibo.monopoli.model.mainunits.GameVersionImpl;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.Ownership;
import it.unibo.monopoli.view.EVersion;

/**
 * */
public class ControllerImpl implements Controller {
    private final List<Player> player;
    private Player actualPlayer;
    private GameStrategy strategy;
    private GameVersion version;
    private Ownership actualPosition;

    /**
     * Set the initial strategy of the game.
     * 
     * - set a strategy {@link JTextField}s
     */
    public ControllerImpl(final EVersion versionEnum,List<Player> players) {
        this.player=players;
        strategy = new ClassicStrategy(players);
        version = new GameVersionImpl(this.strategy);
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
        final Player p = new ClassicPlayer(name.getText(), idPawn, typePlayer);
        this.player.add(p);

    }

    /**
     * This method allow to get set of all Box.
     * 
     * @return Set of {@link Box}
     */
    public List<Box> getAllBoxes() {
        return version.getAllBoxes();
    }

    /**
     * This method allow to get Bank.
     * @return {@link Bank}
     */
    public Bank getBank() {
        return version.getBank();
    }

    /**
     * Remove player from list.
     * 
     * @param name
     *            .
     */
    public void removePlayer(final JTextField name) {
        for (Player p : this.player) {
            if (p.getName().equals(name.getText())) {
                this.player.remove(p);
            }
        }
    }

    /**
     * This method get the list of player {@link Player}.
     * 
     * @return the list <@link List> of player <@link Player>
     */
    public List<Player> getListPlayer() {
        return this.player;
    }

    /**
     * Return the id of the actual player.
     * 
     * @return actual player .
     */
    public Player getActualPlayer() {
        return this.actualPlayer;
    }

    /**
     * Method for buy a property by id.
     * 
     * @param amount
     *            .
     * @param ownership
     *            .
     */
    public void buyOwnership(final int amount, final Ownership ownership) {

        Player p = this.actualPlayer;
        final ToBuyProperties buy = ToBuyProperties.buyAOwnership(amount, ownership);
        buy.play(p);

    }

    /**
     * method for sell a property by id.
     * 
     * @param amount
     *            .
     * @param ownership
     *            .
     */
    public void sellOwnership(final int amount, final Ownership ownership) {

        Player p = this.actualPlayer;
        final ToSellProperties sell = ToSellProperties.buyAOwnership(amount, ownership);
        sell.play(p);
    }

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
    public void buyBuilding(final int amount, final Land land, final Building building) {
        Player p = this.actualPlayer;
        final ToBuyProperties buy = ToBuyProperties.buyABuilding(amount, land, building);
        buy.play(p);
    }

    /**
     * This method allow to mortgage a property by id.
     * 
     * @param amount
     *            .
     * @param ownership
     *            .
     */
    public void mortgageOwnership(final int amount, final Ownership ownership) {
        Player p = this.actualPlayer;
        final ToMortgage mortgage = new ToMortgage(amount, ownership);
        mortgage.play(p);
    }

    /**
     * This method allow to revoke mortgage a property by id.
     * 
     * @param id
     *            .
     */
    public void revokeMortgageOwnership(final int amount, final Ownership ownership) {
        Player p = this.actualPlayer;
        final ToRevokeMortgage mortgage = new ToRevokeMortgage(amount, ownership);
        mortgage.play(p);
    }

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
    public void acceptTrade(final int amount, final Ownership ownership, final Player tradePlayer) {

        this.buyOwnership(amount, ownership);
        final ToSellProperties sell = ToSellProperties.buyAOwnership(amount, ownership);
        sell.play(tradePlayer);
    }

    /**
     * This method allow to get the new position.
     * 
     * @return new position
     */
    public int getNewPosition() {

        Player p = this.actualPlayer;
        return p.getPawn().getActualPos();
    }

    /**
     * go To next player.
     * 
     * @return the integer for next player
     */
    public Player endTurn() {

        this.actualPlayer = version.endOfTurnAndNextPlayer(actualPlayer);

        return this.actualPlayer;
    }
    /**
     * .
     */
    public void takeChanche(){
        
    }
    /**
     * This method allow to roll dice.
     * 
     * @return the list of {@link Dices} rolled;
     */
    public List<Integer> toRollDices() {

        List<Integer> dices = version.toRollDices(actualPlayer);
        return dices;
    }

    /**
     * This method allow to get the result of dices .
     * 
     * @return the result of rolling dices .
     */
    public int getDicesResult() {
        List<Integer> dices = this.toRollDices();
        int result = dices.get(0) + dices.get(1);
        return result;
    }

    /**
     * This is a method for the brain of computer Player
     */
    private void computerPlayer(Player player) {
        Player p = this.actualPlayer;
        /* *
         * Se player.amount > del costo dell'ownership + il costo dell'affitto
         * medio compra altrimenti partecipa all'asta fino a quando ^ sopra
         *
         *
         * Se player.amount < 0 se puo mortgage else bancarotta
         *
         * se ha 2 proprieta uguali contratta per la 3 offrendo il suo costo +
         * random tra il 10% e il 15%
         *
         * se qualcuno ti propone lo scambio il valore delle properieta e dei
         * soldi devono essere uguali + random tra il 10 e il 15%
         *
         * se puoi costruire costruisci fino a quando i tuoi soldi sono 4 volte
         * il costo medio dell'affitto
         *
         * 
         *
         */
    }

    /**
     * do auction for property.
     * 
     * @param property
     *            .
     * @return {@link Ownership}.
     */
    public Card auction(Ownership property) {

        return null;
    }

    /**
     * Method for choose the winner.
     * 
     * @return winner Player {@link Player}
     */
    public Player winner() {
        if(this.player.size()==1){
          return this.actualPlayer;  
        }
        return null;
    }

    /**
     * Method to declare bankruptcy.
     */
    public void bankrupt() {
        int i=this.player.indexOf(actualPlayer);
        this.player.remove(i);
    }

    /* prison action */
    /* dice pair throw */
    /* prison pay */
    /**
     * this method allow to use card for Get out of Jail.
     */
    public void usePrisonCard() {

    }

    public static void main(String[] args) {

    }

}
