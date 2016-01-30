package it.unibo.monopoli.model.mainunits;

import java.util.Optional;

public enum Ownerships {
    OWNERSHIP1(60, 2, 50),
    OWNERSHIP2(60, 4, 50),
    OWNERSHIP3(200),
    OWNERSHIP4(100, 6, 50),
    OWNERSHIP5(100, 6, 50),
    OWNERSHIP6(120, 8, 50),
    OWNERSHIP7(140, 10, 100),
    OWNERSHIP8(150),
    OWNERSHIP9(140, 10, 100),
    OWNERSHIP10(160, 12, 100),
    OWNERSHIP11(200),
    OWNERSHIP12(180, 14, 100),
    OWNERSHIP13(180, 14, 100),
    OWNERSHIP14(200, 16, 100),
    OWNERSHIP15(220, 18, 150),
    OWNERSHIP16(220, 18, 150),
    OWNERSHIP17(240, 20, 150),
    OWNERSHIP18(200),
    OWNERSHIP19(260, 22, 150),
    OWNERSHIP20(260, 22, 150),
    OWNERSHIP21(150),
    OWNERSHIP22(280, 24, 150),
    OWNERSHIP23(300, 26, 200),
    OWNERSHIP24(300, 26, 200),
    OWNERSHIP25(320, 28, 200),
    OWNERSHIP26(200),
    OWNERSHIP27(350, 35, 200),
    OWNERSHIP28(400, 50, 200);

    private final int cost;
    private final Optional<Integer> income;
    private final Optional<Integer> buildingCost;

    Ownerships(final int cost) {
        this.cost = cost;
        this.income = Optional.empty();
        this.buildingCost = Optional.empty();
    }

    Ownerships(final int cost, final int income, final int buildingCost) {
        this.cost = cost;
        this.income = Optional.of(income);
        this.buildingCost = Optional.of(buildingCost);
    }

    public int getCost() {
        return this.cost;
    }

    public Optional<Integer> getIncome() {
        return this.income;
    }

    public Optional<Integer> getBuildingCost() {
        return this.buildingCost;
    }

}
