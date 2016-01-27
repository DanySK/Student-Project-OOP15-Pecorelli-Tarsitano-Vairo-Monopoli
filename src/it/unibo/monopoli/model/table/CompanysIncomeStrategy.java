package it.unibo.monopoli.model.table;

import it.unibo.monopoli.model.mainunits.Player;

public class CompanysIncomeStrategy extends BusinessesIncomeStrategy {

    private static final int ONE_COMPANY = 4;
    private static final int TWO_COMPANIES = 10;

    private final Player player;

    public CompanysIncomeStrategy(final Ownership ownership, final Player player) {
        super(ownership);
        this.player = player;
    }

    @Override
    protected int getBusinessesIncome(final int size) {
        switch (size) {
        case 1: return this.player.lastDicesNumber().stream().reduce((b, b1) -> b + b1).get() * ONE_COMPANY;
        case 2: return this.player.lastDicesNumber().stream().reduce((b, b1) -> b + b1).get() * TWO_COMPANIES;
        default: throw new IllegalArgumentException();
        }
    }

}
