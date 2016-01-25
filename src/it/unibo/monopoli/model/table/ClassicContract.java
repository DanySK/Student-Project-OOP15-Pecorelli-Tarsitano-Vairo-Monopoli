package it.unibo.monopoli.model.table;

public class ClassicContract implements Contract{

    private final Ownership ownership;
    private final int cost;
    private final int income;

    protected ClassicContract(final Ownership ownership, final int cost, final int income) {
        this.ownership = ownership;
        this.cost = cost;
        this.income = income;
    }

    @Override
    public String getname() {
        return this.ownership.getName();
    }

    @Override
    public Ownership getOwnership() {
        return this.ownership;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public int getIncome() {
        return this.income;
    }

    @Override
    public int getMortgageValue() {
        return this.cost / 2;
    }

    public static class Builder {
        private Ownership ownership;
        private int cost;
        private int income;

        public Builder ownership(final Ownership o) {
            this.ownership = o;
            return this;
        }

        public Builder ownershipsCost(final int c) {
            this.cost = c;
            return this;
        }

        public Builder income(final int i) {
            this.income = i;
            return this;
        }

        public ClassicContract build() throws IllegalArgumentException{
            if (this.ownership == null || this.cost == 0 || this.income == 0) {
                throw new IllegalArgumentException();
            }
            return new ClassicContract(this.ownership, this.cost, this.income);
        }
    }

}
