package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import it.unibo.monopoli.model.table.Box;

public class Index {

	public static void main(String[] args) {
		final MyFrame frame = new MyFrame("Monopoli", new BorderLayout());

		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

		// JPanelMain South
		JPanel southP = new JPanel();
		JButton ok = new JButton("Ok");
		JButton quit = new JButton("Quit");
		JButton addPlayer = new JButton("Add Player");
		southP.add(ok);
		southP.add(quit);
		southP.add(addPlayer);
		Dimension dimSouth = new Dimension(0, 50);
		southP.setPreferredSize(dimSouth);

		//JPanelMain Center
		JPanel centerP = new JPanel(); // JPanelMain Centrale
		centerP.setLayout(new BorderLayout());
		
		JPanel tabellone = new ProvaTabellone(null, 10, 10).initialize();
		JPanel tableau = new JPanel();
		centerP.add(tableau,BorderLayout.CENTER);
		tableau.add(tabellone);
		//System.out.println(tabellone.getLayout() + "" + tabellone.getComponents());
		
		
		Border border2 = BorderFactory.createLineBorder(Color.GREEN, 2);
		tabellone.setBorder(border2);
		tableau.setBorder(border);
		centerP.setBorder(border2);
		
		Dimension dimPanelP = new Dimension(400, 0);
		JPanel giocatore = new Player().build();
		
//East		

		JPanel panelE = new JPanel();
		panelE.setLayout(new GridLayout(8 /* mettere i giocatori */, 1));

		
		for (int i = 0; i < 8; i++) {
			panelE.add(new Player().build());
		}
		
		final JScrollPane scrollPane = new JScrollPane(/*panelE*/);
		scrollPane.setViewportView(panelE);
		scrollPane.setPreferredSize(new Dimension (300,0));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		



			

		frame.getMainPanel().add(new JButton(" North "), BorderLayout.NORTH);
		frame.getMainPanel().add(southP, BorderLayout.SOUTH);
		frame.getMainPanel().add(centerP, BorderLayout.CENTER);
		frame.getMainPanel().add(scrollPane, BorderLayout.EAST);

		
		
		
		
		frame.setVisible(true);

	}

}
