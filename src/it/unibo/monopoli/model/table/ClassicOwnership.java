package it.unibo.monopoli.model.table;

import java.util.HashSet;
import java.util.Set;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.mainunits.Owner;

/**
 * This class represents an implementation of {@link Ownership}, more
 * specifically it represents the {@link ClassicOwnership}s of Monopoly.
 *
 */
public class ClassicOwnership implements Ownership {

    private final String name;
    private final int cost;
    private final int id;
    private final Contract contract;
    private Owner owner;
    private final Set<Action> allowedActions;
    private final Set<Action> obligatoryActions;
    private boolean mortgaged;
    private final Group group;

    /**
     * Constructs an implementation of {@link ClassicOwnership} that needs a
     * name, a cost, an ID, a {@link Owner} and a {@link Group}.
     * 
     * @param name
     *            - of the {@link ClassicOwnership}
     * @param costOfOwnership
     *            - cost of the {@link ClassicOwnership}
     * @param id
     *            - of the {@link ClassicOwnership}
     * @param owner
     *            - {@link Owner} of the {@link ClassicOwnership}
     * @param group
     *            - {@link Group} of the {@link ClassicOwnership}
     * @param contract
     *            - {@link Contract} of the {@link ClassicOwnership}
     */
    public ClassicOwnership(final String name, final int costOfOwnership, final int id, final Owner owner,
            final Group group, final Contract contract) {
        this.name = name;
        this.cost = costOfOwnership;
        this.id = id;
        this.contract = contract;
        this.owner = owner;
        this.allowedActions = new HashSet<>();
        this.obligatoryActions = new HashSet<>();
        this.group = group;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getID() {
        return this.id;
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
    public Contract getContract() {
        return this.contract;
    }

    @Override
    public Owner getOwner() {
        return this.owner;
    }

    @Override
    public Group getGroup() {
        return this.group;
    }

    @Override
    public boolean isMortgaged() {
        return this.mortgaged;
    }

    @Override
    public void setMortgage(final boolean mortgage) {
        this.mortgaged = mortgage;
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
    public void setOwner(final Owner owner) {
        this.owner = owner;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

}
