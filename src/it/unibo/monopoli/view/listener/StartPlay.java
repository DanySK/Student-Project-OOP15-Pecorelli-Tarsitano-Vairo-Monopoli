package it.unibo.monopoli.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import it.unibo.monopoli.view.C;
import it.unibo.monopoli.view.Dialog;
import it.unibo.monopoli.view.Index;
import it.unibo.monopoli.view.InizializedPlayer;

public class StartPlay implements ActionListener {
	int count =1;
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String s = new InizializedPlayer().getName();
		System.out.println("Add Player: " + s);
		if(VersionSelected.getSelectedItem().equals(C.NOT_SELECTABLE_OPTION)){
			new Dialog(new JFrame(), "Error", "Non hai selezionato la versione");
		}else{
			new Index();
		}
				
		System.out.println("Version: " + VersionSelected.getSelectedItem());
	}

	
	
}
