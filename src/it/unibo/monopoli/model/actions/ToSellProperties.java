package it.unibo.monopoli.model.actions;

import java.util.Objects;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.ClassicLandContract;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.LandGroup;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This class represent one of the {@link MoneyAction}s of the game. This one
 * allows to sell properties: {@link Ownership}s and/or {@link Building}s.
 *
 */
public final class ToSellProperties extends ToBuyAndSellProperties {

    private final Ownership ownership;

    private ToSellProperties(final int amount, final Ownership ownership) {
        super(amount);
        this.ownership = ownership;
    }

    private ToSellProperties(final int amount, final Land land, final Building building) {
        super(amount, building);
        this.ownership = land;
    }

    /**
     * This is a static method that can be used to create a new instance of this
     * class. It serves for selling an {@link Ownership}.
     * 
     * @param ownership
     *            - the {@link Ownership} to sell
     * @return an instance of this class
     * @throws NullPointerException
     *             - if instead of an {@link Ownership} there is null
     */
    public static ToSellProperties sellAOwnership(final Ownership ownership) {
        // if (amount <= 0) {
        // throw new IllegalArgumentException("Only positive amount different of
        // zero!");
        // }
        return new ToSellProperties(ownership.getContract().getCost(), Objects.requireNonNull(ownership));
    }

    /**
     * This is a static method that can be used to create a new instance of this
     * class. It serves for selling a {@link Building}.
     * 
     * @param land
     *            - the {@link Land} on which the {@link Building} was built
     * @param building
     *            - the {@link Building} to sell
     * @return an instance of this class
     * @throws NullPointerException
     *             - if instead of an {@link Land} and/or a {@link Building}
     */
    public static ToSellProperties sellABuilding(final Land land, final Building building) {
        // if (amount <= 0) {
        // throw new IllegalArgumentException("Only positive amount different of
        // zero!");
        // }
        return new ToSellProperties(((ClassicLandContract) land.getContract()).getCostForEachBuilding(),
                Objects.requireNonNull(land), Objects.requireNonNull(building));
    }

    @Override
    protected void whatToDoWithBuilding(final Building building) {
        if (((LandGroup) this.ownership.getGroup()).getBuildings().contains(building)) {
            ((LandGroup) this.ownership.getGroup()).removeBuilding(building);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    protected void whatToDoWithOwnership(final Player player) {
        if (player.getOwnerships().isPresent() && player.getOwnerships().get().contains(this.ownership)) {
            if (this.ownership.getGroup() instanceof LandGroup
                    && !((LandGroup) this.ownership.getGroup()).getBuildings().isEmpty()) {
                throw new IllegalArgumentException("You can't sell an ownership if in its group there are buildings");
            }
            player.removeOwnership(this.ownership);
        } else {
            throw new IllegalArgumentException();
        }
    }

}
