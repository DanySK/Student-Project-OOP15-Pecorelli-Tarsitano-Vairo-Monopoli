package it.unibo.monopoli.model.table;

public class ClassicContract implements Contract{

    private final Ownership ownership;
    private final int cost;

    protected ClassicContract(final Ownership ownership, final int cost) {
        this.ownership = ownership;
        this.cost = cost;
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
    public int getIncome(final IncomeStrategy income) {
        return income.getIncome();
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

        public ClassicContract build() throws IllegalArgumentException{
            if (this.ownership == null || this.cost == 0) {
                throw new IllegalArgumentException();
            }
            return new ClassicContract(this.ownership, this.cost);
        }
    }

}
