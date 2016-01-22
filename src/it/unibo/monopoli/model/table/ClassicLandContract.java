package it.unibo.monopoli.model.table;

public class ClassicLandContract extends ClassicContract implements LandContract{
    
    public ClassicLandContract(final String name) {
        super(name);
    }

    @Override
    public int getCostForEachBuilding(Building building) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getBuildingIncome() {
        // TODO Auto-generated method stub
        return 0;
    }

}
