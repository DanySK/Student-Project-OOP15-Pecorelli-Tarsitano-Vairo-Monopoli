package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * 
 * 
 *
 */
public class InizializedPlayer {
    private final Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

    private static Map<String,Boolean> map = new HashMap<>();
    private JTextField textNome;
    private boolean isUman;
    private JRadioButton rdbtnComputer;
    private JRadioButton rdbtnUman;
/**
 * 
 */
    public InizializedPlayer() {
        
        
    }

    /**
     * Method that build the Player's panel where there are the information
     * about player situation
     * 
     * @return JPanel playerP
     */
    public JPanel build(final JPanel playerP) {
        final JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(120, 150));

        panel.setBorder(border);
        panel.setLayout(new BorderLayout(0, 0));

        final JPanel northP = new JPanel();
        panel.add(northP, BorderLayout.NORTH);

        final JPanel westP = new JPanel();
        panel.add(westP, BorderLayout.WEST);

        final JPanel southP = new JPanel();
        panel.add(southP, BorderLayout.SOUTH);

        final JButton save = new JButton("Save");
        save.setFont(new Font("Times New Roman", Font.BOLD, 10));
        save.setPreferredSize(new Dimension(70, 22));
        southP.add(save);

        final JButton remove = new JButton("Remove");
        remove.setFont(new Font("Times New Roman", Font.BOLD, 10));
        remove.setPreferredSize(new Dimension(70, 22));
        southP.add(remove);
        remove.setVisible(false);

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {

                if (textNome.getText().isEmpty()) {
                    new Dialog(new JFrame(), "Error", "Error! You must enter the name of the player");
                } else {
                    if (rdbtnUman.isSelected()) {
                        isUman = true;
                    } else {
                        isUman = false;
                    }

                    // alla mappa passo N?NOMEGIOCATORE, MI E' UTILE PER DECIDERE IL COLORE DELLE PEDINE
                    map.put((Go.getNumPlayers() - 1) + C.SPLITTOKEN + textNome.getText(), isUman);
                    System.out.println("" + textNome.getText());
                    System.out.println("" + isUman);
                    save.setVisible(false);
                    remove.setVisible(true);
                    textNome.setEditable(false);
                    rdbtnComputer.setEnabled(false);
                    rdbtnUman.setEnabled(false);

                }

            }
        });

        remove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                map.remove(textNome.getText(), isUman);
                
                panel.setVisible(false);
                playerP.remove(panel);
                Go.addNumPlayers(-1);
                playerP.revalidate();
                System.out.println("Remove: " + playerP.getComponentCount());
                System.out.println("RemoveGet: " + Go.getNumPlayers());
                System.out.println("Remove: size Map: " + InizializedPlayer.getMap().size());

            }

        });

        final JPanel eastP = new JPanel();
        panel.add(eastP, BorderLayout.EAST);

        final JPanel centerP = new JPanel();
        panel.add(centerP, BorderLayout.CENTER);
        centerP.setLayout(new BorderLayout());
        
        final JPanel centerP_row1 = new JPanel();
        centerP.add(centerP_row1,BorderLayout.NORTH);

        final JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        centerP_row1.add(lblNome);

        textNome = new JTextField();
        textNome.setPreferredSize(new Dimension(0,30));
        textNome.setHorizontalAlignment(SwingConstants.CENTER);
        centerP_row1.add(textNome);
        textNome.setColumns(10);

        final JPanel centerP_row2 = new JPanel();
        centerP.add(centerP_row2,BorderLayout.CENTER);
        
        rdbtnUman = new JRadioButton("Umano");
        rdbtnUman.setSelected(true);
        rdbtnUman.setHorizontalAlignment(SwingConstants.LEFT);
        rdbtnUman.setAlignmentY(Component.TOP_ALIGNMENT);
        centerP_row2.add(rdbtnUman);

        rdbtnComputer = new JRadioButton("Computer");
        centerP_row2.add(rdbtnComputer);

        final ButtonGroup group = new ButtonGroup();
        group.add(rdbtnComputer);
        group.add(rdbtnUman);
        rdbtnUman.setSelected(true);

        final JPanel color = new JPanel();
        color.setBackground(C.COLORS[Go.getNumPlayers()]);
        centerP.add(color,BorderLayout.SOUTH);
        
        panel.setVisible(true);
        return panel;

    }

    public static Map<String, Boolean> getMap() {
        return map;
    }

}
