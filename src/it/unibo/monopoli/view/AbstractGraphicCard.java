package it.unibo.monopoli.view;

import javax.swing.JPanel;

public abstract class AbstractGraphicCard implements IBoxGraphic {

	private Position pos;

	public AbstractGraphicCard(Position pos) {
		this.pos = pos;
	}

	@Override
	public Position getPosition() {
		return pos;
	}

	protected JPanel getRotatedPanel() {
		if (pos == Position.NORTH) {
			return new RotatedPanel(180);
		} else if (pos == Position.NORTH) {

		}
		return null;
	}
}
