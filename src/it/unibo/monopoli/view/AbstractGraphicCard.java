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
			dim = new Dimension(50,70);
			return new RotatedPanel(180, dim);
		} else if (pos == Position.EAST) {
			dim = new Dimension(70,50);
			return new RotatedPanel(-90, dim);
		} else if (pos == Position.WEST) {
			dim = new Dimension(70,50);
			return new RotatedPanel(90,dim);
		} else {
			dim = new Dimension(50,70);
			return new RotatedPanel(0,dim);

		}
	}
}
