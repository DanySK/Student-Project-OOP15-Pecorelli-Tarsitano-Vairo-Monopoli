package it.unibo.monopoli.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class CardGraphic {
	
	/**
	 * 
	 */
	private String name;
	private Color color;
	private int value;
	
	Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
	
	public CardGraphic(String name, Color color, int value){
		/*super();
		setBackground(Color.GREEN);*/
		this.name = name;
		this.color = color;
		this.value = value;
		
	}
	
		
	public JPanel build(JPanel panel){
		JPanel card = new RotatedPanel();
		card.setPreferredSize(new Dimension(60, 80));
		card.setLayout(new GridLayout(4, 1));
		
		JLabel colorP = new JLabel();
		colorP.setOpaque(true);
		colorP.setBackground(this.color);
		card.add(colorP);
		
		JLabel nameP = new JLabel(this.name);
		card.add(nameP);
		
		JPanel emptyP = new JPanel();
		card.add(emptyP);
		
		JLabel valueP = new JLabel(""+this.value);
		card.add(valueP);
		
		card.setBorder(border);
		card.setVisible(true);
			
		return card;
		
		
	}

	

}
