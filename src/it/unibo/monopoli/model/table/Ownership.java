package it.unibo.monopoli.model.table;


import it.unibo.monopoli.model.mainunits.Owner;

/**
 * This interface represent all the ownership of this game. They are particular
 * boxes because you can buy and sell them.
 *
 */
public interface Ownership extends Box {

    /**
     * Return the {@link Contract} to which has to fulfill.
     * 
     * @return the {@link Contract} to fulfill
     */
    Contract getContract();

    /**
     * Return the {@link Owner} that own this {@link Ownership}.
     * 
     * @return the {@link Owner} of this {@link Ownership}
     */
    Owner getOwner();

    /**
     * Sets the {@link Owner} of this {@link Ownership}.
     * 
     * @param owner
     *            - the {@link Ownership}'s {@link Owner}
     */
    void setOwner(Owner owner);

    /**
     * Return the {@link Group} to which it belongs.
     * 
     * @return a {@link Group}
     */
    Group getGroup();

    /**
     * Return the {@link Ownership}'s cost.
     * 
     * @return {@link Ownership}'s cost
     */
    int getCost();

    /**
     * Return true if the {@link Ownership} is mortgaged, else false.
     * 
     * @return true if the {@link Ownership} is mortgaged
     */
    boolean isMortgaged();

    /**
     * Set true if the {@link Ownership} has been mortgage, else set false if
     * the {@link Ownership} isn't longer mortgaged.
     * 
     * @param mortgage
     *            - is a {@link Boolean} that indicates whether the
     *            {@link Ownership} is mortgaged (true) or not (false)
     */
    void setMortgage(boolean mortgage);

}
