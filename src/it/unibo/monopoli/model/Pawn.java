package it.unibo.monopoli.model;

public interface Pawn {
    
    /**
     * Return the ID of the {@link Pawn}.
     * 
     * @return the ID of the {@link Pawn}
     */
    int getID();
    
    /**
     * Return the actual position of the {@link Pawn} in the table of play.
     * This position is represented by the ID of the {@link Box} in witch the {@link Pawn} is located.
     * 
     * @return the actual position of the {@link Pawn}
     */
    int getActualPos();

}
