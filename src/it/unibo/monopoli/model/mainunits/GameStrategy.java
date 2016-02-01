package it.unibo.monopoli.model.mainunits;

import java.util.List;

import it.unibo.monopoli.controller.Actions;
import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This interface holds the strategy for initialize the game with the right
 * {@link GameVersion}.
 *
 */
public interface GameStrategy {

    /**
     * Returns a {@link List} of the game's {@link Player}s.
     * 
     * @return a {@link List} of the {@link Player}s
     */
    List<Player> getPlayers();

    /**
     * Return the game's {@link Bank}.
     * 
     * @return the {@link Bank}
     */
    Bank getBank();

    /**
     * Returns a {@link List} with all the game's table's {@link Box}es.
     * 
     * @return a {@link List} of all {@link Box}es
     */
    List<Box> getBoxes();

//    List<Action> getNextBoxsActions(Box box, Player player);
//
    boolean getNextCardsActions(Box box, Card card, Player player);
    
    boolean haveEnoughMoney(Player player, int moneyToPay);

    List<Deck> getDecks();

//    AuctionOfOwnership toAuction(Ownership ownership, Player player);
    
    List<Integer> toRollDices(Player player);

}
