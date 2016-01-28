package it.unibo.monopoli.model.cards;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.mainunits.Player;

/**
 * This is an implementation of {@link Card} that represents the
 * {@link ClassicCard} used in the classic version of Monopoly.
 *
 */
public class ClassicCard implements Card {

    // private final Deck deck;
    private final String description;
    private final Optional<List<Action>> action;
    private Optional<Player> player;
    private final int id;

    /**
     * Constructs a {@link ClassicCard}'s instance. This {@link Card} needs a
     * description, an ID and the {@link Optional} {@link Action} s that is able
     * to fulfill.
     * 
     * @param description
     *            - the {@link Card}'s description
     * @param id
     *            - the {@link Card}'s ID
     * @param actions
     *            - the {@link Card}'s {@link Action}s
     */
    public ClassicCard(final String description, final int id, final Action... actions) {
        // this.deck = deck;
        this.description = description;
        this.action = Optional.ofNullable(Arrays.asList(actions));
        this.id = id;
        this.player = Optional.empty();
    }

    // @Override
    // public Deck getDeck() {
    // return this.deck;
    // }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Optional<List<Action>> getActions() {
        return this.action;
    }

    @Override
    public Optional<Player> getPlayer() {
        return this.player;
    }

    @Override
    public void setPlayer(final Player player) {
        this.player = Optional.ofNullable(player);
    }

    @Override
    public int getID() {
        return this.id;
    }

}
