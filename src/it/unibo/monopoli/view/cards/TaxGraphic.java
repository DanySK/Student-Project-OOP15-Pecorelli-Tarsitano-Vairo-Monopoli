package it.unibo.monopoli.view.cards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.model.table.Tax;
import it.unibo.monopoli.view.JShape.Shapes;
import it.unibo.monopoli.view.cards.IBoxGraphic.Position;

public class TaxGraphic extends AbstractGraphicCard {

    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Tax tax;
    private int id;

    public TaxGraphic(Tax tax, Position pos, int id) {
        super(tax, pos);
        this.tax = tax;
        this.id = id;

    }

    @Override
    public JPanel build() {
        RotatedPanel card = getRotatedPanel();
        card.setPreferredSize(new Dimension(card.getDim()));
        card.setLayout(new GridLayout(4, 1));

        JLabel nameP = new JLabel(tax.getName());
        card.add(nameP);

        card.add(emptyP);

        JLabel valueP = new JLabel("" + tax.getCost());
        card.add(valueP);

        
        card.setBorder(border);
        card.setVisible(true);

        return card;

    }

}
