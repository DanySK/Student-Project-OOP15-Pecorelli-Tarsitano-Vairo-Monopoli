package it.unibo.monopoli.model.actions;

import java.util.Optional;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This abstract class represent the common contract of sub-classes that want to
 * buy or sell {@link Ownership}s and/or {@link Building}s.
 *
 */
public abstract class ToBuyAndSellProperties implements Action {

    private final int amount;
    private final Optional<Building> building;

    /**
     * This constructor serves to get the amount to buy/sell {@link Ownership}s.
     * 
     * @param amount
     *            - of the {@link Ownership}
     */
    protected ToBuyAndSellProperties(final int amount) {
        this.amount = amount;
        this.building = Optional.empty();
    }

    /**
     * This constructor serves to get the amount of the {@link Building} to
     * buy/sell.
     * 
     * @param amount
     *            - of the {@link Building}
     * @param building
     *            - the {@link Building} to buy/sell
     */
    protected ToBuyAndSellProperties(final int amount, final Building building) {
        this.amount = amount;
        this.building = Optional.of(building);
    }

    @Override
    public void play(final Player player) {
        player.setMoney(player.getMoney() + this.amount);
        if (this.building.isPresent()) {
            this.whatToDoWithBuilding(this.building.get());
        } else {
            this.whatToDoWithOwnership(player);
        }
        // this.building.isPresent() ? this.whatToDoWithBuilding() :
        // this.whatToDoWithOwnership();
    }

    /**
     * This is an abstract method that the specifics sub-classes have to
     * implements depending on the strategy. This method represent how to
     * operate with the {@link Building} to buy/sell
     * 
     * @param building
     *            - the {@link Building} to buy/sell
     */
    protected abstract void whatToDoWithBuilding(final Building building);

    /**
     * This is an abstract method that the specifics sub-classes have to
     * implements depending on the strategy. This method represent how to
     * operate with the {@link Ownership} to buy/sell
     * 
     * @param player
     *            - the {@link Player} that is buying/selling
     */
    protected abstract void whatToDoWithOwnership(final Player player);

}
