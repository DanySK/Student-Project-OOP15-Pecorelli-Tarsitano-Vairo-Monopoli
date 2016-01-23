package it.unibo.monopoli.model.cards;

import it.unibo.monopoli.model.actions.Action;

/**
 * This is an implementation of {@link Card} that represents the
 * {@link ClassicCard} used in the classic version of Monopoly.
 *
 */
public class ClassicCard implements Card {

    private final Deck deck;
    private final String description;
    private final Action action;

    /**
     * Constructs a {@link ClassicCard}. This {@link Card} needs the
     * {@link Deck} to which it belongs, a description and the {@link Action}
     * that is able to fulfill.
     * 
     * @param deck
     *            - the {@link Deck} to which the {@link Card} belongs
     * @param description
     *            - the {@link Card}'s description
     * @param action
     *            - the {@link Card}'s {@link Action}
     */
    public ClassicCard(final Deck deck, final String description, final Action action) {
        this.deck = deck;
        this.description = description;
        this.action = action;
    }

    @Override
    public Deck getDeck() {
        return this.deck;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Action getAction() {
        return this.action;
    }

}
