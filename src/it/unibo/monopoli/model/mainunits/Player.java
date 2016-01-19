package it.unibo.monopoli.model.mainunits;

//import java.util.Optional;
import java.util.Set;

import it.unibo.monopoli.model.table.Ownership;
/**
 * This interface represent all the players. They all have a name that identifies them, 
 * a {@link Pawn} that identifies them in the game, some {@link Ownership}s and 
 * some money.
 *
 */
public interface Player {

    /**
     * Return the name of the {@link Player}.
     * @return the name of the {@link Player}
     */
    String getName();

    /**
     * Return the {@link Pawn} of the {@link Player}.
     * Each {@link Player} can have only one {@link Pawn}.
     * @return the {@link Pawn} of the {@link Player}
     */
    Pawn getPawn();

    /**
     * Set the {@link Pawn} that will belong to the {@link Player}.
     */
    void setPawn();

    /**
     * Return the {@link Set} of {@link Ownership} belonging to the {@link Player}.
     * This {@link Set} can be empty.
     * @return a {@link Set} of {@link Ownership}
     */
    Set<Ownership> getOwnerships();

//    /**
//     * If the {@link Ownership} request belongs to the {@link Player}, 
//     * the method return an {@link Optional} of {@link Ownership}, else return 
//     * an {@link Optional} of null.
//     * @return an {@link Optional} of {@link Ownership}
//     * @param ownership - the {@link Ownership} to find the return
//     */
//    Optional<Ownership> getOwnership(Ownership ownership);

    /**
     * Add a new {@link Ownership}.
     * @param ownership - the one to add
     */
    void addOwnership(Ownership ownership);

    /**
     * Remove a {@link Ownership} of this {@link Player}.
     * @param ownership - the one to remove
     */
    void removeOwnership(Ownership ownership);

    /**
     * Return the money belonging to the {@link Player}.
     * @return the money belonging to the {@link Player}
     */
    int getMoney();

    /**
     * Set the money belonging to the {@link Player}.
     * @param amount - the amount of money to set
     */
    void setMoney(int amount);

}
