package it.unibo.monopoli.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.monopoli.model.actions.AuctionOfOwnership;
import it.unibo.monopoli.model.actions.GoToPrison;
import it.unibo.monopoli.model.actions.ToBePaid;
import it.unibo.monopoli.model.actions.ToBuyProperties;
import it.unibo.monopoli.model.actions.ToDrawCards;
import it.unibo.monopoli.model.actions.ToMortgage;
import it.unibo.monopoli.model.actions.ToPay;
import it.unibo.monopoli.model.actions.ToRevokeMortgage;
import it.unibo.monopoli.model.actions.ToSellProperties;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.mainunits.Bank;
import it.unibo.monopoli.model.mainunits.ClassicPawn;
import it.unibo.monopoli.model.mainunits.ClassicPlayer;
import it.unibo.monopoli.model.mainunits.ClassicStrategy;
import it.unibo.monopoli.model.mainunits.GameVersion;
import it.unibo.monopoli.model.mainunits.GameVersionImpl;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.ClassicLandContract;
import it.unibo.monopoli.model.table.CompanysIncomeStrategy;
import it.unibo.monopoli.model.table.DecksBox;
import it.unibo.monopoli.model.table.Home;
import it.unibo.monopoli.model.table.Hotel;
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
import it.unibo.monopoli.view.EVersion;
import it.unibo.monopoli.view.InPlay;

/**
 * */
public class ControllerImpl implements Controller {

    private static final int PRISON_POSITION = 10;
    private static final int FIRST_CHANCE_POSITION = 7;
    private static final int SECOND_CHANCE_POSITION = 22;
    private static final int THIRD_CHANCE_POSITION = 36;
    private static final int FIRST_BOX = 1;

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

    /**
     * 
     */
    public ControllerImpl() {
        this.players = new LinkedList<>();
    }

    @Override
    public void addPlayer(final String name, final ClassicPawn pawn, final boolean isHuman) {
        this.players.add(new ClassicPlayer(name, pawn, isHuman));
    }

    @Override
    public void initializedVersion(final EVersion versionEnum) {
        switch (versionEnum) {
        case CLASSIC:
            this.version = new GameVersionImpl(new ClassicStrategy(this.players));
            break;
        default:
            break;
        }
        this.bank = this.version.getBank();
        this.boxes = this.version.getAllBoxes();
        this.decks = this.version.getDecks();
        this.actualPlayer = this.version.getNextPlayer();
//        if (!this.actualPlayer.isHuman()) {
//            this.computerPlayer();
//        }
    }

    @Override
    public void addView(final InPlay view) {
        this.view = Optional.of(view);
    }

    @Override
    public List<String> getButtons() {
        final List<String> buttons = new LinkedList<>();
        Arrays.asList(Actions.values()).forEach(b -> buttons.add(b.getText()));
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
        
        this.lastDices = this.version.toRollDices(this.actualPlayer);
        this.actualPosition = this.actualPlayer.getPawn().getActualPos();
        System.out.println(this.actualPosition);
        this.boxes.forEach(b -> {
            if (b.getID() == this.actualPosition) {
                this.view.ifPresent(v -> v.setButton(this.getNextBoxsActions(b, this.actualPlayer)));
            }
        });

        return this.actualPosition;
    }

    @Override
    public Box getActualBox(){
        return this.boxes.get(this.actualPosition);
    }
    @Override
    public void endTurn() {
        
        this.view.ifPresent(
                v -> v.setButton(this.getNextBoxsActions(this.boxes.get(this.actualPosition), this.actualPlayer)));
        this.actualPlayer = version.endOfTurnAndNextPlayer();
        if (!this.actualPlayer.isHuman()) {
            this.computerPlayer();
        }
    }

    @Override
    public void buyOwnership() {
        ToBuyProperties.buyAOwnership(((Ownership) this.boxes.get(actualPosition)).getContract().getCost(),
                ((Ownership) this.boxes.get(actualPosition))).play(this.actualPlayer);
        if (this.actualPlayer.isHuman()) {
            this.view.ifPresent(v -> v.setButton(
                    this.getNextBoxsActions(((Ownership) this.boxes.get(actualPosition)), this.actualPlayer)));
        }
    }

