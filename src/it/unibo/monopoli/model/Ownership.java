package it.unibo.monopoli.model;

public interface Ownership extends Box{
    
    /**
     * Return the {@link Contract} to which has to fulfill.
     * 
     * @return the {@link Contract} to fulfill
     */
    LandContract getContract();
    
    /**
     * Return the {@link Player} that own this {@link Ownership}.
     * 
     * @return the owner of this {@link Ownership} or Null if it hasn't a owner
     */
    Player getOwner();
    
    /**
     * Return the {@link Group} to which it belongs
     * 
     * @return a {@link Group}
     */
    Group getGroup();
    
//    /**
//     * Return true if the {@link Ownership} is mortgaged, else false.
//     * 
//     * @return true if the {@link Ownership} is mortgaged
//     */
//    boolean isMortgaged(); //oppure isDisable
//    
//    /**
//     * Set true if the {@link Ownership} has been mortgage, else set false if the {@link Ownership} isn't longer mortgaged
//     *  
//     * @param mortgage - is a {@link Boolean} that indicates whether the {@link Ownership} is mortgaged (true) or not (false)
//     */
//    void setMortgage(boolean mortgage);

}
