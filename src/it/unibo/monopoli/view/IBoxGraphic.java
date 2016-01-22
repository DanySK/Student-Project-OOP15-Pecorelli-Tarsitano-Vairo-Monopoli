package it.unibo.monopoli.view;

import javax.swing.JPanel;

public interface IBoxGraphic {
	public enum Position {
		SOUTH, WEST, NORTH, EAST
	}
	JPanel build();
	Position getPosition();
}
