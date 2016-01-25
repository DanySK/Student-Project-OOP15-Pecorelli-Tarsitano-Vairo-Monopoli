package it.unibo.monopoli.model.table;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.monopoli.model.mainunits.Player;

public class LandIncomeStrategy extends AbstractIncomeStartegy {


    private static final int ONE_HOME = 5;
    private static final int TWO_HOMES = 15;
    private static final int THREE_HOMES = 35;
    private static final int FOUR_HOMES = 55;
    private static final int ONE_HOTEL = 75;

    private final Ownership ownership;

    public LandIncomeStrategy(final Ownership ownership) {
        super(ownership);
        this.ownership = ownership;
    }

    @Override
    protected int getSpecificIncome(final Set<Ownership> allMembers) {
        final List<Building> build = ((LandGroup) this.ownership.getGroup()).getBuildings();
        switch (build.size()) {
        case 1: 
            return (build.get(0) instanceof Home ? ONE_HOME : ONE_HOTEL) * ((ClassicLandContract) this.ownership.getContract()).getLandIncome();
        case 2: return ((ClassicLandContract) this.ownership.getContract()).getLandIncome() * TWO_HOMES;
        case 3: return ((ClassicLandContract) this.ownership.getContract()).getLandIncome() * THREE_HOMES;
        case 4: return ((ClassicLandContract) this.ownership.getContract()).getLandIncome() * FOUR_HOMES;
        default: return (((Player) this.ownership.getOwner()).getOwnerships().containsAll(allMembers) ? 2 : 1) * ((ClassicLandContract) this.ownership.getContract()).getLandIncome();
       }
    }

}
