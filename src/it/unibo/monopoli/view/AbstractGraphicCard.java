package it.unibo.monopoli.view;

import java.awt.Dimension;

import javax.swing.JPanel;

/**
 *
 *
 */
public abstract class AbstractGraphicCard implements IBoxGraphic {
	
	private Position pos = null;
/**
 * 
 * @param pos
 */
	public AbstractGraphicCard(Position pos){
		this.pos = pos;
	}
/**
 * 
 */
	@Override
	public Position getPosition() {
		return pos;
	}

	protected JPanel getRotatedPanel() {
		Dimension dim = null;
		if (pos == Position.NORTH) {
			//Dimension dim = new Dimension(60,80);
			return new RotatedPanel(180, dim);
		} else if (pos == Position.EAST) {
			//Dimension dim = new Dimension(80,60);
			return new RotatedPanel(-90, dim);
		} else if (pos == Position.WEST) {
			//Dimension dim = new Dimension(80,60);
			return new RotatedPanel(90,dim);
		} else {
			//Dimension dim = new Dimension(60,80);
			return new RotatedPanel(0,dim);

		}
	}
}
