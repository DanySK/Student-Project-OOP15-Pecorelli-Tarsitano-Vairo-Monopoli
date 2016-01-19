package it.unibo.monopoli.model.table;

/**
 * This interface represent all the contracts for each {@link Ownership}. All
 * that contracts declare the name, the cost, the income and the mortgage value
 * of their own {@link Ownership}.
 *
 */
public interface Contract {

    /**
     * Return the name of this {@link Contract} that is the same of his
     * {@link Ownership}.
     * 
     * @return {@link Contract}'s name
     */
    String getname();

    /**
     * Return the cost to buy the {@link Ownership} to which the
     * {@link Contract} belongs.
     * 
     * @return the cost to buy the {@link Ownership}
     */
    int getCost();

    /**
     * Return the income of the {@link Ownership} to which the {@link Contract}
     * belongs.
     * 
     * @return the income of the {@link Ownership}
     */
    int getIncome();

    /**
     * Return the mortgage value of the {@link Ownership} to which the
     * {@link Contract} belongs.
     * 
     * @return the mortgage value of the {@link Ownership}
     */
    int getMortgageValue();

}
