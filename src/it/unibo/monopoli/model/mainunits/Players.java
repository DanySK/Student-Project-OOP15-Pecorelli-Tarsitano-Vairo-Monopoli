package it.unibo.monopoli.model.mainunits;

public enum Players {

    TWO_PLAYERS(2, 350, 7), 175 / 7 = 25
    THREE_PLAYERS(3, 300, 6), 100/ 6 = 
    FOUR_PLAYERS(4, 250, 5),
    FIVE_PLAYERS(5, 200, 4),
    SIX_PLAYERS(6, 150, 3);

    private final int nPlayers;
    private final int money;
    private final int nOwnerships;

    Players(final int nPlayers, final int money, final int nOwnerships) {
        this.nPlayers = nPlayers;
        this.money = money;
        this.nOwnerships = nOwnerships;
    }

    public int getNOfPlayers() {
        return this.nPlayers;
    }

    public int getMoneyForEachPlayer() {
        return this.money;
    }

    public int getNOfOwnerships() {
        return this.nOwnerships;
    }

}
