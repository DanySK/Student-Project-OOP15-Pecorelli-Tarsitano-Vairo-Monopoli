package it.unibo.monopoli.model.table;

import java.util.HashSet;
import java.util.Set;

import it.unibo.monopoli.model.actions.Action;

/**
 * This class represents an implementation of {@link ActionBox}. More specifically it
 * represents the {@link PrisonOrTransit}'s {@link ActionBox} of Monopoly.
 *
 */
public class PrisonOrTransit implements ActionBox {

    private final String name;
    private final int ID;
    private final Set<Action> allowedActions;
    private final Set<Action> obligatoryActions;

    public PrisonOrTransit(final String name, final int ID) {
        this.name = name;
        this.ID = ID;
        this.allowedActions = new HashSet<>();
        this.obligatoryActions = new HashSet<>();
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
        return this.allowedActions;
    }

    @Override
    public void setAllowedActions(Set<Action> actions) {
        this.allowedActions.addAll(actions);
    }

    @Override
    public Set<Action> getObligatoryActions() {
        return this.obligatoryActions;
    }

    @Override
    public void setObligatoryActions(Set<Action> actions) {
        this.obligatoryActions.addAll(actions);
    }

}
