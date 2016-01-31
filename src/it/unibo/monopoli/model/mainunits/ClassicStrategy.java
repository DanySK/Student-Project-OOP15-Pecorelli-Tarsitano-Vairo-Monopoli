package it.unibo.monopoli.model.mainunits;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.monopoli.controller.Actions;
import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.actions.AuctionOfOwnership;
import it.unibo.monopoli.model.actions.ClassicAuction;
import it.unibo.monopoli.model.actions.ClassicDicesStrategy;
import it.unibo.monopoli.model.actions.GoToPrison;
import it.unibo.monopoli.model.actions.MoveUpTo;
import it.unibo.monopoli.model.actions.ToAuction;
import it.unibo.monopoli.model.actions.ToBePaid;
import it.unibo.monopoli.model.actions.ToBuyProperties;
import it.unibo.monopoli.model.actions.ToDrawCards;
import it.unibo.monopoli.model.actions.ToPay;
import it.unibo.monopoli.model.actions.ToRollDices;
import it.unibo.monopoli.model.actions.ToSellProperties;
import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.ChanceCards;
import it.unibo.monopoli.model.cards.CommunityChestCards;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.cards.Decks;
import it.unibo.monopoli.model.mainunits.BoxesPositions.*;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.ClassicContract;
import it.unibo.monopoli.model.table.ClassicLand;
import it.unibo.monopoli.model.table.ClassicLandContract;
import it.unibo.monopoli.model.table.ClassicLandGroup;
import it.unibo.monopoli.model.table.Company;
import it.unibo.monopoli.model.table.CompanysIncomeStrategy;
import it.unibo.monopoli.model.table.Contract;
import it.unibo.monopoli.model.table.DecksBox;
import it.unibo.monopoli.model.table.Group;
import it.unibo.monopoli.model.table.Home;
import it.unibo.monopoli.model.table.Hotel;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.LandGroup;
import it.unibo.monopoli.model.table.LandIncomeStrategy;
import it.unibo.monopoli.model.table.NeutralArea;
import it.unibo.monopoli.model.table.Ownership;
import it.unibo.monopoli.model.table.Police;
import it.unibo.monopoli.model.table.PrisonOrTransit;
import it.unibo.monopoli.model.table.Start;
import it.unibo.monopoli.model.table.Station;
import it.unibo.monopoli.model.table.StationIncomeStrategy;
import it.unibo.monopoli.model.table.TaxImpl;

/**
 * This is an implementation of {@link GameStrategy} for initialize the game
 * with the classic version of Monopoly.
 *
 */
public class ClassicStrategy implements GameStrategy {

    private static final int N_MAX_OF_HOUSES = 32;
    private static final int N_MAX_OF_HOTELS = 12;
    private static final int AMOUNT_OF_FEES = 10;
  
    private static final int MAX_PLAYERS = 6;
    private static final int CALCULATE_OWNERSHIP = 9;
    private static final int CALCULATE_MONEY = 50;

