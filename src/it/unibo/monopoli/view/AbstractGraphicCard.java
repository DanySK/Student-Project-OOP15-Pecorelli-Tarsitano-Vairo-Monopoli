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
		} else if (pos == Position.EAST) {
			return new RotatedPanel(-90);
		}else if (pos == Position.WEST) {
			return new RotatedPanel(90);
		}else  {
			return new RotatedPanel(0);
		
		}
	}
}
