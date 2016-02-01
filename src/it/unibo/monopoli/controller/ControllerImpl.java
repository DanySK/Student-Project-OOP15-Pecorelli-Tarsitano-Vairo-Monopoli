package it.unibo.monopoli.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.actions.EvadeTaxes;
import it.unibo.monopoli.model.actions.GoToPrison;
import it.unibo.monopoli.model.actions.ToBePaid;
import it.unibo.monopoli.model.actions.ToBuyProperties;
import it.unibo.monopoli.model.actions.ToDrawCards;
import it.unibo.monopoli.model.actions.ToMortgage;
import it.unibo.monopoli.model.actions.ToPay;
import it.unibo.monopoli.model.actions.ToRevokeMortgage;
import it.unibo.monopoli.model.actions.ToSellProperties;
import it.unibo.monopoli.model.actions.ToStealMoney;
import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.mainunits.Bank;
import it.unibo.monopoli.model.mainunits.BoxesPositions;
import it.unibo.monopoli.model.mainunits.ClassicPawn;
import it.unibo.monopoli.model.mainunits.ClassicPlayer;
import it.unibo.monopoli.model.mainunits.ClassicStrategy;
import it.unibo.monopoli.model.mainunits.GameStrategy;
import it.unibo.monopoli.model.mainunits.GameVersion;
import it.unibo.monopoli.model.mainunits.GameVersionImpl;
import it.unibo.monopoli.model.mainunits.ItalianStrategy;
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
import it.unibo.monopoli.view.InPlay;

/**
 * */
public class ControllerImpl implements Controller {

    private static final int PRISON_POSITION = 10;
    private static final int FIRST_CHANCE_POSITION = 7;
    private static final int SECOND_CHANCE_POSITION = 22;
    private static final int THIRD_CHANCE_POSITION = 36;

    private final List<Player> players;
    private Player actualPlayer;
    private GameVersion version;
    private int actualPosition;
    private List<Integer> lastDices;
    private Bank bank;
    private List<Box> boxes;
    private List<Deck> decks;
    private Optional<InPlay> view;
    private boolean alreadyBuilt;
    private List<Actions> actions;
    private GameStrategy strategy;

    /**
     * 
     */
    public ControllerImpl() {
        this.players = new LinkedList<>();
        this.view = Optional.empty();
    }

    @Override
    public Player getActualPlayer() {
        return this.actualPlayer;
    }

    @Override
    public int getActualPosition() {
        return this.actualPosition;
    }

    @Override
    public void addPlayer(final String name, final ClassicPawn pawn, final boolean isHuman) {
        this.players.add(new ClassicPlayer(name, pawn, isHuman));
    }

    @Override
    public void initializedVersion(final EVersion versionEnum) {
        switch (versionEnum) {
        case CLASSIC:
            this.strategy = new ClassicStrategy(this.players);
            this.version = new GameVersionImpl(this.strategy);
            break;
        case ITALIAN_VERSION:

            this.strategy = new ItalianStrategy(this.players);
            this.version = new GameVersionImpl(this.strategy);
            break;
        default:
            break;
        }
        this.bank = this.version.getBank();

        this.boxes = this.version.getAllBoxes();
        this.decks = this.version.getDecks();
        this.actualPlayer = this.version.getNextPlayer();
        if (!this.actualPlayer.isHuman()) {
            this.computerPlayer();
        }
    }

    @Override
    public void addView(final InPlay view) {
        this.view = Optional.of(view);
    }

    @Override
    public List<Actions> getButtons() {
        final List<Actions> buttons = new LinkedList<>();
        Arrays.asList(Actions.values()).forEach(b -> buttons.add(b));
        return buttons;
    }

    @Override
    public List<Box> getAllBoxes() {
        return this.boxes;
    }

    @Override
    public Bank getBank() {
        return this.bank;
    }

    @Override
    public List<Player> getPlayers() {
        return this.players;
    }

