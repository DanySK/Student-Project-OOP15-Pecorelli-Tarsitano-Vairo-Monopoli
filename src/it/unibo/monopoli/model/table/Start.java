package it.unibo.monopoli.model.table;

import java.util.HashSet;
import java.util.Set;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.actions.ToBePaid;
import it.unibo.monopoli.model.mainunits.Player;

/**
 * This class represents an implementation of {@link Box}. More specifically it
 * represents the {@link Start}'s {@link Box} of Monopoly.
 *
 */
public class Start implements Box {

    private static final int MONEY_TO_PICK_UP = 200;

    private final String name;
    private final int id;
    private final Set<Action> allowedActions;
    private final Set<Action> obligatoryActions;

    /**
     * Constructs an implementation of {@link Start} that needs a name and a ID.
     * 
     * @param name
     *            - of this {@link Box}
     * @param id
     *            - of this {@link Box}
     */
    public Start(final String name, final int id) {
        this.name = name;
        this.id = id;
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
    public void setAllowedActions(final Set<Action> actions) {
        this.allowedActions.addAll(actions);
    }

    @Override
    public void setObligatoryActions(final Set<Action> actions) {
        this.obligatoryActions.addAll(actions);
    }

    /**
     * Returns the values of the money to pick up when some {@link Player} pass
     * from this {@link Box}.
     * 
     * @return the money to pick up
     */
    public static int getMuchToPick() {
        return MONEY_TO_PICK_UP;
    }

}
