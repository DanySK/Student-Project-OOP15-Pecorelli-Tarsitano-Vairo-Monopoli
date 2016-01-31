package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.ItalianNeutralArea;

public class ToStealMoney implements Action {

    private final ItalianNeutralArea neutralArea;

    public ToStealMoney(final ItalianNeutralArea neutralArea) {
        this.neutralArea = neutralArea;
    }

    @Override
    public void play(final Player player) {
        player.setMoney(player.getMoney() + this.neutralArea.getDirtyMoney());
        this.neutralArea.resetMoney();
    }


}
