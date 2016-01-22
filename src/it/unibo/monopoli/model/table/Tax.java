package it.unibo.monopoli.model.table;

/**
 * This interface represent the {@link Tax}es of this game. They are particular
 * {@link ActionBox}es because they also have a cost.
 *
 */
public interface Tax extends ActionBox {

    /**
     * Return the {@link Tax}'s cost.
     * 
     * @return {@link Tax}'s cost
     */
    int getCost();

}
