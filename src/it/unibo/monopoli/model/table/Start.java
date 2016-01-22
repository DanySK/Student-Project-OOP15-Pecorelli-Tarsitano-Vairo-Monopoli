package it.unibo.monopoli.model.table;

import java.util.Set;

import it.unibo.monopoli.model.actions.Action;

public class Start implements Box{

    private final String name;
    private final int ID;
    
    public Start(final String name, final int ID) {
        this.name = name;
        this.ID = ID;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getID() {
        return this.ID;
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

    @Override
    public void setAllowedActions(Set<Action> actions) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setObligatoryActions(Set<Action> actions) {
        // TODO Auto-generated method stub
        
    }

}
