package it.unibo.monopoli.model.table;


public class StationIncomeStrategy extends BusinessesIncomeStrategy {

    private static final int ONE_STATION = 25;
    private static final int TWO_STATION = 50;
    private static final int THREE_STATION = 100;
    private static final int FOUR_STATION = 200;

    public StationIncomeStrategy(final Ownership ownership) {
        super(ownership);
    }

    @Override
    protected int getBusinessesIncome(final int size) {
        switch (size) {
        case 1: return ONE_STATION;
        case 2: return TWO_STATION;
        case 3: return THREE_STATION;
        case 4: return FOUR_STATION;
        default: throw new IllegalArgumentException();
        }
    }

}
