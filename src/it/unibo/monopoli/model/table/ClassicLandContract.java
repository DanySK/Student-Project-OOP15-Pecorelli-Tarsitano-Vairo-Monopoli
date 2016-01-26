package it.unibo.monopoli.model.table;


public class ClassicLandContract extends ClassicContract implements LandContract {

    private final int buildingsCost;
    private final int landIncome;

    protected ClassicLandContract(final Ownership ownership, final int cost, final int landIncome, final int buildingsCost) {
        super(ownership, cost);
        this.buildingsCost = buildingsCost;
        this.landIncome = landIncome;

    }

    @Override
    public int getCostForEachBuilding() {
        return this.buildingsCost;
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
