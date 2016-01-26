package it.unibo.monopoli.model.actions;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Ownership;

public class ClassicAuction implements Auction{

    private Iterator<Player> iterator;
    private final List<Player> players;
    private Player firstPlayer;
    private int auctionValue;
    private final Optional<Ownership> ownership;
    private final Optional<Card> card;
    
    private ClassicAuction(final List<Player> players, final Player firstPlayer, final Ownership ownership) {
        this.players = players;
        this.firstPlayer = firstPlayer;
        this.iterator = this.players.listIterator(this.players.indexOf(firstPlayer));
        this.ownership = Optional.of(ownership);
        this.card = Optional.empty();
    }
    
    private ClassicAuction(final List<Player> players, final Player firstPlayer, final Card card) {
        this.players = players;
        this.firstPlayer = firstPlayer;
        this.iterator = this.players.listIterator(this.players.indexOf(firstPlayer));
        this.ownership = Optional.empty();
        this.card = Optional.of(card);
    }
    
    @Override
    public void setPlayersTheFirstOneAndOwnership(final List<Player> players, final Player firstPlayer, final Ownership ownership) {
        new ClassicAuction(players, firstPlayer, ownership);
    }

    @Override
    public void setPlayersTheFirstOneAndCard(final List<Player> players, final Player firstPlayer, final Card card) {
        new ClassicAuction(players, firstPlayer, card);
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

    @Override
    public void stopAuction(final Player player) {
        if (this.ownership.isPresent()) {
            ToBuyProperties.buyAOwnership(this.ownership.get()).play(player);
        } else {
            new ToBuyCards(this.card.get(), this.getValue()).play(player);
        }
    }

}
