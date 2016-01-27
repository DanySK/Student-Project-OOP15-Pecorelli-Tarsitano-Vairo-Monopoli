package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import it.unibo.monopoli.controller.ControllerImpl;

public class Index {
	
	public Index(){
		final MyFrame frame = new MyFrame("Monopoli", new BorderLayout(), new Dimension(1200, 700));

		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

// JPanelMain South
		final JPanel southP = new JPanel();
		JButton ok = new JButton("Ok");
		JButton quit = new JButton("Quit");
		JButton buildHouse = new JButton("Add House");
		southP.add(ok);
		southP.add(quit);
		southP.add(buildHouse);
		Dimension dimSouth = new Dimension(0, 50);
		southP.setPreferredSize(dimSouth);
//Center
		JPanel centerP = new JPanel();
		JPanel tabellone = new ProvaTabellone(null,10,10, new ControllerImpl(EVersion.CLASSIC)).initialize();
		centerP.add(tabellone);		
//East		
        final JPanel panelE = new JPanel();
		panelE.setLayout(new GridLayout(8 /* mettere i giocatori */, 1));

		for (int i = 0; i < 8; i++) {
			//JPanel player = new Player();
			panelE.add(new Player("Laura",10000).build());
		}

		final JScrollPane scrollPane = new JScrollPane(/*panel*/);
		scrollPane.setViewportView(panelE);
		scrollPane.setPreferredSize(new Dimension(370, 0));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		
		
		frame.getMainPanel().add(southP, BorderLayout.SOUTH);
		frame.getMainPanel().add(scrollPane, BorderLayout.EAST);
		frame.getMainPanel().add(centerP, BorderLayout.CENTER);

		frame.setVisible(true);

	}

	public static void main(String[] args) {
		
	}

}

