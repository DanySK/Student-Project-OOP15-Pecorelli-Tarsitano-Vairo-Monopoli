package it.unibo.monopoli.view.cards;

import javax.swing.JPanel;

import it.unibo.monopoli.model.mainunits.Player;

public interface IBoxGraphic {

    public enum Position {
        SOUTH, WEST, NORTH, EAST
    }

    JPanel build();

    Position getPosition();

    void addPawn(Player p);

    void removePawn(Player p);
}
