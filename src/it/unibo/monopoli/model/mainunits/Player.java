package it.unibo.monopoli.model.mainunits;

import java.util.Set;

import it.unibo.monopoli.model.table.Ownership;

public interface Player {
    
    /**
     * Return the name of the {@link Player}.
     * 
     * @return the name of the {@link Player}
     */
    String getName();
    
    /**
     * Return the {@link Pawn} of the {@link Player}.
     * Each {@link Player} can have only one {@link Pawn}.
     * 
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
     * 
     * @return a {@link Set} of {@link Ownership}
     */
    Set<Ownership> getOwnerships();
    
    /**
     * Add a new {@link Ownership}.
     * 
     * @param the ownership to add
     */
    void addOwnership(Ownership ownership);
    
    /**
     * Remove a {@link Ownership} of this {@link Player}.
     * 
     * @param the ownership to remove
     */
    void removeOwnership(Ownership ownership);
    
    /**
     * Return the money belonging to the {@link Player}.
     * 
     * @return the money belonging to the {@link Player}
     */
    int getMoney();
    
    /**
     * Set the money belonging to the {@link Player}.
     */
    void setMoney();

}
