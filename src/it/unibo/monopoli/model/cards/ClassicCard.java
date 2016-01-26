package it.unibo.monopoli.model.cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private final List<Action> action;
    private Player player;

    /**
     * Constructs a {@link ClassicCard}. This {@link Card} needs a description
     * and the {@link Action} that is able to fulfill.
     * 
     * @param description
     *            - the {@link Card}'s description
     * @param action
     *            - the {@link Card}'s {@link Action}
     */
    public ClassicCard(final String description, final Action... action) {
        // this.deck = deck;
        this.description = description;
        this.action = new ArrayList<>();
        this.action.addAll(Arrays.asList(action));
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
    public List<Action> getActions() {
        return this.action;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void setPlayer(final Player player) {
        this.player = player;
    }

}
