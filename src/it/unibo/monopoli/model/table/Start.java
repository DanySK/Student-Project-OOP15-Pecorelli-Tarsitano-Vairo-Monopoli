package it.unibo.monopoli.model.table;

import java.util.HashSet;
import java.util.Set;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.actions.ToBePaid;

/**
 * This class represents an implementation of {@link ActionBox}. More specifically it
 * represents the {@link Start}'s {@link ActionBox} of Monopoly.
 *
 */
public class Start implements ActionBox {

    private static final int MONEY_TO_PICK_UP = 1;

    private final String name;
    private final int ID;
    private final Set<Action> allowedActions;
    private final Set<Action> obligatoryActions;

    /**
     * Constructs an implementation of {@link Start} that needs a name and a ID.
     * 
     * @param name
     *            - of this {@link ActionBox}
     * @param ID
     *            - of this {@link ActionBox}
     */
    public Start(final String name, final int ID) {
        this.name = name;
        this.ID = ID;
        this.allowedActions = new HashSet<>();
        this.obligatoryActions = new HashSet<>();
        this.obligatoryActions.add(new ToBePaid(MONEY_TO_PICK_UP));
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

}
