package it.unibo.monopoli.controller;

public enum Actions {
    ROLL_DICES("ROLL DICES"),
    END_OF_TURN("END OF TURN"),
    BUY("BUY"),
    SELL("SELL"),
    BUILD("BUILD"),
    SELL_BUILDING("SELL BUILDING"),
    MORTGAGE("MORTGAGE"),
    REVOKE_MORTGAGE("REVOKE MORTGAGE"),
    END_OF_THE_GAME("END OF THE GAME");

    private final String text;

    private Actions(final String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

}
