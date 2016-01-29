package it.unibo.monopoli.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.monopoli.view.Dialog;
import it.unibo.monopoli.view.InizializedPlayer;

public class AddPlayer implements ActionListener {
	private static final int MAX_PLAYERS = 6;
	
	List<InizializedPlayer> player = new ArrayList<InizializedPlayer>();
	String name;
	boolean isUman;
	int cont = 2;
	@Override
	public void actionPerformed(ActionEvent e) {
		cont++;
//		this.name = InizializedPlayer.getName();
//		this.isUman = InizializedPlayer.isUman();
		
		
		if(cont <= MAX_PLAYERS){
			JPanel playerP = (new InizializedPlayer().build());
			
		}else{
			new Dialog(new JFrame(), "Error", "Non puoi inserire più di 6 giocatori");
		}
		
		System.out.println(player);
		System.out.println("" + cont);
		System.out.println("Add Player");


	}

}
