package it.unibo.monopoli.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.model.table.Box;

public class BoxGraphic   extends AbstractGraphicCard  {


	private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
	private Box box;
	
	public BoxGraphic(Box box, Position pos){
		super(pos);
		this.box = box;
		
	}
	
		
	@Override
	public JPanel build(){
		JPanel card = getRotatedPanel();
		card.setPreferredSize(new Dimension(80, 80));
		card.setLayout(new GridLayout(4, 1));
		
		
		JLabel nameP = new JLabel(box.getName());
		card.add(nameP);
		
		JPanel emptyP = new JPanel();
		card.add(emptyP);

		card.setBorder(border);
		card.setVisible(true);
			
		return card;
		
		
	}

	

}
