package it.unibo.monopoli.model.mainunits;

import it.unibo.monopoli.model.cards.Card;

/**
 * This is the enum of all the {@link Card}s' position.
 *
 */
public enum CardsId {

    CARD1(1),
    CARD2(2),
    CARD3(3),
    CARD4(4),
    CARD5(5),
    CARD6(6),
    CARD7(7),
    CARD8(8),
    CARD9(9),
    CARD10(10),
    CARD11(11),
    CARD12(12),
    CARD13(13),
    CARD14(14),
    CARD15(15),
    CARD16(16),
    CARD17(17),
    CARD18(18),
    CARD19(19),
    CARD20(20),
    CARD21(21),
    CARD22(22),
    CARD23(23),
    CARD24(24),
    CARD25(25),
    CARD26(26),
    CARD27(27),
    CARD28(28),
    CARD29(29),
    CARD30(30),
    CARD31(31),
    CARD32(32);
    
    private final int value;
    
    CardsId(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
}
