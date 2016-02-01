package it.unibo.monopoli.model.cards;

import java.util.Iterator;
import java.util.List;

public class ClassicDeck implements Deck {

    private final String name;
    private final List<Card> cards;
    private Iterator<Card> iter;

    /**
     */
    public ClassicDeck(final String name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
        this.iter = this.cards.iterator();
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
        this.cards.add(card);
    }

    @Override
    public void removeCard(final Card card) {
        this.cards.remove(card);
    }

    @Override
    public Card getFirstCard() {
//        if (!this.cards.stream().anyMatch(c -> !c.getPlayer().isPresent())) {
//                throw new IllegalArgumentException("No more cards in this deck");
//        }
        if (!this.iter.hasNext()) {
            this.iter = this.cards.iterator();
            return this.getFirstCard();
        } else {
            final Card next = this.iter.next();
            return next.getPlayer().isPresent() ? this.getFirstCard() : next;
        }
    }

}
