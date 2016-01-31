package it.unibo.monopoli.view.cards;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.view.JShape;
import it.unibo.monopoli.view.RotatedPanel;

/**
 *
 *
 */
public abstract class AbstractGraphicCard implements IBoxGraphic {
    private Map<Player, JShape> pawns;
    private Position pos = null;
    /**
     * panel in cui gestisco le pedine, il riferimento a questo pannello si
     * trova qui perchè è in questa classe che gestisco le pedine
     */
    protected JPanel emptyP;

    /**
     * 
     * @param pos
     */
    public AbstractGraphicCard(Position pos) {
        this.pos = pos;
        pawns = new HashMap<Player, JShape>();
        emptyP = new JPanel();
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
    protected JPanel getRotatedPanel() {
        Dimension dim = null;
        if (pos == Position.NORTH) {
            dim = new Dimension(50, 70);
            return new RotatedPanel(180, dim);
        } else if (pos == Position.EAST) {
            dim = new Dimension(70, 50);
            return new RotatedPanel(-90, dim);
        } else if (pos == Position.WEST) {
            dim = new Dimension(70, 50);
            return new RotatedPanel(90, dim);
        } else {
            dim = new Dimension(50, 70);
            return new RotatedPanel(0, dim);

        }
    }

    @Override
    public void addPawn(final Player p) {
        Color c = null;
        int id = p.getPawn().getID(); // prendo l'id del colore della pedina
        // TODO
        if (id == 0) {
            c = Color.green;
        }

        JShape pawn = new JShape(c); // creo la pedina
        pawns.put(p, pawn); // aggiungo pedina alla mappa
        emptyP.add(pawn); // disegno la pedina (aggiungendola al pannello)
    }

    @Override
    public void removePawn(Player p) {
        JShape pawn = pawns.get(p); // prendo la pedina corrispondente al
                                    // giocatore
        emptyP.remove(pawn); // rimuovo la pedina dal pannello
        pawns.remove(p); // rimuovo il riferimento alla pedina dalla mappa
    }
}
