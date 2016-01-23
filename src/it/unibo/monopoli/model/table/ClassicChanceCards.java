package it.unibo.monopoli.model.table;

import it.unibo.monopoli.model.actions.MoveUpTo;
import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Chance;
import it.unibo.monopoli.model.cards.ClassicCard;
import it.unibo.monopoli.model.mainunits.ClassicStrategy;
import it.unibo.monopoli.model.mainunits.GameVersion;

/**
 * This enum represent all the {@link Chance}'s {@link Card}s in the classic {@link GameVersion} of Monopoly.
 *
 */
public enum ClassicChanceCards {
    CARD1(new ClassicCard("TAKE 3 STEPS BACK (WITH BEST WISHES)", MoveUpTo.takeSteps(-3))),
    CARD2(new ClassicCard("GO TO MAYFAIR: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(), MoveUpTo.moveUpToBox(new ClassicStrategy().getBoxes().get(39))),
    CARD3
    CARD4
    CARD5
    CARD6
    CARD7
    CARD8
    CARD9
    CARD10
    CARD11
    CARD12
    CARD13
    CARD14
    CARD15
    CARD16;
    
    private final Card card;
    
    private ClassicChanceCards (final Card card) {
        this.card = card;
    }
    
    public Card getCard() {
        return this.card;
    }
    
    chance.addCard(new ClassicCard(chance, "TAKE 3 STEPS BACK (WITH BEST WISHES)", MoveUpTo.takeSteps(-3)));
    chance.addCard(new ClassicCard(chance, "GO TO MAYFAIR: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(), MoveUpTo.moveUpToBox(this.allBoxes.get(39))));
    chance.addCard(new ClassicCard(chance, "TAKE 3 STEPS BACK (WITH BEST WISHES)", new MoveUpTo(-3)));
    chance.addCard(new ClassicCard(chance, "TAKE 3 STEPS BACK (WITH BEST WISHES)", new MoveUpTo(-3)));
    chance.addCard(new ClassicCard(chance, "TAKE 3 STEPS BACK (WITH BEST WISHES)", new MoveUpTo(-3)));
    chance.addCard(new ClassicCard(chance, "TAKE 3 STEPS BACK (WITH BEST WISHES)", new MoveUpTo(-3)));
    chance.addCard(new ClassicCard(chance, "TAKE 3 STEPS BACK (WITH BEST WISHES)", new MoveUpTo(-3)));
    chance.addCard(new ClassicCard(chance, "TAKE 3 STEPS BACK (WITH BEST WISHES)", new MoveUpTo(-3)));
    chance.addCard(new ClassicCard(chance, "TAKE 3 STEPS BACK (WITH BEST WISHES)", new MoveUpTo(-3)));
    chance.addCard(new ClassicCard(chance, "TAKE 3 STEPS BACK (WITH BEST WISHES)", new MoveUpTo(-3)));
    chance.addCard(new ClassicCard(chance, "TAKE 3 STEPS BACK (WITH BEST WISHES)", new MoveUpTo(-3)));
    chance.addCard(new ClassicCard(chance, "TAKE 3 STEPS BACK (WITH BEST WISHES)", new MoveUpTo(-3)));
    chance.addCard(new ClassicCard(chance, "TAKE 3 STEPS BACK (WITH BEST WISHES)", new MoveUpTo(-3)));
    chance.addCard(new ClassicCard(chance, "TAKE 3 STEPS BACK (WITH BEST WISHES)", new MoveUpTo(-3)));
    chance.addCard(new ClassicCard(chance, "TAKE 3 STEPS BACK (WITH BEST WISHES)", new MoveUpTo(-3)));
    chance.addCard(new ClassicCard(chance, "TAKE 3 STEPS BACK (WITH BEST WISHES)", new MoveUpTo(-3)));


}
