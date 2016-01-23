package it.unibo.monopoli.model.mainunits;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import it.unibo.monopoli.model.table.Box;

/**
 * This class implements the contract of {@link GameVersion} to bring back the
 * right game's version.
 *
 */
public class GameVersionImpl implements GameVersion {

    private final GameStrategy strategy;
    private final List<Player> players;
    private Iterator<Player> iter;
    private final Set<Box> allBoxes;

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
        this.allBoxes = strategy.getBoxes();
    }

    @Override
    public Bank getBank() {
        return this.strategy.getBank();
    }

    // @Override
    private Player getNextPlayer() {
        if (!this.iter.hasNext()) {
            this.iter = this.players.iterator();
        }
        return (Player) this.iter.next();
    }

    @Override
    public Player endOfTurnAndNextPlayer(final Player player) {
        if (player.dicesAlreadyRolled()) {
            player.setDicesRoll(false);
            // new ToRollDices(new
            // ClassicDicesStrategy()).play(this.getNextPlayer());
            return this.getNextPlayer();
        }
        return player;
    }

    @Override
    public Set<Box> getAllBoxes() {
        return Collections.unmodifiableSet(this.allBoxes);
    }

}
