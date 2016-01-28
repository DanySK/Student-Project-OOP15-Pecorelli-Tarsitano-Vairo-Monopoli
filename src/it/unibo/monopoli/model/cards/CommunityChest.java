package it.unibo.monopoli.model.cards;

import java.util.LinkedList;
import java.util.List;

/**
 * This is a specific implementation of one of classic game's {@link Deck}s.
 *
 */
public class CommunityChest implements Deck {

    private final List<Card> cards;

    /**
     * Constructs an instance of {@link Deck} named {@link CommunityChest}.
     */
    public CommunityChest() {
        this.cards = new LinkedList<>();
    }

    @Override
    public String getName() {
        return "Community Chest";
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
