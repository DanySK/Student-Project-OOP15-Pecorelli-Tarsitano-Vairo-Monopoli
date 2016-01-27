package it.unibo.monopoli.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import it.unibo.monopoli.view.Index;

public class StartPlay implements ActionListener {
	int count =1;
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		new Index();
		
		System.out.println("Version: " + VersionSelected.getSelectedItem());
	}
	
	
	
}
