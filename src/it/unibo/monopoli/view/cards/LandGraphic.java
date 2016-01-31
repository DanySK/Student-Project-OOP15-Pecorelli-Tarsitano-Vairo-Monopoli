package it.unibo.monopoli.view.cards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.view.C;
import it.unibo.monopoli.view.JShape;
import it.unibo.monopoli.view.JShape.Shapes;
import it.unibo.monopoli.view.cards.IBoxGraphic.Position;

public class LandGraphic extends AbstractGraphicCard {

    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Land land;
    private int id;

    public LandGraphic(Land land, Position pos, int id) {
        super(land, pos);
        this.land = land;
        this.id = id;
    }

    @Override
    public JPanel build() {
        RotatedPanel card = getRotatedPanel();
        card.setPreferredSize(new Dimension(card.getDim()));
        card.setLayout(new GridLayout(4, 1));

        JLabel colorP = new JLabel();
        colorP.setOpaque(true);
        colorP.setBackground(land.getColor());
        card.add(colorP);

        JLabel nameP = new JLabel(land.getName());
        nameP.setFont(new Font("Times New Roman", Font.BOLD, 10));
        nameP.setPreferredSize(new Dimension(card.getDim().width,20));
        card.add(nameP);

        card.add(emptyP);

        JLabel valueP = new JLabel("" + land.getContract().getCost());
        card.add(valueP);

        

        card.setBorder(border);
        card.setVisible(true);

        card.validate();
        return card;

    }

    // non ho bisogno della mappa perchè tolgo sempre l'ultima
    private LinkedList<JShape> houses = new LinkedList<>();
    public void addHouse(final Player p) {
        int id = p.getPawn().getID(); // prendo l'id del colore della pedina
        Color c = C.COLORS[id];
        JShape house = new JShape(c); // creo la pedina
        
        houses.add(house); // aggiungo pedina alla mappa
//        pannellocasa.add(house); // disegno la pedina (aggiungendola al pannello)
//        pannellocasa.validate();
    }

    public void removeHouse(Player p) {
        JShape h = houses.getLast();
        emptyP.remove(h); // rimuovo la pedina dal pannello
        houses.removeLast();
    } 
}
