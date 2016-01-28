package it.unibo.monopoli.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.monopoli.model.actions.AuctionOfOwnershipAndCard;
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
import it.unibo.monopoli.model.table.Building;
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
    private static final int EXIT_PRISON_COST = 50;
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
    private Ownership actualOwnership;

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

    // /**
    // * Remove player from list.
    // *
    // * @param name
    // * .
    // */
    // private void removePlayer(final String name) {
    // this.players.forEach(p -> {
    // if (p.getName().equals(name)) {
    // this.players.remove(p);
    // }
    // });
    // }

    @Override
    public List<Player> getPlayers() {
        return this.players;
    }

    // /**
    // * Return the id of the actual player.
    // *
    // * @return actual player .
    // */
    // public Player getActualPlayer() {
    // return this.actualPlayer;
    // }

    @Override
    public int toRollDices() {
        this.lastDices = this.version.toRollDices(this.actualPlayer);
        this.actualPosition = this.actualPlayer.getPawn().getActualPos();
        this.boxes.forEach(b -> {
            if (b.getID() == this.actualPosition) {
                this.view.ifPresent(v -> v.setButton(this.getNextBoxsActions(b, this.actualPlayer)));
            }
        });
        return this.actualPosition;
    }

    @Override
    public Player endTurn() {
        this.view.ifPresent(
                v -> v.setButton(this.getNextBoxsActions(this.boxes.get(this.actualPosition), this.actualPlayer)));
        this.actualPlayer = version.endOfTurnAndNextPlayer(this.actualPlayer);
        return this.actualPlayer;
    }

    @Override
    public void buyOwnership(final Ownership ownership) {
        ToBuyProperties.buyAOwnership(ownership.getContract().getCost(), ownership).play(this.actualPlayer);
        this.view.ifPresent(v -> v.setButton(this.getNextBoxsActions(ownership, this.actualPlayer)));
    }

    @Override
    public void sellOwnership(final Ownership ownership) {
        ToSellProperties.sellAOwnership(ownership.getContract().getCost(), ownership, this.bank)
                .play(this.actualPlayer);
        this.view.ifPresent(v -> v.setButton(this.getNextBoxsActions(ownership, this.actualPlayer)));
    }

    @Override
    public AuctionOfOwnershipAndCard auction(final Ownership ownership) {
        return this.version.toAuction(ownership, this.actualPlayer);
    }

    @Override
    public void build(final Land land) {
        ToBuyProperties.buyABuilding(land, this.bank).play(this.actualPlayer);
        this.alreadyBuilt = true;
        this.view.ifPresent(v -> v.setButton(this.getNextBoxsActions(land, this.actualPlayer)));
        this.alreadyBuilt = false;
    }

    @Override
    public void sellBuilding(final Land land, final Building building) {
        ToSellProperties.sellABuilding(land, building, this.bank).play(this.actualPlayer);
        this.view.ifPresent(v -> v.setButton(this.getNextBoxsActions(land, this.actualPlayer)));
    }

    @Override
    public void mortgageOwnership(final Ownership ownership) {
        new ToMortgage(ownership).play(this.actualPlayer);
        this.view.ifPresent(v -> v.setButton(this.getNextBoxsActions(ownership, this.actualPlayer)));
    }

    @Override
    public void revokeMortgageOwnership(final Ownership ownership) {
        new ToRevokeMortgage(ownership).play(this.actualPlayer);
        this.view.ifPresent(v -> v.setButton(this.getNextBoxsActions(ownership, this.actualPlayer)));
    }

    @Override
    public void trade(final Ownership firstOwnership, final Ownership seconfOwnership, final Player firstPlayer,
            final Player secondPlayer) {
        ToSellProperties.sellAOwnership(firstOwnership.getContract().getCost(), firstOwnership, this.bank)
                .play(firstPlayer);
        ToBuyProperties.buyAOwnership(firstOwnership.getContract().getCost(), firstOwnership).play(secondPlayer);
        ToSellProperties.sellAOwnership(firstOwnership.getContract().getCost(), seconfOwnership, this.bank)
                .play(secondPlayer);
        ToBuyProperties.buyAOwnership(firstOwnership.getContract().getCost(), seconfOwnership).play(firstPlayer);
        this.view.ifPresent(
                v -> v.setButton(this.getNextBoxsActions(this.boxes.get(this.actualPosition), this.actualPlayer)));
    }

    @Override
    public List<Player> endGame() {
        final List<Player> pl = this.players.stream().filter(p -> !p.equals(this.players.get(0)))
                .sorted((p, p1) -> this.patrimony(p) - this.patrimony(p1)).collect(Collectors.toList());
        return pl;
    }

    private int patrimony(final Player player) {
        Optional<Integer> res = Optional.empty();
        if (player.getOwnerships().isPresent()) {
            res = player.getOwnerships().get().stream().map(o -> o.getContract().getMortgageValue())
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
    // public void nextAction() {
    // }
    //
    // /**
    // * .
    // */
    // public void takeChanche() {
    // }

    /**
     * this method allow to say if last throw of dices return twice result .
     * 
     * @return boolean for dices
     */
    public boolean isTwiceDices() {
        return lastDices.get(0) == lastDices.get(1);
    }

    /**
     * This is a method for the brain of computer Player
     */
    private void computerPlayer() {
        Player p = this.actualPlayer;
        if(this.actualPlayer.isInPrison()){
            //dubbio chieder margherita  devo impostare su false la prison
            
            //ControllaAmount(costo);
            new ToPay(EXIT_PRISON_COST, this.actualPlayer).play(this.actualPlayer);
        }
        this.actualPosition= this.toRollDices();
        Box box=this.actualOwnership;//non è cosi sicuramente
        if (box instanceof Land) {
            final Land land = (Land) box;
            if (land.getOwner().equals(this.bank)) {
                if (this.actualPlayer.getMoney() > this.actualOwnership.getContract().getCost()) {
                    this.buyOwnership(this.actualOwnership);
                    // ToBuyProperties.buyAOwnership(this.actualOwnership.getContract().getCost(),
                    // this.actualOwnership).play(this.actualPlayer);
                } else {
                    // asta
                }
            } else if (land.getOwner().equals(this.actualPlayer)) {
                if (this.actualPlayer.getOwnerships().get().containsAll(land.getGroup().getMembers())
                        && ((LandGroup) land.getGroup()).canBuiling() && this.bank.getLeftBuilding().size() > 0
                        && this.actualPlayer.getMoney() >= ((LandContract) land.getContract()).getCostForEachBuilding() // +
                                                                                                                        // il
                                                                                                                        // costo
                                                                                                                        // medio
                                                                                                                        // dell'affitto
                        && !this.alreadyBuilt) {
                    this.bank.getLeftBuilding().forEach(b -> {
                        if ((((LandGroup) land.getGroup()).getBuildings().size() < 4 && b instanceof Home)// capire
                                || (b instanceof Hotel)) {

                        }
                    });
                }

            } else {
                final int amount = ((ClassicLandContract) land.getContract()).getIncome(new LandIncomeStrategy(land));
                if (amount <= this.actualPlayer.getMoney()) {
                    new ToPay(amount, this.actualPlayer).play(this.actualPlayer);
                    new ToBePaid(amount).play((Player) land.getOwner());
                    this.actualPlayer.setDebts(true);
                } else {
                    // this.notMuchMoney(player, actions);
                }
            }
        } else if (box instanceof Ownership) {
            final Ownership ownership = (Ownership) box;
            if (ownership.getOwner().equals(this.bank)) {
                if (this.actualPlayer.getMoney() > this.actualOwnership.getContract().getCost()) {
                    this.buyOwnership(this.actualOwnership);
                } else {
                    // asta
                }
            } else if (!ownership.getOwner().equals(this.actualPlayer)) {
                final int amount = ownership.getContract().getIncome(ownership instanceof Station
                        ? new StationIncomeStrategy(ownership) : new CompanysIncomeStrategy(ownership,this.actualPlayer));
                if (amount <= this.actualPlayer.getMoney()) {
                    new ToPay(amount, this.actualPlayer).play(this.actualPlayer);
                    new ToBePaid(amount).play((Player) ownership.getOwner());
                    this.actualPlayer.setDebts(true);// impostare che venda
                                                     // automaticamente
                } else {
                   // this.notMuchMoney(player, actions);
                }
            }
        } else {
            if (box instanceof Start) {
                new ToBePaid(Start.getMuchToPick()).play(this.actualPlayer);
            }
            // if (box instanceof PrisonOrTransit) { // NON FANNO NULLA; QUINDI
            // OMETTERLI
            // }
            // if (box instanceof NeutralArea) {
            // }
            if (box instanceof Police) {
                new GoToPrison(this.boxes.get(PRISON_POSITION)).play(this.actualPlayer);
            }
            if (box instanceof DecksBox) {
                new ToDrawCards(this.decks.get(box.getID() == FIRST_CHANCE_POSITION
                        || box.getID() == SECOND_CHANCE_POSITION || box.getID() == THIRD_CHANCE_POSITION ? 0 : 1))
                                .play(this.actualPlayer);
            }
            if (box instanceof TaxImpl) {
                new ToPay(((TaxImpl) box).getCost(), this.actualPlayer).play(this.actualPlayer);
            }
        }
        
        /*
         * * Se player.amount > del costo dell'ownership + il costo dell'affitto
         * medio compra altrimenti partecipa all'asta fino a quando ^ sopra
         *
         *
         * Se player.amount < 0 se puo mortgage else bancarotta
         *
         * se ha 2 proprieta uguali contratta per la 3 offrendo il suo costo +
         * random tra il 10% e il 15%
         *
         * se qualcuno ti propone lo scambio il valore delle properieta e dei
         * soldi devono essere uguali + random tra il 10 e il 15%
         *
         * se puoi costruire costruisci fino a quando i tuoi soldi sono 4 volte
         * il costo medio dell'affitto
         *
         * 
         *
         */
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
                } else if (player.getOwnerships().get().containsAll(land.getGroup().getMembers())
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
            // if (box instanceof PrisonOrTransit) { // NON FANNO NULLA; QUINDI
            // OMETTERLI
            // }
            // if (box instanceof NeutralArea) {
            // }
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
        if (player.getOwnerships().isPresent()) {
            player.getOwnerships().get().stream().forEach(o -> {
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
        if (player.getOwnerships().isPresent()) {
            this.players.forEach(p -> {
                if (p.getOwnerships().isPresent()) {
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
        if (player.getOwnerships().isPresent()) {
            player.getOwnerships().get().stream().filter(o -> o.getGroup() instanceof LandGroup)
                    .filter(o -> ((LandGroup) o.getGroup()).getBuildings().size() > 0).forEach(o -> {
                        ((LandGroup) o.getGroup()).getBuildings().forEach(b -> actions.add("Vendi edificio"));
                    });
            if (actions.isEmpty()) {
                player.getOwnerships().get().stream().forEach(o -> {
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
