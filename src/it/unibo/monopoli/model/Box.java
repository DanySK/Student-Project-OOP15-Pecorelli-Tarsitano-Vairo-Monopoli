package it.unibo.monopoli.model;

import java.util.Set;

public interface Box {
    
    /**
     * Return the name that identifies the {@link Box}.
     * 
     * @return the name of the {@link Box}
     */
    String getName();
    
    /**
     * Return the ID of the {@link Box}.
     * 
     * @return the ID of the {@link Box}
     */
    int getID();
    
    /**
     * Return a {@link Set} of allowed {@link Action}s to do in this {@link Box}.
     *  
     * @return a {@link Set} of {@link Action}
     */
    Set<Action> getAllowedActions();
    
    /**
     * Return a {@link Set} of obligatory {@link Action}s to do in this {@link Box}.
     *  
     * @return a {@link Set} of {@link Action}
     */
    Set<Action> getObligatoryActions();

}
