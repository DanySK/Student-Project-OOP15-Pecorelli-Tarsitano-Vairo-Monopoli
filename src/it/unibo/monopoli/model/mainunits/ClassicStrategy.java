package it.unibo.monopoli.model.mainunits;

import java.awt.Point;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import it.unibo.monopoli.model.cards.Chance;
import it.unibo.monopoli.model.cards.CommunityChest;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.DecksBox;
import it.unibo.monopoli.model.table.Home;
import it.unibo.monopoli.model.table.NeutralArea;
import it.unibo.monopoli.model.table.Ownership;
import it.unibo.monopoli.model.table.Police;
import it.unibo.monopoli.model.table.PrisonOrTransit;
import it.unibo.monopoli.model.table.Start;
import it.unibo.monopoli.model.table.TaxImpl;

/**
 * This is an implementation of {@link GameStrategy} for initialize the game
 * with the classic version of Monopoly.
 *
 */
public class ClassicStrategy implements GameStrategy {

    private static final int N_MAX_OF_HOUSES = 32;
    private static final int N_MAX_OF_HOTELS = 12;
    private static final int AMOUNT_OF_FEES = 10;

    private final List<Player> players;
    private final List<Ownership> ownerships;
    private final List<Building> buildings;
    private final List<Deck> decks;
    private final Bank bank;

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
        this.decks = new LinkedList<>();
        this.inizializesDecks();
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

    @Override
    public Set<Box> getBoxes() {
        Set<Box> allBoxes = new HashSet<>();
        allBoxes.addAll(this.ownerships);
        allBoxes.add(new Start("GO", 0));
        PrisonOrTransit prison = new PrisonOrTransit("IN JAIL OR JUST VISITING", 10);
        allBoxes.add(prison);
        allBoxes.add(new NeutralArea("FREE PARKING", 20));
        allBoxes.add(new Police("GO TO JAIL", 30, prison));
        allBoxes.add(new DecksBox("CHANCE", 7, this.decks.get(0)));
        allBoxes.add(new DecksBox("CHANCE", 22, this.decks.get(0)));
        allBoxes.add(new DecksBox("CHANCE", 36, this.decks.get(0)));
        allBoxes.add(new DecksBox("COMMUNITY CHEST", 2, this.decks.get(1)));
        allBoxes.add(new DecksBox("COMMUNITY CHEST", 17, this.decks.get(1)));
        allBoxes.add(new DecksBox("COMMUNITY CHEST", 33, this.decks.get(1)));
        allBoxes.add(new TaxImpl("INCOME TAX", 4, AMOUNT_OF_FEES));
        allBoxes.add(new TaxImpl("SUPER TAX", 38, AMOUNT_OF_FEES));
        return allBoxes;
    }
    
    private void inizializesDecks() {
        Chance chance = new Chance();
        chance.addCard(new );
        CommunityChest chest = new CommunityChest();
        chest.addCard(new );
        this.decks.add(0, chance);
        this.decks.add(1, chest);
    }
}
