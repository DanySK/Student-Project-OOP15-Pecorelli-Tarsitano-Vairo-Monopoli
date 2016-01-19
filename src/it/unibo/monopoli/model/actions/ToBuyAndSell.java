package it.unibo.monopoli.model.actions;

import java.util.Optional;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This abstract class represent the common contract of classes {@link ToBuy} and {@link ToSell}.
 *
 */
public abstract class ToBuyAndSell implements Action {

    private final int amount;
    private final Ownership ownership;
    private final Optional<Building> building;

    /**
     * This constructor serves to get all the information about the {@link Ownership} to buy/sell and its amount.
     * @param amount - of the {@link Ownership}
     * @param ownership - the {@link Ownership} to buy/sell
     */
    protected ToBuyAndSell(final int amount, final Ownership ownership) {
        this.amount = amount;
        this.ownership = ownership;
        this.building = Optional.empty();
    }

    /**
     * This constructor serves to get all the information about the {@link Building} to buy/sell, the {@link Land} where it will be built 
     * and its amount.
     * @param amount - of the {@link Building}
     * @param land - the {@link Land} where the {@link Building} will be built
     * @param building - the {@link Building} to buy/sell
     */
    protected ToBuyAndSell(final int amount, final Land land, final Building building) {
        this.amount = amount;
        this.ownership = land;
        this.building = Optional.of(building);
    }

    @Override
    public void play(final Player player) {
        player.setMoney(player.getMoney() + this.amount);
        if (!player.getOwnerships().contains(this.ownership)) {
            throw new IllegalArgumentException("This player doesn't own this ownership");
        } 
        if (this.building.isPresent()) {
             this.whatToDoWithBuilding((Land) this.ownership, this.building.get());
        } else {
            this.whatToDoWithOwnership(this.ownership, player);
        }
//        this.building.isPresent() ? this.whatToDoWithBuilding() : this.whatToDoWithOwnership();
    }

    /**
     * This is an abstract method that the specifics sub-classes have to implements depending on the strategy.
     * This method represent how to operate with the {@link Building} to buy/sell
     * @param land - the {@link Land} of the {@link Building}
     * @param building - the {@link Building} to buy/sell
     */
    protected abstract void whatToDoWithBuilding(final Land land, final Building building);

    /**
     * This is an abstract method that the specifics sub-classes have to implements depending on the strategy.
     * This method represent how to operate with the {@link Ownership} to buy/sell
     * @param ownership - the {@link Ownership} to buy/sell
     * @param player - the {@link Player} that is buying/selling
     */
    protected abstract void whatToDoWithOwnership(final Ownership ownership, final Player player);

}
