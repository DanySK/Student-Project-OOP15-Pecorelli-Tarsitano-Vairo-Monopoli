package it.unibo.monopoli.model.actions;

import java.util.Objects;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.LandGroup;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This class represent one of the {@link Action}s of the game.
 * This one allows to sell {@link Ownership}s and/or {@link Building}s.
 *
 */
public class ToSell extends ToBuyAndSell {

    private ToSell(final int amount, final Ownership ownership) {
        super(amount, Objects.requireNonNull(ownership));
    }

    private ToSell(final int amount, final Land land, final Building building) {
        super(amount, Objects.requireNonNull(land), Objects.requireNonNull(building));
    }

    /**
     * This is a static method that can be used to create a new instance of this class.
     * @param amount - the amount of the sale
     * @param ownership - the {@link Ownership} to sell
     * @return an instance of this class
     * @throws NullPointerException - if instead of an {@link Ownership} there is null
     */
    public static ToSell buyAOwnership(final int amount, final Ownership ownership) {
        return new ToSell(-amount, Objects.requireNonNull(ownership));
    }

    /**
     * This is a static method that can be used to create a new instance of this class.
     * @param amount - the amount of the sale
     * @param land - the {@link Land} on which the {@link Building} was built 
     * @param building - the {@link Building} to sell
     * @return an instance of this class
     * @throws NullPointerException - if instead of an {@link Land} and/or a {@link Building} there are some null
     */
    public static ToSell buyABuilding(final int amount, final Land land, final Building building) {
        return new ToSell(-amount, Objects.requireNonNull(land), Objects.requireNonNull(building));
    }

    @Override
    protected void whatToDoWithBuilding(final Land land, final Building building) {
        ((LandGroup) land.getGroup()).removeBuilding(building);
    }

    @Override
    protected void whatToDoWithOwnership(final Ownership ownership, final Player player) {
        player.removeOwnership(ownership);
    }

}
