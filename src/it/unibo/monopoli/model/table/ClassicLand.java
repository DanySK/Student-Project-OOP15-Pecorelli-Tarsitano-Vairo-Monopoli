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
     * @param costOfLand
     *            - cost of the {@link ClassicLand}
     * @param ID
     *            - of the {@link ClassicLand}
     * @param owner
     *            - {@link Owner} of the {@link ClassicLand}
     * @param group
     *            - {@link Group} of the {@link ClassicLand}
     * @param contract
     *            - {@link Contract} of the {@link ClassicLand}
     * @param color
     *            - {@link Color} of the {@link ClassicLand}
     */
    public ClassicLand(final String name, final int costOfLand, final int ID, final Owner owner, final Group group,
            final Contract contract, final Color color) {
        super(name, costOfLand, ID, owner, group, contract);
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

}
