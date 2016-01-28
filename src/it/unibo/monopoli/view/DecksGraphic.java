package it.unibo.monopoli.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.model.table.DecksBox;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.Ownership;

public class DecksGraphic extends AbstractGraphicCard {
	
	private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
	private DecksBox deck;
	
	public DecksGraphic( DecksBox deck, Position pos) {
		super(pos);
		this.deck = deck;
	}

	@Override
	public JPanel build() {
		JPanel card = getRotatedPanel();
		card.setPreferredSize(new Dimension(RotatedPanel.getDim()));
		card.setLayout(new GridLayout(2, 1));
		
			
		JLabel nameP = new JLabel(deck.getName());
		card.add(nameP);
		
		JPanel emptyP = new JPanel();
		card.add(emptyP);
		
			
		card.setBorder(border);
		card.setVisible(true);
			
		return card;

	}

}
