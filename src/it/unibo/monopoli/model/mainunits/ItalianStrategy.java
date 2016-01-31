package it.unibo.monopoli.model.mainunits;

import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.monopoli.model.actions.AuctionOfOwnership;
import it.unibo.monopoli.model.actions.ClassicAuction;
import it.unibo.monopoli.model.actions.ToAuction;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.cards.ImprevistiCards;
import it.unibo.monopoli.model.cards.ProbabilitàCards;
import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.ChanceCards;
import it.unibo.monopoli.model.cards.ClassicCard;
import it.unibo.monopoli.model.cards.ClassicDeck;
import it.unibo.monopoli.model.cards.ClassicDecks;
import it.unibo.monopoli.model.cards.CommunityChestCards;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.ClassicContract;
import it.unibo.monopoli.model.table.ClassicLand;
import it.unibo.monopoli.model.table.ClassicLandContract;
import it.unibo.monopoli.model.table.ClassicLandGroup;
import it.unibo.monopoli.model.table.Company;
import it.unibo.monopoli.model.table.Contract;
import it.unibo.monopoli.model.table.DecksBox;
import it.unibo.monopoli.model.table.Group;
import it.unibo.monopoli.model.table.Home;
import it.unibo.monopoli.model.table.Hotel;
import it.unibo.monopoli.model.table.NeutralArea;
import it.unibo.monopoli.model.table.Ownership;
import it.unibo.monopoli.model.table.Police;
import it.unibo.monopoli.model.table.PrisonOrTransit;
import it.unibo.monopoli.model.table.Start;
import it.unibo.monopoli.model.table.Station;
import it.unibo.monopoli.model.table.TaxImpl;

/**
 * This is an implementation of {@link GameStrategy} for initialize the game
 * with the italian version of Monopoly.
 *
 */
public class ItalianStrategy implements GameStrategy {

    private static final int N_MAX_OF_HOUSES = 32;
    private static final int N_MAX_OF_HOTELS = 12;
    private static final int AMOUNT_OF_FEES = 10;

    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 6;
    private static final int CALCULATE_OWNERSHIP = 9;
    private static final int CALCULATE_MONEY = 50;

    private final List<Player> players;
    private final List<Ownership> ownerships;
    private final List<Group> groups;
    private final List<Contract> contracts;
    private final List<Home> homes;
    private final List<Hotel> hotels;
    private List<Box> allBoxes;
    private final List<Deck> decks;
    private final Bank bank;

    /**
     * Constructs a new instance of {@link ItalianStrategy} that needs all the
     * {@link Player}s to be initialized.
     * 
     * @param players
     *            - a {@link List} of all the current {@link Player}s
     */
    public ItalianStrategy(final List<Player> players) {
        this.ownerships = new LinkedList<>();
        this.inizializesOwnerships();
        this.groups = new LinkedList<>();
        this.inizializesGroups();
        this.contracts = new LinkedList<>();
        this.inizializesContracts();
        this.homes = new LinkedList<>();
        this.hotels = new LinkedList<>();
        this.inizializesBuildings();
        final List<Ownership> banksOwnerships = new LinkedList<>();
        banksOwnerships.addAll(this.ownerships);
        this.bank = new ClassicBank(banksOwnerships, this.homes, this.hotels);
        this.players = players;
        this.inizializesPlayers(players);
        this.decks = new LinkedList<>();
        this.inizializesDecks();
        this.allBoxes = new LinkedList<>();
        this.inizializesBoxes();
    }

