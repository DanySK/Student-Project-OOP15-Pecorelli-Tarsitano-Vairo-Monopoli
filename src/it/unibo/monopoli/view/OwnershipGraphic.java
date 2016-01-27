package it.unibo.monopoli.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.model.table.Ownership;

public class OwnershipGraphic extends AbstractGraphicCard {

	private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
	private Ownership own;
	
	public OwnershipGraphic(Ownership own, Position pos){
		super(pos);
		this.own = own;
		
	
	}
	
		
	@Override
	public JPanel build(){
		JPanel card = new RotatedPanel();
		card.setPreferredSize(new Dimension(60, 80));
		card.setLayout(new GridLayout(4, 1));
		
//		JLabel colorP = new JLabel();
//		colorP.setOpaque(true);
//		colorP.setBackground(Color.white);
//		card.add(colorP);
		
		JLabel nameP = new JLabel(own.getName());
		card.add(nameP);
		
		JPanel emptyP = new JPanel();
		card.add(emptyP);
		
		int i = this.own.getContract().getCost();
		JLabel valueP = new JLabel("" + i);
		card.add(valueP);
		
		card.setBorder(border);
		card.setVisible(true);
			
		return card;
		
		
	}

	

}
