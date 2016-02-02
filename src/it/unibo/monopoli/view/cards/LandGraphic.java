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
/**
 * 
 * class that is the graphic implementation of the LandtBox.
 *
 */
public class LandGraphic extends AbstractGraphicCard {

    private  Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private  Land land;
    private JPanel colorP;
/**
 * Builder.
 * @param land
 * @param pos
 * @param id
 */
    public LandGraphic(Land land, Position pos, int id) {
        super(land, pos);
        this.land = land;
        colorP = new JPanel();
    }

    @Override
    public JPanel build() {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(C.DIM));
        card.setLayout(new GridLayout(4, 1));

        card.add(colorP);
        colorP.setOpaque(true);
        colorP.setBackground(land.getColor());
         JLabel nameP = new JLabel("<html>" + land.getName() + "</html>");
        nameP.setFont(new Font("Times New Roman", Font.BOLD, 10));
         card.add(nameP);

        card.add(emptyP);

        JLabel valueP = new JLabel("" + land.getContract().getCost());
        card.add(valueP);

        card.setBorder(border);
        card.setVisible(true);

        card.validate();
        return card;

    }

    private LinkedList<JShape> houses = new LinkedList<>();
    public void addHouse(final Player p) {
        int id = p.getPawn().getID(); // prendo l'id del colore della pedina
        Color c = C.cl.get(id);
        JShape house = new JShape(c); // creo la pedina
        
       houses.add(house); // aggiungo pedina alla mappa
        colorP.add(house);
        colorP.validate();
    }
/**
 * 
 * @param p
 */
    public void removeHouse(Player p) {
        JShape h = houses.getLast();
        colorP.remove(h);
        h.setVisible(false);// rimuovo la pedina dal pannello
        houses.remove(h);
      colorP.doLayout();
        colorP.repaint();
    } 
}
