package it.unibo.monopoli.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class RotatedPanel extends JPanel {
	
	

		private int rotation; //oppure -90 per la rotazione inversa
		private static Dimension dim;
				
		public RotatedPanel(int rotation,Dimension dim) {
			//this.setPreferredSize(dim);
			this.rotation = rotation;
			this.dim = dim;
			
			}
		
		public RotatedPanel() {
			//this(90);
		}
		
		public static Dimension getDim() {
			return dim;
			//return null;
		}

		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2d = (Graphics2D) g;
			
			float x = this.getWidth() / 2.0f;
			float y = this.getHeight() / 2.0f;
			
			g2d.rotate(Math.toRadians(rotation), x, y);
			
		}
}


