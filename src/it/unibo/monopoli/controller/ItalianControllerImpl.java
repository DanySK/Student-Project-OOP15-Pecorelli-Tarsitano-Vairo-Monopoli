package it.unibo.monopoli.controller;

import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoli.model.actions.EvadeTaxes;
import it.unibo.monopoli.model.actions.GoToPrison;
import it.unibo.monopoli.model.actions.ToBePaid;
import it.unibo.monopoli.model.actions.ToDrawCards;
import it.unibo.monopoli.model.actions.ToPay;
import it.unibo.monopoli.model.mainunits.BoxesPositions;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.ClassicLandContract;
import it.unibo.monopoli.model.table.CompanysIncomeStrategy;
import it.unibo.monopoli.model.table.DecksBox;
import it.unibo.monopoli.model.table.Home;
import it.unibo.monopoli.model.table.Hotel;
import it.unibo.monopoli.model.table.ItalianNeutralArea;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.LandContract;
import it.unibo.monopoli.model.table.LandGroup;
import it.unibo.monopoli.model.table.LandIncomeStrategy;
import it.unibo.monopoli.model.table.Ownership;
import it.unibo.monopoli.model.table.Police;
import it.unibo.monopoli.model.table.Start;
import it.unibo.monopoli.model.table.Station;
import it.unibo.monopoli.model.table.StationIncomeStrategy;
import it.unibo.monopoli.model.table.TaxImpl;

public class ItalianControllerImpl {
    

    public List<Actions> getNextBoxsActions(final Box box, final Player player) {
        final List<Actions> actions = new LinkedList<>();
        actions.clear();
        if (!player.dicesAlreadyRolled()) {
            actions.add(Actions.ROLL_DICES);
            return actions;
        } else if (box instanceof Land) {
            final Land land = (Land) box;
            if (land.getOwner().equals(this.bank)) {
                this.toBuyOrToAuction(land, player, actions); //NON PIÙ AUCTION, ALLORA: BUY E ENDOFTURN!!!!!!!!!!!!!
            } else if (land.getOwner().equals(player)) {
                if (land.isMortgaged()) {
                    actions.add(Actions.REVOKE_MORTGAGE);
                } else if (player.getOwnerships().containsAll(land.getGroup().getMembers())
                        && ((LandGroup) land.getGroup()).canBuiling() && this.bank.getLeftBuilding().size() > 0
                        && player.getMoney() >= ((LandContract) land.getContract()).getCostForEachBuilding()
                        && !this.alreadyBuilt) {
                    this.bank.getLeftBuilding().forEach(b -> {
                        if ((((LandGroup) land.getGroup()).getBuildings().size() < 4 && b instanceof Home)
                                || (b instanceof Hotel)) {
                            actions.add(Actions.BUILD);
                        }
                    });
                }
                if (!((LandGroup) land.getGroup()).getBuildings().isEmpty()) {
                    actions.add(Actions.SELL_BUILDING);
                }
            } else {
                final int amount = ((ClassicLandContract) land.getContract()).getIncome(new LandIncomeStrategy(land));
                if (amount <= player.getMoney()) {
                    new ToPay(amount, player).play(player);
                    new ToBePaid(amount).play((Player) land.getOwner());
                    player.setDebts(true);
                } else {
                    this.notMuchMoney(player, actions);
                }
            }
        } else if (box instanceof Ownership) {
            final Ownership ownership = (Ownership) box;
            if (ownership.getOwner().equals(this.bank)) {
                this.toBuyOrToAuction(ownership, player, actions); //NON PIÙ AUCTION, ALLORA: BUY E ENDOFTURN!!!!!!!!!!!!!
            } else if (!ownership.getOwner().equals(player)) {// ERROREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
                final int amount = ownership.getContract().getIncome(ownership instanceof Station
                        ? new StationIncomeStrategy(ownership) : new CompanysIncomeStrategy(ownership, player));
                if (amount <= player.getMoney()) {
                    new ToPay(amount, player).play(player);
                    new ToBePaid(amount).play((Player) ownership.getOwner());
                    player.setDebts(true);
                } else {
                    this.notMuchMoney(player, actions);
                }
            } else if (ownership.getOwner().equals(player)) {//AGGIUNGIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
                if (ownership.isMortgaged()) {
                    actions.add(Actions.REVOKE_MORTGAGE);
                } else {
                    actions.add(Actions.MORTGAGE);
                    actions.add(Actions.SELL);
                }
            }
        } else {
            if (box instanceof Start) {
                new ToBePaid(Start.getMuchToPick()).play(player);
            }
            if (box instanceof Police) {
                new GoToPrison(this.boxes.get(PRISON_POSITION)).play(player);
            }
            if (box instanceof DecksBox) {
                new ToDrawCards(this.decks.get(box.getID() == FIRST_CHANCE_POSITION
                        || box.getID() == SECOND_CHANCE_POSITION || box.getID() == THIRD_CHANCE_POSITION ? 0 : 1))
                                .play(player);
            }
            if (box instanceof TaxImpl) {
                new EvadeTaxes(((TaxImpl) box).getCost(), ((ItalianNeutralArea) this.boxes.get(BoxesPositions.NEUTRAL_AREA_POSITION.getPos()))).play(player);
            }
            
            
            
            
            /*
             * SAREBBE MEGLIO FARE SOLO:
             * 
             *   if (box instanceof TaxImpl) {
             *       new EvadeTaxes(((TaxImpl) box).getCost(), ((ItalianNeutralArea) this.boxes.get(BoxesPositions.NEUTRAL_AREA_POSITION.getPos()))).play(player);
             *   } else {
             *       super.getNextCardsActions().......
             *   }
             */
            
            
            
            
            
            
            
        }
        if (player.dicesAlreadyRolled()) {
            actions.add(Actions.END_OF_TURN);
        }
//        if (box instanceof Ownership) {
//            if (!((Ownership) box).isMortgaged()) {
//                if (((Ownership) box).getOwner().equals(player)) {
//                    actions.add(Actions.MORTGAGE);
//                    actions.add(Actions.SELL);
//                }
//            }
//        }
        actions.add(Actions.END_OF_THE_GAME);
        return actions;
    }

    private void toBuyOrToAuction(final Ownership ownership, final Player player, final List<Actions> actions) {
        if (player.getMoney() >= ownership.getContract().getCost()) {
            actions.add(Actions.BUY);
        }
        // actions.add("Asta");
    }

    private void notMuchMoney(final Player player, final List<Actions> actions) {
        if (!player.getOwnerships().isEmpty()) {
            player.getOwnerships().stream().filter(o -> o.getGroup() instanceof LandGroup)
                    .filter(o -> ((LandGroup) o.getGroup()).getBuildings().size() > 0).forEach(o -> {
                        ((LandGroup) o.getGroup()).getBuildings().forEach(b -> actions.add(Actions.SELL_BUILDING));
                    });
            if (actions.isEmpty()) {
                player.getOwnerships().stream().forEach(o -> {  //PERCHÉ È ANCORA COMMENTATO?????????????????
                    // actions.add("Vendi");
                    // actions.add("Ipoteca");
                });
            }
            // } else {
            // this.gameOver();
        }
    }


}