    @Override
    public void sellOwnership() {
        ToSellProperties.sellAOwnership(((Ownership) this.boxes.get(actualPosition)).getContract().getCost(),
                (Ownership) this.boxes.get(actualPosition), this.bank).play(this.actualPlayer);
        
        this.view.ifPresent(v -> v
                .setButton(this.getNextBoxsActions(((Ownership) this.boxes.get(actualPosition)), this.actualPlayer)));
    }

    @Override
    public AuctionOfOwnership auction() {
        return this.version.toAuction((Ownership) this.boxes.get(actualPosition), this.actualPlayer);
    }

    @Override
    public void build() {
        ToBuyProperties.buyABuilding((Land) this.boxes.get(actualPosition), this.bank).play(this.actualPlayer);
        this.alreadyBuilt = true;
        if (this.actualPlayer.isHuman()) {
        this.view.ifPresent(
                v -> v.setButton(this.getNextBoxsActions((Land) this.boxes.get(actualPosition), this.actualPlayer)));
        }
        this.alreadyBuilt = false;
    }

    @Override
    public void sellBuilding() {
        Land land = (Land) this.boxes.get(actualPosition);
        ToSellProperties.sellABuilding(land, ((LandGroup) land.getGroup()).getBuildings().get(0), this.bank)
                .play(this.actualPlayer);
        this.view.ifPresent(
                v -> v.setButton(this.getNextBoxsActions((Land) this.boxes.get(actualPosition), this.actualPlayer)));
    }

    @Override
    public void mortgageOwnership() {
        
        new ToMortgage((Ownership) this.boxes.get(actualPosition)).play(this.actualPlayer);
        if (this.actualPlayer.isHuman()) {
        this.view.ifPresent(v -> v
                .setButton(this.getNextBoxsActions((Ownership) this.boxes.get(actualPosition), this.actualPlayer)));
        }
    }

