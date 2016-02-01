package it.unibo.monopoli.model.mainunits;

import java.util.List;
import java.util.Set;

import it.unibo.monopoli.controller.Actions;
import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This is the interface that specifies witch instances are to be used depending
 * on the chosen game's version.
 *
 */
public interface GameVersion {

    /**
     * This method return an instance of the only one {@link Bank}, specific of
     * this version.
     * 
     * @return a {@link Bank}
     */
    Bank getBank();

    /**
     * This method return all the {@link Box}es required for the specific
     * version. These {@link Box}es bring with them also the informations about
     * {@link Contract}s, {@link Action}s and {@link Group}s.
     *
     * @return a {@link Set} of {@link Box}es
     */
    List<Box> getAllBoxes();

    List<Deck> getDecks();

//    AuctionOfOwnership toAuction(Ownership ownership, Player player);

    Player getNextPlayer();

    /**
     * Returns the {@link Player} which have to play.
     * 
     * @return the {@link Player} which have to play
     */
    Player endOfTurnAndNextPlayer();

    List<Integer> toRollDices();

//    Action getNextBoxsAction(Box box, Player player);
//
    boolean getNextCardsAction(Box box, Card card, Player player);
    
    boolean haveEnoughMoney(Player player, int moneyToPay);
    
    
}
