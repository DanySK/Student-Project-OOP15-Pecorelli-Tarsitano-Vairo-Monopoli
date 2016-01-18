package it.unibo.monopoli.model.table;

public interface Contract {
    
    /**
     * Return the name of this {@link Contract}.
     * 
     * @return {@link Contract}'s name
     */
    String getname();
    
    /**
     * Return the cost to buy the {@link Ownership} to which the {@link Contract} belongs.
     * 
     * @return the cost to buy the {@link Ownership}
     */
    int getCost();
    
    /**
     * Return the income of the {@link Ownership} to which the {@link Contract} belongs.
     * 
     * @return the income of the {@link Ownership}
     */
    int getIncome();
    
    /**
     * Return the mortgage value of the {@link Ownership} to which the {@link Contract} belongs.
     * 
     * @return the mortgage value of the {@link Ownership}
     */
    int getMortgageValue();

}
