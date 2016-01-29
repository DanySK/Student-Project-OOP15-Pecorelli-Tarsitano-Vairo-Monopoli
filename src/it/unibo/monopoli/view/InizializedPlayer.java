package it.unibo.monopoli.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

/**
 * 
 * 
 *
 */
public class InizializedPlayer {
    final Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    private String name;
    private boolean isUman;

    // public InizializedPlayer() {
    //// build();
    // }

    /**
     * Method that build the Player's panel where there are the information
     * about player situation
     * 
     * @return -return a Player's panel
     */
    public JPanel build() {
        final JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(120, 131));

        panel.setBorder(border);
        panel.setLayout(new BorderLayout(0, 0));

        final JPanel northP = new JPanel();
        panel.add(northP, BorderLayout.NORTH);

        final JPanel westP = new JPanel();
        panel.add(westP, BorderLayout.WEST);

        final JPanel southP = new JPanel();
        panel.add(southP, BorderLayout.SOUTH);

        final JPanel eastP = new JPanel();
        panel.add(eastP, BorderLayout.EAST);

        final JPanel centerP = new JPanel();
        panel.add(centerP, BorderLayout.CENTER);
        centerP.setLayout(new GridLayout(2, 1, 0, 0));

        final JPanel centerP_row1 = new JPanel();
        centerP.add(centerP_row1);

        final JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        centerP_row1.add(lblNome);

        JTextField textNome = new JTextField();
        textNome.setPreferredSize(new Dimension(10, 20));
        textNome.setHorizontalAlignment(SwingConstants.CENTER);
        centerP_row1.add(textNome);
        textNome.setColumns(10);
        this.name = textNome.getText();

        final JPanel centerP_row2 = new JPanel();
        centerP.add(centerP_row2);

        final JRadioButton rdbtnUmano = new JRadioButton("Umano");
        rdbtnUmano.setSelected(true);
        rdbtnUmano.setHorizontalAlignment(SwingConstants.LEFT);
        rdbtnUmano.setAlignmentY(Component.TOP_ALIGNMENT);
        centerP_row2.add(rdbtnUmano);

        final JRadioButton rdbtnComputer = new JRadioButton("Computer");
        centerP_row2.add(rdbtnComputer);

        final ButtonGroup group = new ButtonGroup();
        group.add(rdbtnComputer);
        group.add(rdbtnUmano);

        rdbtnComputer.setSelected(true);

        if (rdbtnComputer.isSelected()) {
            isUman = false;
        } else {
            isUman = true;
        }

        panel.setVisible(true);
        return panel;

    }

    public String getName() {

        return this.name;
    }

    public boolean isUman() {
        return isUman;
    }
}
