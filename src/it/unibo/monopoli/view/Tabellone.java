package it.unibo.monopoli.view;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.ComponentOrientation;

public class Tabellone {

	Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
	
		
	/**
	 * Create the application.
	 */
	public Tabellone() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public JPanel initialize() {
		
		
		final JPanel panel = new JPanel();
		final JPanel panelM = new JPanel();
		panelM.setLayout(new BorderLayout());
		panelM.add(panel, BorderLayout.CENTER);
		//Border border2 = BorderFactory.createLineBorder(Color.BLUE, 6);
		//panel.setBorder(border2);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		
		final JLabel label_1 = new JLabel("19");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		JPanel casella19 = new CardGraphic("Via Rossi",Color.RED,15000).build(panel);
		gbc_label_1.insets = new Insets(0, 0, 0, 0);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 1;
		//panel.add(label_1, gbc_label_1);
		panel.add(casella19,gbc_label_1);
		
				
		final JLabel label_2 = new JLabel("18");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		JPanel casella18 = new CardGraphic("Via Verdi",Color.GREEN,10000).build(panel);
		gbc_label_2.insets = new Insets(0, 0, 0, 0);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 2;
		panel.add(casella18, gbc_label_2);
		
		final JLabel label_3 = new JLabel("17");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		JPanel casella17 = new CardGraphic("Via Gialli",Color.YELLOW,20000).build(panel);
		gbc_label_3.insets = new Insets(0, 0, 0, 0);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 3;
		panel.add(casella17, gbc_label_3);
		
		final JLabel label_4 = new JLabel("16");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		JPanel casella16 = new CardGraphic("Via Verdi",Color.GREEN,10000).build(panel);
		gbc_label_4.insets = new Insets(0, 0, 0, 0);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 4;
		panel.add(casella16, gbc_label_4);
		
		final JLabel label_5 = new JLabel("15");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		JPanel casella15 = new CardGraphic("Via Rossi",Color.RED,15000).build(panel);
		gbc_label_5.insets = new Insets(0, 0, 0, 0);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 5;
		panel.add(casella15, gbc_label_5);
		
		final JLabel label_6 = new JLabel("14");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		JPanel casella14 = new CardGraphic("Via Gialli",Color.YELLOW,20000).build(panel);
		gbc_label_6.insets = new Insets(0, 0, 0, 0);
		gbc_label_6.gridx = 0;
		gbc_label_6.gridy = 6;
		panel.add(casella14, gbc_label_6);
		
		final JLabel label_7 = new JLabel("13");
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		JPanel casella13 = new CardGraphic("Via Gialli",Color.YELLOW,20000).build(panel);
		gbc_label_7.insets = new Insets(0, 0, 0, 0);
		gbc_label_7.gridx = 0;
		gbc_label_7.gridy = 7;
		panel.add(casella13, gbc_label_7);
		
		final JLabel label_8 = new JLabel("12");
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		JPanel casella12 = new CardGraphic("Via Rossi",Color.RED,15000).build(panel);
		gbc_label_8.insets = new Insets(0, 0, 0, 0);
		gbc_label_8.gridx = 0;
		gbc_label_8.gridy = 8;
		panel.add(casella12, gbc_label_8);
		
		final JLabel label_9 = new JLabel("11");
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		JPanel casella11 = new CardGraphic("Via Verdi",Color.GREEN,10000).build(panel);
		gbc_label_9.insets = new Insets(0, 0, 0, 0);
		gbc_label_9.gridx = 0;
		gbc_label_9.gridy = 9;
		panel.add(casella11, gbc_label_9);
		
				return panelM;
	}

}
