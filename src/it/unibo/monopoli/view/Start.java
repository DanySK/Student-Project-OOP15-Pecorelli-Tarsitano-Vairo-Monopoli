package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Point;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Start {
	
	public static void main(String[] args) {
	
		final MyFrame start = new MyFrame("Start - Monopoli", new BorderLayout(), new Dimension(800,450));
		start.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		start.setLocation(new Point(300, 100));
		start.getMainPanel().setLayout(new BorderLayout(0, 0));
		
		final JLabel lblMonopoliBenvenuto = new JLabel("MONOPOLI - BENVENUTO");
		lblMonopoliBenvenuto.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonopoliBenvenuto.setFont(new Font("Berlin Sans FB", Font.BOLD, 50));
		start.getMainPanel().add(lblMonopoliBenvenuto, BorderLayout.NORTH);
		
		final JPanel GridC = new JPanel();
		start.getMainPanel().add(GridC, BorderLayout.CENTER);
		final GridBagLayout gbl_GridC = new GridBagLayout();
		gbl_GridC.columnWidths = new int[] {81, 237, 157, 104, 63, 0, 1};
		gbl_GridC.rowHeights = new int[] {24, 53, 0, 138, 31, 36, -17, 2};
		gbl_GridC.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_GridC.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		GridC.setLayout(gbl_GridC);
		
		
		final JPanel firstRow = new JPanel();
		final FlowLayout flowLayout = (FlowLayout) firstRow.getLayout();
		final GridBagConstraints gbc_firstRow = new GridBagConstraints();
		gbc_firstRow.anchor = GridBagConstraints.WEST;
		gbc_firstRow.insets = new Insets(0, 0, 5, 5);
		gbc_firstRow.fill = GridBagConstraints.VERTICAL;
		gbc_firstRow.gridx = 1;
		gbc_firstRow.gridy = 1;
		GridC.add(firstRow, gbc_firstRow);
		
		final JLabel lblScegliLaVersione = new JLabel("Scegli la versione:");
		lblScegliLaVersione.setHorizontalAlignment(SwingConstants.RIGHT);
		lblScegliLaVersione.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		firstRow.add(lblScegliLaVersione);
		
		final JPanel panel = new JPanel();
		final GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 1;
		GridC.add(panel, gbc_panel);
		
		final JComboBox<String> comboBoxVersion = new JComboBox<String>();
		lblScegliLaVersione.setLabelFor(comboBoxVersion);
		comboBoxVersion.addItem("Verion1");
		comboBoxVersion.addItem("Version2");
		panel.add(comboBoxVersion);
		
		final JPanel secondRow = new JPanel();
		final GridBagConstraints gbc_secondRow = new GridBagConstraints();
		gbc_secondRow.gridwidth = 2;
		gbc_secondRow.anchor = GridBagConstraints.WEST;
		gbc_secondRow.insets = new Insets(0, 0, 5, 5);
		gbc_secondRow.fill = GridBagConstraints.VERTICAL;
		gbc_secondRow.gridx = 1;
		gbc_secondRow.gridy = 2;
		GridC.add(secondRow, gbc_secondRow);
		
		final JLabel lblScegliIlNumero = new JLabel("Scegli il numero dei giocatori e la loro tipologia:");
		lblScegliIlNumero.setVerticalAlignment(SwingConstants.TOP);
		lblScegliIlNumero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblScegliIlNumero.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		secondRow.add(lblScegliIlNumero);
		
		final JPanel panel_1 = new JPanel();
		final GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 3;
		gbc_panel_1.gridy = 2;
		GridC.add(panel_1, gbc_panel_1);
		
		final JPanel playerP = new JPanel();
		final GridBagConstraints gbc_playerP = new GridBagConstraints();
		gbc_playerP.insets = new Insets(0, 0, 5, 5);
		gbc_playerP.gridwidth = 2;
		gbc_playerP.fill = GridBagConstraints.BOTH;
		gbc_playerP.gridx = 1;
		gbc_playerP.gridy = 3;
		GridC.add(playerP, gbc_playerP);
		playerP.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		for (int i = 0; i < 2; i++) {
			playerP.add(new InizializedPlayer().build());
		}
		
		
		final JButton btnAddPlayer = new JButton("Add Player");
		btnAddPlayer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton)e.getSource();
				int count = 0;
				button.setText("AVVIA PARTITA" + count);
				count++;
				new Index();
				
			}
		});
		panel_1.add(btnAddPlayer);
		
		final JPanel row4 = new JPanel();
		GridBagConstraints gbc_row4 = new GridBagConstraints();
		gbc_row4.insets = new Insets(0, 0, 5, 5);
		gbc_row4.fill = GridBagConstraints.BOTH;
		gbc_row4.gridx = 1;
		gbc_row4.gridy = 4;
		GridC.add(row4, gbc_row4);
		
		final JButton btnNewButton = new JButton("AVVIA PARTITA");
		btnNewButton.addActionListener(new CountCLickButton());
		btnNewButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 4;
		GridC.add(btnNewButton, gbc_btnNewButton);
		
		start.setVisible(true);

	}
}
