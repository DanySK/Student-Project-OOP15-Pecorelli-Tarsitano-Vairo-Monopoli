package it.unibo.monopoli.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import it.unibo.monopoli.controller.Controller;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.Ownership;

public class PlayerGraphic extends JPanel {

    private String name;
    int value;
    Color color;
    private Controller controller;
    private HashMap<Integer, JLabel> labels;
    
    public PlayerGraphic(String name, int value, Controller controller) {
        this.name = name;
        this.value = value;
        this.controller = controller;
           labels = new HashMap<>();
    }

    public PlayerGraphic(Controller controller) {
        this(null, 0, controller);
    }
    
    /**
     * Method that build the Player's panel where there are the information
     * about player situation
     * 
     * @return -return a Player's panel
     */
    public JPanel build(List<Box> boxes, List<Player> list, int i) {
        
        this.name = list.get(i).getName();
        this.value = list.get(i).getMoney();
        final Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        System.out.println("Lista" + i + ":" + list.get(i).getOwnerships().get(0));
        
        
        
        
        final JPanel player = new JPanel();
        player.setBorder(new LineBorder(new Color(0, 0, 0), 1));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 0 };
        gridBagLayout.rowHeights = new int[] { 47, 120, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
        player.setPreferredSize(new Dimension(350, 200));
        player.setLayout(gridBagLayout);

        final JPanel row1 = new JPanel();
        row1.setBorder(new LineBorder(new Color(0, 0, 0)));
        final GridBagConstraints gbc_row1 = new GridBagConstraints();
        gbc_row1.insets = new Insets(0, 0, 5, 0);
        gbc_row1.fill = GridBagConstraints.BOTH;
        gbc_row1.gridx = 0;
        gbc_row1.gridy = 0;
        player.add(row1, gbc_row1);
        row1.setLayout(new GridLayout(1, 2, 0, 0));

        final JLabel lblCol = new JLabel(this.name);
        lblCol.setFont(new Font("Papyrus", Font.BOLD, 18));
        row1.add(lblCol);

        final JLabel lblCol_1 = new JLabel("" + this.value);
        lblCol_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
        row1.add(lblCol_1);

        final JPanel row2 = new JPanel();
        row2.setBorder(new LineBorder(new Color(0, 0, 0)));
        final GridBagConstraints gbc_row2 = new GridBagConstraints();
        gbc_row2.insets = new Insets(0, 0, 5, 0);
        gbc_row2.fill = GridBagConstraints.BOTH;
        gbc_row2.gridx = 0;
        gbc_row2.gridy = 1;
        player.add(row2, gbc_row2);
        final GridBagLayout gbl_row2 = new GridBagLayout();
        gbl_row2.columnWidths = new int[] { 0, 25, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_row2.rowHeights = new int[] { 16, 22, 0, 10, 9, 0, 0 };
        gbl_row2.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        gbl_row2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        row2.setLayout(gbl_row2);
        
        
        
        controller.getAllBoxes().stream().filter(o -> o instanceof Ownership).forEach(o -> {
            int id = ((Box)o).getID();
            final JLabel l =  new JLabel("" + (id < 10 ? "0" : "") + id);
           
            labels.put(id, l);
            l.setBorder(new LineBorder(new Color(0, 0, 0)));
            l.setPreferredSize(new Dimension(20, 20));
            l.setOpaque(true);   
            final GridBagConstraints gbc_label_1 = new GridBagConstraints();
            gbc_label_1.insets = new Insets(0, 0, 5, 5);
            gbc_label_1.gridx = id % 15; // 15 caselle per riga
            gbc_label_1.gridy = id / 15; // una riga ogni 15 caselle
            row2.add(l, gbc_label_1);
        });
        
        list.get(i).getOwnerships().stream().filter(o -> o instanceof Ownership).forEach(o -> {
            Box b = ((Box)o);
            JLabel l = labels.get(b.getID());
                     
            if (o instanceof Land) l.setBackground(((Land)b).getColor());
            else  l.setBackground(Color.BLACK);
        });


//        final JLabel label_13 = new JLabel("");
//        final GridBagConstraints gbc_label_13 = new GridBagConstraints();
//        gbc_label_13.insets = new Insets(0, 0, 5, 5);
//        gbc_label_13.gridx = 2;
//        gbc_label_13.gridy = 0;
//        row2.add(label_13, gbc_label_13);
//
//        final JLabel label = new JLabel("X");
//        label.setOpaque(true);
//        label.setBackground(color);
//        label.setBorder(new LineBorder(new Color(0, 0, 0)));
//        label.setPreferredSize(new Dimension(20, 20));
//        final GridBagConstraints gbc_label = new GridBagConstraints();
//        gbc_label.insets = new Insets(0, 0, 5, 5);
//        gbc_label.gridx = 2;
//        gbc_label.gridy = 1;
//        row2.add(label, gbc_label);
//
//        final JLabel label_1 = new JLabel("02");
//        label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
//        label_1.setPreferredSize(new Dimension(20, 20));
//        final GridBagConstraints gbc_label_1 = new GridBagConstraints();
//        gbc_label_1.insets = new Insets(0, 0, 5, 5);
//        gbc_label_1.gridx = 3;
//        gbc_label_1.gridy = 1;
//        row2.add(label_1, gbc_label_1);
//
//        final JLabel label_2 = new JLabel("03");
//        label_2.setPreferredSize(new Dimension(20, 20));
//        label_2.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_2 = new GridBagConstraints();
//        gbc_label_2.insets = new Insets(0, 0, 5, 5);
//        gbc_label_2.gridx = 6;
//        gbc_label_2.gridy = 1;
//        row2.add(label_2, gbc_label_2);
//
//        final JLabel label_3 = new JLabel("04");
//        label_3.setPreferredSize(new Dimension(20, 20));
//        label_3.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_3 = new GridBagConstraints();
//        gbc_label_3.insets = new Insets(0, 0, 5, 5);
//        gbc_label_3.gridx = 7;
//        gbc_label_3.gridy = 1;
//        row2.add(label_3, gbc_label_3);
//
//        final JLabel label_4 = new JLabel("05");
//        label_4.setPreferredSize(new Dimension(20, 20));
//        label_4.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_4 = new GridBagConstraints();
//        gbc_label_4.insets = new Insets(0, 0, 5, 5);
//        gbc_label_4.gridx = 8;
//        gbc_label_4.gridy = 1;
//        row2.add(label_4, gbc_label_4);
//
//        final JLabel label_5 = new JLabel("06");
//        label_5.setPreferredSize(new Dimension(20, 20));
//        label_5.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_5 = new GridBagConstraints();
//        gbc_label_5.insets = new Insets(0, 0, 5, 5);
//        gbc_label_5.gridx = 11;
//        gbc_label_5.gridy = 1;
//        row2.add(label_5, gbc_label_5);
//
//        final JLabel label_6 = new JLabel("07");
//        label_6.setPreferredSize(new Dimension(20, 20));
//        label_6.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_6 = new GridBagConstraints();
//        gbc_label_6.insets = new Insets(0, 0, 5, 5);
//        gbc_label_6.gridx = 12;
//        gbc_label_6.gridy = 1;
//        row2.add(label_6, gbc_label_6);
//
//        final JLabel label_7 = new JLabel("08");
//        label_7.setPreferredSize(new Dimension(20, 20));
//        label_7.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_7 = new GridBagConstraints();
//        gbc_label_7.insets = new Insets(0, 0, 5, 5);
//        gbc_label_7.gridx = 13;
//        gbc_label_7.gridy = 1;
//        row2.add(label_7, gbc_label_7);
//
//        final JLabel label_8 = new JLabel("09");
//        label_8.setPreferredSize(new Dimension(20, 20));
//        label_8.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_8 = new GridBagConstraints();
//        gbc_label_8.insets = new Insets(0, 0, 5, 5);
//        gbc_label_8.gridx = 15;
//        gbc_label_8.gridy = 1;
//        row2.add(label_8, gbc_label_8);
//
//        final JLabel label_9 = new JLabel("10");
//        label_9.setPreferredSize(new Dimension(20, 20));
//        label_9.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_9 = new GridBagConstraints();
//        gbc_label_9.insets = new Insets(0, 0, 5, 5);
//        gbc_label_9.gridx = 16;
//        gbc_label_9.gridy = 1;
//        row2.add(label_9, gbc_label_9);
//
//        final JLabel label_10 = new JLabel("11");
//        label_10.setPreferredSize(new Dimension(20, 20));
//        label_10.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_10 = new GridBagConstraints();
//        gbc_label_10.insets = new Insets(0, 0, 5, 0);
//        gbc_label_10.gridx = 17;
//        gbc_label_10.gridy = 1;
//        row2.add(label_10, gbc_label_10);
//
//        final JLabel label_14 = new JLabel("");
//        final GridBagConstraints gbc_label_14 = new GridBagConstraints();
//        gbc_label_14.insets = new Insets(0, 0, 5, 5);
//        gbc_label_14.gridx = 0;
//        gbc_label_14.gridy = 2;
//        row2.add(label_14, gbc_label_14);
//
//        final JLabel label_12 = new JLabel("");
//        final GridBagConstraints gbc_label_12 = new GridBagConstraints();
//        gbc_label_12.insets = new Insets(0, 0, 5, 5);
//        gbc_label_12.gridx = 1;
//        gbc_label_12.gridy = 3;
//        row2.add(label_12, gbc_label_12);
//
//        final JLabel label_11 = new JLabel("12");
//        label_11.setPreferredSize(new Dimension(20, 20));
//        label_11.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_11 = new GridBagConstraints();
//        gbc_label_11.insets = new Insets(0, 0, 5, 5);
//        gbc_label_11.gridx = 2;
//        gbc_label_11.gridy = 3;
//        row2.add(label_11, gbc_label_11);
//
//        final JLabel label_15 = new JLabel("13");
//        label_15.setPreferredSize(new Dimension(20, 20));
//        label_15.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_15 = new GridBagConstraints();
//        gbc_label_15.insets = new Insets(0, 0, 5, 5);
//        gbc_label_15.gridx = 3;
//        gbc_label_15.gridy = 3;
//        row2.add(label_15, gbc_label_15);
//
//        final JLabel label_16 = new JLabel("14");
//        label_16.setPreferredSize(new Dimension(20, 20));
//        label_16.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_16 = new GridBagConstraints();
//        gbc_label_16.insets = new Insets(0, 0, 5, 5);
//        gbc_label_16.gridx = 4;
//        gbc_label_16.gridy = 3;
//        row2.add(label_16, gbc_label_16);
//
//        final JLabel label_17 = new JLabel("15");
//        label_17.setPreferredSize(new Dimension(20, 20));
//        label_17.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_17 = new GridBagConstraints();
//        gbc_label_17.insets = new Insets(0, 0, 5, 5);
//        gbc_label_17.gridx = 6;
//        gbc_label_17.gridy = 3;
//        row2.add(label_17, gbc_label_17);
//
//        final JLabel label_18 = new JLabel("16");
//        label_18.setPreferredSize(new Dimension(20, 20));
//        label_18.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_18 = new GridBagConstraints();
//        gbc_label_18.insets = new Insets(0, 0, 5, 5);
//        gbc_label_18.gridx = 7;
//        gbc_label_18.gridy = 3;
//        row2.add(label_18, gbc_label_18);
//
//        final JLabel label_19 = new JLabel("17");
//        label_19.setPreferredSize(new Dimension(20, 20));
//        label_19.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_19 = new GridBagConstraints();
//        gbc_label_19.insets = new Insets(0, 0, 5, 5);
//        gbc_label_19.gridx = 8;
//        gbc_label_19.gridy = 3;
//        row2.add(label_19, gbc_label_19);
//
//        final JLabel label_20 = new JLabel("18");
//        label_20.setPreferredSize(new Dimension(20, 20));
//        label_20.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_20 = new GridBagConstraints();
//        gbc_label_20.insets = new Insets(0, 0, 5, 5);
//        gbc_label_20.gridx = 11;
//        gbc_label_20.gridy = 3;
//        row2.add(label_20, gbc_label_20);
//
//        final JLabel label_21 = new JLabel("19");
//        label_21.setPreferredSize(new Dimension(20, 20));
//        label_21.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_21 = new GridBagConstraints();
//        gbc_label_21.insets = new Insets(0, 0, 5, 5);
//        gbc_label_21.gridx = 12;
//        gbc_label_21.gridy = 3;
//        row2.add(label_21, gbc_label_21);
//
//        final JLabel label_22 = new JLabel("20");
//        label_22.setPreferredSize(new Dimension(20, 20));
//        label_22.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_22 = new GridBagConstraints();
//        gbc_label_22.insets = new Insets(0, 0, 5, 5);
//        gbc_label_22.gridx = 13;
//        gbc_label_22.gridy = 3;
//        row2.add(label_22, gbc_label_22);
//
//        final JLabel label_23 = new JLabel("21");
//        label_23.setPreferredSize(new Dimension(20, 20));
//        label_23.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_23 = new GridBagConstraints();
//        gbc_label_23.insets = new Insets(0, 0, 5, 5);
//        gbc_label_23.gridx = 15;
//        gbc_label_23.gridy = 3;
//        row2.add(label_23, gbc_label_23);
//
//        final JLabel label_24 = new JLabel("22");
//        label_24.setPreferredSize(new Dimension(20, 20));
//        label_24.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_24 = new GridBagConstraints();
//        gbc_label_24.insets = new Insets(0, 0, 5, 5);
//        gbc_label_24.gridx = 16;
//        gbc_label_24.gridy = 3;
//        row2.add(label_24, gbc_label_24);
//
//        final JLabel label_26 = new JLabel("");
//        final GridBagConstraints gbc_label_26 = new GridBagConstraints();
//        gbc_label_26.insets = new Insets(0, 0, 5, 5);
//        gbc_label_26.gridx = 2;
//        gbc_label_26.gridy = 4;
//        row2.add(label_26, gbc_label_26);
//
//        final JLabel label_25 = new JLabel("23");
//        label_25.setPreferredSize(new Dimension(20, 20));
//        label_25.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_25 = new GridBagConstraints();
//        gbc_label_25.insets = new Insets(0, 0, 0, 5);
//        gbc_label_25.gridx = 5;
//        gbc_label_25.gridy = 5;
//        row2.add(label_25, gbc_label_25);
//
//        final JLabel label_27 = new JLabel("24");
//        label_27.setPreferredSize(new Dimension(20, 20));
//        label_27.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_27 = new GridBagConstraints();
//        gbc_label_27.insets = new Insets(0, 0, 0, 5);
//        gbc_label_27.gridx = 6;
//        gbc_label_27.gridy = 5;
//        row2.add(label_27, gbc_label_27);
//
//        final JLabel label_29 = new JLabel("25");
//        label_29.setPreferredSize(new Dimension(20, 20));
//        label_29.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_29 = new GridBagConstraints();
//        gbc_label_29.insets = new Insets(0, 0, 0, 5);
//        gbc_label_29.gridx = 10;
//        gbc_label_29.gridy = 5;
//        row2.add(label_29, gbc_label_29);
//
//        final JLabel label_28 = new JLabel("26");
//        label_28.setPreferredSize(new Dimension(20, 20));
//        label_28.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_28 = new GridBagConstraints();
//        gbc_label_28.insets = new Insets(0, 0, 0, 5);
//        gbc_label_28.gridx = 11;
//        gbc_label_28.gridy = 5;
//        row2.add(label_28, gbc_label_28);
//
//        final JLabel label_30 = new JLabel("27");
//        label_30.setPreferredSize(new Dimension(20, 20));
//        label_30.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_30 = new GridBagConstraints();
//        gbc_label_30.insets = new Insets(0, 0, 0, 5);
//        gbc_label_30.gridx = 12;
//        gbc_label_30.gridy = 5;
//        row2.add(label_30, gbc_label_30);
//
//        final JLabel label_31 = new JLabel("28");
//        label_31.setPreferredSize(new Dimension(20, 20));
//        label_31.setBorder(new LineBorder(new Color(0, 0, 0)));
//        final GridBagConstraints gbc_label_31 = new GridBagConstraints();
//        gbc_label_31.insets = new Insets(0, 0, 0, 5);
//        gbc_label_31.gridx = 13;
//        gbc_label_31.gridy = 5;
//        row2.add(label_31, gbc_label_31);

        final JPanel row3 = new JPanel();
        final FlowLayout flowLayout = (FlowLayout) row3.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        flowLayout.setAlignOnBaseline(true);
        row3.setBorder(new LineBorder(new Color(0, 0, 0)));
        final GridBagConstraints gbc_row3 = new GridBagConstraints();
        gbc_row3.fill = GridBagConstraints.BOTH;
        gbc_row3.gridx = 0;
        gbc_row3.gridy = 2;
        player.add(row3, gbc_row3);

        final JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\LauraT\\Desktop\\casadim.png"));
        lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        lblNewLabel.setPreferredSize(new Dimension(30, 25));
        row3.add(lblNewLabel);

        final JLabel lblNewLabel_1 = new JLabel("X");
        lblNewLabel_1.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 17));
        row3.add(lblNewLabel_1);

        final JLabel label_32 = new JLabel("");
        label_32.setIcon(new ImageIcon("C:\\Users\\LauraT\\Desktop\\hoteldim.png"));
        label_32.setPreferredSize(new Dimension(30, 25));
        label_32.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        row3.add(label_32);

        final JLabel label_33 = new JLabel("X");
        label_33.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 17));
        row3.add(label_33);
        
        
        
        

        player.setVisible(true);
        return player;

    }
}