    @Override
    public int toRollDices() {

        this.lastDices = this.version.toRollDices();

        this.actualPosition = this.actualPlayer.getPawn().getActualPos();
        System.out.println(this.actualPosition);
        System.out.println(this.actualPlayer.getMoney());
        for (Box b : this.boxes) {
            if (b.getID() == this.actualPosition) {
                this.view.ifPresent(v -> v.setButton(this.getNextBoxsActions(b, this.actualPlayer)));
            }

        }
        return this.actualPosition;
    }

    @Override
    public Box getActualBox() {
        return this.boxes.get(this.actualPosition);
    }

    public void setActualPosition(int position) {
        this.actualPosition = position;
    }

    @Override
    public void endTurn() {
        this.actualPlayer = this.version.endOfTurnAndNextPlayer();
        if (!this.actualPlayer.isHuman()) {
            this.computerPlayer();
        } else {
            this.view.ifPresent(
                    v -> v.setButton(this.getNextBoxsActions(this.boxes.get(this.actualPosition), this.actualPlayer)));
        }
        System.out.println(this.actualPlayer.getName());
        // Player p=this.version.endOfTurnAndNextPlayer();
        // this.actualPlayer=this.players.indexOf(p);
        //
        // this.getActualPlayer().setDicesRoll(false);

    }

