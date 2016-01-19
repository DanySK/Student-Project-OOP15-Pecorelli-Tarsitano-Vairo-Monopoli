package it.unibo.monopoli.model.actions;

import java.util.Objects;
import java.util.Optional;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.LandGroup;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This class represent one of the {@link Action}s of the game.
 * This one allows to buy {@link Ownership}s and/or {@link Building}s.
 *
 */
public class ToBuy implements Action {

    private final int amount;
    private final Ownership ownership;
    private final Optional<Building> building;

    private ToBuy(final int amount, final Ownership ownership) {
        this.amount = amount;
        this.ownership = ownership;
        this.building = Optional.empty();
    }

    private ToBuy(final int amount, final Land land, final Building building) {
        this.amount = amount;
        this.ownership = land;
        this.building = Optional.of(building);
    }

    /**
     * This is a static method that can be used to create a new instance of this class.
     * @param amount - the amount necessary to buy something
     * @param ownership - the {@link Ownership} to buy
     * @return an instance of this class
     * @throws NullPointerException - if instead of an {@link Ownership} there is null
     */
    public static ToBuy buyAOwnership(final int amount, final Ownership ownership) {
        return new ToBuy(amount, Objects.requireNonNull(ownership));
    }

    /**
     * This is a static method that can be used to create a new instance of this class.
     * @param amount - the amount necessary to buy something
     * @param land - the {@link Land} on which you want to built the {@link Building} that you're buying 
     * @param building - the {@link Building} to buy
     * @return an instance of this class
     * @throws NullPointerException - if instead of an {@link Land} and/or a {@link Building} there are some null
     */
    public static ToBuy buyABuilding(final int amount, final Land land, final Building building) {
        return new ToBuy(amount, Objects.requireNonNull(land), Objects.requireNonNull(building));
    }

    @Override
    public void play(final Player player) {
        player.setMoney(player.getMoney() - this.amount);
        if (this.building.isPresent() && player.getOwnerships().contains(this.ownership)) {
            ((LandGroup) this.ownership.getGroup()).addBuilding(this.building.get());
        } else {
            player.addOwnership(this.ownership);
        }
    }

}
