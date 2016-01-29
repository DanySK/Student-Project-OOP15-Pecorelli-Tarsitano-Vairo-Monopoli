package it.unibo.monopoli.model.mainunits;

import it.unibo.monopoli.model.table.Box;

/**
 * This is the enum of all the {@link Box}es' position.
 *
 */
public enum BoxesPositions {
    START_POSITION(0),
    PRISON_POSITION(10),
    NEUTRAL_AREA_POSITION(20),
    POLICE_POSITION(30),
    FIRST_CHANCE_POSITION(7),
    SECOND_CHANCE_POSITION(22),
    THIRD_CHANCE_POSITION(36),
    FIRST_COMMUNITY_CHEST_POSITION(2),
    SECOND_COMMUNITY_CHEST_POSITION(17),
    THIRD_COMMUNITY_CHEST_POSITION(33),
    INCOME_TAX_POSITION(4),
    SUPER_TAX_POSITION(38),

    OWNERSHIP_N_1(0),
    OWNERSHIP_N_2(1),
    OWNERSHIP_N_3(2),
    OWNERSHIP_N_4(3),
    OWNERSHIP_N_5(4),
    OWNERSHIP_N_6(5),
    OWNERSHIP_N_7(6),
    OWNERSHIP_N_8(7),
    OWNERSHIP_N_9(8),
    OWNERSHIP_N_10(9),
    OWNERSHIP_N_11(10),
    OWNERSHIP_N_12(11),
    OWNERSHIP_N_13(12),
    OWNERSHIP_N_14(13),
    OWNERSHIP_N_15(14),
    OWNERSHIP_N_16(15),
    OWNERSHIP_N_17(16),
    OWNERSHIP_N_18(17),
    OWNERSHIP_N_19(18),
    OWNERSHIP_N_20(19),
    OWNERSHIP_N_21(20),
    OWNERSHIP_N_22(21),
    OWNERSHIP_N_23(22),
    OWNERSHIP_N_24(23),
    OWNERSHIP_N_25(24),
    OWNERSHIP_N_26(25),
    OWNERSHIP_N_27(26),
    OWNERSHIP_N_28(27);

    private final int pos;

    BoxesPositions(final int position) {
        this.pos = position;
    }

    /**
     * Returns the position in the game's table of the specific {@link Box}.
     * 
     * @return the position of the {@link Box}
     */
    public int getPos() {
        return this.pos;
    }

}
