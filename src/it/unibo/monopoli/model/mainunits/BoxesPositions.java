package it.unibo.monopoli.model.mainunits;

import it.unibo.monopoli.model.table.Box;

/**
 * This is the enum of all the {@link Box}es' position.
 *
 */
public enum BoxesPositions {
    OWNERSHIP1_POSITION(1),
    OWNERSHIP2_POSITION(3),
    OWNERSHIP3_POSITION(5),
    OWNERSHIP4_POSITION(6),
    OWNERSHIP5_POSITION(8),
    OWNERSHIP6_POSITION(9),
    OWNERSHIP7_POSITION(11),
    OWNERSHIP8_POSITION(12),
    OWNERSHIP9_POSITION(13),
    OWNERSHIP10_POSITION(14),
    OWNERSHIP11_POSITION(15),
    OWNERSHIP12_POSITION(16),
    OWNERSHIP13_POSITION(18),
    OWNERSHIP14_POSITION(19),
    OWNERSHIP15_POSITION(21),
    OWNERSHIP16_POSITION(23),
    OWNERSHIP17_POSITION(24),
    OWNERSHIP18_POSITION(25),
    OWNERSHIP19_POSITION(26),
    OWNERSHIP20_POSITION(27),
    OWNERSHIP21_POSITION(28),
    OWNERSHIP22_POSITION(29),
    OWNERSHIP23_POSITION(31),
    OWNERSHIP24_POSITION(32),
    OWNERSHIP25_POSITION(34),
    OWNERSHIP26_POSITION(35),
    OWNERSHIP27_POSITION(37),
    OWNERSHIP28_POSITION(39),

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

    INDEX_0(0),
    INDEX_1(0),
    INDEX_2(0),
    INDEX_3(0),
    INDEX_4(0),
    INDEX_5(0),
    INDEX_6(0),
    INDEX_7(0),
    INDEX_8(0),
    INDEX_9(0),
    INDEX_10(0),
    INDEX_11(0),
    INDEX_12(0),
    INDEX_13(0),
    INDEX_14(0),
    INDEX_15(0),
    INDEX_16(0),
    INDEX_17(0),
    INDEX_18(0),
    INDEX_19(0),
    INDEX_20(0),
    INDEX_21(0),
    INDEX_22(0),
    INDEX_23(0),
    INDEX_24(0),
    INDEX_25(0),
    INDEX_26(0),
    INDEX_27(0),
    INDEX_28(0),
    INDEX_29(0),
    INDEX_30(0),
    INDEX_31(0),
    INDEX_32(0),
    INDEX_33(0),
    INDEX_34(0),
    INDEX_35(0),
    INDEX_36(0),
    INDEX_37(0),
    INDEX_38(0),
    INDEX_39(0),

    GROUP_0(0),
    GROUP_1(1),
    GROUP_2(2),
    GROUP_3(3),
    GROUP_4(4),
    GROUP_5(5),
    GROUP_6(6),
    GROUP_7(7),
    GROUP_8(8),
    GROUP_9(9);

//    OWNERSHIP_N_1(0),
//    OWNERSHIP_N_2(1),
//    OWNERSHIP_N_3(2),
//    OWNERSHIP_N_4(3),
//    OWNERSHIP_N_5(4),
//    OWNERSHIP_N_6(5),
//    OWNERSHIP_N_7(6),
//    OWNERSHIP_N_8(7),
//    OWNERSHIP_N_9(8),
//    OWNERSHIP_N_10(9),
//    OWNERSHIP_N_11(10),
//    OWNERSHIP_N_12(11),
//    OWNERSHIP_N_13(12),
//    OWNERSHIP_N_14(13),
//    OWNERSHIP_N_15(14),
//    OWNERSHIP_N_16(15),
//    OWNERSHIP_N_17(16),
//    OWNERSHIP_N_18(17),
//    OWNERSHIP_N_19(18),
//    OWNERSHIP_N_20(19),
//    OWNERSHIP_N_21(20),
//    OWNERSHIP_N_22(21),
//    OWNERSHIP_N_23(22),
//    OWNERSHIP_N_24(23),
//    OWNERSHIP_N_25(24),
//    OWNERSHIP_N_26(25),
//    OWNERSHIP_N_27(26),
//    OWNERSHIP_N_28(27);

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
