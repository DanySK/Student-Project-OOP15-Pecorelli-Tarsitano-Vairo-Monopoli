package it.unibo.monopoli.model.mainunits;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Building;

/**
 * This class implements the contract of {@link GameVersion} to bring back the
 * right game's version.
 *
 */
public class GameVersionImpl implements GameVersion {

    private final GameStrategy strategy;

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
    }

    @Override
    public Collection<Building> getBuildings() {
        Set<Building> buildings = new HashSet<>();
        for (int i = 0; i < this.strategy.howManyBuildings(); i++) {
            buildings.add(this.strategy.getBuilding());
        }
        return Collections.unmodifiableCollection(buildings);
    }

    @Override
    public Set<Box> getBoxes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Bank getBank() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Action> getAllActions() {
        // TODO Auto-generated method stub
        return null;
    }

}
