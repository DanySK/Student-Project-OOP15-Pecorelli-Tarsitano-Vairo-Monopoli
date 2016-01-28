package it.unibo.monopoli.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;

public class East extends JPanel {
	
	Border border = BorderFactory.createLineBorder(Color.GREEN, 5);
	public East(){
		setPreferredSize(new Dimension(370, 415));
		final GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{118, 0};
		gridBagLayout.rowHeights = new int[]{120, 295, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		final JPanel panelBank = new JPanel();
		final GridBagConstraints gbc_panelBank = new GridBagConstraints();
		gbc_panelBank.insets = new Insets(0, 0, 5, 0);
		gbc_panelBank.fill = GridBagConstraints.BOTH;
		gbc_panelBank.gridx = 0;
		gbc_panelBank.gridy = 0;
		add(panelBank, gbc_panelBank);
		final JLabel bank = new JLabel("Bank");
		panelBank.add(bank);
		
		final JScrollPane scrollPane_1 = new JScrollPane();
		final GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		add(scrollPane_1, gbc_scrollPane_1);
		
		final JPanel panelPlayer = new JPanel();
		scrollPane_1.setViewportView(panelPlayer);
		panelPlayer.setLayout(new GridLayout(5,1));
		
		
		for (int i = 0; i < 5; i++) {
			//JPanel player = new Player();
			panelPlayer.add(new Player("Laura",10000).build());
		}
		build(scrollPane_1);
	}
	
	public JComponent build(JComponent scrollPane_1){
//		final JPanel panelE = new JPanel();
//		panelE.setLayout(new GridLayout(8 /* mettere i giocatori */, 1));
//
//		final JScrollPane scrollPane = new JScrollPane(panelE);
//		scrollPane.setViewportView(panelE);
//		scrollPane.setBorder(border);
//		//scrollPane.setPreferredSize(new Dimension(370, 0));
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scrollPane.add(new JLabel("Jscroll"));
//		scrollPane.setVisible(true);
//		for (int i = 0; i < 8; i++) {
//			//JPanel player = new Player();
//			scrollPane.add(new Player("Laura",10000).build());
//		}
//
//
		return scrollPane_1;
		//return null;
	}
}
