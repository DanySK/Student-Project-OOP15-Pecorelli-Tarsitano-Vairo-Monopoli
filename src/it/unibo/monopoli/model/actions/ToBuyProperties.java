package it.unibo.monopoli.model.actions;

import java.util.Objects;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.LandGroup;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This class represent one of the {@link Action}s of the game. This one allows
 * to buy {@link Ownership}s and/or {@link Building}s.
 *
 */
public class ToBuyProperties extends ToBuyAndSellProperties {

    private final Ownership ownership;

    private ToBuyProperties(final int amount, final Ownership ownership) {
        super(amount);
        this.ownership = ownership;
    }

    private ToBuyProperties(final int amount, final Land land, final Building building) {
        super(amount, building);
        this.ownership = land;
    }

    /**
     * This is a static method that can be used to create a new instance of this
     * class.
     * 
     * @param amount
     *            - the amount necessary to buy something
     * @param ownership
     *            - the {@link Ownership} to buy
     * @return an instance of this class
     * @throws NullPointerException
     *             - if instead of an {@link Ownership} there is null
     * @throws IllegalArgumentException
     *             - if the amount is less than or equal to zero
     */
    public static ToBuyProperties buyAOwnership(final int amount, final Ownership ownership) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Only positive amount different of zero!");
        }
        return new ToBuyProperties(-amount, Objects.requireNonNull(ownership));
    }

    /**
     * This is a static method that can be used to create a new instance of this
     * class.
     * 
     * @param amount
     *            - the amount necessary to buy something
     * @param land
     *            - the {@link Land} on which the {@link Building} will be built
     * @param building
     *            - the {@link Building} to buy
     * @return an instance of this class
     * @throws NullPointerException
     *             - if instead of an {@link Land} and/or a {@link Building}
     *             there are some null
     * @throws IllegalArgumentException
     *             - if the amount is less than or equal to zero
     */
    public static ToBuyProperties buyABuilding(final int amount, final Land land, final Building building) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Only positive amount different of zero!");
        }
        return new ToBuyProperties(-amount, Objects.requireNonNull(land), Objects.requireNonNull(building));
    }

    @Override
    protected void whatToDoWithBuilding(final Building building) {
        ((LandGroup) this.ownership.getGroup()).addBuilding(building);
    }

    @Override
    protected void whatToDoWithOwnership(final Player player) {
        player.addOwnership(this.ownership);
    }

}
