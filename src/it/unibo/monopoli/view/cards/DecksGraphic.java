package it.unibo.monopoli.view.cards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.model.table.DecksBox;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.Ownership;
import it.unibo.monopoli.view.RotatedPanel;
import it.unibo.monopoli.view.JShape.Shapes;
import it.unibo.monopoli.view.cards.IBoxGraphic.Position;

public class DecksGraphic extends AbstractGraphicCard {

    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private DecksBox deck;
    private int id;

    public DecksGraphic(DecksBox deck, Position pos, int id) {
        super(pos);
        this.deck = deck;
        this.id = id;
    }

    @Override
    public JPanel build() {
        JPanel card = getRotatedPanel();
        card.setPreferredSize(new Dimension(RotatedPanel.getDim()));
        card.setLayout(new GridLayout(2, 1));

        JLabel nameP = new JLabel(deck.getName());
        card.add(nameP);

        card.add(emptyP);

        card.setBorder(border);
        card.setVisible(true);

        return card;

    }

}
