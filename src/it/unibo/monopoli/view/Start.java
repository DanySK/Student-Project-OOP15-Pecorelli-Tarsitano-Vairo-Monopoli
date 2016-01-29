package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.monopoli.view.listener.StartPlay;
import it.unibo.monopoli.view.listener.VersionSelected;

public class Start {
    private static final int MAX_PLAYERS = 6;
    private int cont = 2;

    Map<String, Boolean> map = new HashMap<>();

    public static void main(String[] args) {

        final MyFrame start = new MyFrame("Start - Monopoli", new BorderLayout(), new Dimension(900, 450));
        start.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        start.setLocation(new Point(300, 100));
        start.getMainPanel().setLayout(new BorderLayout(0, 0));

        final JPanel panel = new JPanel();
        start.getMainPanel().add(panel, BorderLayout.CENTER);

        final JLabel lblMonopoliBenvenuto = new JLabel("MONOPOLI - WELCOME");
        panel.add(lblMonopoliBenvenuto);
        lblMonopoliBenvenuto.setHorizontalAlignment(SwingConstants.CENTER);
        lblMonopoliBenvenuto.setFont(new Font("Berlin Sans FB", Font.BOLD, 50));

        final JPanel GridC = new JPanel();
        panel.add(GridC);
        final GridBagLayout gbl_GridC = new GridBagLayout();
        gbl_GridC.columnWidths = new int[] { 81, 237, 157, 104, 0, 0, 63, 0, 1 };
        gbl_GridC.rowHeights = new int[] { 24, 53, 0, 166, 31, 36, -17, 2 };
        gbl_GridC.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
        gbl_GridC.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
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

        final JLabel lblScegliLaVersione = new JLabel("Choose version: ");
        lblScegliLaVersione.setHorizontalAlignment(SwingConstants.RIGHT);
        lblScegliLaVersione.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
        firstRow.add(lblScegliLaVersione);

        final JPanel panelComboBox = new JPanel();
        final GridBagConstraints gbc_panelComboBox = new GridBagConstraints();
        gbc_panelComboBox.insets = new Insets(0, 0, 5, 5);
        gbc_panelComboBox.fill = GridBagConstraints.BOTH;
        gbc_panelComboBox.gridx = 2;
        gbc_panelComboBox.gridy = 1;
        GridC.add(panelComboBox, gbc_panelComboBox);

        final JComboBox<String> comboBoxVersion = new JComboBox<String>();
        lblScegliLaVersione.setLabelFor(comboBoxVersion);
        comboBoxVersion.addItemListener(new VersionSelected());
        panelComboBox.add(comboBoxVersion);
        comboBoxVersion.setModel(new DefaultComboBoxModel<String>() {

            boolean selectionAllowed = true;

            @Override
            public void setSelectedItem(Object anObject) {
                if (!EVersion.NOT_SELECTABLE_OPTION.getName().equals(anObject)) {
                    super.setSelectedItem(anObject);
                } else if (selectionAllowed) {
                    // Allow this just once
                    selectionAllowed = false;
                    super.setSelectedItem(anObject);
                }
            }

        });
        comboBoxVersion.addItem(EVersion.NOT_SELECTABLE_OPTION.getName());
        Arrays.asList(EVersion.values()).forEach(v -> comboBoxVersion.addItem(v.getName()));

        final JPanel secondRow = new JPanel();
        final GridBagConstraints gbc_secondRow = new GridBagConstraints();
        gbc_secondRow.gridwidth = 2;
        gbc_secondRow.anchor = GridBagConstraints.WEST;
        gbc_secondRow.insets = new Insets(0, 0, 5, 5);
        gbc_secondRow.fill = GridBagConstraints.VERTICAL;
        gbc_secondRow.gridx = 1;
        gbc_secondRow.gridy = 2;
        GridC.add(secondRow, gbc_secondRow);

        final JLabel lblScegliIlNumero = new JLabel("Choose number of players, their type and color of the pawn: ");
        lblScegliIlNumero.setVerticalAlignment(SwingConstants.TOP);
        lblScegliIlNumero.setHorizontalAlignment(SwingConstants.RIGHT);
        lblScegliIlNumero.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
        secondRow.add(lblScegliIlNumero);

        final JPanel panelBtnAddPlayer = new JPanel();
        final GridBagConstraints gbc_panelBtnAddPlayer = new GridBagConstraints();
        gbc_panelBtnAddPlayer.insets = new Insets(0, 0, 5, 5);
        gbc_panelBtnAddPlayer.fill = GridBagConstraints.BOTH;
        gbc_panelBtnAddPlayer.gridx = 3;
        gbc_panelBtnAddPlayer.gridy = 2;
        GridC.add(panelBtnAddPlayer, gbc_panelBtnAddPlayer);

        final JPanel playerP = new JPanel();
        final GridBagConstraints gbc_playerP = new GridBagConstraints();
        gbc_playerP.insets = new Insets(0, 0, 5, 5);
        gbc_playerP.gridwidth = 6;
        gbc_playerP.fill = GridBagConstraints.BOTH;
        gbc_playerP.gridx = 1;
        gbc_playerP.gridy = 3;
        GridC.add(playerP, gbc_playerP);
        final FlowLayout fl_playerP = new FlowLayout();
        playerP.setLayout(fl_playerP/* (FlowLayout.CENTER, 5, 5) */);

        playerP.add(new InizializedComputer().build());

        final JButton btnAddPlayer = new JButton("Add Player");

        btnAddPlayer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO
                if (playerP.getComponentCount() < MAX_PLAYERS) {
                    playerP.add(new InizializedPlayer().build());
                    playerP.revalidate();
                } else {
                    new Dialog(new JFrame(), "Error", "Non puoi inserire più di 6 giocatori");
                }
                // System.out.println("" + playerP.getComponentCount());
                // System.out.println("Add Player");
            }
        });

        panelBtnAddPlayer.add(btnAddPlayer);

        final JButton btnNewButton = new JButton("AVVIA PARTITA");
        btnNewButton.addActionListener(new StartPlay());
        btnNewButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
        final GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.gridheight = 2;
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 3;
        gbc_btnNewButton.gridy = 4;
        GridC.add(btnNewButton, gbc_btnNewButton);

        start.setVisible(true);

    }

    public int Prova() {
        return this.cont;
    }

    public void Prova2() {
        this.cont++;
    }
}
