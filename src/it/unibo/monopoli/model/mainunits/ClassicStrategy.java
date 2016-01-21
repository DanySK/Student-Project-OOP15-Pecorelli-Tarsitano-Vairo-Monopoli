package it.unibo.monopoli.model.mainunits;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import it.unibo.monopoli.model.cards.Chance;
import it.unibo.monopoli.model.cards.CommunityChest;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This is an implementation of {@link GameStrategy} for initialize the game
 * with the classic version of Monopoly.
 *
 */
public class ClassicStrategy implements GameStrategy {

    private final List<Player> players;
    private final List<Ownership> ownerships;
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
        this.ownerships = this.inizializesOwnerships();
        this.inizializesPlayers(players);
        this.decks = new HashSet<>();
        this.decks.add(new CommunityChest());
        this.decks.add(new Chance());
    }

    private void inizializesPlayers(final List<Player> players) {
        Random r = new Random();
        // final List<Ownership> copyOfOwnerships = this.ownerships;
        switch (players.size()) {
        case 2:
            players.stream().forEach(p -> {
                p.addOwnership(this.ownerships.remove(r.nextInt(this.ownerships.size())));
                p.setMoney();
            });
        case 3:
            players.stream().forEach(p -> {
                p.addOwnership(this.ownerships.remove(r.nextInt(this.ownerships.size())));
                p.setMoney();
            });
        case 4:
            players.stream().forEach(p -> {
                p.addOwnership(this.ownerships.remove(r.nextInt(this.ownerships.size())));
                p.setMoney();
            });
        case 5:
            players.stream().forEach(p -> {
                p.addOwnership(this.ownerships.remove(r.nextInt(this.ownerships.size())));
                p.setMoney();
            });
        case 6:
            players.stream().forEach(p -> {
                p.addOwnership(this.ownerships.remove(r.nextInt(this.ownerships.size())));
                p.setMoney();
            });
        default:
            break;
        }
    }

    private List<Ownership> inizializesOwnerships() { // potrei fare una classe 
                        //a parte visibile solo al package oppure inner class
        this.ownerships.add(new );
    }

    private List<Ownership> inizializesDecks() {
        this.ownerships.add(new );
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
        return this.players;
    }

    @Override
    public Bank getBank() {
        if (bank == null) {
            bank = new Classicbank(this.ownerships);
        }
        return bank;
    }
}
