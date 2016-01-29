package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.plaf.synth.SynthSpinnerUI;

import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.DecksBox;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.Ownership;
import it.unibo.monopoli.model.table.TaxImpl;
import it.unibo.monopoli.view.IBoxGraphic.Position;
import it.unibo.monopoli.controller.*;

public class ProvaTabellone {

     private final Controller controller;
    private List<Box> cards;
    private int col, row;

    private Border border;

    public ProvaTabellone(int col, int row , final Controller controller ) {
        border = BorderFactory.createLineBorder(Color.BLACK, 2);
        this.col = col;
        this.row = row;
         this.controller = controller;
         this.cards = this.controller.getAllBoxes();
    }

    /**
     * @wbp.parser.entryPoint
     */
    public JPanel initialize() {
        int i = 0;
        int j = 0;

        final JPanel panel = new JPanel();
        final JPanel panelM = new JPanel();
        panelM.setLayout(new BorderLayout());
        panelM.add(panel, BorderLayout.CENTER);
        // Border borders2 = BorderFactory.createLineBorder(Color.BLUE, 6);
        // panel.setBorder(border2);
        final GridBagLayout gbl_panel = new GridBagLayout();

        gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_panel.rowHeights = new int[] { 36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
                Double.MIN_VALUE };

        panel.setLayout(gbl_panel);

        if (cards != null) {
            int i1 = 0;

            cards.forEach(card -> {
                int c = -1, r = -1;
                Position pos = null;
                if (card.getID() < col) {
                    r = row - 1;
                    c = col - 1 - card.getID();
                    pos = Position.SOUTH;
                    System.out.println("" + card.getID());
                } else if (card.getID() < (col + row) - 1) {
                    c = 0;
                    r = row - 2 - card.getID() - (-row);
                    pos = Position.WEST;
                    System.out.println("" + card.getID());
                } else if (card.getID() < (col * 2 + row) - 2) {
                    r = 0;
                    c = /* col - 1 - card.getID() - (col + row); */ (card.getID() - 20);
                    pos = Position.NORTH;
                    System.out.println("" + card.getID());
                } else {
                    c = col - 1;
                    r = /* row - 1 - card.getID() - (col * 2 + row); */(card.getID() - 30);
                    pos = Position.EAST;
                    System.out.println("" + card.getID());
                }

                IBoxGraphic newcard;
                // crei la carta necessaria
                if (card instanceof Land) {
                    newcard = new LandGraphic((Land) card, pos);
                } else if (card instanceof Ownership) {
                    newcard = new OwnershipGraphic((Ownership) card, pos);
                } else if (card instanceof TaxImpl) {
                    newcard = new TaxGraphic((TaxImpl) card, pos);
                } else if (card instanceof DecksBox) {
                    newcard = new DecksGraphic((DecksBox) card, pos);
                } else {
                    newcard = new BoxGraphic(card, pos);
                }

                // aggiungi la carta nella griglia, alla posizione r, c
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(0, 0, 0, 0);
                gbc.gridx = c;
                gbc.gridy = r;
                panel.add(newcard.build(), gbc);

            });

        }
        // IBoxGraphic casella20;
        // final GridBagConstraints gbc_lblCiaooooo = new GridBagConstraints();
        // casella20 = new CardGraphicBoxProva("Via Verdi", Color.RED, 10000,
        // Position.NORTH );
        // gbc_lblCiaooooo.insets = new Insets(0, 0, 0, 0);
        // gbc_lblCiaooooo.gridx = 0;
        // gbc_lblCiaooooo.gridy = 0;
        // panel.add(casella20.build(), gbc_lblCiaooooo);
        //
        // IBoxGraphic casella21;
        // final GridBagConstraints gbc_label_11 = new GridBagConstraints();
        // casella21 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.NORTH);
        // gbc_label_11.insets = new Insets(0, 0, 0, 0);
        // gbc_label_11.gridx = 1;
        // gbc_label_11.gridy = 0;
        // panel.add(casella21.build(), gbc_label_11);
        //
        // IBoxGraphic casella22;
        // final GridBagConstraints gbc_label_12 = new GridBagConstraints();
        // casella22 = new CardGraphic("Via Gialli", Color.YELLOW,
        // 20000,Position.NORTH);
        // gbc_label_12.insets = new Insets(0, 0, 0, 0);
        // gbc_label_12.gridx = 2;
        // gbc_label_12.gridy = 0;
        // panel.add(casella22.build(), gbc_label_12);
        //
        // IBoxGraphic casella23;
        // final GridBagConstraints gbc_label_13 = new GridBagConstraints();
        // casella23 = new CardGraphic("Via Verdi", Color.BLUE, 10000,
        // Position.NORTH);
        // gbc_label_13.insets = new Insets(0, 0, 0, 0);
        // gbc_label_13.gridx = 3;
        // gbc_label_13.gridy = 0;
        // panel.add(casella23.build(), gbc_label_13);
        //
        // IBoxGraphic casella24;
        // final GridBagConstraints gbc_label_14 = new GridBagConstraints();
        // casella24 = new CardGraphic("Via Gialli", Color.YELLOW, 20000,
        // Position.NORTH);
        // gbc_label_14.insets = new Insets(0, 0, 0, 0);
        // gbc_label_14.gridx = 4;
        // gbc_label_14.gridy = 0;
        // panel.add(casella24.build(), gbc_label_14);
        //
        // IBoxGraphic casella25;
        // final GridBagConstraints gbc_label_15 = new GridBagConstraints();
        // casella25 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.NORTH);
        // gbc_label_15.insets = new Insets(0, 0, 0, 0);
        // gbc_label_15.gridx = 5;
        // gbc_label_15.gridy = 0;
        // panel.add(casella25.build(), gbc_label_15);
        //
        // IBoxGraphic casella26;
        // final GridBagConstraints gbc_label_16 = new GridBagConstraints();
        // casella26 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.NORTH);
        // gbc_label_16.insets = new Insets(0, 0, 0, 0);
        // gbc_label_16.gridx = 6;
        // gbc_label_16.gridy = 0;
        // panel.add(casella26.build(), gbc_label_16);
        //
        // IBoxGraphic casella27;
        // final GridBagConstraints gbc_label_17 = new GridBagConstraints();
        // casella27 = new CardGraphic("Via Verdi", Color.GREEN,
        // 10000,Position.NORTH);
        // gbc_label_17.insets = new Insets(0, 0, 0, 0);
        // gbc_label_17.gridx = 7;
        // gbc_label_17.gridy = 0;
        // panel.add(casella27.build(), gbc_label_17);
        //
        // IBoxGraphic casella28;
        // final GridBagConstraints gbc_label_18 = new GridBagConstraints();
        // casella28 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.NORTH);
        // gbc_label_18.insets = new Insets(0, 0, 0, 0);
        // gbc_label_18.gridx = 8;
        // gbc_label_18.gridy = 0;
        // panel.add(casella28.build(), gbc_label_18);
        //
        // IBoxGraphic casella29;
        // final GridBagConstraints gbc_label_19 = new GridBagConstraints();
        // casella29 = new CardGraphic("Via Verdi", Color.GREEN,
        // 10000,Position.NORTH);
        // gbc_label_19.insets = new Insets(0, 0, 0, 0);
        // gbc_label_19.gridx = 9;
        // gbc_label_19.gridy = 0;
        // panel.add(casella29.build(), gbc_label_19);
        //
        // IBoxGraphic casella30;
        // final GridBagConstraints gbc_label_20 = new GridBagConstraints();
        // casella30 = new CardGraphicBoxProva("Via Verdi", Color.GREEN,
        // 10000,Position.NORTH);
        // gbc_label_20.insets = new Insets(0, 0, 0, 0);
        // gbc_label_20.gridx = 10;
        // gbc_label_20.gridy = 0;
        // panel.add(casella30.build(), gbc_label_20);
        //
        // IBoxGraphic casella19;
        // final GridBagConstraints gbc_label_1 = new GridBagConstraints();
        // casella19 = new CardGraphic("Via Bianchi", Color.RED, 15000,
        // Position.WEST);
        // gbc_label_1.insets = new Insets(0, 0, 0, 0);
        // gbc_label_1.gridx = 0;
        // gbc_label_1.gridy = 1;
        // panel.add(casella19.build(), gbc_label_1);
        //
        // IBoxGraphic casella18;
        // final GridBagConstraints gbc_label_2 = new GridBagConstraints();
        // casella18 = new CardGraphic("Via Pesci", Color.CYAN, 10000,
        // Position.WEST);
        // gbc_label_2.insets = new Insets(0, 0, 0, 0);
        // gbc_label_2.gridx = 0;
        // gbc_label_2.gridy = 2;
        // panel.add(casella18.build(), gbc_label_2);
        //
        // IBoxGraphic casella17;
        // final GridBagConstraints gbc_label_3 = new GridBagConstraints();
        // casella17 = new CardGraphic("Via Gialli", Color.YELLOW,
        // 20000,Position.WEST);
        // gbc_label_3.insets = new Insets(0, 0, 0, 0);
        // gbc_label_3.gridx = 0;
        // gbc_label_3.gridy = 3;
        // panel.add(casella17.build(), gbc_label_3);
        //
        // IBoxGraphic casella16;
        // final GridBagConstraints gbc_label_4 = new GridBagConstraints();
        // casella16 = new CardGraphic("Via Verdi", Color.GRAY,
        // 10000,Position.WEST);
        // gbc_label_4.insets = new Insets(0, 0, 0, 0);
        // gbc_label_4.gridx = 0;
        // gbc_label_4.gridy = 4;
        // panel.add(casella16.build(), gbc_label_4);
        //
        // IBoxGraphic casella15;
        // final GridBagConstraints gbc_label_5 = new GridBagConstraints();
        // casella15 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.WEST);
        // gbc_label_5.insets = new Insets(0, 0, 0, 0);
        // gbc_label_5.gridx = 0;
        // gbc_label_5.gridy = 5;
        // panel.add(casella15.build(), gbc_label_5);
        //
        // IBoxGraphic casella14;
        // final GridBagConstraints gbc_label_6 = new GridBagConstraints();
        // casella14 = new CardGraphic("Via Gialli", Color.YELLOW,
        // 20000,Position.WEST);
        // gbc_label_6.insets = new Insets(0, 0, 0, 0);
        // gbc_label_6.gridx = 0;
        // gbc_label_6.gridy = 6;
        // panel.add(casella14.build(), gbc_label_6);
        //
        // IBoxGraphic casella13;
        // final GridBagConstraints gbc_label_7 = new GridBagConstraints();
        // casella13 = new CardGraphic("Via Gialli", Color.YELLOW,
        // 20000,Position.WEST);
        // gbc_label_7.insets = new Insets(0, 0, 0, 0);
        // gbc_label_7.gridx = 0;
        // gbc_label_7.gridy = 7;
        // panel.add(casella13.build(), gbc_label_7);
        //
        //// IBoxGraphic casella12;
        //// GridBagConstraints gbc_label_8 = new GridBagConstraints();
        //// casella12 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.WEST);
        //// gbc_label_8.insets = new Insets(0, 0, 0, 0);
        //// gbc_label_8.gridx = 0;
        //// gbc_label_8.gridy = 8;
        //// panel.add(casella12.build(), gbc_label_8);
        ////
        //// IBoxGraphic casella11;
        //// GridBagConstraints gbc_label_9 = new GridBagConstraints();
        //// casella11 = new CardGraphic("Via Verdi", Color.GREEN,
        // 10000,Position.WEST);
        //// gbc_label_9.insets = new Insets(0, 0, 0, 0);
        //// gbc_label_9.gridx = 0;
        //// gbc_label_9.gridy = 9;
        //// panel.add(casella11.build(), gbc_label_9);
        //
        // IBoxGraphic casella31;
        // final GridBagConstraints gbc_label_21 = new GridBagConstraints();
        // casella31 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.EAST);
        // gbc_label_21.insets = new Insets(0, 0, 0, 0);
        // gbc_label_21.gridx = 10;
        // gbc_label_21.gridy = 1;
        // panel.add(casella31.build(), gbc_label_21);
        //
        // IBoxGraphic casella32;
        // final GridBagConstraints gbc_label_22 = new GridBagConstraints();
        // casella32 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.EAST);
        // gbc_label_22.insets = new Insets(0, 0, 0, 0);
        // gbc_label_22.gridx = 10;
        // gbc_label_22.gridy = 2;
        // panel.add(casella32.build(), gbc_label_22);
        //
        //// final JLabel lblCiaooooo = new JLabel("Ciaooooo");
        //// GridBagConstraints gbc_lblCiaooooo1 = new GridBagConstraints();
        //// gbc_lblCiaooooo1.insets = new Insets(0, 0, 0, 0);
        //// gbc_lblCiaooooo1.gridx = 4;
        //// gbc_lblCiaooooo1.gridy = 4;
        //// panel.add(lblCiaooooo, gbc_lblCiaooooo1);
        //
        // IBoxGraphic casella33;
        // final GridBagConstraints gbc_label_23 = new GridBagConstraints();
        // casella33 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.EAST);
        // gbc_label_23.insets = new Insets(0, 0, 0, 0);
        // gbc_label_23.gridx = 10;
        // gbc_label_23.gridy = 3;
        // panel.add(casella33.build(), gbc_label_23);
        //
        // IBoxGraphic casella34;
        // final GridBagConstraints gbc_label_24 = new GridBagConstraints();
        // casella34 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.EAST);
        // gbc_label_24.insets = new Insets(0, 0, 0, 0);
        // gbc_label_24.gridx = 10;
        // gbc_label_24.gridy = 4;
        // panel.add(casella34.build(), gbc_label_24);
        //
        //// final JLabel label_25 = new JLabel("35");
        //// GridBagConstraints gbc_label_25 = new GridBagConstraints();
        //// JPanel casella35 = new CardGraphic("Via Rossi", Color.RED,
        // 15000).build();
        //// gbc_label_25.insets = new Insets(0, 0, 0, 0);
        //// gbc_label_25.gridx = 10;
        //// gbc_label_25.gridy = 5;
        //// panel.add(casella35, gbc_label_25);
        ////
        //// final JLabel label_26 = new JLabel("36");
        //// GridBagConstraints gbc_label_26 = new GridBagConstraints();
        //// JPanel casella36 = new CardGraphic("Via Rossi", Color.RED,
        // 15000).build();
        //// gbc_label_26.insets = new Insets(0, 0, 0, 0);
        //// gbc_label_26.gridx = 10;
        //// gbc_label_26.gridy = 6;
        //// panel.add(casella36, gbc_label_26);
        ////
        //// final JLabel label_27 = new JLabel("37");
        //// GridBagConstraints gbc_label_27 = new GridBagConstraints();
        //// JPanel casella37 = new CardGraphic("Via Rossi", Color.RED,
        // 15000).build();
        //// gbc_label_27.insets = new Insets(0, 0, 0, 0);
        //// gbc_label_27.gridx = 10;
        //// gbc_label_27.gridy = 7;
        //// panel.add(casella37, gbc_label_27);
        ////
        //// final JLabel label_28 = new JLabel("38");
        //// GridBagConstraints gbc_label_28 = new GridBagConstraints();
        //// JPanel casella38 = new CardGraphic("Via Rossi", Color.RED,
        // 15000).build();
        //// gbc_label_28.insets = new Insets(0, 0, 0, 0);
        //// gbc_label_28.gridx = 10;
        //// gbc_label_28.gridy = 8;
        //// panel.add(casella38, gbc_label_28);
        ////
        //// final JLabel label_29 = new JLabel("39");
        //// GridBagConstraints gbc_label_29 = new GridBagConstraints();
        //// JPanel casella39 = new CardGraphic("Via Rossi", Color.RED,
        // 15000).build();
        //// gbc_label_29.insets = new Insets(0, 0, 0, 0);
        //// gbc_label_29.gridx = 10;
        //// gbc_label_29.gridy = 9;
        //// panel.add(casella39, gbc_label_29);
        ////
        // IBoxGraphic casella0;
        // final GridBagConstraints gbc_label_30 = new GridBagConstraints();
        // casella0 = new CardGraphicBoxProva("Via Rossi", Color.RED,
        // 15000,Position.SOUTH);
        // gbc_label_30.insets = new Insets(0, 0, 0, 0);
        // gbc_label_30.gridx = 10;
        // gbc_label_30.gridy = 10;
        // panel.add(casella0.build(), gbc_label_30);
        //
        // IBoxGraphic casella1;
        // final GridBagConstraints gbc_label_31 = new GridBagConstraints();
        // casella1 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.SOUTH);
        // gbc_label_31.insets = new Insets(0, 0, 0, 0);
        // gbc_label_31.gridx = 9;
        // gbc_label_31.gridy = 10;
        // panel.add(casella1.build(), gbc_label_31);
        //
        // IBoxGraphic casella2;
        // final GridBagConstraints gbc_label_32 = new GridBagConstraints();
        // casella2 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.SOUTH);
        // gbc_label_32.insets = new Insets(0, 0, 0, 0);
        // gbc_label_32.gridx = 8;
        // gbc_label_32.gridy = 10;
        // panel.add(casella2.build(), gbc_label_32);
        //
        // IBoxGraphic casella3;
        // final GridBagConstraints gbc_label_33 = new GridBagConstraints();
        // casella3 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.SOUTH);
        // gbc_label_33.insets = new Insets(0, 0, 0, 0);
        // gbc_label_33.gridx = 7;
        // gbc_label_33.gridy = 10;
        // panel.add(casella3.build(), gbc_label_33);
        //
        // IBoxGraphic casella4;
        // final GridBagConstraints gbc_label_34 = new GridBagConstraints();
        // casella4 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.SOUTH);
        // gbc_label_34.insets = new Insets(0, 0, 0, 0);
        // gbc_label_34.gridx = 6;
        // gbc_label_34.gridy = 10;
        // panel.add(casella4.build(), gbc_label_34);
        //
        //
        // IBoxGraphic casella5;
        // final GridBagConstraints gbc_label_35 = new GridBagConstraints();
        // casella5 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.SOUTH);
        // gbc_label_35.insets = new Insets(0, 0, 0, 0);
        // gbc_label_35.gridx = 5;
        // gbc_label_35.gridy = 10;
        // panel.add(casella5.build(), gbc_label_35);
        //
        // IBoxGraphic casella6;
        // final GridBagConstraints gbc_label_36 = new GridBagConstraints();
        // casella6 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.SOUTH);
        // gbc_label_36.insets = new Insets(0, 0, 0, 0);
        // gbc_label_36.gridx = 4;
        // gbc_label_36.gridy = 10;
        // panel.add(casella6.build(), gbc_label_36);
        //
        // IBoxGraphic casella7;
        // final GridBagConstraints gbc_label_37 = new GridBagConstraints();
        // casella7 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.SOUTH);
        // gbc_label_37.insets = new Insets(0, 0, 0, 0);
        // gbc_label_37.gridx = 3;
        // gbc_label_37.gridy = 10;
        // panel.add(casella7.build(), gbc_label_37);
        //
        // IBoxGraphic casella8;
        // final GridBagConstraints gbc_label_38 = new GridBagConstraints();
        // casella8 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.SOUTH);
        // gbc_label_38.insets = new Insets(0, 0, 0, 0);
        // gbc_label_38.gridx = 2;
        // gbc_label_38.gridy = 10;
        // panel.add(casella8.build(), gbc_label_38);
        //
        // IBoxGraphic casella9;
        // final GridBagConstraints gbc_label_39 = new GridBagConstraints();
        // casella9 = new CardGraphic("Via Rossi", Color.RED,
        // 15000,Position.SOUTH);
        // gbc_label_39.insets = new Insets(0, 0, 0, 0);
        // gbc_label_39.gridx = 1;
        // gbc_label_39.gridy = 10;
        // panel.add(casella9.build(), gbc_label_39);
        //
        // IBoxGraphic casella10;
        // final GridBagConstraints gbc_label_40 = new GridBagConstraints();
        // casella10 = new CardGraphicBoxProva("Via Gialli", Color.RED,
        // 15000,Position.SOUTH);
        // gbc_label_40.insets = new Insets(0, 0, 0, 0);
        // gbc_label_40.gridx = 0;
        // gbc_label_40.gridy = 10;
        // panel.add(casella10.build(), gbc_label_40);

        panelM.add(panel, BorderLayout.CENTER);
        panelM.setVisible(true);

        return panel;
    }

}
