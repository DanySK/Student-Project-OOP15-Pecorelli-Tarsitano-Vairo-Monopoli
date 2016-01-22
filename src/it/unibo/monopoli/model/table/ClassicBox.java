package it.unibo.monopoli.model.table;

import java.util.Set;

import it.unibo.monopoli.model.actions.Action;

public class ClassicBox implements Box{

    private final String name;
    private final ClassicLandContract contract;
    
    public ClassicBox(final String name) {
        this.name = name;
        this.contract = new ClassicLandContract(name);
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Set<Action> getAllowedActions() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Action> getObligatoryActions() {
        // TODO Auto-generated method stub
        return null;
    }

}
