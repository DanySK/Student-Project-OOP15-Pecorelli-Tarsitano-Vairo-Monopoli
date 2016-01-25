package it.unibo.monopoli.model.mainunits;

import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.ClassicGroup;
import it.unibo.monopoli.model.table.LandGroup;
import it.unibo.monopoli.model.table.Ownership;

public class ClassicLandGroup extends ClassicGroup implements LandGroup {

    private final List<Building> buildings;

    public ClassicLandGroup(final String name, final Ownership... members) {
        super(name, members);
        this.buildings = new LinkedList<>();
    }

    @Override
    public List<Building> getBuildings() {
        return this.buildings;
    }

    @Override
    public void addBuilding(final Building building) {
        this.buildings.add(building);
    }

    @Override
    public void removeBuilding(final Building building) {
        this.buildings.remove(building);
    }

}