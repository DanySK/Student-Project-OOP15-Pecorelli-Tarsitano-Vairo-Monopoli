package it.unibo.monopoli.model.table;

import java.util.List;

public class CompanysIncomeStrategy implements IncomeStrategy {

    private final Ownership ownership;

    public CompanysIncomeStrategy(final Ownership ownership) {
        this.ownership = ownership;
    }

    @Override
    public int getIncome() {
        final Set<Ownership> members = this.ownership.getGroup().getMembers();
        switch (build.size()) {
        case 1: 
            return (build.get(0) instanceof Home ? ONE_HOME : ONE_HOTEL) * ((ClassicLandContract) this.ownership.getContract()).getLandIncome();
        case 2: return ((ClassicLandContract) this.ownership.getContract()).getLandIncome() * TWO_HOMES;
        case 3: return ((ClassicLandContract) this.ownership.getContract()).getLandIncome() * THREE_HOMES;
        case 4: return ((ClassicLandContract) this.ownership.getContract()).getLandIncome() * FOUR_HOMES;
        default: return ((ClassicLandContract) this.ownership.getContract()).getLandIncome();
       }
    }

}
