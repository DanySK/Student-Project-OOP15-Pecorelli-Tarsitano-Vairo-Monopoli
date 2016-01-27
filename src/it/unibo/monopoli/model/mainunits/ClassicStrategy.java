package it.unibo.monopoli.model.mainunits;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import it.unibo.monopoli.model.actions.Action;
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
import it.unibo.monopoli.model.actions.ToSellCards;
import it.unibo.monopoli.model.actions.ToSellProperties;
import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Chance;
import it.unibo.monopoli.model.cards.ClassicCard;
import it.unibo.monopoli.model.cards.ClassicCardBelonging;
import it.unibo.monopoli.model.cards.CommunityChest;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.ClassicContract;
import it.unibo.monopoli.model.table.ClassicLand;
import it.unibo.monopoli.model.table.ClassicLandContract;
import it.unibo.monopoli.model.table.ClassicLandGroup;
import it.unibo.monopoli.model.table.ClassicOwnership;
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

    private static final int START_POSITION = 0;
    private static final int PRISON_POSITION = 10;
    private static final int NEUTRAL_AREA_POSITION = 20;
    private static final int POLICE_POSITION = 30;
    private static final int FIRST_CHANCE_POSITION = 7;
    private static final int SECOND_CHANCE_POSITION = 22;
    private static final int THIRD_CHANCE_POSITION = 36;
    private static final int FIRST_COMMUNITY_CHEST_POSITION = 2;
    private static final int SECOND_COMMUNITY_CHEST_POSITION = 17;
    private static final int THIRD_COMMUNITY_CHEST_POSITION = 33;
    private static final int INCOME_TAX_POSITION = 4;
    private static final int SUPER_TAX_POSITION = 38;

    private static final int CARD1 = 1;
    private static final int CARD2 = 2;
    private static final int CARD3 = 3;
    private static final int CARD4 = 4;
    private static final int CARD5 = 5;
    private static final int CARD6 = 6;
    private static final int CARD7 = 7;
    private static final int CARD8 = 8;
    private static final int CARD9 = 9;
    private static final int CARD10 = 10;
    private static final int CARD11 = 11;
    private static final int CARD12 = 12;
    private static final int CARD13 = 13;
    private static final int CARD14 = 14;
    private static final int CARD15 = 15;
    private static final int CARD16 = 16;
    private static final int CARD17 = 17;
    private static final int CARD18 = 18;
    private static final int CARD19 = 19;
    private static final int CARD20 = 20;
    private static final int CARD21 = 21;
    private static final int CARD22 = 22;
    private static final int CARD23 = 23;
    private static final int CARD24 = 24;
    private static final int CARD25 = 25;
    private static final int CARD26 = 26;
    private static final int CARD27 = 27;
    private static final int CARD28 = 28;
    private static final int CARD29 = 29;
    private static final int CARD30 = 30;
    private static final int CARD31 = 31;
    private static final int CARD32 = 32;

    private static final int OWNERSHIP_N_1 = 0;
    private static final int OWNERSHIP_N_2 = 1;
    private static final int OWNERSHIP_N_3 = 2;
    private static final int OWNERSHIP_N_4 = 3;
    private static final int OWNERSHIP_N_5 = 4;
    private static final int OWNERSHIP_N_6 = 5;
    private static final int OWNERSHIP_N_7 = 6;
    private static final int OWNERSHIP_N_8 = 7;
    private static final int OWNERSHIP_N_9 = 8;
    private static final int OWNERSHIP_N_10 = 9;
    private static final int OWNERSHIP_N_11 = 10;
    private static final int OWNERSHIP_N_12 = 11;
    private static final int OWNERSHIP_N_13 = 12;
    private static final int OWNERSHIP_N_14 = 13;
    private static final int OWNERSHIP_N_15 = 14;
    private static final int OWNERSHIP_N_16 = 15;
    private static final int OWNERSHIP_N_17 = 16;
    private static final int OWNERSHIP_N_18 = 17;
    private static final int OWNERSHIP_N_19 = 18;
    private static final int OWNERSHIP_N_20 = 19;
    private static final int OWNERSHIP_N_21 = 20;
    private static final int OWNERSHIP_N_22 = 21;
    private static final int OWNERSHIP_N_23 = 22;
    private static final int OWNERSHIP_N_24 = 23;
    private static final int OWNERSHIP_N_25 = 24;
    private static final int OWNERSHIP_N_26 = 25;
    private static final int OWNERSHIP_N_27 = 26;
    private static final int OWNERSHIP_N_28 = 27;

    private static final int N_MAX_OF_HOUSES = 32;
    private static final int N_MAX_OF_HOTELS = 12;
    private static final int AMOUNT_OF_FEES = 10;

    private static final int CARD_TAX = 50;

    private final List<Player> players;
    private final List<Ownership> ownerships;
    private final List<Group> groups;
    private final List<Contract> contracts;
    private final List<Home> homes;
    private final List<Hotel> hotels;
    private final List<Box> allBoxes;
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
        this.bank = new ClassicBank(this.ownerships, this.homes, this.hotels);
        this.players = players;
        this.inizializesPlayers(players);
        this.decks = new LinkedList<>();
        this.inizializesDecks();
        this.allBoxes = new LinkedList<>();
        this.inizializesBoxes();
    }

    private void inizializesPlayers(final List<Player> players) {
        switch (players.size()) {
        case 2:
            players.stream().forEach(p -> {
                this.addOwnerships(7, p);
                p.setMoney(350);
            });
        case 3:
            players.stream().forEach(p -> {
                this.addOwnerships(6, p);
                p.setMoney(300);
            });
        case 4:
            players.stream().forEach(p -> {
                this.addOwnerships(5, p);
                p.setMoney(250);
            });
        case 5:
            players.stream().forEach(p -> {
                this.addOwnerships(4, p);
                p.setMoney(200);
            });
        case 6:
            players.stream().forEach(p -> {
                this.addOwnerships(3, p);
                p.setMoney(150);
            });
        default:
            break;
        }
    }

    private void addOwnerships(final int nOfOwnership, final Player player) {
        for (int i = 0; i < nOfOwnership; i++) {
            Ownership ow = this.bank.getOwnership();
            player.addOwnership(ow);
            ow.setOwner(player);
        }
    }

    private void inizializesGroups() {
        this.groups.add(new ClassicLandGroup("Dark Gray", this.ownerships.get(OWNERSHIP_N_1),
                this.ownerships.get(OWNERSHIP_N_2)));
        this.groups.add(new ClassicLandGroup("Cyan", this.ownerships.get(OWNERSHIP_N_4),
                this.ownerships.get(OWNERSHIP_N_5), this.ownerships.get(OWNERSHIP_N_6)));
        this.groups.add(new ClassicLandGroup("Magenta", this.ownerships.get(OWNERSHIP_N_7),
                this.ownerships.get(OWNERSHIP_N_9), this.ownerships.get(OWNERSHIP_N_10)));
        this.groups.add(new ClassicLandGroup("Orange", this.ownerships.get(OWNERSHIP_N_12),
                this.ownerships.get(OWNERSHIP_N_13), this.ownerships.get(OWNERSHIP_N_14)));
        this.groups.add(new ClassicLandGroup("Red", this.ownerships.get(OWNERSHIP_N_15),
                this.ownerships.get(OWNERSHIP_N_16), this.ownerships.get(OWNERSHIP_N_17)));
        this.groups.add(new ClassicLandGroup("Yellow", this.ownerships.get(OWNERSHIP_N_19),
                this.ownerships.get(OWNERSHIP_N_20), this.ownerships.get(OWNERSHIP_N_22)));
        this.groups.add(new ClassicLandGroup("Green", this.ownerships.get(OWNERSHIP_N_23),
                this.ownerships.get(OWNERSHIP_N_24), this.ownerships.get(OWNERSHIP_N_25)));
        this.groups.add(
                new ClassicLandGroup("Blue", this.ownerships.get(OWNERSHIP_N_27), this.ownerships.get(OWNERSHIP_N_28)));
        this.groups.add(new ClassicLandGroup("Stations", this.ownerships.get(OWNERSHIP_N_3),
                this.ownerships.get(OWNERSHIP_N_11), this.ownerships.get(OWNERSHIP_N_18),
                this.ownerships.get(OWNERSHIP_N_26)));
        this.groups.add(new ClassicLandGroup("Companies", this.ownerships.get(OWNERSHIP_N_8),
                this.ownerships.get(OWNERSHIP_N_21)));
    }

    private void inizializesContracts() {
        this.contracts.add(OWNERSHIP_N_1, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_1))
                .landCost(60).landIncome(2).buildingCost(50).build());
        this.ownerships.get(OWNERSHIP_N_1).setContract(this.contracts.get(OWNERSHIP_N_1));
        this.contracts.add(OWNERSHIP_N_2, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_2))
                .landCost(60).landIncome(4).buildingCost(50).build());
        this.ownerships.get(OWNERSHIP_N_2).setContract(this.contracts.get(OWNERSHIP_N_2));
        this.contracts.add(OWNERSHIP_N_3, new ClassicContract.Builder().ownership(this.ownerships.get(OWNERSHIP_N_3))
                .ownershipsCost(200).build());
        this.ownerships.get(OWNERSHIP_N_3).setContract(this.contracts.get(OWNERSHIP_N_3));
        this.contracts.add(OWNERSHIP_N_4, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_4))
                .landCost(100).landIncome(6).buildingCost(50).build());
        this.ownerships.get(OWNERSHIP_N_4).setContract(this.contracts.get(OWNERSHIP_N_4));
        this.contracts.add(OWNERSHIP_N_5, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_5))
                .landCost(100).landIncome(6).buildingCost(50).build());
        this.ownerships.get(OWNERSHIP_N_5).setContract(this.contracts.get(OWNERSHIP_N_5));
        this.contracts.add(OWNERSHIP_N_6, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_6))
                .landCost(120).landIncome(8).buildingCost(50).build());
        this.ownerships.get(OWNERSHIP_N_6).setContract(this.contracts.get(OWNERSHIP_N_6));
        this.contracts.add(OWNERSHIP_N_7, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_7))
                .landCost(140).landIncome(10).buildingCost(100).build());
        this.ownerships.get(OWNERSHIP_N_7).setContract(this.contracts.get(OWNERSHIP_N_7));
        this.contracts.add(OWNERSHIP_N_8, new ClassicContract.Builder().ownership(this.ownerships.get(OWNERSHIP_N_8))
                .ownershipsCost(150).build());
        this.ownerships.get(OWNERSHIP_N_8).setContract(this.contracts.get(OWNERSHIP_N_8));
        this.contracts.add(OWNERSHIP_N_9, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_9))
                .landCost(140).landIncome(10).buildingCost(100).build());
        this.ownerships.get(OWNERSHIP_N_9).setContract(this.contracts.get(OWNERSHIP_N_9));
        this.contracts.add(OWNERSHIP_N_10, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_10))
                .landCost(160).landIncome(12).buildingCost(100).build());
        this.ownerships.get(OWNERSHIP_N_10).setContract(this.contracts.get(OWNERSHIP_N_10));
        this.contracts.add(OWNERSHIP_N_11, new ClassicContract.Builder().ownership(this.ownerships.get(OWNERSHIP_N_11))
                .ownershipsCost(200).build());
        this.ownerships.get(OWNERSHIP_N_11).setContract(this.contracts.get(OWNERSHIP_N_11));
        this.contracts.add(OWNERSHIP_N_12, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_12))
                .landCost(180).landIncome(14).buildingCost(100).build());
        this.ownerships.get(OWNERSHIP_N_12).setContract(this.contracts.get(OWNERSHIP_N_12));
        this.contracts.add(OWNERSHIP_N_13, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_13))
                .landCost(180).landIncome(14).buildingCost(100).build());
        this.ownerships.get(OWNERSHIP_N_13).setContract(this.contracts.get(OWNERSHIP_N_13));
        this.contracts.add(OWNERSHIP_N_14, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_14))
                .landCost(200).landIncome(16).buildingCost(100).build());
        this.ownerships.get(OWNERSHIP_N_14).setContract(this.contracts.get(OWNERSHIP_N_14));
        this.contracts.add(OWNERSHIP_N_15, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_15))
                .landCost(220).landIncome(18).buildingCost(150).build());
        this.ownerships.get(OWNERSHIP_N_15).setContract(this.contracts.get(OWNERSHIP_N_15));
        this.contracts.add(OWNERSHIP_N_16, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_16))
                .landCost(220).landIncome(18).buildingCost(150).build());
        this.ownerships.get(OWNERSHIP_N_16).setContract(this.contracts.get(OWNERSHIP_N_16));
        this.contracts.add(OWNERSHIP_N_17, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_17))
                .landCost(240).landIncome(20).buildingCost(150).build());
        this.ownerships.get(OWNERSHIP_N_17).setContract(this.contracts.get(OWNERSHIP_N_17));
        this.contracts.add(OWNERSHIP_N_18, new ClassicContract.Builder().ownership(this.ownerships.get(OWNERSHIP_N_18))
                .ownershipsCost(200).build());
        this.ownerships.get(OWNERSHIP_N_18).setContract(this.contracts.get(OWNERSHIP_N_18));
        this.contracts.add(OWNERSHIP_N_19, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_19))
                .landCost(260).landIncome(22).buildingCost(150).build());
        this.ownerships.get(OWNERSHIP_N_19).setContract(this.contracts.get(OWNERSHIP_N_19));
        this.contracts.add(OWNERSHIP_N_20, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_20))
                .landCost(260).landIncome(22).buildingCost(150).build());
        this.ownerships.get(OWNERSHIP_N_20).setContract(this.contracts.get(OWNERSHIP_N_20));
        this.contracts.add(OWNERSHIP_N_21, new ClassicContract.Builder().ownership(this.ownerships.get(OWNERSHIP_N_21))
                .ownershipsCost(150).build());
        this.ownerships.get(OWNERSHIP_N_21).setContract(this.contracts.get(OWNERSHIP_N_21));
        this.contracts.add(OWNERSHIP_N_22, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_22))
                .landCost(280).landIncome(24).buildingCost(150).build());
        this.ownerships.get(OWNERSHIP_N_22).setContract(this.contracts.get(OWNERSHIP_N_22));
        this.contracts.add(OWNERSHIP_N_23, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_23))
                .landCost(300).landIncome(26).buildingCost(200).build());
        this.ownerships.get(OWNERSHIP_N_23).setContract(this.contracts.get(OWNERSHIP_N_23));
        this.contracts.add(OWNERSHIP_N_24, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_24))
                .landCost(300).landIncome(26).buildingCost(200).build());
        this.ownerships.get(OWNERSHIP_N_24).setContract(this.contracts.get(OWNERSHIP_N_24));
        this.contracts.add(OWNERSHIP_N_25, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_25))
                .landCost(320).landIncome(28).buildingCost(200).build());
        this.ownerships.get(OWNERSHIP_N_25).setContract(this.contracts.get(OWNERSHIP_N_25));
        this.contracts.add(OWNERSHIP_N_26, new ClassicContract.Builder().ownership(this.ownerships.get(OWNERSHIP_N_26))
                .ownershipsCost(200).build());
        this.ownerships.get(OWNERSHIP_N_26).setContract(this.contracts.get(OWNERSHIP_N_26));
        this.contracts.add(OWNERSHIP_N_27, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_27))
                .landCost(350).landIncome(35).buildingCost(200).build());
        this.ownerships.get(OWNERSHIP_N_27).setContract(this.contracts.get(OWNERSHIP_N_27));
        this.contracts.add(OWNERSHIP_N_28, new ClassicLandContract.Builder().land(this.ownerships.get(OWNERSHIP_N_28))
                .landCost(400).landIncome(50).buildingCost(200).build());
        this.ownerships.get(OWNERSHIP_N_28).setContract(this.contracts.get(OWNERSHIP_N_28));
    }

    private void inizializesOwnerships() {
        this.ownerships.add(OWNERSHIP_N_1, new ClassicLand("OLD KENT ROAD", 1, this.bank, Color.DARK_GRAY));
        this.ownerships.add(OWNERSHIP_N_2, new ClassicLand("WHITECHAPEL ROAD", 3, this.bank, Color.DARK_GRAY));
        this.ownerships.add(OWNERSHIP_N_3, new Station("KINGS CROSS STATION", 5, this.bank));
        this.ownerships.add(OWNERSHIP_N_4, new ClassicLand("THE ANGEL, ISLINGTON", 6, this.bank, Color.CYAN));
        this.ownerships.add(OWNERSHIP_N_5, new ClassicLand("EUSTON ROAD", 8, this.bank, Color.CYAN));
        this.ownerships.add(OWNERSHIP_N_6, new ClassicLand("PENTONVILLE ROAD", 9, this.bank, Color.CYAN));
        this.ownerships.add(OWNERSHIP_N_7, new ClassicLand("PALL MALL", 11, this.bank, Color.MAGENTA));
        this.ownerships.add(OWNERSHIP_N_8, new Company("ELECTRIC COMPANY", 12, this.bank));
        this.ownerships.add(OWNERSHIP_N_9, new ClassicLand("WHITEHALL", 13, this.bank, Color.MAGENTA));
        this.ownerships.add(OWNERSHIP_N_10, new ClassicLand("NORTHUMRL'D AVENUE", 14, this.bank, Color.MAGENTA));
        this.ownerships.add(OWNERSHIP_N_11, new Station("MARYLEBONE STATION", 15, this.bank));
        this.ownerships.add(OWNERSHIP_N_12, new ClassicLand("BOW STREET", 16, this.bank, Color.ORANGE));
        this.ownerships.add(OWNERSHIP_N_13, new ClassicLand("MARLBOROUGH STREET", 18, this.bank, Color.ORANGE));
        this.ownerships.add(OWNERSHIP_N_14, new ClassicLand("VINE STREET", 19, this.bank, Color.ORANGE));
        this.ownerships.add(OWNERSHIP_N_15, new ClassicLand("STRAND", 21, this.bank, Color.RED));
        this.ownerships.add(OWNERSHIP_N_16, new ClassicLand("FLEET STREET", 23, this.bank, Color.RED));
        this.ownerships.add(OWNERSHIP_N_17, new ClassicLand("TRAFALGAR SQUARE", 24, this.bank, Color.RED));
        this.ownerships.add(OWNERSHIP_N_18, new Station("FENCHURCH ST. STATION", 25, this.bank));
        this.ownerships.add(OWNERSHIP_N_19, new ClassicLand("LEICESTER SQUARE", 26, this.bank, Color.YELLOW));
        this.ownerships.add(OWNERSHIP_N_20, new ClassicLand("COVENTRY STREET", 27, this.bank, Color.YELLOW));
        this.ownerships.add(OWNERSHIP_N_21, new Company("WATER COMPANY", 28, this.bank));
        this.ownerships.add(OWNERSHIP_N_22, new ClassicLand("PICCADILLY", 29, this.bank, Color.YELLOW));
        this.ownerships.add(OWNERSHIP_N_23, new ClassicLand("REGENT STREET", 31, this.bank, Color.GREEN));
        this.ownerships.add(OWNERSHIP_N_24, new ClassicLand("OXFORD STREET", 32, this.bank, Color.GREEN));
        this.ownerships.add(OWNERSHIP_N_25, new ClassicLand("BOND STREET", 34, this.bank, Color.GREEN));
        this.ownerships.add(OWNERSHIP_N_26, new Station("LIVERPOOL ST. STATION", 35, this.bank));
        this.ownerships.add(OWNERSHIP_N_27, new ClassicLand("PARK LANE", 37, this.bank, Color.BLUE));
        this.ownerships.add(OWNERSHIP_N_28, new ClassicLand("MAYFAIR", 39, this.bank, Color.BLUE));
    }

    private void inizializesBuildings() {
        for (int i = 0; i < N_MAX_OF_HOUSES; i++) {
            this.homes.add(new Home());
        }
        for (int i = 0; i < N_MAX_OF_HOTELS; i++) {
            this.hotels.add(new Hotel());
        }
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
        return this.allBoxes;
    }

    private void inizializesBoxes() {
        final Box[] boxes = new Box[40];
        this.ownerships.stream().forEach(o -> {
                                     boxes[o.getID()] = o;
                                 });
        boxes[START_POSITION] = new Start("GO", START_POSITION);
        final PrisonOrTransit prison = new PrisonOrTransit("IN JAIL OR JUST VISITING", PRISON_POSITION);
        boxes[PRISON_POSITION] = prison;
        boxes[NEUTRAL_AREA_POSITION] = new NeutralArea("FREE PARKING", NEUTRAL_AREA_POSITION);
        boxes[POLICE_POSITION] = new Police("GO TO JAIL", POLICE_POSITION, prison);
        boxes[FIRST_CHANCE_POSITION] = new DecksBox("CHANCE", FIRST_CHANCE_POSITION, this.decks.get(0));
        boxes[SECOND_CHANCE_POSITION] = new DecksBox("CHANCE", SECOND_CHANCE_POSITION, this.decks.get(0));
        boxes[THIRD_CHANCE_POSITION] = new DecksBox("CHANCE", THIRD_CHANCE_POSITION, this.decks.get(0));
        boxes[FIRST_COMMUNITY_CHEST_POSITION] = new DecksBox("COMMUNITY CHEST", FIRST_COMMUNITY_CHEST_POSITION, this.decks.get(1));
        boxes[SECOND_COMMUNITY_CHEST_POSITION] = new DecksBox("COMMUNITY CHEST", SECOND_COMMUNITY_CHEST_POSITION, this.decks.get(1));
        boxes[THIRD_COMMUNITY_CHEST_POSITION] = new DecksBox("COMMUNITY CHEST", THIRD_COMMUNITY_CHEST_POSITION, this.decks.get(1));
        boxes[INCOME_TAX_POSITION] = new TaxImpl("INCOME TAX", INCOME_TAX_POSITION, AMOUNT_OF_FEES);
        boxes[SUPER_TAX_POSITION] = new TaxImpl("SUPER TAX", SUPER_TAX_POSITION, AMOUNT_OF_FEES);
        this.allBoxes.addAll(Arrays.asList(boxes));
    }

    private void inizializesDecks() {
        final Chance chance = new Chance();
        chance.addCard(new ClassicCard("TAKE 3 STEPS BACK (WITH BEST WISHES)", CARD1, MoveUpTo.takeSteps(-3)));
        chance.addCard(new ClassicCard("GO TO PARK LANE: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(), CARD2));
        chance.addCard(new ClassicCard("ADVANCE TO THE NEAREST STATION: IF IT'S FREE, YOU CAN BUY IT;"
                        + "IF IT IS OWNED BY ANOTHER PLAYER, PAY HIM TWICE THE PRICE THAT MATTER", CARD3, MoveUpTo.theNearestStation()));
        chance.addCard(new ClassicCard("GET OUT FREE OF JAIL. YOU CAN KEEP THIS CARD AND USE IT WHEN YOU WANT TO, OR YOU CAN SELL IT", CARD4));
        chance.addCard(new ClassicCard("FINE FOR SPEEDING. PAY $20", CARD5));
        chance.addCard(new ClassicCard("THE BANK WILL YOU PAY A BONUS OF $50", CARD6, new ToBePaid(50)));
        chance.addCard(new ClassicCard("GO DIRECTLY TO JAIL", CARD7));
        chance.addCard(new ClassicCard("PERFORM MAINTENANCE WORK ON ALL OUR BUILDINGS. YOU HAVE TO PAY $25 FOR EACH HOME AND $100 FOR EACH HOTEL THAT YOU OWN",
                CARD8));
        chance.addCard(new ClassicCard("YOU HAVE BEEN PROMOTED TO THE PRESIDENCY OF THE BOARD OF DIRECTORS. YOU HAVE TO PAY 50 TO ANY PLAYER",
                CARD9));
        chance.addCard(new ClassicCard("GO TO BOX 'GO' AND TAKE $" + Start.getMuchToPick(), CARD10));
        chance.addCard(
                new ClassicCard("GO TO THE FENCHURCH ST. STATION: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(),
                        CARD11));
        chance.addCard(new ClassicCard(
                "ADVANCE TO THE NEAREST STATION: IF IT'S FREE, YOU CAN BUY IT;"
                        + "IF IT IS OWNED BY ANOTHER PLAYER, PAY HIM TWICE THE PRICE THAT MATTER",
                CARD12, MoveUpTo.theNearestStation()));
        chance.addCard(new ClassicCard("GO TO THE TRAFALGAR SQUARE: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(),
                CARD13));
        chance.addCard(new ClassicCard("GO TO THE WHITEHALL: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(), CARD14));
        chance.addCard(new ClassicCard(
                "ADVANCE TO THE NEAREST STATION: IF IT'S FREE, YOU CAN BUY IT;"
                        + "IF IT IS OWNED BY ANOTHER PLAYER, LAUNCHING THE DICES AND PAY THE OWNER 10 TIMES THE NUMBER RELEASED",
                CARD15, MoveUpTo.theNearestStation(), new ToRollDices(new ClassicDicesStrategy())));
        chance.addCard(new ClassicCard("MATURANO THE COUPONS OF YOUR REAL ESTATE FUNDS: COLLECT $150", CARD16,
                new ToBePaid(150)));
        final CommunityChest chest = new CommunityChest();
        chest.addCard(new ClassicCard("RECEIVE DOCTOR'S BILL, PAY $50", CARD17));
        chest.addCard(new ClassicCard("PAY SCHOOL FEES OF OUR CHILDREN: $50", CARD18));
        chest.addCard(new ClassicCard("INHERITED $100 FROM A UNCLE", CARD19, new ToBePaid(50)));
        chest.addCard(new ClassicCard(
                "GET OUT FREE OF JAIL. YOU CAN KEEP THIS CARD AND USE IT WHEN YOU WANT TO, OR YOU CAN SELL IT",
                CARD20));
        chest.addCard(new ClassicCard("PAY HOSPITAL'S BILL. PAY $100", CARD21));
        chest.addCard(new ClassicCard("FOR THE SALE OF A STOCK OF PRODUCTS YOU OBTAIN $50", CARD22, new ToBePaid(50)));
        chest.addCard(
                new ClassicCard("GO DIRECTLY TO JAIL", CARD23));
        chest.addCard(new ClassicCard(
                "PAY CONTRIBUTIONS TO IMPROVE THE ROADS. YOU HAVE TO PAY $40 FOR EACH HOME AND $115 FOR EACH HOTEL THAT YOU OWN",
                CARD24));
        chest.addCard(new ClassicCard("IS YOUR BIRTHDAY. EACH PLAYER GIVES YOU $10", CARD25));
        chest.addCard(new ClassicCard("GO TO BOX 'GO' AND TAKE $" + Start.getMuchToPick(), CARD26));
        chest.addCard(new ClassicCard("GET $25 FOR YOUR ADVICE", CARD27, new ToBePaid(25)));
        chest.addCard(
                new ClassicCard("BANK RECOGNIZES AN ERROR IN YOUR STATEMENT. COLLECT $200", CARD28, new ToBePaid(200)));
        chest.addCard(
                new ClassicCard("YOU WON THE SECOND PRIZE IN A BEAUTY CONTEST. COLLECT $10", CARD29, new ToBePaid(10)));
        chest.addCard(
                new ClassicCard("MATURANO THE COUPONS OF YOUR ACTITONS. COLLECT $100", CARD30, new ToBePaid(100)));
        chest.addCard(new ClassicCard("FEE INCOME WAS REFUNDED. GET $20", CARD31, new ToBePaid(20)));
        chest.addCard(
                new ClassicCard("MATURANO INTEREST ON YOUR LIFE INSURANCE: COLLECT $100", CARD32, new ToBePaid(100)));

        this.decks.add(0, chance);
        this.decks.add(1, chest);
    }

    @Override
    public List<Action> getNextBoxsActions(final Box box, final Player player) {
        final List<Action> actions = new LinkedList<>();
        if (!player.dicesAlreadyRolled()) {
            actions.add(new ToRollDices(new ClassicDicesStrategy()));
            return actions;
        }
        if (box instanceof Land) {
            final Land land = (Land) box;
            if (land.getOwner().equals(this.bank)) {
                final Action action1 = ToBuyProperties.buyAOwnership(land);
                final Action action2 = ToAuction.ownerships(this.players, new ClassicAuction(), land);
                actions.add(action1);
                actions.add(action2);
            } else if (land.getOwner().equals(player)) {
                if (player.getOwnerships().get().containsAll(land.getGroup().getMembers())
                        && ((LandGroup) land.getGroup()).canBuiling() && this.bank.getLeftBuilding().size() > 0) {
                    try {
                        actions.add(ToBuyProperties.buyABuilding(land, this.bank.getBuilding(
                                ((LandGroup) land.getGroup()).getBuildings().size() < 4 ? new Home() : new Hotel())));
                    } catch (IndexOutOfBoundsException i) {
                    }
                }
            } else {
                final int amount = ((ClassicLandContract) land.getContract()).getIncome(new LandIncomeStrategy(land));
                if (amount <= player.getMoney()) {
                    actions.add(new ToPay(amount, player));
                    new ToBePaid(amount).play((Player) land.getOwner());
                    player.setDebts(true);
                } else {
                    this.notMuchMoney(player, actions);
                }
            }
        } else if (box instanceof Ownership) {
            final Ownership ownership = (Ownership) box;
            if (ownership.getOwner().equals(this.bank)) {
                final Action action1 = ToBuyProperties.buyAOwnership(ownership);
                final Action action2 = ToAuction.ownerships(this.players, new ClassicAuction(), ownership);
                actions.add(action1);
                actions.add(action2);
            } else if (!ownership.getOwner().equals(player)) {
                final int amount = ownership.getContract().getIncome(ownership instanceof Station
                        ? new StationIncomeStrategy(ownership) : new CompanysIncomeStrategy(ownership, player));
                if (amount <= player.getMoney()) {
                    actions.add(new ToPay(amount, player));
                    new ToBePaid(amount).play((Player) ownership.getOwner());
                    player.setDebts(true);
                } else {
                    this.notMuchMoney(player, actions);
                }
            }
        } else {
            if (box instanceof Start) {
                actions.add(new ToBePaid(Start.getMuchToPick()));
            }
            // if (box instanceof PrisonOrTransit) { // NON FANNO NULLA; QUINDI
            // OMETTERLI
            // }
            // if (box instanceof NeutralArea) {
            // }
            if (box instanceof Police) {
                actions.add(new GoToPrison(this.allBoxes.get(PRISON_POSITION)));
            }
            if (box instanceof DecksBox) {
                actions.add(new ToDrawCards(this.decks
                        .get(box.getID() == FIRST_CHANCE_POSITION || box.getID() == SECOND_CHANCE_POSITION ? 0 : 1)));
            }
            if (box instanceof TaxImpl) {
                new ToPay(((TaxImpl) box).getCost(), player);
            }
        }
        if (actions.isEmpty()) {
            player.setDebts(false);
            player.setDicesRoll(false);
        }
        return actions;
    }

    @Override
    public List<Action> getNextCardsActions(final Box box, final Card card, final Player player) {
        final List<Action> actions = new LinkedList<>();
        switch (card.getID()) {
        case CARD2 : actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(37)));
        case CARD3:
            if (((Ownership) box).getOwner().equals(player)) {
                actions.addAll(this.getNextBoxsActions(box, player));
            } else {
                final int amount = 2
                        * ((Ownership) box).getContract().getIncome(new StationIncomeStrategy(((Ownership) box)));
                actions.add(new ToPay(amount, player));
                new ToBePaid(amount);
            }
            break;
        case CARD4:
            player.addCard(card);
            break;
        case CARD5:
            actions.add(new ToPay(20, player));
            break;
        case CARD7 : actions.add(new GoToPrison(this.allBoxes.get(PRISON_POSITION)));
        case CARD8:
            player.getOwnerships().get().stream().filter(o -> o instanceof Land)
                    .filter(o -> !((LandGroup) o.getGroup()).getBuildings().isEmpty())
                    .map(o -> ((LandGroup) o.getGroup()).getBuildings()).forEach(l -> {
                        l.forEach(b -> {
                            actions.add(new ToPay(b instanceof Home ? 25 : 100, player));
                        });
                    });
            break;
        case CARD9:
            this.players.stream().filter(p -> !p.equals(player)).forEach(p -> {
                try {
                    new ToPay(CARD_TAX, player).play(player);
                    new ToBePaid(CARD_TAX).play(p);
                } catch (IllegalArgumentException i) {
                    this.notMuchMoney(player, actions);
                }
            });
            break;
        case CARD10 : actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(START_POSITION)));
        case CARD11 : actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(OWNERSHIP_N_18)));
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
        case CARD13 : actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(OWNERSHIP_N_17)));
        case CARD14 : actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(OWNERSHIP_N_9)));
        case CARD15:
            if (((Ownership) box).getOwner().equals(player)) {
                actions.addAll(this.getNextBoxsActions(box, player));
            } else {
                final int amount = (player.lastDicesNumber().stream().reduce((d, d1) -> d + d1).get() * 10);
                actions.add(new ToPay(amount, player));
                new ToBePaid(amount);
            }
            break;
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
        case CARD23 : actions.add(new GoToPrison(this.allBoxes.get(PRISON_POSITION)));
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
        case CARD26 : actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(START_POSITION)));
        default:
            break;
        }
        return actions;
    }

    private void notMuchMoney(final Player player, final List<Action> actions) {
        if (player.getOwnerships().isPresent()) {
            player.getOwnerships().get().stream().filter(o -> o.getGroup() instanceof LandGroup)
                    .filter(o -> ((LandGroup) o.getGroup()).getBuildings().size() > 0).forEach(o -> {
                        ((LandGroup) o.getGroup()).getBuildings()
                                .forEach(b -> actions.add(ToSellProperties.sellABuilding(((Land) o), b)));
                    });
            if (actions.isEmpty()) {
                player.getOwnerships().get().stream().forEach(o -> {
                    actions.add(ToSellProperties.sellAOwnership(o));
                });
            }
        } else if (player.getCards().isPresent()) {
            player.getCards().get().forEach(c -> actions.add(ToAuction.cards(this.players, new ClassicAuction(), c)));
        } else {
            // FINE DEL GIOCO -> interfaccia funzionale (Action ?) + classe
            // anonima: rimuove il giocatore con il play)
            actions.add(p -> {
                this.players.remove(p);
            });
        }
    }
}
