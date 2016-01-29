package it.unibo.monopoli.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class JShape extends JPanel {

	public static enum Shapes {
		TRIANGLE, RECTANGLE, CIRCLE, ROMBO;
	}

	private final Shapes shape;
	private Color shapeFillColor = Color.GREEN;
	private Color shapeBorderColor = Color.BLACK;

	public JShape(Shapes shape) {
		this.shape = shape;
		this.setPreferredSize(new Dimension(C.JSHAPE_PREFERRED_SIZE, C.JSHAPE_PREFERRED_SIZE));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int[] xs;
		int[] ys;

		switch (this.shape) {
		case TRIANGLE:
			xs = new int[] { 0, getWidth()-1, (getWidth() / 2)-1 };
			ys = new int[] { getHeight()-1, getHeight()-1, 0 };
			g.setColor(shapeFillColor);
			g.fillPolygon(xs, ys, 3);
			g.setColor(shapeBorderColor);
			g.drawPolygon(xs, ys, 3);
			break;

		case RECTANGLE:
			g.setColor(shapeFillColor);
			g.fillRect(0, 0, getWidth()-1, getHeight()-1);
			g.setColor(shapeBorderColor);
			g.drawRect(0, 0, getWidth()-1, getHeight()-1);
			break;

		case CIRCLE:
			g.setColor(shapeFillColor);
			g.fillOval(0, 0, getWidth()-1, getHeight()-1);
			g.setColor(shapeBorderColor);
			g.drawOval(0, 0, getWidth()-1, getHeight()-1);
			break;

		case ROMBO:
			xs = new int[] { (getWidth() / 2), 0, (getWidth() / 2), getWidth() };
			ys = new int[] { 0, (getWidth() / 2), getHeight(), (getHeight() / 2)};
			g.setColor(shapeFillColor);
			g.fillPolygon(xs, ys, 4);
			g.setColor(shapeBorderColor);
			g.drawPolygon(xs, ys, 4);
			break;
		}
		
	}

	public Color getShapeFillColor() {
		return shapeFillColor;
	}

	public void setShapeFillColor(Color shapeFillColor) {
		this.shapeFillColor = shapeFillColor;
	}

	public Color getShapeBorderColor() {
		return shapeBorderColor;
	}

	public void setShapeBorderColor(Color shapeBorderColor) {
		this.shapeBorderColor = shapeBorderColor;
	}

	public Shapes getShape() {
		return shape;
	}
	
	

}