    @Override
    public void buyOwnership() {

        if (actions.contains(Actions.BUY)) {
            ToBuyProperties.buyAOwnership(((Ownership) this.boxes.get(actualPosition)).getContract().getCost(),
                    ((Ownership) this.boxes.get(actualPosition))).play(this.actualPlayer);
            if (this.actualPlayer.isHuman()) {
                System.out.println(((Ownership) this.getActualBox()).getOwner());
                this.view.ifPresent(v -> v.setButton(
                        this.getNextBoxsActions(((Ownership) this.boxes.get(actualPosition)), this.actualPlayer)));

            }

        } else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void sellOwnership() {

        if (actions.contains(Actions.SELL)) {
            ToSellProperties.sellAOwnership(((Ownership) this.boxes.get(actualPosition)).getContract().getCost(),
                    (Ownership) this.boxes.get(actualPosition), this.bank).play(this.actualPlayer);

            this.view.ifPresent(v -> v.setButton(
                    this.getNextBoxsActions(((Ownership) this.boxes.get(actualPosition)), this.actualPlayer)));
        } else {
            throw new IllegalArgumentException();
        }
    }

//    @Override
//    public AuctionOfOwnership auction() {
//        return this.version.toAuction((Ownership) this.boxes.get(actualPosition), this.actualPlayer);
//    }

    @Override
    public void build() {

        if (actions.contains(Actions.BUILD)) {
            ToBuyProperties.buyABuilding((Land) this.boxes.get(actualPosition), this.bank).play(this.actualPlayer);
            this.alreadyBuilt = true;
            if (this.actualPlayer.isHuman()) {
                this.view.ifPresent(v -> v
                        .setButton(this.getNextBoxsActions((Land) this.boxes.get(actualPosition), this.actualPlayer)));
            }
            this.alreadyBuilt = false;
        } else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void sellBuilding() {

        if (actions.contains(Actions.SELL_BUILDING)) {
            Land land = (Land) this.boxes.get(actualPosition);
            ToSellProperties.sellABuilding(land, ((LandGroup) land.getGroup()).getBuildings().get(0), this.bank)
                    .play(this.actualPlayer);
            this.view.ifPresent(v -> v
                    .setButton(this.getNextBoxsActions((Land) this.boxes.get(actualPosition), this.actualPlayer)));
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void mortgageOwnership() {

        if (actions.contains(Actions.MORTGAGE)) {
            new ToMortgage((Ownership) this.boxes.get(actualPosition)).play(this.actualPlayer);
            if (this.actualPlayer.isHuman()) {
                this.view.ifPresent(v -> v.setButton(
                        this.getNextBoxsActions((Ownership) this.boxes.get(actualPosition), this.actualPlayer)));
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void revokeMortgageOwnership() {

        if (actions.contains(Actions.REVOKE_MORTGAGE)) {
            new ToRevokeMortgage((Ownership) this.boxes.get(actualPosition)).play(this.actualPlayer);
            if (this.actualPlayer.isHuman()) {
                if (this.view != null) {
                    this.view.ifPresent(v -> v.setButton(
                            this.getNextBoxsActions((Ownership) this.boxes.get(actualPosition), this.actualPlayer)));
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    // @Override
    // public void trade(final Ownership firstOwnership, final Ownership
    // secondOwnership, final Player firstPlayer,
    // final Player secondPlayer) {
    // ToSellProperties.sellAOwnership(firstOwnership.getContract().getCost(),
    // firstOwnership, this.bank)
    // .play(firstPlayer);
    // ToBuyProperties.buyAOwnership(firstOwnership.getContract().getCost(),
    // firstOwnership).play(secondPlayer);
    // ToSellProperties.sellAOwnership(firstOwnership.getContract().getCost(),
    // secondOwnership, this.bank)
    // .play(secondPlayer);
    // ToBuyProperties.buyAOwnership(firstOwnership.getContract().getCost(),
    // secondOwnership).play(firstPlayer);
    // this.view.ifPresent(
    // v ->
    // v.setButton(this.getNextBoxsActions(this.boxes.get(this.actualPosition),
    // this.actualPlayer)));
    // }

    @Override
    public List<Player> endGame() {
        final List<Player> pl = this.players.stream().filter(p -> !p.equals(this.players.get(0)))
                .sorted((p, p1) -> this.patrimony(p) - this.patrimony(p1)).collect(Collectors.toList());
        return pl;
    }

    private int patrimony(final Player player) {
        Optional<Integer> res = Optional.empty();
        if (!player.getOwnerships().isEmpty()) {
            res = player.getOwnerships().stream().map(o -> o.getContract().getMortgageValue())
                    .reduce((i, i1) -> i + i1);
        }
        return player.getMoney() + (res.isPresent() ? res.get() : 0);
    }

    // /**
    // * This method allow to get the new position.
    // *
    // * @return new position
    // */
    // public int getNewPosition() {
    // Player p = this.actualPlayer;
    // return p.getPawn().getActualPos();
    // }
    //
    private void gameOverPerson(Player player) {
        this.players.remove(this.players.indexOf(player));

    }

    /**
     * this method allow to say if last throw of dices return twice result .
     * 
     * @return boolean for dices
     */
    public boolean isTwiceDices() {
        return lastDices.get(0) == lastDices.get(1);
    }

    private int getAverageCost() {
        return ((Land) this.boxes.get((this.boxes.size() / 2) - 1)).getContract()
                .getIncome(new LandIncomeStrategy((Land) this.boxes.get((this.boxes.size() / 2) - 1)));

    }

    private void computerPlayer() {
//         if (this.actualPlayer.isInPrison()) {
//            this.actualPlayer.getCards().forEach(c->{
//                if(c.getID()==ChanceCards.CARD4.getID() || c.getID()==CommunityChestCards.CARD4.getID()){
//                    this.actualPlayer.setPrison(false);
//                }
//            });
            
         


         this.actualPosition = this.toRollDices();
        // perfetta
        if (this.boxes.get(this.actualPosition) instanceof Ownership) {
            if (((Ownership) this.boxes.get(this.actualPosition)).isMortgaged()) {
                if (((Ownership) this.boxes.get(this.actualPosition)).getOwner().equals(this.actualPlayer)) {

                    if ((((Ownership) this.boxes.get(this.actualPosition)).getContract().getCost())
                            / 2 < (this.actualPlayer.getMoney() + this.getAverageCost())) {
                        this.revokeMortgageOwnership();

                    }
                }
            }
        }

        if (this.boxes.get(this.actualPosition) instanceof Land) {
            final Land land = ((Land) this.boxes.get(this.actualPosition));
            if (land.getOwner().equals(this.bank)) {
                // ok
                if (this.actualPlayer.getMoney() > ((Land) this.boxes.get(this.actualPosition)).getContract().getCost()
                        + getAverageCost()) {
                    this.buyOwnership();
                } else {
                    // this.auction();
                }
            } else if (land.getOwner().equals(this.actualPlayer)) {
                // ok
                if (this.actualPlayer.getOwnerships().containsAll(land.getGroup().getMembers())
                        && ((LandGroup) land.getGroup()).canBuiling() && this.bank.getLeftBuilding().size() > 0
                        && this.actualPlayer.getMoney() >= (((LandContract) land.getContract()).getCostForEachBuilding()
                                + (getAverageCost() * 4))
                        && !this.alreadyBuilt) {
                    this.bank.getLeftBuilding().forEach(b -> {
                        if ((((LandGroup) land.getGroup()).getBuildings().size() < 4 && b instanceof Home)// capire
                                || (b instanceof Hotel)) {
                            this.build();

                        }
                    });
                }

            } else {
                // ok
                final int amount = ((ClassicLandContract) land.getContract()).getIncome(new LandIncomeStrategy(land));
                if (amount <= this.actualPlayer.getMoney()) {

                    new ToPay(amount, this.actualPlayer).play(this.actualPlayer);
                    new ToBePaid(amount).play((Player) land.getOwner());

                } else {
                    if (this.version.haveEnoughMoney(this.actualPlayer, amount)) {
                        new ToPay(amount, this.actualPlayer).play(this.actualPlayer);
                        new ToBePaid(amount).play((Player) land.getOwner());

                    } else {

                        new ToBePaid(amount).play((Player) land.getOwner());
                        this.gameOverPerson(this.actualPlayer);
                    }
                    // se ha delle case le vende con not much money

                    // while (amount >= this.actualPlayer.getMoney()) {
                    //
                    // if (!this.actualPlayer.getOwnerships().isEmpty()) {
                    //
                    // Ownership own = ((Ownership) this.boxes.get(FIRST_BOX));
                    // int massimo = 0;
                    // for (Ownership o : this.actualPlayer.getOwnerships()) {
                    // if (o.getContract().getCost() >= massimo) {
                    // massimo = o.getContract().getCost();
                    // own = o;
                    // }
                    //
                    // }
                    // new ToMortgage(own).play(this.actualPlayer);
                    // } else {
                    // new ToPay(amount,
                    // this.actualPlayer).play(this.actualPlayer);
                    // new ToBePaid(amount).play((Player) land.getOwner());
                    //
                    // // far perdere
                    // this.endTurn();
                    // }
                    //
                    // }
                    //
                }
            }
        } else if (this.boxes.get(this.actualPosition) instanceof Ownership) {
            // ok
            final Ownership ownership = ((Ownership) this.boxes.get(this.actualPosition));
            if (ownership.getOwner().equals(this.bank)) {
                if (this.actualPlayer.getMoney() > ((Ownership) this.boxes.get(this.actualPosition)).getContract()
                        .getCost()) {
                    this.buyOwnership();
                } else {
                    // asta
                }
            } else if (!ownership.getOwner().equals(this.actualPlayer)) {

                final int amount = ownership.getContract()
                        .getIncome(ownership instanceof Station ? new StationIncomeStrategy(ownership)
                                : new CompanysIncomeStrategy(ownership, this.actualPlayer));

                if (amount <= this.actualPlayer.getMoney()) {
                    // oik
                    new ToPay(amount, this.actualPlayer).play(this.actualPlayer);
                    new ToBePaid(amount).play((Player) ((Ownership) this.boxes.get(this.actualPosition)).getOwner());
                } else {
                    // ok
                    if (version.haveEnoughMoney(this.actualPlayer, amount)) {
                        new ToPay(amount, this.actualPlayer).play(this.actualPlayer);
                        new ToBePaid(amount)
                                .play((Player) ((Ownership) this.boxes.get(this.actualPosition)).getOwner());

                    } else {
                        // ok
                        new ToBePaid(amount)
                                .play((Player) ((Ownership) this.boxes.get(this.actualPosition)).getOwner());
                        this.gameOverPerson(this.actualPlayer);
                    }
                    // List<Actions> ac = new LinkedList<>();
                    // while (amount >= this.actualPlayer.getMoney()) {
                    // ac.clear();
                    // this.notMuchMoney(actualPlayer, ac);
                    //
                    // Ownership own = ((Ownership) this.boxes.get(FIRST_BOX));
                    // int massimo = 0;
                    // for (Ownership o : this.actualPlayer.getOwnerships()) {
                    // if (o.getContract().getCost() >= massimo) {
                    // massimo = o.getContract().getCost();
                    // own = o;
                    // }
                    //
                    // }
                    // if (ac.contains(Actions.MORTGAGE)) {
                    // new ToMortgage(own).play(this.actualPlayer);
                    // }
                    //
                    // }
                    // new ToPay(amount,
                    // this.actualPlayer).play(this.actualPlayer);
                    // new ToBePaid(amount).play((Player) ((Ownership)
                    // this.boxes.get(this.actualPosition)).getOwner());
                }
            }
        } else {
            if (this.boxes.get(this.actualPosition) instanceof Start) {
                new ToBePaid(Start.getMuchToPick()).play(this.actualPlayer);
            }
            // if (box instanceof PrisonOrTransit) { // NON FANNO NULLA; QUINDI
            // OMETTERLI
            // }
            // if (box instanceof NeutralArea) {
            // }
            if (this.boxes.get(this.actualPosition) instanceof Police) {
                new GoToPrison(this.boxes.get(PRISON_POSITION)).play(this.actualPlayer);
            }
            // FAR PESCARE LA CARTA BHA
            if (this.boxes.get(this.actualPosition) instanceof DecksBox) {

                this.drawCard((this.decks.get(this.boxes.get(this.actualPosition).getID() == FIRST_CHANCE_POSITION
                        || this.boxes.get(this.actualPosition).getID() == SECOND_CHANCE_POSITION
                        || this.boxes.get(this.actualPosition).getID() == THIRD_CHANCE_POSITION ? 0 : 1)));

            }
            if (this.boxes.get(this.actualPosition) instanceof TaxImpl) {
                new ToPay(((TaxImpl) this.boxes.get(this.actualPosition)).getCost(), this.actualPlayer)
                        .play(this.actualPlayer);
            }

        }
        if (this.isTwiceDices()) {
            this.computerPlayer();
        } else {
            this.endTurn();
        }
    }

    // public Card getLastCardDrawn() {
    // return this.card;
    // }

    public void drawCard(final Deck deck) {
        new ToDrawCards(deck).play(this.actualPlayer);
        final Card card = this.actualPlayer.lastCardDrew();
        if (card.getActions().isPresent()) {
            List<Action> list = card.getActions().get();
            for (Action a : list) {
                a.play(this.getActualPlayer());

            }
        }
        if (this.version.getNextCardsAction(this.getActualBox(), card, this.actualPlayer)) {
            this.gameOverPerson(this.actualPlayer);
        }

    }
    // /**
    // * Method for choose the winner.
    // *
    // * @return winner Player {@link Player}
    // */
    // public Player winner() {
    // if (this.players.size() == 1) {
    // return this.actualPlayer;
    // }
    // return null;
    // }
    //
    // /**
    // * Method to declare bankruptcy.
    // */
    // public void gameOver() {
    // this.view.gameOver(this.actualPlayer);
    // this.players.remove(this.actualPlayer);
    // }
    //
    // public void usePrisonCard() {
    //
    // }

    public List<Actions> getNextBoxsActions(final Box box, final Player player) {
        actions = new LinkedList<>();
        actions.clear();
        if (!player.dicesAlreadyRolled()) {
            actions.add(Actions.ROLL_DICES);
            return actions;
        } else if (box instanceof Land) {
            final Land land = (Land) box;
            if (land.getOwner().equals(this.bank)) {
                this.toBuyOrToEndOfTurn(land, player, actions);
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
                    if (version.haveEnoughMoney(this.actualPlayer, amount)) {
                        new ToPay(amount, this.actualPlayer).play(this.actualPlayer);
                        new ToBePaid(amount).play((Player) land.getOwner());

                    } else {

                        new ToBePaid(amount).play((Player) land.getOwner());
                        this.gameOverPerson(this.actualPlayer);
                    }
                }
            }
        } else if (box instanceof Ownership) {
            final Ownership ownership = (Ownership) box;
            if (ownership.getOwner().equals(this.bank)) {
                this.toBuyOrToEndOfTurn(ownership, player, actions);
            } else if (!ownership.getOwner().equals(player)) {

                final int amount = ownership.getContract().getIncome(ownership instanceof Station
                        ? new StationIncomeStrategy(ownership) : new CompanysIncomeStrategy(ownership, player));
                if (amount <= player.getMoney()) {
                    new ToPay(amount, player).play(player);
                    new ToBePaid(amount).play((Player) ownership.getOwner());
                    player.setDebts(true);
                } else {
                    if (version.haveEnoughMoney(this.actualPlayer, amount)) {
                        new ToPay(amount, this.actualPlayer).play(this.actualPlayer);
                        new ToBePaid(amount).play((Player) ownership.getOwner());

                    } else {
                        new ToBePaid(amount).play((Player) ownership.getOwner());
                        this.gameOverPerson(this.actualPlayer);
                    }
                }
            } else {
                if (ownership.isMortgaged()) {
                    this.actions.add(Actions.REVOKE_MORTGAGE);
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

                this.drawCard((this.decks.get(box.getID() == FIRST_CHANCE_POSITION
                        || box.getID() == SECOND_CHANCE_POSITION || box.getID() == THIRD_CHANCE_POSITION ? 0 : 1)));

            }
            if (this.strategy instanceof ItalianStrategy && box instanceof ItalianNeutralArea) {
                new ToStealMoney((ItalianNeutralArea) box);
            }

            if (box instanceof TaxImpl) {
                if (this.strategy instanceof ClassicStrategy) {
                    if (this.version.haveEnoughMoney(this.actualPlayer, ((TaxImpl) box).getCost())) {
                        new ToPay(((TaxImpl) box).getCost(), player).play(player);
                    } else {
                        this.gameOverPerson(this.actualPlayer);
                    }

                } else {

                    if (this.version.haveEnoughMoney(this.actualPlayer, ((TaxImpl) box).getCost())) {
                        new EvadeTaxes(((TaxImpl) box).getCost(),
                                ((ItalianNeutralArea) this.boxes.get(BoxesPositions.NEUTRAL_AREA_POSITION.getPos())))
                                        .play(player);
                    } else {
                        this.gameOverPerson(this.actualPlayer);
                    }

                }
            }
        }
        if (player.dicesAlreadyRolled()) {
            actions.add(Actions.END_OF_TURN);
        }
        if ((box instanceof Ownership) && !((Ownership) box).isMortgaged()
                && (((Ownership) box).getOwner().equals(player))) {
            actions.add(Actions.MORTGAGE);
            actions.add(Actions.SELL);
        }

        // if (actions.isEmpty()) {
        // player.setDebts(false);
        // actions.add(Actions.END_OF_TURN);
        // }

        // if (((this.boxes.get(actualPosition) instanceof Ownership))) {
        //
        // Ownership o = (Ownership) this.boxes.get(actualPosition);
        // if (!o.isMortgaged()) {
        // if (this.boxes.get(actualPosition) instanceof Land && ((LandGroup)
        // o.getGroup()).getBuildings().isEmpty()) {
        // actions.add(Actions.MORTGAGE);
        // actions.add(Actions.SELL);
        // } else if (this.boxes.get(actualPosition) instanceof Ownership) {
        // actions.add(Actions.BUILD);
        // actions.add(Actions.SELL);
        // }
        //
        // }
        // }

        actions.add(Actions.END_OF_THE_GAME);
        return actions;
    }

    private void toBuyOrToEndOfTurn(final Ownership ownership, final Player player, final List<Actions> actions) {
        if (player.getMoney() >= ownership.getContract().getCost()) {
            actions.add(Actions.BUY);
        }
        actions.add(Actions.END_OF_TURN);
    }
}
// private void notMuchMoney(final Player player, final List<Actions>
// actions) {
// if (!player.getOwnerships().isEmpty()) {
// player.getOwnerships().stream().filter(o -> o.getGroup() instanceof
// LandGroup)
// .filter(o -> ((LandGroup) o.getGroup()).getBuildings().size() >
// 0).forEach(o -> {
// ((LandGroup) o.getGroup()).getBuildings().forEach(b -> {
// this.sellBuilding();
// });
// });
// if (actions.isEmpty()) {
// player.getOwnerships().stream().forEach(o -> {
// actions.add(Actions.SELL);
// actions.add(Actions.MORTGAGE);
// });
// }
// } else {
// this.endGame();
// }
// }

// @Override
// public List<Action> getNextCardsActions(final Box box, final Card card,
// final Player player) {
// final List<Action> actions = new LinkedList<>();
// switch (card.getID()) {
// case CardsId.CARD2.getValue():
// MoveUpTo.moveUpToBox(this.allBoxes.get(37)).play(player);

// case CardsId.CARD3.getValue():
// if (((Ownership) box).getOwner().equals(player)) {
// actions.addAll(this.getNextBoxsActions(box, player));
// } else {
// final int amount = 2
// * ((Ownership) box).getContract().getIncome(new
// StationIncomeStrategy(((Ownership) box)));
// actions.add(new ToPay(amount, player));
// new ToBePaid(amount);
// }
// break;
// case CardsId.CARD4.getValue():
// player.addCard(card);
// break;
// case CACardsId.CARD5.getValue():
// actions.add(new ToPay(20, player));
// break;
// case CARD7:
// actions.add(new
// GoToPrison(this.allBoxes.get(BoxesPositions.PRISON_POSITION.getPos())));
// case CardsId.CARD8.getValue():
// player.getOwnerships().get().stream().filter(o -> o instanceof Land)
// .filter(o -> !((LandGroup) o.getGroup()).getBuildings().isEmpty())
// .map(o -> ((LandGroup) o.getGroup()).getBuildings()).forEach(l -> {
// l.forEach(b -> {
// actions.add(new ToPay(b instanceof Home ? 25 : 100, player));
// });
// });
// break;
// case CardsId.CARD9.getValue():
// this.players.stream().filter(p -> !p.equals(player)).forEach(p -> {
// try {
// new ToPay(CARD_TAX, player).play(player);
// new ToBePaid(CARD_TAX).play(p);
// } catch (IllegalArgumentException i) {
// this.notMuchMoney(player, actions);
// }
// });
// break;
// case CARCardsId.CARD10.getValue()D10:
// actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(BoxesPositions.START_POSITION.getPos())));
// case CARD11:
// actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(OWNERSHIP_N_18)));
// case CARD12:
// if (((Ownership) box).getOwner().equals(player)) {
// actions.addAll(this.getNextBoxsActions(box, player));
// } else {
// final int amount = 2
// * ((Ownership) box).getContract().getIncome(new
// StationIncomeStrategy(((Ownership) box)));
// actions.add(new ToPay(amount, player));
// new ToBePaid(amount);
// }
// break;
// case CARD13:
// actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(OWNERSHIP_N_17)));
// case CARD14:
// actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(OWNERSHIP_N_9)));
// case CARD15:
// if (((Ownership) box).getOwner().equals(player)) {
// actions.addAll(this.getNextBoxsActions(box, player));
// } else {
// final int amount = (player.lastDicesNumber().stream().reduce((d, d1) -> d
// + d1).get() * 10);
// actions.add(new ToPay(amount, player));
// new ToBePaid(amount);
// }
// break;
// case CARD17:
// actions.add(new ToPay(50, player));
// break;
// case CARD18:
// actions.add(new ToPay(50, player));
// break;
// case CARD20:
// player.addCard(card);
// break;
// case CARD21:
// actions.add(new ToPay(100, player));
// break;
// case CARD23:
// actions.add(new
// GoToPrison(this.allBoxes.get(BoxesPositions.PRISON_POSITION.getPos())));
// case CARD24:
// player.getOwnerships().get().stream().filter(o -> o instanceof Land)
// .filter(o -> !((LandGroup) o.getGroup()).getBuildings().isEmpty())
// .map(o -> ((LandGroup) o.getGroup()).getBuildings()).forEach(l -> {
// l.forEach(b -> {
// actions.add(new ToPay(b instanceof Home ? 40 : 115, player));
// });
// });
// break;
// case CARD25:
// this.players.stream().filter(p -> !p.equals(player)).forEach(p -> {
// try {
// new ToPay(10, p).play(p);
// new ToBePaid(10).play(player);
// } catch (IllegalArgumentException i) {
// this.notMuchMoney(player, actions);
// }
// });
// break;
// case CARD26:
// actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(BoxesPositions.START_POSITION.getPos())));
// default:
// break;
// }
// return actions;
// }
// private void notMuchMoney(final Player player, final List<Action>
// actions) {
// if (player.getOwnerships().isPresent()) {
// player.getOwnerships().get().stream().filter(o -> o.getGroup() instanceof
// LandGroup)
// .filter(o -> ((LandGroup) o.getGroup()).getBuildings().size() >
// 0).forEach(o -> {
// ((LandGroup) o.getGroup()).getBuildings()
// .forEach(b -> actions.add(ToSellProperties.sellABuilding(((Land) o), b,
// this.bank)));
// });
// if (actions.isEmpty()) {
// player.getOwnerships().get().stream().forEach(o -> {
// actions.add(ToSellProperties.sellAOwnership(o, this.bank));
// });
// }
// } else if (player.getCards().isPresent()) {
// player.getCards().get().forEach(c ->
// actions.add(ToAuction.cards(this.players, new ClassicAuction(), c)));
// } else {
// // FINE DEL GIOCO -> interfaccia funzionale (Action ?) + classe
// // anonima: rimuove il giocatore con il play)
// actions.add(p -> {
// this.players.remove(p);
// });
// }
// }
// private void notMuchMoney(final Player player, final List<Action>
// actions) {
// if (player.getOwnerships().isPresent()) {
// player.getOwnerships().get().stream().filter(o -> o.getGroup() instanceof
// LandGroup)
// .filter(o -> ((LandGroup) o.getGroup()).getBuildings().size() >
// 0).forEach(o -> {
// ((LandGroup) o.getGroup()).getBuildings()
// .forEach(b -> actions.add(ToSellProperties.sellABuilding(((Land) o), b,
// this.bank)));
// });
// if (actions.isEmpty()) {
// player.getOwnerships().get().stream().forEach(o -> {
// actions.add(ToSellProperties.sellAOwnership(o, this.bank));
// });
// }
// } else if (player.getCards().isPresent()) {
// player.getCards().get().forEach(c ->
// actions.add(ToAuction.cards(this.players, new ClassicAuction(), c)));
// } else {
// // FINE DEL GIOCO -> interfaccia funzionale (Action ?) + classe
// // anonima: rimuove il giocatore con il play)
// actions.add(p -> {
// this.players.remove(p);
// });
// }
// }
//
