package it.unibo.monopoli.view;

import java.util.List;

public interface InPlay {

    void setButton(List<String> name);

    void gameOver(it.unibo.monopoli.model.mainunits.Player player);
    
    List<String> getButtons();

}
