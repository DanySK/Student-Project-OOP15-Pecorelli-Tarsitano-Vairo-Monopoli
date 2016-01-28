package it.unibo.monopoli.model.mainunits;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.table.Ownership;

public class ClassicPlayer implements Player{

    private final String name;
    private final Pawn pawn;
    private final Optional<Set<Ownership>> ownerships;
    private final Optional<List<Card>> cards;
    private boolean alreadyRolled;
    private boolean isAPrisoner;
    private final List<Integer> dicesNumbers;
    private int money;
    private boolean debtsPaid;
    private final boolean human;

    public ClassicPlayer(final String name, final Pawn pawn, final boolean isHuman) {
        this.name = name;
        this.pawn = pawn;
        this.ownerships = Optional.empty();
        this.cards = Optional.empty();
        this.alreadyRolled = false;
        this.isAPrisoner = false;
        this.dicesNumbers = new LinkedList<>();
        this.human = isHuman;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Pawn getPawn() {
        return this.pawn;
    }

    @Override
    public Optional<Set<Ownership>> getOwnerships() {
        return this.ownerships;
    }

    @Override
    public void addCard(final Card card) {
        this.cards.orElse(new LinkedList<>()).add(card);
        card.setPlayer(this);
    }

    @Override
    public void removeCard(Card card) {
        this.cards.get().remove(card);
        card.setPlayer(null);
    }

    @Override
    public Optional<List<Card>> getCards() {
        return this.cards;
    }

    @Override
    public boolean dicesAlreadyRolled() {
        return this.alreadyRolled;
    }

    @Override
    public void setDicesRoll(final boolean alreadyRolled) {
        this.alreadyRolled = alreadyRolled;
    }

    @Override
    public boolean isInPrison() {
        return this.isAPrisoner;
    }

    @Override
    public List<Integer> lastDicesNumber() {
        return Collections.unmodifiableList(this.dicesNumbers);
    }

    @Override
    public void setLastDicesNumber(final List<Integer> numbers) {
        if (!this.dicesNumbers.isEmpty()) {
            this.dicesNumbers.clear();
        }
        this.dicesNumbers.addAll(numbers);
    }

    @Override
    public void setPrison(final boolean isGoingToPrison) {
        this.isAPrisoner = isGoingToPrison;
    }

    @Override
    public void addOwnership(final Ownership ownership) {
        this.ownerships.orElse(new HashSet<>()).add(ownership);
    }

    @Override
    public void removeOwnership(final Ownership ownership) {
        this.ownerships.get().remove(ownership);
    }

    @Override
    public int getMoney() {
        return this.money;
    }

    @Override
    public void setMoney(final int amount) {
        this.money = amount;
    }

    @Override
    public boolean areDebtsPaid() {
        return this.debtsPaid;
    }

    @Override
    public void setDebts(final boolean arePaid) {
        this.debtsPaid = arePaid;
    }

    @Override
    public boolean isHuman() {
        return this.human;
    }

}
