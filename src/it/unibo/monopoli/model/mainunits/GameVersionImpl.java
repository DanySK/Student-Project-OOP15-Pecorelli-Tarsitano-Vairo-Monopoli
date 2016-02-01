package it.unibo.monopoli.model.mainunits;

import java.util.Iterator;
import java.util.List;

import it.unibo.monopoli.controller.Actions;
import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This class implements the contract of {@link GameVersion} to bring back the
 * right game's version.
 *
 */
public class GameVersionImpl implements GameVersion {

    private final GameStrategy strategy;
    private final List<Player> players;
    private Iterator<Player> iter;
    private Player actualPlayer;
//    private final List<Box> allBoxes;

    /**
     * Constructs an instance that will be able to give back the right version
     * thanks to the {@link GameStrategy} passed in input.
     * 
     * @param strategy
     *            - the {@link GameStrategy} who will report the strategy to
     *            implement the right {@link GameVersion}
     */
    public GameVersionImpl(final GameStrategy strategy) {
        this.strategy = strategy;
        this.players = strategy.getPlayers();
        this.iter = this.players.iterator();
//        this.allBoxes = strategy.getBoxes();
    }

    @Override
    public Bank getBank() {
        return this.strategy.getBank();
    }

    @Override
    public List<Box> getAllBoxes() {
        return this.strategy.getBoxes();
    }

    @Override
    public List<Deck> getDecks() {
        return this.strategy.getDecks();
    }
//
//    @Override
//    public AuctionOfOwnership toAuction(final Ownership ownership, final Player player) {
//        return this.strategy.toAuction(ownership, player);
//    }

    @Override
    public Player getNextPlayer() { //PRIVATO?????????????
        if (!this.iter.hasNext()) {
            this.iter = this.players.iterator();
        }
        this.actualPlayer = (Player) this.iter.next();
        return this.actualPlayer;
    }

    @Override
    public Player endOfTurnAndNextPlayer() {
        this.actualPlayer.setDicesRoll(false);
        this.actualPlayer.setIfIsTheFirstLaunch(true);        
        return this.getNextPlayer();
    }

    @Override
    public List<Integer> toRollDices() {
        return this.strategy.toRollDices(this.actualPlayer);
    }

    // @Override
    // public Action getNextBoxsAction(final Box box, final Player player) {
    // // TODO Auto-generated method stub
    // return null;
    // }

     @Override
     public boolean getNextCardsAction(final Box box, final Card card, final Player player) {
        return this.strategy.getNextCardsActions(box, card, player);
     }

     @Override
     public boolean haveEnoughMoney(final Player player, final int moneyToPay) {
         return this.strategy.haveEnoughMoney(player, moneyToPay);
     }


}
