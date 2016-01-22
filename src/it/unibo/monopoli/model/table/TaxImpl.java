package it.unibo.monopoli.model.table;

import java.util.HashSet;
import java.util.Set;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.actions.ToPay;

/**
 * This class represents an implementation of {@link Tax}. More specifically it
 * represents the {@link Tax}'s {@link Box} of Monopoly.
 *
 */
public class TaxImpl implements Tax {

    private final String name;
    private final int ID;
    private final int cost;
    private final Set<Action> allowedActions;
    private final Set<Action> obligatoryActions;

    /**
     * Constructs an implementation of {@link TaxImpl} that needs a name, an ID
     * and a cost.
     * 
     * @param name
     *            - of this {@link Box}
     * @param ID
     *            - of this {@link Box}
     * @param cost
     *            - of this {@link Box}
     */
    public TaxImpl(final String name, final int ID, final int cost) {
        this.name = name;
        this.ID = ID;
        this.cost = cost;
        this.allowedActions = new HashSet<>();
        this.obligatoryActions = new HashSet<>();
        this.obligatoryActions.add(new ToPay(this.cost));
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
    public Set<Action> getObligatoryActions() {
        return this.obligatoryActions;
    }

    @Override
    public void setAllowedActions(final Set<Action> actions) {
        this.allowedActions.addAll(actions);
    }

    @Override
    public void setObligatoryActions(final Set<Action> actions) {
        this.obligatoryActions.addAll(actions);
    }

    @Override
    public int getCost() {
        return this.cost;
    }

}
