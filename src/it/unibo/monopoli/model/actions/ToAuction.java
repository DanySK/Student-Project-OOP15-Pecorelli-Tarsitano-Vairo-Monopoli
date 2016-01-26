package it.unibo.monopoli.model.actions;

import java.util.List;

import it.unibo.monopoli.model.mainunits.Player;

public class ToAuction implements Action {

    private final List<Player> players;
    private final Auction auction;
    private boolean isObligatory;

    public ToAuction(final List<Player> players, final Auction auction) {
        this.players = players;
        this.auction = auction;
    }

    @Override
    public void play(final Player player) {
        this.auction.setPlayersAndTheFirstOne(this.players, player);
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

    @Override
    public boolean isObligatory() {
        return this.isObligatory;
    }

    @Override
    public void setObligatory(final boolean isObligatory) {
        this.isObligatory = isObligatory;
    }
}
