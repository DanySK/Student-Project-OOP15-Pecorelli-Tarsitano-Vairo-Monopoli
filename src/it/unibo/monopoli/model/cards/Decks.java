package it.unibo.monopoli.model.cards;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This is a specific implementation of one of classic game's {@link Deck}s.
 *
 */
public enum Decks implements Deck {
    COMMUNITY_CHEST("Community Chest", Arrays.asList(CommunityChestCards.values())),
    CHANCE("Chance", Arrays.asList(ChanceCards.values()));

    private final String name;
    private final List<Card> cards;

    /**
     * Constructs an instance of {@link Deck} named {@link CommunityChest}.
     */
    Decks(final String name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Card> getCards() {
        return this.cards;
    }

    @Override
    public void addCard(final Card card) {
        // if (this.cards.contains(card)) {
        // throw new IllegalArgumentException("This card is already present");
        // }
        this.cards.add(card);
    }

    @Override
    public void removeCard(final Card card) {
        this.cards.remove(card);
    }

    @Override
    public Card getFirstCard() {
        return this.cards.get(this.cards.size() - 1);
    }

}
