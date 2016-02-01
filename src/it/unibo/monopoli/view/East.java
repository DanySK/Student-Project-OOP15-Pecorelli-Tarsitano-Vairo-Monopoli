package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import it.unibo.monopoli.controller.Controller;

public class East extends JPanel {
    private  Map<String, PlayerGraphic> players;

    public East(final Controller controller) {
        
        this.players = new HashMap<>();

        // setPreferredSize(new Dimension(370, 415));
        this.setLayout(new BorderLayout());

        final JPanel panelBank = new JPanel();
        // panelBank.setPreferredSize(new Dimension(10, 100));
        panelBank.setLayout(new BorderLayout());
        panelBank.add(new PlayerGraphic(controller).buildBank(controller.getAllBoxes(),controller.getBank()), BorderLayout.CENTER);
        this.add(panelBank, BorderLayout.NORTH);
        int j = InizializedPlayer.getMap().size();
        System.out.println("j: " + j);
        final JPanel panelPlayer = new JPanel();
        panelPlayer.setLayout(new GridLayout(j, 1));
        
        
        System.out.println("Size.map: " + InizializedPlayer.getMap().size());
        for (int i = 0; i < InizializedPlayer.getMap().size(); i++) {
            PlayerGraphic graphic = new PlayerGraphic(controller); 
            panelPlayer.add(graphic.build(controller.getAllBoxes(),controller.getPlayers(), i));
            this.players.put(controller.getPlayers().get(i).getName(), graphic);
            System.out.println("***Mappa***" + this.players.get(controller.getPlayers().get(i).getName()));

        }
        this.add(new JScrollPane(panelPlayer), BorderLayout.CENTER);

    }
    
    public Map<String, PlayerGraphic> getMap(){
        return this.players;
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
