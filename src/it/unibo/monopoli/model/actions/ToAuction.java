package it.unibo.monopoli.model.actions;

import java.util.List;
import java.util.Optional;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Ownership;

public class ToAuction implements Action {

    private final List<Player> players;
    private final Auction auction;
    private final Optional<Ownership> ownership;
    private final Optional<Card> card;

    private ToAuction(final List<Player> players, final Auction auction, final Ownership ownership) {
        this.players = players;
        this.auction = auction;
        this.ownership = Optional.of(ownership);
        this.card = Optional.empty();
    }
    
    private ToAuction(final List<Player> players, final Auction auction, final Card card) {
        this.players = players;
        this.auction = auction;
        this.ownership = Optional.empty();
        this.card = Optional.of(card);
    }
    
    public static ToAuction ownerships(final List<Player> players, final Auction auction, final Ownership ownership) {
        return new ToAuction(players, auction, ownership);
    }
    
    public static ToAuction cards(final List<Player> players, final Auction auction, final Card card) {
        return new ToAuction(players, auction, card);
    }

    @Override
    public void play(final Player player) {
        if (this.ownership.isPresent()) {
            this.auction.setPlayersTheFirstOneAndOwnership(this.players, player, this.ownership.get());
        } else {
            this.auction.setPlayersTheFirstOneAndCard(this.players, player, this.card.get());
        }
    }

    /**
     * Return an {@link Auction}'s strategy. The {@link Player} in input is the
     * one that started this {@link Auction}, so is the first to begin.
     * 
     * @param player
     *            - the {@link Player} that started this {@link Auction}, so is
     *            the first to begin
     * @return a specific {@link Auction}.
     */
    public Auction getAuction(final Player player) {
        this.play(player);
        return this.auction;
    }
}