    private static final int CARD1_STEPS = -3;
    private static final int CARD6_19_22_PAY = 50;
    private static final int CARD16_PAY = 150;
    private static final int CARD27_PAY = 25;
    private static final int CARD28_PAY = 200;
    private static final int CARD31_PAY = 20;
    private static final int CARD_TAX = 50;

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
     * Constructs a new instance of {@link ClassicStrategy} that needs all the
     * {@link Player}s to be initialized.
     * 
     * @param players
     *            - a {@link List} of all the current {@link Player}s
     */
    public ClassicStrategy(final List<Player> players) {
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
        this.ownerships.add(new ClassicLand("OLD KENT ROAD", BoxesPositions.OWNERSHIP1_POSITION.getPos(), this.bank,
                Color.DARK_GRAY));
        this.ownerships.add(new ClassicLand("WHITECHAPEL ROAD", BoxesPositions.OWNERSHIP2_POSITION.getPos(), this.bank,
                Color.DARK_GRAY));
        this.ownerships.add(new Station("KINGS CROSS STATION", BoxesPositions.OWNERSHIP3_POSITION.getPos(), this.bank));
        this.ownerships.add(new ClassicLand("THE ANGEL, ISLINGTON", BoxesPositions.OWNERSHIP4_POSITION.getPos(),
                this.bank, Color.CYAN));
        this.ownerships.add(
                new ClassicLand("EUSTON ROAD", BoxesPositions.OWNERSHIP5_POSITION.getPos(), this.bank, Color.CYAN));
        this.ownerships.add(new ClassicLand("PENTONVILLE ROAD", BoxesPositions.OWNERSHIP6_POSITION.getPos(), this.bank,
                Color.CYAN));
        this.ownerships.add(
                new ClassicLand("PALL MALL", BoxesPositions.OWNERSHIP7_POSITION.getPos(), this.bank, Color.MAGENTA));
        this.ownerships.add(new Company("ELETTRIC COMPANY", BoxesPositions.OWNERSHIP8_POSITION.getPos(), this.bank));
        this.ownerships.add(
                new ClassicLand("WHITEHALL", BoxesPositions.OWNERSHIP9_POSITION.getPos(), this.bank, Color.MAGENTA));
        this.ownerships.add(new ClassicLand("NORTHUMRL'D AVENUE", BoxesPositions.OWNERSHIP10_POSITION.getPos(),
                this.bank, Color.MAGENTA));
        this.ownerships.add(new Station("MARYLEBONE STATION", BoxesPositions.OWNERSHIP11_POSITION.getPos(), this.bank));
        this.ownerships.add(
                new ClassicLand("BOW STREET", BoxesPositions.OWNERSHIP12_POSITION.getPos(), this.bank, Color.ORANGE));
        this.ownerships.add(new ClassicLand("MARLBOROUGH STREET", BoxesPositions.OWNERSHIP13_POSITION.getPos(),
                this.bank, Color.ORANGE));
        this.ownerships.add(
                new ClassicLand("VINE STREET", BoxesPositions.OWNERSHIP14_POSITION.getPos(), this.bank, Color.ORANGE));
        this.ownerships
                .add(new ClassicLand("STRAND", BoxesPositions.OWNERSHIP15_POSITION.getPos(), this.bank, Color.RED));
        this.ownerships.add(
                new ClassicLand("FLEET STREET", BoxesPositions.OWNERSHIP16_POSITION.getPos(), this.bank, Color.RED));
        this.ownerships.add(new ClassicLand("TRAFALGAR SQUARE", BoxesPositions.OWNERSHIP17_POSITION.getPos(), this.bank,
                Color.RED));
        this.ownerships
                .add(new Station("FENCHURCH ST. STATION", BoxesPositions.OWNERSHIP18_POSITION.getPos(), this.bank));
        this.ownerships.add(new ClassicLand("LEICESTER SQUARE", BoxesPositions.OWNERSHIP19_POSITION.getPos(), this.bank,
                Color.YELLOW));
        this.ownerships.add(new ClassicLand("COVENTRY STREET", BoxesPositions.OWNERSHIP20_POSITION.getPos(), this.bank,
                Color.YELLOW));
        this.ownerships.add(new Company("WATER COMPANY", BoxesPositions.OWNERSHIP21_POSITION.getPos(), this.bank));
        this.ownerships.add(
                new ClassicLand("PICCADILLY", BoxesPositions.OWNERSHIP22_POSITION.getPos(), this.bank, Color.YELLOW));
        this.ownerships.add(
                new ClassicLand("REGENT STREET", BoxesPositions.OWNERSHIP23_POSITION.getPos(), this.bank, Color.GREEN));
        this.ownerships.add(
                new ClassicLand("OXFORD STREET", BoxesPositions.OWNERSHIP24_POSITION.getPos(), this.bank, Color.GREEN));
        this.ownerships.add(
                new ClassicLand("BOND STREET", BoxesPositions.OWNERSHIP25_POSITION.getPos(), this.bank, Color.GREEN));
        this.ownerships
                .add(new Station("LIVERPOOL ST. STATION", BoxesPositions.OWNERSHIP26_POSITION.getPos(), this.bank));
        this.ownerships
                .add(new ClassicLand("PARK LANE", BoxesPositions.OWNERSHIP27_POSITION.getPos(), this.bank, Color.BLUE));
        this.ownerships
                .add(new ClassicLand("MAYFAIR", BoxesPositions.OWNERSHIP28_POSITION.getPos(), this.bank, Color.BLUE));
    }

