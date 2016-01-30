package it.unibo.monopoli.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.view.JShape.Shapes;

public class LandGraphic extends AbstractGraphicCard {

    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Land land;
    private int id;

    public LandGraphic(Land land, Position pos, int id) {
        super(pos);
        this.land = land;
        this.id = id;
    }

    @Override
    public JPanel build() {
        JPanel card = getRotatedPanel();
        card.setPreferredSize(new Dimension(RotatedPanel.getDim()));
        card.setLayout(new GridLayout(4, 1));

        JLabel colorP = new JLabel();
        colorP.setOpaque(true);
        colorP.setBackground(land.getColor());
        card.add(colorP);

        JLabel nameP = new JLabel(land.getName());
        nameP.setFont(new Font("Times New Roman", Font.BOLD, 10));
        nameP.setPreferredSize(new Dimension(RotatedPanel.getDim().width,20));
        card.add(nameP);

        JPanel emptyP = new JPanel();
        card.add(emptyP);

        JLabel valueP = new JLabel("" + land.getContract().getCost());
        card.add(valueP);

        

        card.setBorder(border);
        card.setVisible(true);

        return card;

    }

    public void addShape(JShape shape) {
        addShape(shape);
    }

}
