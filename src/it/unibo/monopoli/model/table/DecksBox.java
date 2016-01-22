package it.unibo.monopoli.model.table;

import java.util.HashSet;
import java.util.Set;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.actions.ToDrawCards;
import it.unibo.monopoli.model.cards.Deck;

/**
 * This class represents an implementation of {@link ActionBox}. It represents
 * {@link DecksBox}'s {@link ActionBox}es of Monopoly, the {@link ActionBox}es on which you have
 * to draw from a {@link Deck}.
 *
 */
public class DecksBox implements ActionBox {

    private final String name;
    private final int ID;
    private final Set<Action> allowedActions;
    private final Set<Action> obligatoryActions;

    /**
     * Constructs an implementation of {@link DecksBox}'s {@link ActionBox} that needs a
     * name and a ID.
     * 
     * @param name
     *            - of this {@link ActionBox}
     * @param ID
     *            - of this {@link ActionBox}
     * @param deck
     *            - {@link Deck} to draw from
     */
    public DecksBox(final String name, final int ID, final Deck deck) {
        this.name = name;
        this.ID = ID;
        this.allowedActions = new HashSet<>();
        this.obligatoryActions = new HashSet<>();
        this.obligatoryActions.add(new ToDrawCards(deck));
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
