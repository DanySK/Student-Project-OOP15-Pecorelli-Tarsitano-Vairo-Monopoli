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

public enum CommunityChestCards {

    CARD1("RECEIVE DOCTOR'S BILL, PAY $50", 17),
    CARD2("PAY SCHOOL FEES OF OUR CHILDREN: $50", 18),
    CARD3("INHERITED $100 FROM A UNCLE", 19, new ToBePaid(100)),
    CARD4("GET OUT FREE OF JAIL. YOU CAN KEEP THIS CARD AND USE IT WHEN YOU WANT TO", 20),
    CARD5("PAY HOSPITAL'S BILL. PAY $100", 21),
    CARD6("FOR THE SALE OF A STOCK OF PRODUCTS YOU OBTAIN $50", 22, new ToBePaid(50)),
    CARD7("GO DIRECTLY TO JAIL WITHOUT PASSING FROM 'GO'", 23),
    CARD8("PAY CONTRIBUTIONS TO IMPROVE THE ROADS. YOU HAVE TO PAY $40 FOR EACH HOME AND $115 FOR EACH HOTEL THAT YOU OWN", 24),
    CARD9("IS YOUR BIRTHDAY. EACH PLAYER GIVES YOU $10", 25),
    CARD10("GO TO BOX 'GO' AND TAKE $" + Start.getMuchToPick(), 26),
    CARD11("GET $25 FOR YOUR ADVICE", 27, new ToBePaid(25)),
    CARD12("BANK RECOGNIZES AN ERROR IN YOUR STATEMENT. COLLECT $200", 28, new ToBePaid(200)),
    CARD13("YOU WON THE SECOND PRIZE IN A BEAUTY CONTEST. COLLECT $10", 29, new ToBePaid(10)),
    CARD14("MATURANO THE COUPONS OF YOUR ACTITONS. COLLECT $100", 30, new ToBePaid(100)),
    CARD15("FEE INCOME WAS REFUNDED. GET $20", 31, new ToBePaid(20)),
    CARD16("MATURANO INTEREST ON YOUR LIFE INSURANCE: COLLECT $100", 32, new ToBePaid(100));

    private final String description;
    private final int cardId;
    private final Optional<List<Action>> actions;

    CommunityChestCards(final String description, final int id, final Action... actions) {
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
