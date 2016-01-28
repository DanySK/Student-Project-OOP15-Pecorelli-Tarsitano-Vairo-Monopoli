package it.unibo.monopoli.model.table;


/**
 * This class represents an implementation of {@link Box}. More specifically it
 * represents the {@link Police}'s {@link Box} of Monopoly.
 *
 */
public class Police implements Box {

    private final String name;
    private final int ID;
//    private final Set<Action> allowedActions;
//    private final Set<Action> obligatoryActions;

    /**
     * Constructs an implementation of {@link Police} that needs a name, an ID
     * and the {@link PrisonOrTransit}'s {@link Box}.
     * 
     * @param name
     *            - of this {@link Box}
     * @param ID
     *            - of this {@link Box}
     * @param prison
     *            - the {@link PrisonOrTransit}'s {@link Box}
     */
    public Police(final String name, final int ID, final Box prison) {
        this.name = name;
        this.ID = ID;
//        this.allowedActions = new HashSet<>();
//        this.obligatoryActions = new HashSet<>();
//        this.obligatoryActions.add(new GoToPrison(prison));
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getID() {
        return this.ID;
    }

//    @Override
//    public Set<Action> getAllowedActions() {
//        return Collections.unmodifiableSet(this.allowedActions);
//    }
//
//    @Override
//    public void setAllowedActions(final Set<Action> actions) {
//        this.allowedActions.addAll(actions);
//    }
//
//    @Override
//    public Set<Action> getObligatoryActions() {
//        return Collections.unmodifiableSet(this.obligatoryActions);
//    }
//
//    @Override
//    public void setObligatoryActions(final Set<Action> actions) {
//        this.obligatoryActions.addAll(actions);
//    }

}
