package it.unibo.monopoli.model.actions;

import java.util.List;
import java.util.Optional;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.mainunits.Bank;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This class represent one of the {@link Action}s of the game. This one is to
 * auction a {@link Card} or an {@link Ownership}.
 *
 */
public final class ToAuction implements Action {

    private final List<Player> players;
    private final AuctionOfOwnership auction;
    private final Optional<Ownership> ownership;
    // private final Optional<Card> card;
    private final Bank bank;

    private ToAuction(final List<Player> players, final AuctionOfOwnership auction, final Ownership ownership,
            final Bank bank) {
        this.players = players;
        this.auction = auction;
        this.ownership = Optional.of(ownership);
        // this.card = Optional.empty();
        this.bank = bank;
    }

    // private ToAuction(final List<Player> players, final
    // AuctionOfOwnershipAndCard auction, final Card card) {
    // this.players = players;
    // this.auction = auction;
    // this.ownership = Optional.empty();
    // this.card = Optional.of(card);
    // }

    /**
     * This static method constructs an instance of this class for
     * {@link Ownership}'s auction.
     * 
     * @param players
     *            - the {@link Player} who started the auction
     * @param auction
     *            - the {@link AuctionOfOwnership}'s method to use
     * @param ownership
     *            -the {@link Ownership} {@link ToAuction}
     * @param bank
     *            - the {@link Bank} through which the sale takes place.
     * @return an instance of this class
     */
    public static ToAuction ownerships(final List<Player> players, final AuctionOfOwnership auction,
            final Ownership ownership, final Bank bank) {
        return new ToAuction(players, auction, ownership, bank);
    }

    // /**
    // * This static method constructs an instance of this class for {@link
    // Card}
    // * 's auction.
    // *
    // * @param players
    // * - the {@link Player} who started the auction
    // * @param auction
    // * - the {@link AuctionOfOwnershipAndCard}'s method to use
    // * @param card
    // * -the {@link Card} {@link ToAuction}
    // * @return an instance of this class
    // */
    // public static ToAuction cards(final List<Player> players, final
    // AuctionOfOwnershipAndCard auction,
    // final Card card) {
    // return new ToAuction(players, auction, card);
    // }

    @Override
    public void play(final Player player) {
        if (this.ownership.isPresent()) {
            this.auction.initializedOwnershipsAuction(this.players, player, this.ownership.get(), this.bank);
            // } else {
            // this.auction.initializedCardsAuction(this.players, player,
            // this.card.get());
        }
    }

    /**
     * Return an {@link AuctionOfOwnership}'s class. The {@link Player}
     * in input is the one that started this auction, so he is the first to
     * begin.
     * 
     * @param player
     *            - the {@link Player} that started the auction
     * @return a specific {@link AuctionOfOwnership}.
     */
    public AuctionOfOwnership getAuction(final Player player) {
        this.play(player);
        return this.auction;
    }
}
