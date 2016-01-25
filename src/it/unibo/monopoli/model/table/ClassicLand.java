package it.unibo.monopoli.model.table;

import java.awt.Color;

import it.unibo.monopoli.model.mainunits.Owner;

/**
 * This class represents an implementation of {@link Land}, more specifically it
 * represents the {@link ClassicLand}s of Monopoly.
 *
 */
public class ClassicLand extends ClassicOwnership implements Land {

    private final Color color;

    /**
     * Constructs an implementation of {@link ClassicLand} that needs a name, a
     * cost, an ID, a {@link Owner}, a {@link Group} and a {@link Color}.
     * 
     * @param name
     *            - of the {@link ClassicLand}
     * @param id
     *            - of the {@link ClassicLand}
     * @param owner
     *            - {@link Owner} of the {@link ClassicLand}
     * @param color
     *            - {@link Color} of the {@link ClassicLand}
     */
    public ClassicLand(final String name, final int id, final Owner owner, final Color color) {
        super(name, id, owner);
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

}
