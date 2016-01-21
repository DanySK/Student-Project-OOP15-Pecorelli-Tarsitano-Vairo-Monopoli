package it.unibo.monopoli.model.actions;

import java.util.List;

import it.unibo.monopoli.model.mainunits.Player;

public interface DicesStrategy {
    
    int howManyDices();
    
    int whatToDoWhitDicesNumbers(List<Integer> numbers, Player player);

}
