package it.unibo.monopoli.model.actions;

import java.util.Iterator;
import java.util.List;

import it.unibo.monopoli.model.mainunits.Player;

public class ClassicAuction implements Auction{

    private Iterator<Player> iterator;
    private List<Player> players;
    private Player firstPlayer;
    private int auctionValue;

    @Override
    public void setPlayersAndTheFirstOne(final List<Player> players, final Player firstPlayer) {
        this.players = players;
        this.firstPlayer = firstPlayer;
        this.iterator = this.players.listIterator(this.players.indexOf(firstPlayer));
    }

    @Override
    public void increment() {
        this.auctionValue++;
    }

    @Override
    public void decrement() {
        this.auctionValue--;
    }

    @Override
    public int getValue() {
        return this.auctionValue;
    }

    @Override
    public void removePlayer() {
        this.iterator.remove();
    }

    @Override
    public Player nextPlayer() {
        if (!this.iterator.hasNext()) {
            this.iterator = this.players.listIterator(this.players.indexOf(this.firstPlayer));
        }
        return this.iterator.next();
    }

    @Override
    public boolean isAbleToDoIt(final Player player) {
        return player.getMoney() >= this.auctionValue;
    }

}
