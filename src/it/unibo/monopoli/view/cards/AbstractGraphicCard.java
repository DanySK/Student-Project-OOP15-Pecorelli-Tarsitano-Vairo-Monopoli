package it.unibo.monopoli.view.cards;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.view.C;
import it.unibo.monopoli.view.JShape;

/**
 *
 *
 */
public abstract class AbstractGraphicCard implements IBoxGraphic {
    private Map<Player, JShape> pawns;
    private Position pos = null;
    private Box card;
    /**
     * panel in cui gestisco le pedine, il riferimento a questo pannello si
     * trova qui perchè è in questa classe che gestisco le pedine
     */
    protected JPanel emptyP;

    /**
     * 
     * @param pos
     */
    public AbstractGraphicCard(Box card, Position pos) {
        this.pos = pos;
        pawns = new HashMap<Player, JShape>();
        emptyP = new JPanel();
        this.card = card;
    }

    /**
     * 
     */
    @Override
    public Position getPosition() {
        return pos;
    }
/**
 * 
 * @return
 */
    protected RotatedPanel getRotatedPanel() {
        Dimension dim = new Dimension(C.BOX_WIDTH, C.BOX_HEIGHT);
        if (pos == Position.NORTH) {
            dim = new Dimension(C.BOX_WIDTH, C.BOX_HEIGHT);
            return new RotatedPanel(180, dim);
        } else if (pos == Position.EAST) {
            dim = new Dimension(C.BOX_HEIGHT, C.BOX_WIDTH);
            return new RotatedPanel(-90, dim);
        } else if (pos == Position.WEST) {
            dim = new Dimension(C.BOX_HEIGHT, C.BOX_WIDTH);
            return new RotatedPanel(90, dim);
        } else {
            dim = new Dimension(C.BOX_WIDTH, C.BOX_HEIGHT);
            return new RotatedPanel(0, dim);

        }
    }

    @Override
    public void addPawn(final Player p) {
        int id = p.getPawn().getID(); // prendo l'id del colore della pedina
        Color c = C.COLORS[id];
        JShape pawn = new JShape(c); // creo la pedina
        pawns.put(p, pawn); // aggiungo pedina alla mappa
        emptyP.add(pawn); // disegno la pedina (aggiungendola al pannello)
        emptyP.validate();
    }

    @Override
    public void removePawn(Player p) {
        JShape pawn = pawns.get(p); // prendo la pedina corrispondente al
        pawn.setVisible(false);                            // giocatore
        emptyP.remove(pawn);// rimuovo la pedina dal pannello
        
        pawns.remove(p); // rimuovo il riferimento alla pedina dalla mappa
        
        emptyP.validate();
    }
    

    @Override
    public String getName() {
        return card.getName();
    }

    @Override
    public int getID() {
        return card.getID();
    }
}
