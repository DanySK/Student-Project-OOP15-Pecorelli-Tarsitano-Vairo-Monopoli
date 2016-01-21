package it.unibo.monopoli.model.mainunits;

import java.util.List;

import it.unibo.monopoli.model.table.Building;

/**
 * This is an implementation of {@link GameStrategy} for initialize the game
 * with the classic version of Monopoly.
 *
 */
public class ClassicStrategy implements GameStrategy {

    private final List<Player> players;

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
    }
    
    private void inizializesPlayers(final List<Player> players) {
        switch (players.size()){
        case 2 : players.stream()
                        .forEach(p -> {
                            p.addOwnership();
                            p.setMoney();
                        });
        case 3 : players.stream()
                        .forEach(p -> {
                            p.addOwnership();
                            p.setMoney();
                        });
        case 4 : players.stream()
                        .forEach(p -> {
                            p.addOwnership();
                            p.setMoney();
                        });
        case 5 : players.stream()
                        .forEach(p -> {
                            p.addOwnership();
                            p.setMoney();
                        });
        case 6 : players.stream()
                        .forEach(p -> {
                            p.addOwnership();
                            p.setMoney();
                        });                 
        }
    }

    @Override
    public int howManyBuildings() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Building getBuilding() {
        // TODO Auto-generated method stub
        return null;
    }
}
