package it.unibo.monopoli.model.cards;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.actions.ClassicDicesStrategy;
import it.unibo.monopoli.model.actions.MoveUpTo;
import it.unibo.monopoli.model.actions.ToBePaid;
import it.unibo.monopoli.model.actions.ToRollDices;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Start;

/**
 * This is the enum of all the {@link Card}s' position.
 *
 */
public enum ChanceCards {

    CARD1("TAKE 3 STEPS BACK (WITH BEST WISHES)", 1, MoveUpTo.takeSteps(-3)),
    CARD2("GO TO PARK LANE: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(), 2),
    CARD3("ADVANCE TO THE NEAREST STATION: IF IT'S FREE, YOU CAN BUY IT; IF IT IS OWNED BY ANOTHER PLAYER, PAY HIM TWICE THE PRICE THAT MATTER",
            3, MoveUpTo.theNearestStation()),
    CARD4("GET OUT FREE OF JAIL. YOU CAN KEEP THIS CARD AND USE IT WHEN YOU WANT TO", 4),
    CARD5("FINE FOR SPEEDING. PAY $20", 5),
    CARD6("THE BANK WILL YOU PAY A BONUS OF $50", 6, new ToBePaid(50)),
    CARD7("GO DIRECTLY TO JAIL WITHOUT PASSING FROM 'GO'", 7),
    CARD8("PERFORM MAINTENANCE WORK ON ALL OUR BUILDINGS. YOU HAVE TO PAY $25 FOR EACH HOME AND $100 FOR EACH HOTEL THAT YOU OWN", 8),
    CARD9("YOU HAVE BEEN PROMOTED TO THE PRESIDENCY OF THE BOARD OF DIRECTORS. YOU HAVE TO PAY 50 TO ANY PLAYER", 9),
    CARD10("GO TO BOX 'GO' AND TAKE $" + Start.getMuchToPick(), 10),
    CARD11("GO TO THE FENCHURCH ST. STATION: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(),11),
    CARD12("ADVANCE TO THE NEAREST STATION: IF IT'S FREE, YOU CAN BUY IT; IF IT IS OWNED BY ANOTHER PLAYER, PAY HIM TWICE THE PRICE THAT MATTER",
            12, MoveUpTo.theNearestStation()),
    CARD13("GO TO THE TRAFALGAR SQUARE: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(), 13),
    CARD14("GO TO THE WHITEHALL: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(), 14),
    CARD15("ADVANCE TO THE NEAREST STATION: IF IT'S FREE, YOU CAN BUY IT; IF IT IS OWNED BY ANOTHER PLAYER, PAY THE OWNER 10 TIMES THE NUMBER RELEASED WITH DICES",
            15, MoveUpTo.theNearestStation()),
    CARD16("MATURANO THE COUPONS OF YOUR REAL ESTATE FUNDS: COLLECT $150", 16, new ToBePaid(150));

    private final String description;
    private final int cardId;
    private final Optional<List<Action>> actions;

    ChanceCards(final String description, final int id, final Action... actions) {
        this.description = description;
        this.cardId = id;
        this.actions = Optional.of(Arrays.asList(actions));
    }

    public int getID() {
        return this.cardId;
    }

    public String getDescription() {
        return this.description;
    }

    public Optional<List<Action>> getActions() {
        return this.actions;
    }
}
