package it.unibo.monopoli.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class CardGraphic extends AbstractGraphicCard {

	private String name;
	private Color color;
	private int value;
	private Position pos;
	private Dimension dim;
	private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

	public CardGraphic(String name, Color color, int value, Position pos) {
		super(pos);
		this.name = name;
		this.color = color;
		this.value = value;
		this.pos = pos;

	}

	@Override
	public JPanel build() {
		JPanel card = new JPanel();
		
		card = getRotatedPanel();
		card.setLayout(new GridLayout(4, 1));
		card.setPreferredSize(new Dimension(RotatedPanel.getDim()));

		JPanel colorP = new JPanel();
		colorP.setOpaque(true);
		colorP.setBackground(this.color);
		colorP.setLayout(new FlowLayout());
		for (int i = 0; i < 4; i++) {
			JPanel casa = new BuildCasa().addCasa();
			colorP.add(casa);
		}

		card.add(colorP);
		JLabel nameP = new JLabel(this.name);
		card.add(nameP);

		JPanel emptyP = new JPanel();
		card.add(emptyP);

		JLabel valueP = new JLabel("" + this.value);
		card.add(valueP);

		card.setBorder(border);

		card.setVisible(true);
		return card;

	}

}