    private void inizializesOwnerships() {
        this.ownerships.add(new ClassicLand("VICOLO CORTO", BoxesPositions.OWNERSHIP1_POSITION.getPos(), this.bank,
                Color.DARK_GRAY));
        this.ownerships.add(new ClassicLand("VICOLO STRETTO", BoxesPositions.OWNERSHIP2_POSITION.getPos(), this.bank,
                Color.DARK_GRAY));
        this.ownerships.add(new Station("STAZIONE SUD", BoxesPositions.OWNERSHIP3_POSITION.getPos(), this.bank));
        this.ownerships.add(new ClassicLand("BASTIONI GRAN SASSO", BoxesPositions.OWNERSHIP4_POSITION.getPos(),
                this.bank, Color.CYAN));
        this.ownerships.add(
                new ClassicLand("VIALE MONTEROSA", BoxesPositions.OWNERSHIP5_POSITION.getPos(), this.bank, Color.CYAN));
        this.ownerships.add(new ClassicLand("VIALE VESUVIO", BoxesPositions.OWNERSHIP6_POSITION.getPos(), this.bank,
                Color.CYAN));
        this.ownerships.add(
                new ClassicLand("VIA ACCADEMIA", BoxesPositions.OWNERSHIP7_POSITION.getPos(), this.bank, Color.MAGENTA));
        this.ownerships.add(new Company("SOCIETÀ ELETTRICA", BoxesPositions.OWNERSHIP8_POSITION.getPos(), this.bank));
        this.ownerships.add(
                new ClassicLand("CORSO ATENEO", BoxesPositions.OWNERSHIP9_POSITION.getPos(), this.bank, Color.MAGENTA));
        this.ownerships.add(new ClassicLand("PIAZZA UNIVERSITÀ", BoxesPositions.OWNERSHIP10_POSITION.getPos(),
                this.bank, Color.MAGENTA));
        this.ownerships.add(new Station("STAZIONE OVEST", BoxesPositions.OWNERSHIP11_POSITION.getPos(), this.bank));
        this.ownerships.add(
                new ClassicLand("VIA VERDI", BoxesPositions.OWNERSHIP12_POSITION.getPos(), this.bank, Color.ORANGE));
        this.ownerships.add(new ClassicLand("CORSO RAFFAELLO", BoxesPositions.OWNERSHIP13_POSITION.getPos(),
                this.bank, Color.ORANGE));
        this.ownerships.add(
                new ClassicLand("PIAZZA DANTE", BoxesPositions.OWNERSHIP14_POSITION.getPos(), this.bank, Color.ORANGE));
        this.ownerships
                .add(new ClassicLand("VIA MARCO POLO", BoxesPositions.OWNERSHIP15_POSITION.getPos(), this.bank, Color.RED));
        this.ownerships.add(
                new ClassicLand("CORSO MAGELLANO", BoxesPositions.OWNERSHIP16_POSITION.getPos(), this.bank, Color.RED));
        this.ownerships.add(new ClassicLand("LARGO COLOMBO", BoxesPositions.OWNERSHIP17_POSITION.getPos(), this.bank,
                Color.RED));
        this.ownerships
                .add(new Station("STAZIONE NORD", BoxesPositions.OWNERSHIP18_POSITION.getPos(), this.bank));
        this.ownerships.add(new ClassicLand("VIALE COSTANTINO", BoxesPositions.OWNERSHIP19_POSITION.getPos(), this.bank,
                Color.YELLOW));
        this.ownerships.add(new ClassicLand("VIALE TRAIANO", BoxesPositions.OWNERSHIP20_POSITION.getPos(), this.bank,
                Color.YELLOW));
        this.ownerships.add(new Company("SOCIETÀ ACQUA POTABILE", BoxesPositions.OWNERSHIP21_POSITION.getPos(), this.bank));
        this.ownerships.add(
                new ClassicLand("PIAZZA GIULIO CESARE", BoxesPositions.OWNERSHIP22_POSITION.getPos(), this.bank, Color.YELLOW));
        this.ownerships.add(
                new ClassicLand("VIA ROMA", BoxesPositions.OWNERSHIP23_POSITION.getPos(), this.bank, Color.GREEN));
        this.ownerships.add(
                new ClassicLand("CORSO IMPERO", BoxesPositions.OWNERSHIP24_POSITION.getPos(), this.bank, Color.GREEN));
        this.ownerships.add(
                new ClassicLand("LARGO AUGUSTO", BoxesPositions.OWNERSHIP25_POSITION.getPos(), this.bank, Color.GREEN));
        this.ownerships
                .add(new Station("STAZIONE EST", BoxesPositions.OWNERSHIP26_POSITION.getPos(), this.bank));
        this.ownerships
                .add(new ClassicLand("VIALE DEI GIARDINI", BoxesPositions.OWNERSHIP27_POSITION.getPos(), this.bank, Color.BLUE));
        this.ownerships
                .add(new ClassicLand("PARCO DELLA VITTORIA", BoxesPositions.OWNERSHIP28_POSITION.getPos(), this.bank, Color.BLUE));
    }

