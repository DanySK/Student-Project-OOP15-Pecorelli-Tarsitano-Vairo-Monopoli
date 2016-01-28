package it.unibo.monopoli.model.table;

import it.unibo.monopoli.model.cards.Deck;

/**
 * This class represents an implementation of {@link Box}. It represents
 * {@link DecksBox}'s {@link Box}es of Monopoly, the {@link Box}es on which you have
 * to draw from a {@link Deck}.
 *
 */
public class DecksBox implements Box {

    private final String name;
    private final int id;
//    private final Set<Action> allowedActions;
//    private final Set<Action> obligatoryActions;

    /**
     * Constructs an implementation of {@link DecksBox}'s {@link Box} that needs a
     * name and a ID.
     * 
     * @param name
     *            - of this {@link Box}
     * @param id
     *            - of this {@link Box}
     * @param deck
     *            - {@link Deck} to draw from
     */
    public DecksBox(final String name, final int id, final Deck deck) {
        this.name = name;
        this.id = id;
//        this.allowedActions = new HashSet<>();
//        this.obligatoryActions = new HashSet<>();
//        this.obligatoryActions.add(new ToDrawCards(deck));
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
//
//    @Override
//    public void setAllowedActions(final Set<Action> actions) {
//        this.allowedActions.addAll(actions);
//    }
//
//    @Override
//    public void setObligatoryActions(final Set<Action> actions) {
//        this.obligatoryActions.addAll(actions);
//    }

}
