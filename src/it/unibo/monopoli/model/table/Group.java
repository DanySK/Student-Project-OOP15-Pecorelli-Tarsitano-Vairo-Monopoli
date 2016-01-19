package it.unibo.monopoli.model.table;

import java.util.Set;

/**
 * This interface represent all the groups of {@link Ownership}s present in the
 * game. Indeed, every {@link Ownership}s belongs to a group.
 *
 */
public interface Group {

    /**
     * Return a {@link Set} of the members of this {@link Group}.
     * 
     * @return a {@link Set} of {@link Ownership}s
     */
    Set<Ownership> getMembers();

}
