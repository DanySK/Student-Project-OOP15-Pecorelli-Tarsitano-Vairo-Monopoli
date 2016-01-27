package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Start;

public class PassFromStar implements Action{

    @Override
    public void play(final Player player) {
        if (!player.isInPrison()) {
            new ToBePaid(Start.getMuchToPick()).play(player);
        }
    }


}