    @Override
    public void revokeMortgageOwnership() {

        new ToRevokeMortgage((Ownership) this.boxes.get(actualPosition)).play(this.actualPlayer);
        if (this.actualPlayer.isHuman()) {
        this.view.ifPresent(v -> v
                .setButton(this.getNextBoxsActions((Ownership) this.boxes.get(actualPosition), this.actualPlayer)));
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

    /**
     * this method allow to say if last throw of dices return twice result .
     * 
     * @return boolean for dices
     */
    public boolean isTwiceDices() {
        return lastDices.get(0) == lastDices.get(1);
    }

    private void computerPlayer() {

        this.actualPosition = this.toRollDices();
        // if (this.actualPlayer.isInPrison()) {
        // if (this.actualPlayer.getCards()) {// se ha la carta per uscire di
        // // prigione
        // // rimuovi la carta
        // this.actualPlayer.setPrison(false);
        // } else if (this.isTwiceDices()) {
        // this.actualPlayer.setPrison(false);
        // } else {
        // this.endTurn();
        // }
        // }

        if (this.boxes.get(this.actualPosition) instanceof Ownership) {
            if (((Ownership) this.boxes.get(this.actualPosition)).isMortgaged()) {
                if (((Ownership) this.boxes.get(this.actualPosition)).getOwner().equals(this.actualPlayer)) {

                    if ((((Ownership) this.boxes.get(this.actualPosition)).getContract().getCost())
                            / 2 > (this.actualPlayer.getMoney()
                                    + ((Land) this.boxes.get(this.boxes.size() - 1)).getContract().getIncome(
                                            new LandIncomeStrategy((Land) this.boxes.get(this.boxes.size() - 1))))) {
                        this.revokeMortgageOwnership();

                    }
                }
            }
        }

        if (this.boxes.get(this.actualPosition) instanceof Land) {
            final Land land = ((Land) this.boxes.get(this.actualPosition));
            if (land.getOwner().equals(this.bank)) {
                if (this.actualPlayer.getMoney() > ((Land) this.boxes.get(this.actualPosition)).getContract().getCost()
                        + ((Land) this.boxes.get(this.boxes.size() - 1)).getContract()
                                .getIncome(new LandIncomeStrategy((Land) this.boxes.get(this.boxes.size() - 1)))) {
                    this.buyOwnership();
                } else {
                    // this.auction();
                }
            } else if (land.getOwner().equals(this.actualPlayer)) {
                if (this.actualPlayer.getOwnerships().containsAll(land.getGroup().getMembers())
                        && ((LandGroup) land.getGroup()).canBuiling() && this.bank.getLeftBuilding().size() > 0
                        && this.actualPlayer
                                .getMoney() >= (((LandContract) land.getContract()).getCostForEachBuilding()
                                        + (((Land) this.boxes.get(this.boxes.size() - 1)).getContract()
                                                .getIncome(new LandIncomeStrategy(
                                                        (Land) this.boxes.get(this.boxes.size() - 1)))
                                                * 4))
                        && !this.alreadyBuilt) {
                    this.bank.getLeftBuilding().forEach(b -> {
                        if ((((LandGroup) land.getGroup()).getBuildings().size() < 4 && b instanceof Home)// capire
                                || (b instanceof Hotel)) {
                            this.build();

                        }
                    });
                }

            } else {
                final int amount = ((ClassicLandContract) land.getContract()).getIncome(new LandIncomeStrategy(land));
                if (amount <= this.actualPlayer.getMoney()) {

                    new ToPay(amount, this.actualPlayer).play(this.actualPlayer);
                    new ToBePaid(amount).play((Player) land.getOwner());

                } else {
                    while (amount >= this.actualPlayer.getMoney()) {

                        if (!this.actualPlayer.getOwnerships().isEmpty()) {

                            Ownership own = ((Ownership) this.boxes.get(FIRST_BOX));
                            int massimo = 0;
                            for (Ownership o : this.actualPlayer.getOwnerships()) {
                                if (o.getContract().getCost() >= massimo) {
                                    massimo = o.getContract().getCost();
                                    own = o;
                                }

                            }
                            new ToMortgage(own).play(this.actualPlayer);
                        } else {
                            new ToPay(amount, this.actualPlayer).play(this.actualPlayer);
                            new ToBePaid(amount).play((Player) land.getOwner());

                            // far perdere
                            this.endTurn();
                        }

                    }
                    new ToPay(amount, this.actualPlayer).play(this.actualPlayer);
                    new ToBePaid(amount).play((Player) land.getOwner());
                }
            }
        } else if (this.boxes.get(this.actualPosition) instanceof Ownership) {
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

                    new ToPay(amount, this.actualPlayer).play(this.actualPlayer);
                    new ToBePaid(amount).play((Player) ((Ownership) this.boxes.get(this.actualPosition)).getOwner());
                } else {
                    while (amount >= this.actualPlayer.getMoney()) {

                        if (!this.actualPlayer.getOwnerships().isEmpty()) {

                            Ownership own = ((Ownership) this.boxes.get(FIRST_BOX));
                            int massimo = 0;
                            for (Ownership o : this.actualPlayer.getOwnerships()) {
                                if (o.getContract().getCost() >= massimo) {
                                    massimo = o.getContract().getCost();
                                    own = o;
                                }

                            }
                            new ToMortgage(own).play(this.actualPlayer);
                        } else {
                            new ToPay(amount, this.actualPlayer).play(this.actualPlayer);
                            new ToBePaid(amount)
                                    .play((Player) ((Ownership) this.boxes.get(this.actualPosition)).getOwner());

                            // far perdere
                            this.endTurn();
                        }

                    }
                    new ToPay(amount, this.actualPlayer).play(this.actualPlayer);
                    new ToBePaid(amount).play((Player) ((Ownership) this.boxes.get(this.actualPosition)).getOwner());
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
            if (this.boxes.get(this.actualPosition) instanceof DecksBox) {
                new ToDrawCards(this.decks.get(this.boxes.get(this.actualPosition).getID() == FIRST_CHANCE_POSITION
                        || this.boxes.get(this.actualPosition).getID() == SECOND_CHANCE_POSITION
                        || this.boxes.get(this.actualPosition).getID() == THIRD_CHANCE_POSITION ? 0 : 1))
                                .play(this.actualPlayer);
            }
            if (this.boxes.get(this.actualPosition) instanceof TaxImpl) {
                new ToPay(((TaxImpl) this.boxes.get(this.actualPosition)).getCost(), this.actualPlayer)
                        .play(this.actualPlayer);
            }

        }
        this.endTurn();
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

    private List<String> getNextBoxsActions(final Box box, final Player player) {
        final List<String> actions = new LinkedList<>();
        if (!player.dicesAlreadyRolled()) {
            actions.add("Tira dadi");
            return actions;
        } else if (box instanceof Land) {
            final Land land = (Land) box;
            if (land.getOwner().equals(this.bank)) {
                this.toBuyOrToAuction(land, player, actions);
            } else if (land.getOwner().equals(player)) {
                if (land.isMortgaged()) {
                    actions.add("revoca");
                } else if (player.getOwnerships().containsAll(land.getGroup().getMembers())
                        && ((LandGroup) land.getGroup()).canBuiling() && this.bank.getLeftBuilding().size() > 0
                        && player.getMoney() >= ((LandContract) land.getContract()).getCostForEachBuilding()
                        && !this.alreadyBuilt) {
                    this.bank.getLeftBuilding().forEach(b -> {
                        if ((((LandGroup) land.getGroup()).getBuildings().size() < 4 && b instanceof Home)
                                || (b instanceof Hotel)) {
                            actions.add("Costruisci");
                        }
                    });
                }
                if (!((LandGroup) land.getGroup()).getBuildings().isEmpty()) {
                    actions.add("Vendi edifici");
                }
                actions.add("Finisci turno");
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
                this.toBuyOrToAuction(ownership, player, actions);
            } else if (!ownership.getOwner().equals(player)) {
                final int amount = ownership.getContract().getIncome(ownership instanceof Station
                        ? new StationIncomeStrategy(ownership) : new CompanysIncomeStrategy(ownership, player));
                if (amount <= player.getMoney()) {
                    new ToPay(amount, player).play(player);
                    new ToBePaid(amount).play((Player) ownership.getOwner());
                    player.setDebts(true);
                } else {
                    this.notMuchMoney(player, actions);
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
                new ToPay(((TaxImpl) box).getCost(), player).play(player);
            }
        }
        if (actions.isEmpty()) {
            player.setDebts(false);
            actions.add("finisci turno");
        }
        if (!player.getOwnerships().isEmpty()) {
            player.getOwnerships().stream().forEach(o -> {
                if (o instanceof Land && ((LandGroup) o.getGroup()).getBuildings().isEmpty()) {
                    actions.add("Ipoteca");
                    actions.add("Vendi");
                } else if (o instanceof Ownership) {
                    actions.add("Ipoteca");
                    actions.add("Vendi");
                }
                if (o.isMortgaged()) {
                    actions.add("Rimuove ipoteca");
                }
            });
        }
        if (!player.getOwnerships().isEmpty()) {
            this.players.forEach(p -> {
                if (!p.getOwnerships().isEmpty()) {
                    actions.add("Scambio");
                }
            });
        }
        actions.add("Finisci partita");
        return actions;
    }

    private void toBuyOrToAuction(final Ownership ownership, final Player player, final List<String> actions) {
        if (player.getMoney() >= ownership.getContract().getCost()) {
            actions.add("Compra");
        }
        actions.add("Asta");
    }

    private void notMuchMoney(final Player player, final List<String> actions) {
        if (!player.getOwnerships().isEmpty()) {
            player.getOwnerships().stream().filter(o -> o.getGroup() instanceof LandGroup)
                    .filter(o -> ((LandGroup) o.getGroup()).getBuildings().size() > 0).forEach(o -> {
                        ((LandGroup) o.getGroup()).getBuildings().forEach(b -> actions.add("Vendi edificio"));
                    });
            if (actions.isEmpty()) {
                player.getOwnerships().stream().forEach(o -> {
                    // actions.add("Vendi");
                    // actions.add("Ipoteca");
                });
            }
            // } else {
            // this.gameOver();
        }
    }

    // public static void main(String[] args) {
    // LinkedList<Integer> l = new LinkedList<>();
    // l.add(1);
    //
    // // List<Integer> in = new LinkedList<>();
    //
    // Optional<Integer> in = l.stream().sorted((s, s1) -> (s - s1)).reduce((i,
    // i1) -> i + i1);
    //
    // System.out.println(l);
    // System.out.println(in.get());
    // }

}
