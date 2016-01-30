package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import it.unibo.monopoli.controller.Controller;

public class East extends JPanel {

    public East(final Controller controller) {

        // setPreferredSize(new Dimension(370, 415));
        this.setLayout(new BorderLayout());

        final JPanel panelBank = new JPanel();
        // panelBank.setPreferredSize(new Dimension(10, 100));
        panelBank.setLayout(new BorderLayout());
        panelBank.add(new PlayerGraphic("Bank", 10000).build(), BorderLayout.CENTER);
        this.add(panelBank, BorderLayout.NORTH);
        int j = InizializedPlayer.getMap().size();
        final JPanel panelPlayer = new JPanel();
        panelPlayer.setLayout(new GridLayout(j, 1));
        
        
        
        for (int i = 0; i < InizializedPlayer.getMap().size(); i++) {
            panelPlayer.add(new PlayerGraphic(controller.getPlayers(), i).build());
        }
        this.add(new JScrollPane(panelPlayer), BorderLayout.CENTER);

    }

    public JPanel build() {
        // final JPanel panelE = new JPanel();
        // panelE.setLayout(new GridLayout(8 /* mettere i giocatori */, 1));
        //
        // final JScrollPane scrollPane = new JScrollPane(panelE);
        // scrollPane.setViewportView(panelE);
        // scrollPane.setBorder(border);
        // //scrollPane.setPreferredSize(new Dimension(370, 0));
        // scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        // scrollPane.add(new JLabel("Jscroll"));
        // scrollPane.setVisible(true);
        // for (int i = 0; i < 8; i++) {
        // //JPanel player = new Player();
        // scrollPane.add(new Player("Laura",10000).build());
        // }
        //
        //
        return this;
        // return null;
    }
}
