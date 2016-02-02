package it.unibo.monopoli.view;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import it.unibo.monopoli.controller.Controller;
import it.unibo.monopoli.model.mainunits.Bank;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Home;
import it.unibo.monopoli.model.table.Hotel;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.LandGroup;
import it.unibo.monopoli.model.table.Ownership;

/**
 * 
 * @author LauraT
 *
 */
public class PlayerGraphic extends JPanel {

    private String name;
    int value;
    Color color;
    private Controller controller;
    private List<Player> list;
    private int i;
    private JLabel lblColc;
    private JLabel lblCol_1;
    private JLabel l;
    private JPanel row2;
    private HashMap<Integer, JLabel> labels;
    private int house;
    private int hotel;
    private JLabel lblHouse;
    private JLabel lblHotel;
    private Bank bank;

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
        this.list = list;
        this.i = i;

        this.name = list.get(i).getName();
        this.value = list.get(i).getMoney();

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
        row1.setLayout(new GridLayout(1, 3, 0, 0));

        final JLabel lblCol = new JLabel(this.name);
        lblCol.setFont(new Font("Papyrus", Font.BOLD, 18));
        row1.add(lblCol);

        lblCol_1 = new JLabel("" + this.value);
        lblCol_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
        row1.add(lblCol_1);

        lblColc = new JLabel();
        lblColc.setOpaque(true);
        lblColc.setBackground(C.cl.get(list.get(i).getPawn().getID()));
        lblColc.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
        row1.add(lblColc);

        row2 = new JPanel();
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
            int id = o.getID();
            l = new JLabel("" + (id < 10 ? "0" : "") + id);

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

        setLAbelContract();

        player.setVisible(true);
        return player;

    }

    public void setLAbelContract() {
        Player p = list.get(i);
    
        lblCol_1.setText(p.getMoney() + "");

        controller.getAllBoxes().stream().filter(o -> o instanceof Ownership).forEach(o -> {
            Ownership own = (Ownership) o;

            l = labels.get(o.getID());
            if (own.getOwner().equals(p)) {
                l.setOpaque(true);
                l.setVisible(true);

                if (o instanceof Land) {
                    l.setBackground(((Land) o).getColor());
                    if (((Ownership) o).isMortgaged()) {
                        l.setEnabled(false);
                    } else {
                        l.setEnabled(true);
                    }
                } else {
                    l.setBackground(Color.WHITE);
                }
                // TODO
                // dIE SE èISMORTGAGE colorarla di un altro modo
                // così il contrario
            } else {
                l.setOpaque(false);
                l.setVisible(false);
            }
        });
    }

    public Component buildBank(List<Box> allBoxes, Bank bank) {
        this.bank = bank;
        this.name = "Bank";
        
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
        final GridBagConstraints gcbRow1 = new GridBagConstraints();
        gcbRow1.insets = new Insets(0, 0, 5, 0);
        gcbRow1.fill = GridBagConstraints.BOTH;
        gcbRow1.gridx = 0;
        gcbRow1.gridy = 0;
        player.add(row1, gcbRow1);
        row1.setLayout(new GridLayout(1, 2, 0, 0));

        final JLabel lblCol = new JLabel(this.name);
        lblCol.setFont(new Font("Papyrus", Font.BOLD, 18));
        row1.add(lblCol);

        row2 = new JPanel();
        row2.setBorder(new LineBorder(new Color(0, 0, 0)));
        final GridBagConstraints gbcRow2 = new GridBagConstraints();
        gbcRow2.insets = new Insets(0, 0, 5, 0);
        gbcRow2.fill = GridBagConstraints.BOTH;
        gbcRow2.gridx = 0;
        gbcRow2.gridy = 1;
        player.add(row2, gbcRow2);
        
        final GridBagLayout gblRow2 = new GridBagLayout();
        gblRow2.columnWidths = new int[] { 0, 25, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gblRow2.rowHeights = new int[] { 16, 22, 0, 10, 9, 0, 0 };
        gblRow2.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        gblRow2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        row2.setLayout(gblRow2);

        controller.getAllBoxes().stream().filter(o -> o instanceof Ownership).forEach(o -> {
            int id = ((Box) o).getID();
            final JLabel l = new JLabel("" + (id < 10 ? "0" : "") + id);

            labels.put(id, l);
            l.setBorder(new LineBorder(new Color(0, 0, 0)));
            l.setPreferredSize(new Dimension(20, 20));
            l.setOpaque(true);
            final GridBagConstraints gbcLabels = new GridBagConstraints();
            gbcLabels.insets = new Insets(0, 0, 5, 5);
            gbcLabels.gridx = id % 15; // 15 caselle per riga
            gbcLabels.gridy = id / 15; // una riga ogni 15 caselle
            row2.add(l, gbcLabels);
        });

        final JPanel row3 = new JPanel();
        final FlowLayout flowLayout = (FlowLayout) row3.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        flowLayout.setAlignOnBaseline(true);
        row3.setBorder(new LineBorder(new Color(0, 0, 0)));
        final GridBagConstraints gbcRow3 = new GridBagConstraints();
        gbcRow3.fill = GridBagConstraints.BOTH;
        gbcRow3.gridx = 0;
        gbcRow3.gridy = 2;
        player.add(row3, gbcRow3);

        setLabelBank();

        player.setVisible(true);
        return player;

    }

    public void setLabelBank() {
        Bank p = controller.getBank();

        controller.getAllBoxes().stream().filter(o -> o instanceof Ownership).forEach(o -> {
            Ownership own = (Ownership) o;
            l = labels.get(o.getID());
            if (own.getOwner().equals(p)) {
                l.setOpaque(true);
                l.setVisible(true);
                if (o instanceof Land) {
                    l.setBackground(((Land) o).getColor());
                } else {
                    l.setBackground(Color.WHITE);
                }
            } else {
                l.setOpaque(false);
                l.setVisible(false);
            }
        });

    }

    public void setColor(Color color) {
        this.color = color;
    }

}
