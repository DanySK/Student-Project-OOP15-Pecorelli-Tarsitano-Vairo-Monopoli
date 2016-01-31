package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
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
public class InizializedComputer {
    final Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    private static boolean isUman;
    private static String name;

    // private JTextField textNome;

    public InizializedComputer(String name, boolean isUman) {
        this.name = name;
        this.isUman = isUman;
        // build();
    }

    public InizializedComputer() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Method that build the Player's panel where there are the information
     * about player situation
     * 
     * @return -return a Player's panel
     */
    public JPanel build(JPanel playerP) {
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

        JTextField textNome = new JTextField();
        textNome.setPreferredSize(new Dimension(0,30));
        textNome.setHorizontalAlignment(SwingConstants.CENTER);
        centerP_row1.add(textNome);
        textNome.setColumns(10);
        textNome.setText("Computer");
        textNome.setEditable(false);

        final JPanel centerP_row2 = new JPanel();
        centerP.add(centerP_row2,BorderLayout.CENTER);
        
        JRadioButton rdbtnUman = new JRadioButton("Umano");
        rdbtnUman.setHorizontalAlignment(SwingConstants.LEFT);
        rdbtnUman.setAlignmentY(Component.TOP_ALIGNMENT);
        rdbtnUman.setEnabled(false);
        centerP_row2.add(rdbtnUman);

        JRadioButton rdbtnComputer = new JRadioButton("Computer");
        rdbtnUman.setHorizontalAlignment(SwingConstants.LEFT);
        rdbtnUman.setAlignmentY(Component.TOP_ALIGNMENT);
        centerP_row2.add(rdbtnComputer);
        rdbtnComputer.setSelected(true);
        rdbtnComputer.setEnabled(false);

        final ButtonGroup group = new ButtonGroup();
        group.add(rdbtnComputer);
        group.add(rdbtnUman);
        

        final JPanel color = new JPanel();
        color.setBackground(C.COLORS[Go.getNumPlayers()]);
        centerP.add(color,BorderLayout.SOUTH);
        
        final JButton remove = new JButton("Remove");
        remove.setFont(new Font("Times New Roman", Font.BOLD, 10));
        remove.setPreferredSize(new Dimension(70, 22));
        southP.add(remove);
        
        
        remove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InizializedPlayer.getMap().remove(textNome.getText(), isUman);
                panel.setVisible(false);
                playerP.remove(panel);
                Go.addNumPlayers(-1);
                playerP.revalidate();
                System.out.println("Remove: " + playerP.getComponentCount());
                System.out.println("RemoveGet: "+ Go.getNumPlayers());

            }

        });
        
        if (rdbtnComputer.isSelected()) {
            isUman = false;
        } else {
            isUman = true;
        }
        
        InizializedPlayer.getMap().put(Go.getNumPlayers()+C.SPLITTOKEN+textNome.getText(), isUman);

        panel.setVisible(true);
        return panel;

    }

    public static boolean isUman() {
        return isUman;
    }

    public void setUman(boolean isUman) {
        this.isUman = isUman;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
