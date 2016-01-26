package it.unibo.monopoli.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class BuildCasa {
	private Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

	/*
	 * Field:
	 * -In quale casella costruire la casa
	 * -Quante case???
	 * */
	
	public JPanel addCasa(){
		final JPanel casa = new JPanel();
		final JLabel casina = new JLabel();
		casina.setIcon(new ImageIcon("C:\\Users\\LauraT\\Desktop\\casadim20.png"));
		casa.setBorder(border);
		casa.setVisible(true);
		return casa;
		
	}

}
