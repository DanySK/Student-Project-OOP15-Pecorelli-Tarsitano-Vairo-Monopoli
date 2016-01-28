package it.unibo.monopoli.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.model.table.Tax;

public class TaxGraphic extends AbstractGraphicCard {


	private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
	private Tax tax;
	
	public TaxGraphic(Tax tax, Position pos){
		super(pos);
		this.tax = tax;
		
	}
	
		
	@Override
	public JPanel build(){
		JPanel card = getRotatedPanel();
		card.setPreferredSize(new Dimension(RotatedPanel.getDim()));
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
