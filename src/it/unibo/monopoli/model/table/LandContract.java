package it.unibo.monopoli.model.table;

public interface LandContract extends Contract{
    
    /**
     * Return the cost to build a {@link Building} on the {@link Land}.
     * 
     * @param building - the specific {@link Building}
     * @return the cost to build a {@link Building}
     */
    int getCostForEachBuilding(final Building building);
    
    /**
     * Return the income of the {@link Building}.
     * 
     * @return the income of the {@link Building}
     */
    int getBuildingIncome();

}
