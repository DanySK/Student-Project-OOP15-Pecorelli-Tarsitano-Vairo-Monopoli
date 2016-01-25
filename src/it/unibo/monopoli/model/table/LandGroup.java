package it.unibo.monopoli.model.table;

import java.util.List;

/**
 * This interface represent the {@link Group}s of {@link Land}s. They are
 * different from other {@link Group}s because on these you can build
 * {@link Building}s.
 *
 */
public interface LandGroup {

    /**
     * Return a {@link List} of {@link Building}s that were built on such
     * {@link LandGroup}.
     * 
     * @return a {@link List} of {@link Building}
     */
    List<Building> getBuildings();

    /**
     * Add a new {@link Building}.
     * 
     * @param building
     *            - the one to add
     */
    void addBuilding(Building building);

    /**
     * Remove a {@link Building} from the {@link Group}.
     * 
     * @param building
     *            - the one to remove
     */
    void removeBuilding(Building building);

}
