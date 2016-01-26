package it.unibo.monopoli.model.mainunits;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Contract;
import it.unibo.monopoli.model.table.Home;
import it.unibo.monopoli.model.table.Hotel;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This class implements the contract of {@link Bank} and represents the classic
 * Bank of Monopoly.
 *
 */
public class ClassicBank implements Bank {

    private static final int N_MAX_OF_OWNERSHIP = 28;

    private final List<Ownership> ownerships;
    private final List<Building> buildings;
    private final List<? extends Building> homes;
    private final List<? extends Building> hotels;

    /**
     * Constructs an instance of a {@link ClassicBank} that want in input a
     * {@link List} of the {@link Ownership}s used in the game.
     * 
     * @param ownerships
     *            - a {@link List} of the {@link Ownership}s used in the game
     * @param homes
     *            - a {@link List} of the {@link Home}s used in the game
     * @param hotels
     *            - a {@link List} of the {@link Hotel}s used in the game
     */
    public ClassicBank(final List<Ownership> ownerships, final List<Home> homes, final List<Hotel> hotels) {
        this.ownerships = ownerships;
        this.homes = homes;
        this.hotels = hotels;
        this.buildings = new LinkedList<>();
        this.buildings.addAll(this.homes);
        this.buildings.addAll(this.hotels);
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
    public void addBuilding(final Building building) {
        this.buildings.add(building);
    }

    @Override
    public Ownership getOwnership(final int ownershipsIndex) {
        for (int i = 0; i < this.ownerships.size(); i++) {
            if (this.ownerships.get(i).getID() == ownershipsIndex) {
                return this.ownerships.remove(i);
            }
        }
        throw new IllegalArgumentException(this.ownerships.isEmpty() ? "There are no more ownership in the bank" : "The ownership with the ID in input is not contained in the bank");
    }

    @Override
    public Building getBuilding(final Building buildingsType) {
        try {
            return buildingsType instanceof Home ? this.homes.remove(0) : this.hotels.remove(0);
        } catch (IndexOutOfBoundsException i) {
            System.out.println("There are no more buildings of this type");
            throw i;
        }
    }

    @Override
    public Ownership getOwnership() {
        try {
            return this.getOwnership(new Random().nextInt(N_MAX_OF_OWNERSHIP + 1));
        } catch (IllegalArgumentException i) {
            if (!this.ownerships.isEmpty()) {
                return this.getOwnership();
            } else {
                throw i;
            }
        }
    }

}
