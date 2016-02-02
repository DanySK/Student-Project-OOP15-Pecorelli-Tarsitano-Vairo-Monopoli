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
    private   Map<Player, JShape> pawns;
    private  Position pos;
    private  Box card;
    private static final Dimension DIMENSION = new Dimension(60, 60);

    /**
     * panel in which I manage the checkers, the reference to this panel Here
     * because it is in this class that I manage the pieces.
     */
    protected JPanel emptyP;

    /**
     *Builder. 
     * @param pos
     * @param card
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

  
    

    @Override
    public void addPawn(final Player p) {
        int id = p.getPawn().getID(); // prendo l'id del colore della pedina
        Color c = C.cl.get(id);
        JShape pawn = new JShape(c); // creo la pedina
        pawns.put(p, pawn); // aggiungo pedina alla mappa
        emptyP.add(pawn); // disegno la pedina (aggiungendola al pannello)
        emptyP.validate();
        // System.out.println(p.getName());
        // System.out.println("AddPawn: " + pawns.pawns.keySet());
        // System.out.println("Key: " + pawns.get(p).getName());
    }

    @Override
    public void removePawn(Player p) {
        JShape pawn = pawns.get(p); // prendo la pedina corrispondente al
        pawn.setVisible(false); // giocatore
        emptyP.remove(pawn); // rimuovo la pedina dal pannello
        pawns.remove(p); // rimuovo il riferimento alla pedina dalla mappa di
                         // quel giocatore
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
