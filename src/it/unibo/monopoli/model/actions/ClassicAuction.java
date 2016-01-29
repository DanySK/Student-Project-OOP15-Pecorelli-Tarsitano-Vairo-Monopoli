package it.unibo.monopoli.model.actions;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import it.unibo.monopoli.model.mainunits.Bank;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This class represent a classic implementation of Monopoly's auction.
 *
 */
public class ClassicAuction implements AuctionOfOwnership {

    private Iterator<Player> iterator;
    private List<Player> players;
    private int auctionValue;
    private Optional<Ownership> ownership;
//    private Optional<Card> card;
    private Bank bank;

    // private ClassicAuction(final List<Player> players, final Player
    // firstPlayer, final Ownership ownership) {
    // this.players = players;
    // this.firstPlayer = firstPlayer;
    // this.iterator =
    // this.players.listIterator(this.players.indexOf(firstPlayer));
    // this.ownership = Optional.of(ownership);
    // this.card = Optional.empty();
    // }
    //
    // private ClassicAuction(final List<Player> players, final Player
    // firstPlayer, final Card card) {
    // this.players = players;
    // this.firstPlayer = firstPlayer;
    // this.iterator =
    // this.players.listIterator(this.players.indexOf(firstPlayer));
    // this.ownership = Optional.empty();
    // this.card = Optional.of(card);
    // }

    @Override
    public void initializedOwnershipsAuction(final List<Player> players, final Player firstPlayer,
            final Ownership ownership, final Bank bank) {
        this.players = players;
        final int pos = this.players.indexOf(firstPlayer);
        final List<Player> list = new LinkedList<>();
        list.addAll(0, this.players.subList(pos, this.players.size() - 1));
        list.addAll(this.players.size() - pos, this.players.subList(0, pos - 1));
        this.iterator = list.listIterator(0);
        this.ownership = Optional.of(ownership);
//        this.card = Optional.empty();
        this.bank = bank;
    }

//    @Override
//    public void initializedCardsAuction(final List<Player> players, final Player firstPlayer, final Card card,
//            final Bank bank) {
//        this.players = players;
//        final int pos = this.players.indexOf(firstPlayer);
//        final List<Player> list = new LinkedList<>();
//        list.addAll(0, this.players.subList(pos, this.players.size() - 1));
//        list.addAll(this.players.size() - pos, this.players.subList(0, pos - 1));
//        this.iterator = list.listIterator(0);
//        this.ownership = Optional.empty();
//        this.card = Optional.of(card);
//        this.bank = bank;
//    }

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
            this.iterator = this.players.listIterator(0);
        }
        return this.iterator.next();
    }

    @Override
    public boolean isAbleToDoIt(final Player player) {
        return player.getMoney() >= this.auctionValue;
    }

    @Override
    public void stopAuction(final Player player) {
//        if (this.ownership.isPresent()) {
            if (this.ownership.get().getOwner() instanceof Player) {
                ToSellProperties.sellAOwnership(this.auctionValue, this.ownership.get(), this.bank)
                        .play(((Player) this.ownership.get().getOwner()));
            }
            ToBuyProperties.buyAOwnership(this.auctionValue, this.ownership.get()).play(player);
//        } else {
//            if (this.card.get().getPlayer().isPresent() && this.card.get().getPlayer().get() instanceof Player) {
//                new ToSellCards(this.card.get(), this.getValue());
//            }
//            new ToBuyCards(this.card.get(), this.getValue()).play(player);
//        }
    }

}
