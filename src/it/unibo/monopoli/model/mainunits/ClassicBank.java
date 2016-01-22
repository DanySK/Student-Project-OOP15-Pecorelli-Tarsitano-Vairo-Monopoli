package it.unibo.monopoli.model.mainunits;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Contract;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This class implements the contract of {@link Bank} and represents the classic
 * Bank of Monopoly.
 *
 */
public class ClassicBank implements Bank {

    private final List<Ownership> ownerships;
    private final List<Building> buildings;

    /**
     * Constructs an instance of a {@link ClassicBank} that want in input a
     * {@link List} of the {@link Ownership}s used in the game.
     * 
     * @param ownerships
     *            - a {@link List} of the {@link Ownership}s used in the game
     * @param buildings
     *            - a {@link List} of the {@link Building}s used in the game
     */
    public ClassicBank(final List<Ownership> ownerships, final List<Building> buildings) {
        this.ownerships = ownerships;
        this.buildings = buildings;
    }

    @Override
    public List<Contract> getLeftContract() {
        final List<Contract> contracts = new LinkedList<>();
        this.ownerships.stream().forEach(o -> {
            contracts.add(o.getContract());
        });
        return contracts;
    }

    @Override
    public List<Building> getLeftBuilding() {
        return Collections.unmodifiableList(this.buildings);
    }

    @Override
    public void addContract(final Contract contract) {
        this.ownerships.add(contract.getOwnership());
    }

    @Override
    public void removeContract(final Contract contract) {
        this.ownerships.remove(contract.getOwnership());
    }

    @Override
    public void addBuilding(final Building building) {
        this.buildings.add(building);
    }

    @Override
    public void removeBuilding(final Building building) {
        this.buildings.remove(building);
    }

}
