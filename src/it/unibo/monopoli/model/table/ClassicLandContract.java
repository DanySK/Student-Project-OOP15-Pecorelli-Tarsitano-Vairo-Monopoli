package it.unibo.monopoli.model.table;

import java.util.List;

public class ClassicLandContract extends ClassicContract implements LandContract {

    private static final int ONE_HOME = 5;
    private static final int TWO_HOMES = 15;
    private static final int THREE_HOMES = 35;
    private static final int FOUR_HOMES = 55;
    private static final int ONE_HOTEL = 75;

    private final int buildingsCost;
    private final int landIncome;

    protected ClassicLandContract(final Ownership ownership, final int cost, final int income, final int buildingsCost) {
        super(ownership, cost, income);
        this.buildingsCost = buildingsCost;
        this.landIncome = income;

    }

    @Override
    public int getCostForEachBuilding(final Building building) {
        return this.buildingsCost;
    }

    @Override
    public int getBuildingIncome() {
        List<Building> build = ((LandGroup) this.getOwnership().getGroup()).getBuildings();
        switch (build.size()) {
        case 1: 
            return build.get(0) instanceof Home ? this.getLandIncome() * ONE_HOME : this.getLandIncome() * ONE_HOTEL;
        case 2: return this.getLandIncome() * TWO_HOMES;
        case 3: return this.getLandIncome() * THREE_HOMES;
        case 4: return this.getLandIncome() * FOUR_HOMES;
        default: throw new IllegalStateException("There are no buildings constructed");
       }
    }

    @Override
    public int getLandIncome() {
        return this.landIncome;
    }

    public static class Builder {
        private Ownership land;
        private int landCost;
        private int landIncome;
        private int buildingsCost;

        public Builder land(final Ownership l) {
            this.land = l;
            return this;
        }

        public Builder landCost(final int c) {
            this.landCost = c;
            return this;
        }

        public Builder landIncome(final int i) {
            this.landIncome = i;
            return this;
        }

        public Builder buildingCost(final int bc) {
            this.buildingsCost = bc;
            return this;
        }

        public ClassicContract build() throws IllegalArgumentException {
            if (this.land == null || this.landCost == 0 || this.landIncome == 0
                    || this.buildingsCost == 0) {
                throw new IllegalArgumentException();
            }
            return new ClassicLandContract(this.land, this.landCost, this.landIncome,
                    this.buildingsCost);
        }
    }
}
