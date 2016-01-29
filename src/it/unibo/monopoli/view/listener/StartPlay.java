package it.unibo.monopoli.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.monopoli.controller.Controller;
import it.unibo.monopoli.model.mainunits.ClassicPawn;
import it.unibo.monopoli.view.Dialog;
import it.unibo.monopoli.view.EVersion;
import it.unibo.monopoli.view.Index;
import it.unibo.monopoli.view.InizializedPlayer;

public class StartPlay implements ActionListener {
	int count = 1;
	

	public StartPlay(JPanel player) {
		super();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();

		if (VersionSelected.getSelectedItem().equals(EVersion.NOT_SELECTABLE_OPTION)) {
			new Dialog(new JFrame(), "Error", "Non hai selezionato la versione");
		} else {
			Index i = new Index();
			Controller contr = i.getController();
			Set<Entry<String, Boolean>> s = InizializedPlayer.getMap().entrySet();
			s.forEach(g -> {
				contr.addPlayer(g.getKey(), new ClassicPawn(0), g.getValue());
			});
			switch (VersionSelected.getSelectedItem()){
			case "Classic":
				contr.initializedVersion(EVersion.CLASSIC);
				break;
			default: break;
			}
	    	
	    	contr.getPlayers().forEach(p -> {
	    		System.out.println(p.getName());
	    		System.out.println("" + p.isHuman());
	    		System.out.println("" + p.getPawn().getID());
	    		
	    	});
		}
		System.out.println("Version: " + VersionSelected.getSelectedItem());
		
		
		
		
		
	}

}
