package it.unibo.monopoli.view.cards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.model.table.Ownership;
import it.unibo.monopoli.view.RotatedPanel;
import it.unibo.monopoli.view.JShape.Shapes;
import it.unibo.monopoli.view.cards.IBoxGraphic.Position;

public class OwnershipGraphic extends AbstractGraphicCard {

    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Ownership own;
    private int id;

    public OwnershipGraphic(Ownership own, Position pos, int id) {
        super(pos);
        this.own = own;
        this.id = id;

    }

    @Override
    public JPanel build() {
        JPanel card = getRotatedPanel();
        card.setPreferredSize(new Dimension(RotatedPanel.getDim()));
        card.setLayout(new GridLayout(4, 1));

        // JLabel colorP = new JLabel();
        // colorP.setOpaque(true);
        // colorP.setBackground(Color.white);
        // card.add(colorP);

        JLabel nameP = new JLabel(own.getName());
        card.add(nameP);

        card.add(emptyP);

        int i = this.own.getContract().getCost();
        JLabel valueP = new JLabel("" + i);
        card.add(valueP);

        card.setBorder(border);
        card.setVisible(true);

        return card;

    }

}
