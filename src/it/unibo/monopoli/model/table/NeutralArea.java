package it.unibo.monopoli.model.table;


/**
 * This class represents an implementation of {@link Box}. More specifically it
 * represents the {@link NeutralArea}'s {@link Box} of Monopoly.
 *
 */
public class NeutralArea implements Box {

    private final String name;
    private final int id;
//    private final Set<Action> allowedActions;
//    private final Set<Action> obligatoryActions;

    /**
     * Constructs an implementation of {@link NeutralArea} that needs a name and
     * an ID.
     * 
     * @param name
     *            - of this {@link Box}
     * @param id
     *            - of this {@link Box}
     */
    public NeutralArea(final String name, final int id) {
        this.name = name;
        this.id = id;
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
