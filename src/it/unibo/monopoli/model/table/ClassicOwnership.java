package it.unibo.monopoli.model.table;

import it.unibo.monopoli.model.mainunits.Owner;

/**
 * This class represents an implementation of {@link Ownership}, more
 * specifically it represents the {@link ClassicOwnership}s of Monopoly.
 *
 */
public class ClassicOwnership implements Ownership {

    private final String name;
    private final int id;
    private Contract contract;
    private Owner owner;
//    private final Set<Action> allowedActions;
//    private final Set<Action> obligatoryActions;
    private boolean mortgaged;
    private Group group;

    /**
     * Constructs an implementation of {@link ClassicOwnership} that needs a
     * name, an ID and an {@link Owner}.
     * 
     * @param name
     *            - of the {@link ClassicOwnership}
     * @param id
     *            - of the {@link ClassicOwnership}
     * @param owner
     *            - {@link Owner} of the {@link ClassicOwnership}
     */
    public ClassicOwnership(final String name, final int id, final Owner owner) {
        this.name = name;
        this.id = id;
        this.owner = owner;
//        this.allowedActions = new HashSet<>();
//        this.obligatoryActions = new HashSet<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getID() {
        return this.id;
    }

//    @Override
//    public Set<Action> getAllowedActions() {
//        return this.allowedActions;
//    }
//
//    @Override
//    public Set<Action> getObligatoryActions() {
//        return this.obligatoryActions;
//    }

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

//    @Override
//    public void setAllowedActions(final Set<Action> actions) {
//        this.allowedActions.addAll(actions);
//    }
//
//    @Override
//    public void setObligatoryActions(final Set<Action> actions) {
//        this.obligatoryActions.addAll(actions);
//    }

    @Override
    public void setOwner(final Owner owner) {
        this.owner = owner;
    }

    @Override
    public void setContract(final Contract contract) {
        this.contract = contract;
    }

    @Override
    public void setGroup(final Group group) {
        this.group = group;
    }

}
