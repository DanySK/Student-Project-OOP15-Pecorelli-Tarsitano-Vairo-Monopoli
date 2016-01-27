package it.unibo.monopoli.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CountCLickButton implements ActionListener {
	int count =1;
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		button.setText("AVVIA PARTITA" + count);
		count++;
		new Index();
	}
	
	
}
