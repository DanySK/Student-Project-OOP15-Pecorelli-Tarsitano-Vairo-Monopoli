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

public class Index {

	public static void main(String[] args) {
		final MyFrame frame = new MyFrame("Monopoli", new BorderLayout(), new Dimension(1024, 700));

		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

		// JPanelMain South
		final JPanel southP = new JPanel();
		JButton ok = new JButton("Ok");
		JButton quit = new JButton("Quit");
		JButton addPlayer = new JButton("Add Player");
		southP.add(ok);
		southP.add(quit);
		southP.add(addPlayer);
		Dimension dimSouth = new Dimension(0, 50);
		southP.setPreferredSize(dimSouth);
		
        final JPanel panelE = new JPanel();
		panelE.setLayout(new GridLayout(2 /* mettere i giocatori */, 1));

		for (int i = 0; i < 2; i++) {
			panelE.add(new Player());
		}

		final JScrollPane scrollPane = new JScrollPane(/* panelE */);
		scrollPane.setViewportView(panelE);
		scrollPane.setPreferredSize(new Dimension(300, 0));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		frame.getMainPanel().add(new JButton(" North "), BorderLayout.NORTH);
		frame.getMainPanel().add(southP, BorderLayout.SOUTH);
		frame.getMainPanel().add(scrollPane, BorderLayout.EAST);

		frame.setVisible(true);

	}

}
