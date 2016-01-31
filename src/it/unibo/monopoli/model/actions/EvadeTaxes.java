package it.unibo.monopoli.model.actions;

import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.ItalianNeutralArea;

public class EvadeTaxes implements Action {

    private static final double TAX = 0.22;

    private final int amount;
    private final ItalianNeutralArea neutralArea;

    public EvadeTaxes(final int amount, final ItalianNeutralArea neutralArea) {
        this.amount = amount - ((int) Math.floor(amount * TAX));
        if (amount <= 0) {
            throw new IllegalArgumentException("Only positive amount different of zero!");
        }
        this.neutralArea = neutralArea;
    }

    @Override
    public void play(final Player player) {
        if (this.amount > player.getMoney()) {
            throw new IllegalArgumentException();
        }
        player.setMoney(player.getMoney() - amount);
        this.neutralArea.setDirtyMoney(this.neutralArea.getDirtyMoney() + this.amount);
    }

}
