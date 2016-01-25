package it.unibo.monopoli.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JTextField;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.actions.ToBuyProperties;
import it.unibo.monopoli.model.actions.ToMortgage;
import it.unibo.monopoli.model.actions.ToPay;
import it.unibo.monopoli.model.actions.ToRevokeMortgage;
import it.unibo.monopoli.model.actions.ToSellProperties;
import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.mainunits.Bank;
import it.unibo.monopoli.model.mainunits.ClassicStrategy;
import it.unibo.monopoli.model.mainunits.GameStrategy;
import it.unibo.monopoli.model.mainunits.GameVersion;
import it.unibo.monopoli.model.mainunits.GameVersionImpl;
import it.unibo.monopoli.model.mainunits.Pawn;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Ownership;

/**
 * */
public class ControllerImpl implements Controller {
    private final List<Player> player = new ArrayList<>();
    private Player actualPlayer;
    private Action action;
    private GameStrategy strategy;
    private GameVersion version;
    private Ownership actualPosition;

    /**
     * Set the initial strategy of the game.
     * 
     * - set a strategy {@link JTextField}s
     */
    public void setStrategy() {
        strategy = new ClassicStrategy(this.player);
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
        final Player p = new Player(name.getText(), idPawn, typePlayer);
        this.player.add(p);

    }

    /**
     * This method allow to get set of all Box.
     * 
     * @return Set of {@link Box}
     */
    public Set<Box> getAllBoxes() {
        return version.getAllBoxes();
    }

    /**
     * This method allow to get Bank.
     * 
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
     * This method allow to buy building for a properties
     */
    void BuyBuilding() {

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
    void revokeMortgageOwnership(final int amount, final Ownership ownership) {
        Player p = this.actualPlayer;
        final ToRevokeMortgage mortgage = new ToRevokeMortgage(amount, ownership);
        mortgage.play(p);
    }

    /**
     * this method is used for do a trade with other player.
     */
    void trade() {
    }

    /* trade action */
    /**
     * This method allow to counter a trade.
     */
    void counter() {
    }

    /**
     * This method allow to accept a trade.
     */
    void accept() {
    }

    /**
     * This method allow to reject a trade.
     */
    void reject() {
    }

    /**
     * Get pawn.
     * 
     * @return pawn
     */
    Pawn getPawn() {

        Player p = this.actualPlayer;
        return p.getPawn();
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
     * This is a method for the brain of computer Player
     */
    private void computerPlayer(Player player) {
        Player p = this.actualPlayer;
        /*
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
     * @return {@link Ownershio}.
     */
    Card auction(Ownership property);

    /**
     * Method for choose the winner.
     * 
     * @return winner Player {@link Player}
     */
    /**
    *
    */
    void pay(int amount) {
        final ToPay pay = new ToPay(amount);
    }

    /* prison action */
    /* dice pair throw */
    /* prison pay */
    /**
     * this method allow to use card for Get out of Jail.
     */
    void usePrisonCard();

    /**/
    /* prison action */
    /* dice pair throw */
    /* prison pay */
    /**
     * this method allow to use card for Get out of Jail.
     */
    void usePrisonCard();

    public static void main(String[] args) {

    }

}
