package it.unibo.monopoli.model.mainunits;

import java.util.Iterator;
import java.util.List;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.actions.ClassicDicesStrategy;
import it.unibo.monopoli.model.actions.ToRollDices;

/**
 * This class implements the contract of {@link GameVersion} to bring back the
 * right game's version.
 *
 */
public class GameVersionImpl implements GameVersion {

    private final GameStrategy strategy;
    private final List<Player> players;
    private Iterator<Player> iter;
    // private Player actualPlayer;

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
    }

    // @Override
    // public List<Building> getBuildings() {
    // final List<Building> buildings = new LinkedList<>();
    // for (int i = 0; i < this.strategy.howManyBuildings(); i++) {
    // buildings.add(this.strategy.getBuilding());
    // }
    // return Collections.unmodifiableList(buildings);
    // }

    // @Override
    // public Set<Box> getBoxes() {
    // // TODO Auto-generated method stub
    // return null;
    // }

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
            new ToRollDices(new ClassicDicesStrategy()).play(this.getNextPlayer());
            return this.getNextPlayer();
        }
        return player;
    }

    // @Override
    // public Set<Action> getAllActions() {
    // // TODO Auto-generated method stub
    // return null;
    // }

}
