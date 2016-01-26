package it.unibo.monopoli.model.mainunits;

import java.util.List;
import java.util.Set;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.table.Box;

/**
 * This is the interface that specifies witch instances are to be used depending
 * on the chosen game's version.
 *
 */
public interface GameVersion {

    /**
     * This method return all the {@link Box}es required for the specific
     * version. These {@link Box}es bring with them also the informations about
     * {@link Contract}s, {@link Action}s and {@link Group}s.
     *
     * @return a {@link Set} of {@link Box}es
     */
    List<Box> getAllBoxes();

    /**
     * This method return an instance of the only one {@link Bank}, specific of
     * this version.
     * 
     * @return a {@link Bank}
     */
    Bank getBank();

    /**
     * Returns the {@link Player} which have to play.
     * 
     * @param player
     *            - the {@link Player} who played until then
     * @return the {@link Player} which have to play
     */
    Player endOfTurnAndNextPlayer(Player player);

    List<Integer> toRollDices(Player player);

    Action getNextBoxsAction(Box box);

    Action getNextCardsAction(Card card);
}
