package it.unibo.monopoli.view.listener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.GeneralSecurityException;

import javax.swing.JButton;

import it.unibo.monopoli.view.Index;
import it.unibo.monopoli.view.InizializedPlayer;

public class StartPlay implements ActionListener {
	int count =1;
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String s = InizializedPlayer().getName();
		new Index();		
		System.out.println("Version: " + VersionSelected.getSelectedItem());
	}

	
	
}
