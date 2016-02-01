package it.unibo.monopoli.view.cards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.view.JShape.Shapes;
import it.unibo.monopoli.view.cards.IBoxGraphic.Position;
/**
 * 
 * 
 *
 */
public class BoxGraphic extends AbstractGraphicCard {

    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Box box;
    private int id;
/**
 * 
 * @param box
 * @param pos
 */
    public BoxGraphic(Box box, Position pos, int id) {
        super(box, pos);
        this.box = box;
        this.id = id;

    }

    @Override
    public JPanel build() {
        JPanel card = getRotatedPanel();
        card.setPreferredSize(new Dimension(55, 55));
        card.setLayout(new GridLayout(2, 1));

        JLabel nameP = new JLabel("<html>"+box.getName()+"</html>");
        card.add(nameP);

        card.add(emptyP);

        card.setBorder(border);
        card.setVisible(true);

        return card;

    }

}
