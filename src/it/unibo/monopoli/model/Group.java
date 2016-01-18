package it.unibo.monopoli.model;

import java.util.Set;

public interface Group {
    
    /**
     * Return a {@link Set} of the members of this {@link Group}.
     * 
     * @return a {@link Set} of {@link Ownership}
     */
    Set<Ownership> getMembers();

}
