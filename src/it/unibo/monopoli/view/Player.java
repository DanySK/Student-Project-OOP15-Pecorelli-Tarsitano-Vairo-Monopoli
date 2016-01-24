package it.unibo.monopoli.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Player extends JComponent {

	Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

	public Player() {
		build();
	}

	/**
	 * Method that build the Player's panel where there are the information
	 * about player situation
	 * 
	 * @return -return a Player's panel
	 */
	public JPanel build() {
		JPanel panelM = new JPanel();
		JPanel panel = new JPanel();

		GridBagLayout gridBagLayout = new GridBagLayout();

		panel.setLayout(gridBagLayout);
		panel.setPreferredSize(new Dimension(270, 70));

		panel.setBorder(border);

		final JLabel lblGiocatore = new JLabel("Giocatore");
		lblGiocatore.setFont(new Font("Papyrus", Font.BOLD, 22));
		GridBagConstraints gbc_lblGiocatore = new GridBagConstraints();
		gbc_lblGiocatore.gridwidth = 8;
		gbc_lblGiocatore.insets = new Insets(0, 0, 5, 0);
		gbc_lblGiocatore.gridx = 0;
		gbc_lblGiocatore.gridy = 0;
		panel.add(lblGiocatore, gbc_lblGiocatore);

		final JLabel lblMoney = new JLabel("Money:");
		lblMoney.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblMoney = new GridBagConstraints();
		gbc_lblMoney.insets = new Insets(0, 0, 5, 5);
		gbc_lblMoney.gridx = 3;
		gbc_lblMoney.gridy = 4;
		panel.add(lblMoney, gbc_lblMoney);

		final JLabel lblSoldi = new JLabel("Soldi");
		GridBagConstraints gbc_lblSoldi = new GridBagConstraints();
		gbc_lblSoldi.insets = new Insets(0, 0, 5, 5);
		gbc_lblSoldi.gridx = 5;
		gbc_lblSoldi.gridy = 4;
		panel.add(lblSoldi, gbc_lblSoldi);
		panelM.add(panel);
		panel.setVisible(true);
		panelM.setVisible(true);

		return panelM;

	}

}