    private void inizializesGroups() {
        this.groups.add(new ClassicLandGroup("Dark Gray", this.ownerships.get(BoxesPositions.INDEX_1.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_2.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_1.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_0.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_2.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_0.getPos()));
        this.groups.add(new ClassicLandGroup("Cyan", this.ownerships.get(BoxesPositions.INDEX_4.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_5.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_6.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_4.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_1.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_5.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_1.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_6.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_1.getPos()));
        this.groups.add(new ClassicLandGroup("Magenta", this.ownerships.get(BoxesPositions.INDEX_7.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_9.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_10.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_7.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_2.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_9.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_2.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_10.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_2.getPos()));
        this.groups.add(new ClassicLandGroup("Orange", this.ownerships.get(BoxesPositions.INDEX_12.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_13.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_14.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_12.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_3.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_13.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_3.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_14.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_3.getPos()));
        this.groups.add(new ClassicLandGroup("Red", this.ownerships.get(BoxesPositions.INDEX_15.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_16.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_17.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_15.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_4.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_16.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_4.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_17.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_4.getPos()));
        this.groups.add(new ClassicLandGroup("Yellow", this.ownerships.get(BoxesPositions.INDEX_19.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_20.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_22.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_19.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_5.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_20.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_5.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_22.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_5.getPos()));
        this.groups.add(new ClassicLandGroup("Green", this.ownerships.get(BoxesPositions.INDEX_23.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_24.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_25.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_23.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_6.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_24.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_6.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_25.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_6.getPos()));
        this.groups.add(new ClassicLandGroup("Blue", this.ownerships.get(BoxesPositions.INDEX_27.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_28.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_27.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_7.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_28.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_7.getPos()));
        this.groups.add(new ClassicLandGroup("Stations", this.ownerships.get(BoxesPositions.INDEX_3.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_11.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_18.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_26.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_3.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_8.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_11.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_8.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_18.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_8.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_26.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_8.getPos()));
        this.groups.add(new ClassicLandGroup("Companies", this.ownerships.get(BoxesPositions.INDEX_8.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_21.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_8.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_9.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_21.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_9.getPos()));
    }

    private void inizializesContracts() {
        this.contracts.add(BoxesPositions.INDEX_1.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_1.getPos()))
                        .landCost(Ownerships.OWNERSHIP1.getCost()).landIncome(Ownerships.OWNERSHIP1.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP1.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_1.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_1.getPos()));
        this.contracts.add(BoxesPositions.INDEX_2.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_2.getPos()))
                        .landCost(Ownerships.OWNERSHIP2.getCost()).landIncome(Ownerships.OWNERSHIP2.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP2.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_2.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_2.getPos()));
        this.contracts.add(BoxesPositions.INDEX_3.getPos(),
                new ClassicContract.Builder().ownership(this.ownerships.get(BoxesPositions.INDEX_3.getPos()))
                        .ownershipsCost(Ownerships.OWNERSHIP3.getCost()).build());
        this.ownerships.get(BoxesPositions.INDEX_3.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_3.getPos()));
        this.contracts.add(BoxesPositions.INDEX_4.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_4.getPos()))
                        .landCost(Ownerships.OWNERSHIP4.getCost()).landIncome(Ownerships.OWNERSHIP4.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP4.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_4.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_4.getPos()));
        this.contracts.add(BoxesPositions.INDEX_5.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_5.getPos()))
                        .landCost(Ownerships.OWNERSHIP5.getCost()).landIncome(Ownerships.OWNERSHIP5.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP5.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_5.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_5.getPos()));
        this.contracts.add(BoxesPositions.INDEX_6.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_6.getPos()))
                        .landCost(Ownerships.OWNERSHIP6.getCost()).landIncome(Ownerships.OWNERSHIP6.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP6.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_6.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_6.getPos()));
        this.contracts.add(BoxesPositions.INDEX_7.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_7.getPos()))
                        .landCost(Ownerships.OWNERSHIP7.getCost()).landIncome(Ownerships.OWNERSHIP7.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP7.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_7.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_7.getPos()));
        this.contracts.add(BoxesPositions.INDEX_8.getPos(),
                new ClassicContract.Builder().ownership(this.ownerships.get(BoxesPositions.INDEX_8.getPos()))
                        .ownershipsCost(Ownerships.OWNERSHIP8.getCost()).build());
        this.ownerships.get(BoxesPositions.INDEX_8.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_8.getPos()));
        this.contracts.add(BoxesPositions.INDEX_9.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_9.getPos()))
                        .landCost(Ownerships.OWNERSHIP9.getCost()).landIncome(Ownerships.OWNERSHIP9.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP9.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_9.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_9.getPos()));
        this.contracts.add(BoxesPositions.INDEX_10.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_10.getPos()))
                        .landCost(Ownerships.OWNERSHIP10.getCost()).landIncome(Ownerships.OWNERSHIP10.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP10.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_10.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_10.getPos()));
        this.contracts.add(BoxesPositions.INDEX_11.getPos(),
                new ClassicContract.Builder().ownership(this.ownerships.get(BoxesPositions.INDEX_11.getPos()))
                        .ownershipsCost(Ownerships.OWNERSHIP11.getCost()).build());
        this.ownerships.get(BoxesPositions.INDEX_11.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_11.getPos()));
        this.contracts.add(BoxesPositions.INDEX_12.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_12.getPos()))
                        .landCost(Ownerships.OWNERSHIP12.getCost()).landIncome(Ownerships.OWNERSHIP12.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP12.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_12.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_12.getPos()));
        this.contracts.add(BoxesPositions.INDEX_13.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_13.getPos()))
                        .landCost(Ownerships.OWNERSHIP13.getCost()).landIncome(Ownerships.OWNERSHIP13.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP13.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_13.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_13.getPos()));
        this.contracts.add(BoxesPositions.INDEX_14.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_14.getPos()))
                        .landCost(Ownerships.OWNERSHIP14.getCost()).landIncome(Ownerships.OWNERSHIP14.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP14.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_14.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_14.getPos()));
        this.contracts.add(BoxesPositions.INDEX_15.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_15.getPos()))
                        .landCost(Ownerships.OWNERSHIP15.getCost()).landIncome(Ownerships.OWNERSHIP15.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP15.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_15.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_15.getPos()));
        this.contracts.add(BoxesPositions.INDEX_16.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_16.getPos()))
                        .landCost(Ownerships.OWNERSHIP16.getCost()).landIncome(Ownerships.OWNERSHIP16.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP16.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_16.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_16.getPos()));
        this.contracts.add(BoxesPositions.INDEX_17.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_17.getPos()))
                        .landCost(Ownerships.OWNERSHIP17.getCost()).landIncome(Ownerships.OWNERSHIP17.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP17.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_17.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_17.getPos()));
        this.contracts.add(BoxesPositions.INDEX_18.getPos(),
                new ClassicContract.Builder().ownership(this.ownerships.get(BoxesPositions.INDEX_18.getPos()))
                        .ownershipsCost(Ownerships.OWNERSHIP18.getCost()).build());
        this.ownerships.get(BoxesPositions.INDEX_18.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_18.getPos()));
        this.contracts.add(BoxesPositions.INDEX_19.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_19.getPos()))
                        .landCost(Ownerships.OWNERSHIP19.getCost()).landIncome(Ownerships.OWNERSHIP19.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP19.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_19.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_19.getPos()));
        this.contracts.add(BoxesPositions.INDEX_20.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_20.getPos()))
                        .landCost(Ownerships.OWNERSHIP20.getCost()).landIncome(Ownerships.OWNERSHIP20.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP20.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_20.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_20.getPos()));
        this.contracts.add(BoxesPositions.INDEX_21.getPos(),
                new ClassicContract.Builder().ownership(this.ownerships.get(BoxesPositions.INDEX_21.getPos()))
                        .ownershipsCost(Ownerships.OWNERSHIP21.getCost()).build());
        this.ownerships.get(BoxesPositions.INDEX_21.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_21.getPos()));
        this.contracts.add(BoxesPositions.INDEX_22.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_22.getPos()))
                        .landCost(Ownerships.OWNERSHIP22.getCost()).landIncome(Ownerships.OWNERSHIP22.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP22.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_22.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_22.getPos()));
        this.contracts.add(BoxesPositions.INDEX_23.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_23.getPos()))
                        .landCost(Ownerships.OWNERSHIP23.getCost()).landIncome(Ownerships.OWNERSHIP23.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP23.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_23.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_23.getPos()));
        this.contracts.add(BoxesPositions.INDEX_24.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_24.getPos()))
                        .landCost(Ownerships.OWNERSHIP24.getCost()).landIncome(Ownerships.OWNERSHIP24.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP24.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_24.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_24.getPos()));
        this.contracts.add(BoxesPositions.INDEX_25.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_25.getPos()))
                        .landCost(Ownerships.OWNERSHIP25.getCost()).landIncome(Ownerships.OWNERSHIP25.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP25.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_25.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_25.getPos()));
        this.contracts.add(BoxesPositions.INDEX_26.getPos(),
                new ClassicContract.Builder().ownership(this.ownerships.get(BoxesPositions.INDEX_26.getPos()))
                        .ownershipsCost(Ownerships.OWNERSHIP26.getCost()).build());
        this.ownerships.get(BoxesPositions.INDEX_26.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_26.getPos()));
        this.contracts.add(BoxesPositions.INDEX_27.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_27.getPos()))
                        .landCost(Ownerships.OWNERSHIP27.getCost()).landIncome(Ownerships.OWNERSHIP27.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP27.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_27.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_27.getPos()));
        this.contracts.add(BoxesPositions.INDEX_28.getPos(),
                new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_28.getPos()))
                        .landCost(Ownerships.OWNERSHIP28.getCost()).landIncome(Ownerships.OWNERSHIP28.getIncome().get())
                        .buildingCost(Ownerships.OWNERSHIP28.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_28.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_28.getPos()));
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
            throw new IllegalArgumentException("Minimum players: 2. Maximum players: 6");
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
        this.decks.add(Decks.CHANCE);
        this.decks.add(Decks.COMMUNITY_CHEST);
    }

    private void inizializesBoxes() {
        final List<Box> temp = new LinkedList<>();
        // final Box[] boxes = new Box[40];
        // this.ownerships.stream().forEach(o -> {
        // boxes[o.getID()] = o;
        // });
        temp.add(new Start("GO", BoxesPositions.START_POSITION.getPos()));
        final PrisonOrTransit prison = new PrisonOrTransit("IN JAIL OR JUST VISITING",
                BoxesPositions.PRISON_POSITION.getPos());
        temp.add(prison);
        temp.add(new NeutralArea("FREE PARKING", BoxesPositions.NEUTRAL_AREA_POSITION.getPos()));
        temp.add(new Police("GO TO JAIL", BoxesPositions.POLICE_POSITION.getPos(), prison));
        temp.add(new DecksBox("CHANCE", BoxesPositions.FIRST_CHANCE_POSITION.getPos(), this.decks.get(0)));
        temp.add(new DecksBox("CHANCE", BoxesPositions.SECOND_CHANCE_POSITION.getPos(), this.decks.get(0)));
        temp.add(new DecksBox("CHANCE", BoxesPositions.THIRD_CHANCE_POSITION.getPos(), this.decks.get(0)));
        temp.add(new DecksBox("COMMUNITY CHEST", BoxesPositions.FIRST_COMMUNITY_CHEST_POSITION.getPos(),
                this.decks.get(1)));
        temp.add(new DecksBox("COMMUNITY CHEST", BoxesPositions.SECOND_COMMUNITY_CHEST_POSITION.getPos(),
                this.decks.get(1)));
        temp.add(new DecksBox("COMMUNITY CHEST", BoxesPositions.THIRD_COMMUNITY_CHEST_POSITION.getPos(),
                this.decks.get(1)));
        temp.add(new TaxImpl("INCOME TAX", BoxesPositions.INCOME_TAX_POSITION.getPos(), AMOUNT_OF_FEES));
        temp.add(new TaxImpl("SUPER TAX", BoxesPositions.SUPER_TAX_POSITION.getPos(), AMOUNT_OF_FEES));
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

    // private boolean twice(final Player player) {
    // return player.lastDicesNumber().get(0) ==
    // player.lastDicesNumber().get(1);
    // }
    //
    // @Override
    // public List<Action> getNextBoxsActions(final Box box, final Player
    // player) {
    // final List<Action> actions = new LinkedList<>();
    // if (!player.dicesAlreadyRolled()) {
    // actions.add(new ToRollDices(new ClassicDicesStrategy()));
    // return actions;
    // } else if (player.isInPrison() && this.twice(player)) {
    // player.setPrison(false);
    // MoveUpTo.takeSteps(player.lastDicesNumber().get(0) +
    // player.lastDicesNumber().get(1));
    // }
    // if (box instanceof Land) {
    // final Land land = (Land) box;
    // if (land.getOwner().equals(this.bank)) {
    // final Action action1 = ToBuyProperties.buyAOwnership(land);
    // final Action action2 = ToAuction.ownerships(this.players, new
    // ClassicAuction(), land);
    // actions.add(action1);
    // actions.add(action2);
    // } else if (land.getOwner().equals(player)) {
    // if
    // (player.getOwnerships().get().containsAll(land.getGroup().getMembers())
    // && ((LandGroup) land.getGroup()).canBuiling() &&
    // this.bank.getLeftBuilding().size() > 0) {
    // try {
    // actions.add(ToBuyProperties.buyABuilding(land, this.bank.getBuilding(
    // ((LandGroup) land.getGroup()).getBuildings().size() < 4 ? new Home() :
    // new Hotel())));
    // } catch (IndexOutOfBoundsException i) {
    // }
    // }
    // } else {
    // final int amount = ((ClassicLandContract)
    // land.getContract()).getIncome(new LandIncomeStrategy(land));
    // if (amount <= player.getMoney()) {
    // actions.add(new ToPay(amount, player));
    // new ToBePaid(amount).play((Player) land.getOwner());
    // player.setDebts(true);
    // } else {
    // this.notMuchMoney(player, actions);
    // }
    // }
    // } else if (box instanceof Ownership) {
    // final Ownership ownership = (Ownership) box;
    // if (ownership.getOwner().equals(this.bank)) {
    // final Action action1 = ToBuyProperties.buyAOwnership(ownership);
    // final Action action2 = ToAuction.ownerships(this.players, new
    // ClassicAuction(), ownership);
    // actions.add(action1);
    // actions.add(action2);
    // } else if (!ownership.getOwner().equals(player)) {
    // final int amount = ownership.getContract().getIncome(ownership instanceof
    // Station
    // ? new StationIncomeStrategy(ownership) : new
    // CompanysIncomeStrategy(ownership, player));
    // if (amount <= player.getMoney()) {
    // actions.add(new ToPay(amount, player));
    // new ToBePaid(amount).play((Player) ownership.getOwner());
    // player.setDebts(true);
    // } else {
    // this.notMuchMoney(player, actions);
    // }
    // }
    // } else {
    // if (box instanceof Start) {
    // actions.add(new ToBePaid(Start.getMuchToPick()));
    // }
    // // if (box instanceof PrisonOrTransit) { // NON FANNO NULLA; QUINDI
    // // OMETTERLI
    // // }
    // // if (box instanceof NeutralArea) {
    // // }
    // if (box instanceof Police) {
    // actions.add(new GoToPrison(this.allBoxes.get(PRISON_POSITION)));
    // }
    // if (box instanceof DecksBox) {
    // actions.add(new ToDrawCards(this.decks
    // .get(box.getID() == FIRST_CHANCE_POSITION || box.getID() ==
    // SECOND_CHANCE_POSITION || box.getID() == THIRD_CHANCE_POSITION ? 0 :
    // 1)));
    // }
    // if (box instanceof TaxImpl) {
    // new ToPay(((TaxImpl) box).getCost(), player);
    // }
    // }
    // if (actions.isEmpty()) {
    // player.setDebts(false);
    // player.setDicesRoll(false);
    // }
    // return actions;
    // }

    public List<Actions> getNextCardsActions(final Box box, final Card card, final Player player) {
        final List<Action> actions = new LinkedList<>();
        if (card instanceof ChanceCards) {
            ChanceCards chance = (ChanceCards) card;
            switch (chance) {
            case CARD2:
                MoveUpTo.moveUpToBox(this.allBoxes.get(37)).play(player);
                break;
            case ChanceCards.CARD3.getValue():
                if (((Ownership) box).getOwner().equals(player)) {
                    actions.addAll(this.getNextBoxsActions(box, player));
                } else {
                    final int amount = 2
                            * ((Ownership) box).getContract().getIncome(new StationIncomeStrategy(((Ownership) box)));
                    new ToPay(amount, player).play(player);
                    new ToBePaid(amount).play(player);
                }
                break;
            case ChanceCards.CARD4.getValue():
                player.addCard(card);
                break;
            case ChanceCards.CARD5.getValue():
                new ToPay(20, player).play(player);
                break;
            case CARD7:
                new GoToPrison(this.allBoxes.get(BoxesPositions.PRISON_POSITION.getPos())).play(player);
            case ChanceCards.CARD8.getValue():
                player.getOwnerships().get().stream().filter(o -> o instanceof Land)
                        .filter(o -> !((LandGroup) o.getGroup()).getBuildings().isEmpty())
                        .map(o -> ((LandGroup) o.getGroup()).getBuildings()).forEach(l -> {
                            l.forEach(b -> {
                                new ToPay(b instanceof Home ? 25 : 100, player).play(player);
                            });
                        });
                break;
            case ChanceCards.CARD9.getValue():
                this.players.stream().filter(p -> !p.equals(player)).forEach(p -> {
                    try {
                        new ToPay(CARD_TAX, player).play(player);
                        new ToBePaid(CARD_TAX).play(p);
                    } catch (IllegalArgumentException i) {
                        this.notMuchMoney(player, actions);
                    }
                });
                break;
            case CARCardsId.CARD10.getValue():
                actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(BoxesPositions.START_POSITION.getPos())));
            case CARD11:
                actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(OWNERSHIP_N_18)));
            case CARD12:
                if (((Ownership) box).getOwner().equals(player)) {
                    actions.addAll(this.getNextBoxsActions(box, player));
                } else {
                    final int amount = 2
                            * ((Ownership) box).getContract().getIncome(new StationIncomeStrategy(((Ownership) box)));
                    actions.add(new ToPay(amount, player));
                    new ToBePaid(amount);
                }
                break;
            case CARD13:
                actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(OWNERSHIP_N_17)));
            case CARD14:
                actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(OWNERSHIP_N_9)));
            case CARD15:
                if (((Ownership) box).getOwner().equals(player)) {
                    actions.addAll(this.getNextBoxsActions(box, player));
                } else {
                    final int amount = (player.lastDicesNumber().stream().reduce((d, d1) -> d + d1).get() * 10);
                    actions.add(new ToPay(amount, player));
                    new ToBePaid(amount);
                }
                break;
            }
        } else {
            CommunityChestCards chest = (CommunityChestCards) card;
            switch (chest) {
            case CARD17:
                actions.add(new ToPay(50, player));
                break;
            case CARD18:
                actions.add(new ToPay(50, player));
                break;
            case CARD20:
                player.addCard(card);
                break;
            case CARD21:
                actions.add(new ToPay(100, player));
                break;
            case CARD23:
                actions.add(new GoToPrison(this.allBoxes.get(BoxesPositions.PRISON_POSITION.getPos())));
            case CARD24:
                player.getOwnerships().get().stream().filter(o -> o instanceof Land)
                        .filter(o -> !((LandGroup) o.getGroup()).getBuildings().isEmpty())
                        .map(o -> ((LandGroup) o.getGroup()).getBuildings()).forEach(l -> {
                            l.forEach(b -> {
                                actions.add(new ToPay(b instanceof Home ? 40 : 115, player));
                            });
                        });
                break;
            case CARD25:
                this.players.stream().filter(p -> !p.equals(player)).forEach(p -> {
                    try {
                        new ToPay(10, p).play(p);
                        new ToBePaid(10).play(player);
                    } catch (IllegalArgumentException i) {
                        this.notMuchMoney(player, actions);
                    }
                });
                break;
            case CARD26:
                actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(BoxesPositions.START_POSITION.getPos())));
            default:
                break;
            }
        }
        return actions;
    }
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
    // public static void main(String[] args) {
    // List<Player> l = new LinkedList<>();
    // l.add(new ClassicPlayer("v", new ClassicPawn(3), true));
    // ClassicStrategy c = new ClassicStrategy(l);
    // List<Box> b = c.getBoxes();
    // for(int i = 0; i < 40 ; i++) {
    // System.out.println(b.get(i).getName());
    // System.out.println(b.get(i).getID());
    // }
    //
    // List<Integer> n = new LinkedList<>();
    // n.add(3);
    // n.add(5);
    // n.add(1);
    // n.add(8);
    // n.add(2);
    // n.add(6);
    //
    // List<Integer> v = n.stream().sorted((i, i1) -> (i -
    // i1)).collect(Collectors.toList());
    //
    // System.out.println(v);
    // }
}
