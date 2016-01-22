package it.unibo.monopoli.model.mainunits;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import it.unibo.monopoli.model.cards.Chance;
import it.unibo.monopoli.model.cards.CommunityChest;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Home;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This is an implementation of {@link GameStrategy} for initialize the game
 * with the classic version of Monopoly.
 *
 */
public class ClassicStrategy implements GameStrategy {

    private static final int N_MAX_OF_HOUSES = 32;
    private static final int N_MAX_OF_HOTELS = 12;

    private final List<Player> players;
    private final List<Ownership> ownerships;
    private final List<Building> buildings;
    private final Bank bank;
    private final Set<Deck> decks;

    /**
     * Constructs a new instance of {@link ClassicStrategy} that needs all the
     * {@link Player}s to be initialized.
     * 
     * @param players
     *            - a {@link List} of all the current {@link Player}s
     */
    public ClassicStrategy(final List<Player> players) {
        this.players = players;
        this.inizializesPlayers(players);
        this.ownerships = this.inizializesOwnerships();
        this.buildings = this.inizializesBuildings();
        this.decks = new HashSet<>();
        this.decks.add(new CommunityChest());
        this.decks.add(new Chance());
        this.bank = new ClassicBank(this.ownerships, this.buildings);
    }

    private void inizializesPlayers(final List<Player> players) {
        switch (players.size()) {
        case 2:
            players.stream().forEach(p -> {
                this.addOwnerships(7, p);
                p.setMoney(350);
            });
        case 3:
            players.stream().forEach(p -> {
                this.addOwnerships(6, p);
                p.setMoney(300);
            });
        case 4:
            players.stream().forEach(p -> {
                this.addOwnerships(5, p);
                p.setMoney(250);
            });
        case 5:
            players.stream().forEach(p -> {
                this.addOwnerships(4, p);
                p.setMoney(200);
            });
        case 6:
            players.stream().forEach(p -> {
                this.addOwnerships(3, p);
                p.setMoney(150);
            });
        default:
            break;
        }
    }

    private void addOwnerships(final int nOfOwner, final Player player) {
        Random r = new Random();
        for (int i = 0; i < nOfOwner; i++) {
            player.addOwnership(this.ownerships.remove(r.nextInt(this.ownerships.size())));
        }
    }

    private List<Ownership> inizializesOwnerships() {
        this.ownerships.add(new );
    }

    private List<Building> inizializesBuildings() {
        for (int i = 0; i < N_MAX_OF_HOUSES; i++) {
            this.buildings.add(new Home(i + 1));
        }
        return this.buildings;
    }

    // @Override
    // public int howManyBuildings() {
    // // TODO Auto-generated method stub
    // return 0;
    // }
    //
    // @Override
    // public Building getBuilding() {
    // // TODO Auto-generated method stub
    // return null;
    // }

    @Override
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }

    @Override
    public Bank getBank() {
        return this.bank;
    }
}
