package it.unibo.monopoli.model.actions;

import java.util.Objects;

import it.unibo.monopoli.model.mainunits.Bank;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Home;
import it.unibo.monopoli.model.table.Hotel;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.LandContract;
import it.unibo.monopoli.model.table.LandGroup;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This class represent one of the {@link MoneyAction}s of the game. This one
 * allows to buy properties: {@link Ownership}s and/or {@link Building}s.
 *
 */
public final class ToBuyProperties extends ToBuyAndSellProperties {

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
     * class. It serves for buying an {@link Ownership}.
     * 
     * @param amount
     *            - the amount of the {@link Ownership}.
     * @param ownership
     *            - the {@link Ownership} to buy
     * @return an instance of this class
     * @throws NullPointerException
     *             - if {@link Ownership} is null
     */
    public static ToBuyProperties buyAOwnership(final int amount, final Ownership ownership) {
        // if (amount <= 0) {
        // throw new IllegalArgumentException("Only positive amount different of
        // zero!");
        // }
        return new ToBuyProperties(-amount, Objects.requireNonNull(ownership));
    }

    /**
     * This is a static method that can be used to create a new instance of this
     * class. It serves for buying a {@link Building}.
     * @param land
     *            - the {@link Land} on which the {@link Building} will be built
     * @param bank
     *            - the {@link Bank} whence buy the {@link Building}
     * @return an instance of this class
     * @throws NullPointerException
     *             - if instead of {@link Land} and/or {@link Building} there
     *             are some null
     */
    public static ToBuyProperties buyABuilding(final Land land, final Bank bank) {
         if (land.isMortgaged()) {
             throw new IllegalArgumentException("Can't build in a mortgaged land");
         }
        return new ToBuyProperties(-((LandContract) land.getContract()).getCostForEachBuilding(), Objects.requireNonNull(land), bank.getBuilding(
                ((LandGroup) land.getGroup()).getBuildings().size() < 4 ? new Home() : new Hotel()));
    }

    @Override
    protected void whatToDoWithBuilding(final Building building) {
        if (this.ownership.isMortgaged()) {
            throw new IllegalArgumentException("You can't build in a mortgage land");
        }
        ((LandGroup) this.ownership.getGroup()).addBuilding(building);
    }

    @Override
    protected void whatToDoWithOwnership(final Player player) {
        player.addOwnership(this.ownership);
    }

}
