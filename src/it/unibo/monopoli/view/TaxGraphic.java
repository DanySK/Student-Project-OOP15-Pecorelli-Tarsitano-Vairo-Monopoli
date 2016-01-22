package it.unibo.monopoli.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.model.table.Tax;

public class TaxGraphic implements IBoxGraphic {


	private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
	private Tax tax;
	
	public TaxGraphic(Tax tax){
		this.tax = tax;
		
	}
	
		
	public JPanel build(){
		JPanel card = new RotatedPanel();
		card.setPreferredSize(new Dimension(60, 80));
		card.setLayout(new GridLayout(4, 1));

		JLabel nameP = new JLabel(tax.getName());
		card.add(nameP);
		
		JPanel emptyP = new JPanel();
		card.add(emptyP);
		
		JLabel valueP = new JLabel(""+tax.getCost());
		card.add(valueP);
		
		card.setBorder(border);
		card.setVisible(true);
			
		return card;
		
		
	}

	

}
