package it.unibo.monopoli.model.cards;

import java.util.LinkedList;
import java.util.List;

/**
 * This is a specific implementation of a classic game's {@link Deck}.
 *
 */
public class CommunityChest implements Deck {

    private final List<Card> cards;

    /**
     * Constructs an instance of {@link Deck} named {@link Chance} and adds to
     * it all its {@link Card}s.
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
        // TODO Auto-generated method stub

    }

    @Override
    public Card getFirstCard() {
        return this.cards.get(this.cards.size() - 1);
    }

}
