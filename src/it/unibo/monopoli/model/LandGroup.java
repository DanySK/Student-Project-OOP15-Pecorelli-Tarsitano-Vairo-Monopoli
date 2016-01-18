package it.unibo.monopoli.model;

import java.util.Set;

public interface LandGroup { 
    
    /**
     * Return a {@link Set} of {@link Building}s that were built on such {@link LandGroup}
     * 
     * @return a {@link Set} of {@link Building}
     */
    Set<Building> getBuildings();
    
    /**
     * Add a new {@link Building}.
     * 
     * @param the building to add
     */
    void addBuilding(Building building);
    
    /**
     * Remove a {@link Building} of this {@link Group}.
     * 
     * @param the building to remove
     */
    void removeBuilding(Building building);

}