    private void inizializesGroups() {
        this.groups.add(new ClassicLandGroup("Grigio scuro", this.ownerships.get(BoxesPositions.INDEX_0.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_1.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_0.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_0.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_1.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_0.getPos()));
        this.groups.add(new ClassicLandGroup("Ciano", this.ownerships.get(BoxesPositions.INDEX_3.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_4.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_5.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_3.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_1.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_4.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_1.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_5.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_1.getPos()));
        this.groups.add(new ClassicLandGroup("Magenta", this.ownerships.get(BoxesPositions.INDEX_6.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_8.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_9.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_6.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_2.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_8.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_2.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_9.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_2.getPos()));
        this.groups.add(new ClassicLandGroup("Arancione", this.ownerships.get(BoxesPositions.INDEX_11.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_12.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_13.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_11.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_3.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_12.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_3.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_13.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_3.getPos()));
        this.groups.add(new ClassicLandGroup("Rosso", this.ownerships.get(BoxesPositions.INDEX_14.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_15.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_16.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_14.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_4.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_15.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_4.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_16.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_4.getPos()));
        this.groups.add(new ClassicLandGroup("Giallo", this.ownerships.get(BoxesPositions.INDEX_18.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_19.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_21.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_18.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_5.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_19.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_5.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_21.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_5.getPos()));
        this.groups.add(new ClassicLandGroup("Verde", this.ownerships.get(BoxesPositions.INDEX_22.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_23.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_24.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_22.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_6.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_23.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_6.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_24.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_6.getPos()));
        this.groups.add(new ClassicLandGroup("Blu", this.ownerships.get(BoxesPositions.INDEX_26.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_27.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_26.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_7.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_27.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_7.getPos()));
        this.groups.add(new ClassicLandGroup("Stazioni", this.ownerships.get(BoxesPositions.INDEX_2.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_10.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_17.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_25.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_2.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_8.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_10.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_8.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_17.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_8.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_25.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_8.getPos()));
        this.groups.add(new ClassicLandGroup("Società", this.ownerships.get(BoxesPositions.INDEX_7.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_20.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_7.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_9.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_20.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_9.getPos()));
    }

    private void inizializesContracts() {
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_0.getPos()))
                        .landCost(Ownerships.OWNERSHIP1.getCost()).landIncome(Ownerships.OWNERSHIP1.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP1.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_0.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_0.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_1.getPos()))
                        .landCost(Ownerships.OWNERSHIP2.getCost()).landIncome(Ownerships.OWNERSHIP2.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP2.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_1.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_1.getPos()));
        this.contracts.add(new ClassicContract.Builder().ownership(this.ownerships.get(BoxesPositions.INDEX_2.getPos()))
                        .ownershipsCost(Ownerships.OWNERSHIP3.getCost()).build());
        this.ownerships.get(BoxesPositions.INDEX_2.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_2.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_3.getPos()))
                        .landCost(Ownerships.OWNERSHIP4.getCost()).landIncome(Ownerships.OWNERSHIP4.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP4.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_3.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_3.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_4.getPos()))
                        .landCost(Ownerships.OWNERSHIP5.getCost()).landIncome(Ownerships.OWNERSHIP5.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP5.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_4.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_4.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_5.getPos()))
                        .landCost(Ownerships.OWNERSHIP6.getCost()).landIncome(Ownerships.OWNERSHIP6.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP6.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_5.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_5.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_6.getPos()))
                        .landCost(Ownerships.OWNERSHIP7.getCost()).landIncome(Ownerships.OWNERSHIP7.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP7.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_6.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_6.getPos()));
        this.contracts.add(new ClassicContract.Builder().ownership(this.ownerships.get(BoxesPositions.INDEX_7.getPos()))
                        .ownershipsCost(Ownerships.OWNERSHIP8.getCost()).build());
        this.ownerships.get(BoxesPositions.INDEX_7.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_7.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_8.getPos()))
                        .landCost(Ownerships.OWNERSHIP9.getCost()).landIncome(Ownerships.OWNERSHIP9.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP9.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_8.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_8.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_9.getPos()))
                        .landCost(Ownerships.OWNERSHIP10.getCost()).landIncome(Ownerships.OWNERSHIP10.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP10.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_9.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_9.getPos()));
        this.contracts.add(new ClassicContract.Builder().ownership(this.ownerships.get(BoxesPositions.INDEX_10.getPos()))
                        .ownershipsCost(Ownerships.OWNERSHIP11.getCost()).build());
        this.ownerships.get(BoxesPositions.INDEX_10.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_10.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_11.getPos()))
                        .landCost(Ownerships.OWNERSHIP12.getCost()).landIncome(Ownerships.OWNERSHIP12.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP12.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_11.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_11.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_12.getPos()))
                        .landCost(Ownerships.OWNERSHIP13.getCost()).landIncome(Ownerships.OWNERSHIP13.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP13.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_12.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_12.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_13.getPos()))
                        .landCost(Ownerships.OWNERSHIP14.getCost()).landIncome(Ownerships.OWNERSHIP14.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP14.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_13.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_13.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_14.getPos()))
                        .landCost(Ownerships.OWNERSHIP15.getCost()).landIncome(Ownerships.OWNERSHIP15.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP15.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_14.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_14.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_15.getPos()))
                        .landCost(Ownerships.OWNERSHIP16.getCost()).landIncome(Ownerships.OWNERSHIP16.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP16.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_15.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_15.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_16.getPos()))
                        .landCost(Ownerships.OWNERSHIP17.getCost()).landIncome(Ownerships.OWNERSHIP17.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP17.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_16.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_16.getPos()));
        this.contracts.add(new ClassicContract.Builder().ownership(this.ownerships.get(BoxesPositions.INDEX_17.getPos()))
                        .ownershipsCost(Ownerships.OWNERSHIP18.getCost()).build());
        this.ownerships.get(BoxesPositions.INDEX_17.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_17.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_18.getPos()))
                        .landCost(Ownerships.OWNERSHIP19.getCost()).landIncome(Ownerships.OWNERSHIP19.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP19.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_18.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_18.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_19.getPos()))
                        .landCost(Ownerships.OWNERSHIP20.getCost()).landIncome(Ownerships.OWNERSHIP20.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP20.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_19.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_19.getPos()));
        this.contracts.add(new ClassicContract.Builder().ownership(this.ownerships.get(BoxesPositions.INDEX_20.getPos()))
                        .ownershipsCost(Ownerships.OWNERSHIP21.getCost()).build());
        this.ownerships.get(BoxesPositions.INDEX_20.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_20.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_21.getPos()))
                        .landCost(Ownerships.OWNERSHIP22.getCost()).landIncome(Ownerships.OWNERSHIP22.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP22.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_21.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_21.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_22.getPos()))
                        .landCost(Ownerships.OWNERSHIP23.getCost()).landIncome(Ownerships.OWNERSHIP23.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP23.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_22.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_22.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_23.getPos()))
                        .landCost(Ownerships.OWNERSHIP24.getCost()).landIncome(Ownerships.OWNERSHIP24.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP24.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_23.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_23.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_24.getPos()))
                        .landCost(Ownerships.OWNERSHIP25.getCost()).landIncome(Ownerships.OWNERSHIP25.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP25.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_24.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_24.getPos()));
        this.contracts.add(new ClassicContract.Builder().ownership(this.ownerships.get(BoxesPositions.INDEX_25.getPos()))
                        .ownershipsCost(Ownerships.OWNERSHIP26.getCost()).build());
        this.ownerships.get(BoxesPositions.INDEX_25.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_25.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_26.getPos()))
                        .landCost(Ownerships.OWNERSHIP27.getCost()).landIncome(Ownerships.OWNERSHIP27.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP27.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_26.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_26.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_27.getPos()))
                        .landCost(Ownerships.OWNERSHIP28.getCost()).landIncome(Ownerships.OWNERSHIP28.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP28.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_27.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_27.getPos()));
    }

    private void inizializesBuildings() {
        for (int i = 0; i < N_MAX_OF_HOUSES; i++) {
            this.homes.add(new Home());
        }
        for (int i = 0; i < N_MAX_OF_HOTELS; i++) {
            this.hotels.add(new Hotel());
        }
    }

    private void inizializesPlayers(final List<Player> players) {
        if (players.size() < MIN_PLAYERS || players.size() > MAX_PLAYERS) {
            throw new IllegalArgumentException("Minimo giocatori: 2. Massimo giocatori: 6");
        }
        players.stream().forEach(p -> {
            final int i = (CALCULATE_OWNERSHIP - players.size());
            this.addOwnerships(i, p);
            p.setMoney(i * CALCULATE_MONEY);
        });
    }

    private void addOwnerships(final int nOfOwnership, final Player player) {
        for (int i = 0; i < nOfOwnership; i++) {
            final Ownership ow = this.bank.getOwnership();
            player.addOwnership(ow);
            ow.setOwner(player);
        }
    }

    private void inizializesDecks() {
        final List<Card> chance = new LinkedList<>();
        chance.add(new ClassicCard(ImprevistiCards.CARD1.getDescription(), ImprevistiCards.CARD1.getID(),
                ImprevistiCards.CARD1.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD2.getDescription(), ImprevistiCards.CARD2.getID(),
                ImprevistiCards.CARD2.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD3.getDescription(), ImprevistiCards.CARD3.getID(),
                ImprevistiCards.CARD3.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD4.getDescription(), ImprevistiCards.CARD4.getID(),
                ImprevistiCards.CARD4.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD5.getDescription(), ImprevistiCards.CARD5.getID(),
                ImprevistiCards.CARD5.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD6.getDescription(), ImprevistiCards.CARD6.getID(),
                ImprevistiCards.CARD6.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD7.getDescription(), ImprevistiCards.CARD7.getID(),
                ImprevistiCards.CARD7.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD8.getDescription(), ImprevistiCards.CARD8.getID(),
                ImprevistiCards.CARD8.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD9.getDescription(), ImprevistiCards.CARD9.getID(),
                ImprevistiCards.CARD9.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD10.getDescription(), ImprevistiCards.CARD10.getID(),
                ImprevistiCards.CARD10.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD11.getDescription(), ImprevistiCards.CARD11.getID(),
                ImprevistiCards.CARD11.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD12.getDescription(), ImprevistiCards.CARD12.getID(),
                ImprevistiCards.CARD12.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD13.getDescription(), ImprevistiCards.CARD13.getID(),
                ImprevistiCards.CARD13.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD14.getDescription(), ImprevistiCards.CARD14.getID(),
                ImprevistiCards.CARD14.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD15.getDescription(), ImprevistiCards.CARD15.getID(),
                ImprevistiCards.CARD15.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD16.getDescription(), ImprevistiCards.CARD16.getID(),
                ImprevistiCards.CARD16.getActions()));
        this.decks.add(new ClassicDeck(ClassicDecks.CHANCE.getName(), chance));
        final List<Card> chest = new LinkedList<>();
        chest.add(new ClassicCard(ProbabilitàCards.CARD1.getDescription(), ProbabilitàCards.CARD1.getID(),
                ProbabilitàCards.CARD1.getActions()));
        chest.add(new ClassicCard(ProbabilitàCards.CARD2.getDescription(), ProbabilitàCards.CARD2.getID(),
                ProbabilitàCards.CARD2.getActions()));
        chest.add(new ClassicCard(ProbabilitàCards.CARD3.getDescription(), ProbabilitàCards.CARD3.getID(),
                ProbabilitàCards.CARD3.getActions()));
        chest.add(new ClassicCard(ProbabilitàCards.CARD4.getDescription(), ProbabilitàCards.CARD4.getID(),
                ProbabilitàCards.CARD4.getActions()));
        chest.add(new ClassicCard(ProbabilitàCards.CARD5.getDescription(), ProbabilitàCards.CARD5.getID(),
                ProbabilitàCards.CARD5.getActions()));
        chest.add(new ClassicCard(ProbabilitàCards.CARD6.getDescription(), ProbabilitàCards.CARD6.getID(),
                ProbabilitàCards.CARD6.getActions()));
        chest.add(new ClassicCard(ProbabilitàCards.CARD7.getDescription(), ProbabilitàCards.CARD7.getID(),
                ProbabilitàCards.CARD7.getActions()));
        chest.add(new ClassicCard(ProbabilitàCards.CARD8.getDescription(), ProbabilitàCards.CARD8.getID(),
                ProbabilitàCards.CARD8.getActions()));
        chest.add(new ClassicCard(ProbabilitàCards.CARD9.getDescription(), ProbabilitàCards.CARD9.getID(),
                ProbabilitàCards.CARD9.getActions()));
        chest.add(new ClassicCard(ProbabilitàCards.CARD10.getDescription(), ProbabilitàCards.CARD10.getID(),
                ProbabilitàCards.CARD10.getActions()));
        chest.add(new ClassicCard(ProbabilitàCards.CARD11.getDescription(), ProbabilitàCards.CARD11.getID(),
                ProbabilitàCards.CARD11.getActions()));
        chest.add(new ClassicCard(ProbabilitàCards.CARD12.getDescription(), ProbabilitàCards.CARD12.getID(),
                ProbabilitàCards.CARD12.getActions()));
        chest.add(new ClassicCard(ProbabilitàCards.CARD13.getDescription(), ProbabilitàCards.CARD13.getID(),
                ProbabilitàCards.CARD13.getActions()));
        chest.add(new ClassicCard(ProbabilitàCards.CARD14.getDescription(), ProbabilitàCards.CARD14.getID(),
                ProbabilitàCards.CARD14.getActions()));
        chest.add(new ClassicCard(ProbabilitàCards.CARD15.getDescription(), ProbabilitàCards.CARD15.getID(),
                ProbabilitàCards.CARD15.getActions()));
        chest.add(new ClassicCard(ProbabilitàCards.CARD16.getDescription(), ProbabilitàCards.CARD16.getID(),
                ProbabilitàCards.CARD16.getActions()));
        this.decks.add(new ClassicDeck(ClassicDecks.COMMUNITY_CHEST.getName(), chest));
    }

    private void inizializesBoxes() {
        final List<Box> temp = new LinkedList<>();
        // final Box[] boxes = new Box[40];
        // this.ownerships.stream().forEach(o -> {
        // boxes[o.getID()] = o;
        // });
        temp.add(new Start("VIA", BoxesPositions.START_POSITION.getPos()));
        final PrisonOrTransit prison = new PrisonOrTransit("IN JAIL OR JUST VISITING",
                BoxesPositions.PRISON_POSITION.getPos());
        temp.add(prison);
        temp.add(new NeutralArea("PARCHEGGIO GRATUITO", BoxesPositions.NEUTRAL_AREA_POSITION.getPos()));
        temp.add(new Police("IN PRIGIONE!", BoxesPositions.POLICE_POSITION.getPos(), prison));
        temp.add(new DecksBox("IMPREVISTI", BoxesPositions.FIRST_CHANCE_POSITION.getPos(), this.decks.get(0)));
        temp.add(new DecksBox("IMPREVISTI", BoxesPositions.SECOND_CHANCE_POSITION.getPos(), this.decks.get(0)));
        temp.add(new DecksBox("IMPREVISTI", BoxesPositions.THIRD_CHANCE_POSITION.getPos(), this.decks.get(0)));
        temp.add(new DecksBox("PROBABILITÀ", BoxesPositions.FIRST_COMMUNITY_CHEST_POSITION.getPos(),
                this.decks.get(1)));
        temp.add(new DecksBox("PROBABILITÀ", BoxesPositions.SECOND_COMMUNITY_CHEST_POSITION.getPos(),
                this.decks.get(1)));
        temp.add(new DecksBox("PROBABILITÀ", BoxesPositions.THIRD_COMMUNITY_CHEST_POSITION.getPos(),
                this.decks.get(1)));
        temp.add(new TaxImpl("TASSA PATRIMONIALE", BoxesPositions.INCOME_TAX_POSITION.getPos(), AMOUNT_OF_FEES));
        temp.add(new TaxImpl("TASSA DI LUSSO", BoxesPositions.SUPER_TAX_POSITION.getPos(), AMOUNT_OF_FEES));
        temp.addAll(this.ownerships);
        this.allBoxes = temp.stream().sorted(new Comparator<Box>() {

            @Override
            public int compare(final Box o1, final Box o2) {
                return o1.getID() - o2.getID();
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }

    @Override
    public Bank getBank() {
        return this.bank;
    }

    @Override
    public List<Box> getBoxes() {
        return Collections.unmodifiableList(this.allBoxes);
    }

    @Override
    public List<Deck> getDecks() {
        return Collections.unmodifiableList(this.decks);
    }

    @Override
    public AuctionOfOwnership toAuction(final Ownership ownership, final Player player) {
        return ToAuction.ownerships(this.players, new ClassicAuction(), ownership, this.bank).getAuction(player);
    }
}
