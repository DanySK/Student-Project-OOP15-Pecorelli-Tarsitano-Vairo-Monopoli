package it.unibo.monopoli.model.mainunits;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
import it.unibo.monopoli.model.cards.Chance;
import it.unibo.monopoli.model.cards.ClassicCard;
import it.unibo.monopoli.model.cards.CommunityChest;
import it.unibo.monopoli.model.cards.Deck;
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
            break;
        case 3:
            players.stream().forEach(p -> {
                this.addOwnerships(6, p);
                p.setMoney(300);
            });
            break;
        case 4:
            players.stream().forEach(p -> {
                this.addOwnerships(5, p);
                p.setMoney(250);
            });
            break;
        case 5:
            players.stream().forEach(p -> {
                this.addOwnerships(4, p);
                p.setMoney(200);
            });
            break;
        case 6:
            players.stream().forEach(p -> {
                this.addOwnerships(3, p);
                p.setMoney(150);
            });
            break;
        default:
            break;
        }
    }

    private void addOwnerships(final int nOfOwnership, final Player player) {
        for (int i = 0; i < nOfOwnership; i++) {
            final Ownership ow = this.bank.getOwnership();
            player.addOwnership(ow);
            ow.setOwner(player);
        }
    }

    private void inizializesGroups() {
        this.groups.add(new ClassicLandGroup("Dark Gray", this.ownerships.get(BoxesPositions.OWNERSHIP_N_1.getPos()),
                this.ownerships.get(BoxesPositions.OWNERSHIP_N_2.getPos())));
        this.groups.add(new ClassicLandGroup("Cyan", this.ownerships.get(BoxesPositions.OWNERSHIP_N_4.getPos()),
                this.ownerships.get(BoxesPositions.OWNERSHIP_N_5.getPos()), this.ownerships.get(BoxesPositions.OWNERSHIP_N_6.getPos())));
        this.groups.add(new ClassicLandGroup("Magenta", this.ownerships.get(BoxesPositions.OWNERSHIP_N_7.getPos()),
                this.ownerships.get(BoxesPositions.OWNERSHIP_N_9.getPos()), this.ownerships.get(BoxesPositions.OWNERSHIP_N_10.getPos())));
        this.groups.add(new ClassicLandGroup("Orange", this.ownerships.get(BoxesPositions.OWNERSHIP_N_12.getPos()),
                this.ownerships.get(BoxesPositions.OWNERSHIP_N_13.getPos()), this.ownerships.get(BoxesPositions.OWNERSHIP_N_14.getPos())));
        this.groups.add(new ClassicLandGroup("Red", this.ownerships.get(BoxesPositions.OWNERSHIP_N_15.getPos()),
                this.ownerships.get(BoxesPositions.OWNERSHIP_N_16.getPos()), this.ownerships.get(BoxesPositions.OWNERSHIP_N_17.getPos())));
        this.groups.add(new ClassicLandGroup("Yellow", this.ownerships.get(BoxesPositions.OWNERSHIP_N_19.getPos()),
                this.ownerships.get(BoxesPositions.OWNERSHIP_N_20.getPos()), this.ownerships.get(BoxesPositions.OWNERSHIP_N_22.getPos())));
        this.groups.add(new ClassicLandGroup("Green", this.ownerships.get(BoxesPositions.OWNERSHIP_N_23.getPos()),
                this.ownerships.get(BoxesPositions.OWNERSHIP_N_24.getPos()), this.ownerships.get(BoxesPositions.OWNERSHIP_N_25.getPos())));
        this.groups.add(
                new ClassicLandGroup("Blue", this.ownerships.get(BoxesPositions.OWNERSHIP_N_27.getPos()), this.ownerships.get(BoxesPositions.OWNERSHIP_N_28.getPos())));
        this.groups.add(new ClassicLandGroup("Stations", this.ownerships.get(BoxesPositions.OWNERSHIP_N_3.getPos()),
                this.ownerships.get(BoxesPositions.OWNERSHIP_N_11.getPos()), this.ownerships.get(BoxesPositions.OWNERSHIP_N_18.getPos()),
                this.ownerships.get(BoxesPositions.OWNERSHIP_N_26.getPos())));
        this.groups.add(new ClassicLandGroup("Companies", this.ownerships.get(BoxesPositions.OWNERSHIP_N_8.getPos()),
                this.ownerships.get(BoxesPositions.OWNERSHIP_N_21.getPos())));
    }

    private void inizializesContracts() {
        this.contracts.add(BoxesPositions.OWNERSHIP_N_1.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_1.getPos()))
                .landCost(60).landIncome(2).buildingCost(50).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_1.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_1.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_2.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_2.getPos()))
                .landCost(60).landIncome(4).buildingCost(50).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_2.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_2.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_3.getPos(), new ClassicContract.Builder().ownership(this.ownerships.get(BoxesPositions.OWNERSHIP_N_3.getPos()))
                .ownershipsCost(200).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_3.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_3.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_4.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_4.getPos()))
                .landCost(100).landIncome(6).buildingCost(50).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_4.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_4.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_5.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_5.getPos()))
                .landCost(100).landIncome(6).buildingCost(50).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_5.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_5.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_6.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_6.getPos()))
                .landCost(120).landIncome(8).buildingCost(50).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_6.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_6.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_7.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_7.getPos()))
                .landCost(140).landIncome(10).buildingCost(100).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_7.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_7.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_8.getPos(), new ClassicContract.Builder().ownership(this.ownerships.get(BoxesPositions.OWNERSHIP_N_8.getPos()))
                .ownershipsCost(150).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_8.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_8.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_9.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_9.getPos()))
                .landCost(140).landIncome(10).buildingCost(100).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_9.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_9.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_10.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_10.getPos()))
                .landCost(160).landIncome(12).buildingCost(100).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_10.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_10.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_11.getPos(), new ClassicContract.Builder().ownership(this.ownerships.get(BoxesPositions.OWNERSHIP_N_11.getPos()))
                .ownershipsCost(200).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_11.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_11.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_12.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_12.getPos()))
                .landCost(180).landIncome(14).buildingCost(100).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_12.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_12.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_13.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_13.getPos()))
                .landCost(180).landIncome(14).buildingCost(100).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_13.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_13.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_14.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_14.getPos()))
                .landCost(200).landIncome(16).buildingCost(100).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_14.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_14.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_15.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_15.getPos()))
                .landCost(220).landIncome(18).buildingCost(150).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_15.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_15.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_16.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_16.getPos()))
                .landCost(220).landIncome(18).buildingCost(150).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_16.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_16.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_17.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_17.getPos()))
                .landCost(240).landIncome(20).buildingCost(150).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_17.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_17.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_18.getPos(), new ClassicContract.Builder().ownership(this.ownerships.get(BoxesPositions.OWNERSHIP_N_18.getPos()))
                .ownershipsCost(200).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_18.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_18.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_19.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_19.getPos()))
                .landCost(260).landIncome(22).buildingCost(150).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_19.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_19.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_20.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_20.getPos()))
                .landCost(260).landIncome(22).buildingCost(150).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_20.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_20.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_21.getPos(), new ClassicContract.Builder().ownership(this.ownerships.get(BoxesPositions.OWNERSHIP_N_21.getPos()))
                .ownershipsCost(150).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_21.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_21.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_22.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_22.getPos()))
                .landCost(280).landIncome(24).buildingCost(150).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_22.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_22.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_23.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_23.getPos()))
                .landCost(300).landIncome(26).buildingCost(200).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_23.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_23.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_24.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_24.getPos()))
                .landCost(300).landIncome(26).buildingCost(200).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_24.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_24.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_25.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_25.getPos()))
                .landCost(320).landIncome(28).buildingCost(200).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_25.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_25.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_26.getPos(), new ClassicContract.Builder().ownership(this.ownerships.get(BoxesPositions.OWNERSHIP_N_26.getPos()))
                .ownershipsCost(200).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_26.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_26.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_27.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_27.getPos()))
                .landCost(350).landIncome(35).buildingCost(200).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_27.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_27.getPos()));
        this.contracts.add(BoxesPositions.OWNERSHIP_N_28.getPos(), new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.OWNERSHIP_N_28.getPos()))
                .landCost(400).landIncome(50).buildingCost(200).build());
        this.ownerships.get(BoxesPositions.OWNERSHIP_N_28.getPos()).setContract(this.contracts.get(BoxesPositions.OWNERSHIP_N_28.getPos()));
    }

    private void inizializesOwnerships() {
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_1.getPos(), new ClassicLand("OLD KENT ROAD", 1, this.bank, Color.DARK_GRAY));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_2.getPos(), new ClassicLand("WHITECHAPEL ROAD", 3, this.bank, Color.DARK_GRAY));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_3.getPos(), new Station("KINGS CROSS STATION", 5, this.bank));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_4.getPos(), new ClassicLand("THE ANGEL, ISLINGTON", 6, this.bank, Color.CYAN));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_5.getPos(), new ClassicLand("EUSTON ROAD", 8, this.bank, Color.CYAN));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_6.getPos(), new ClassicLand("PENTONVILLE ROAD", 9, this.bank, Color.CYAN));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_7.getPos(), new ClassicLand("PALL MALL", 11, this.bank, Color.MAGENTA));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_8.getPos(), new Company("ELECTRIC COMPANY", 12, this.bank));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_9.getPos(), new ClassicLand("WHITEHALL", 13, this.bank, Color.MAGENTA));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_10.getPos(), new ClassicLand("NORTHUMRL'D AVENUE", 14, this.bank, Color.MAGENTA));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_11.getPos(), new Station("MARYLEBONE STATION", 15, this.bank));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_12.getPos(), new ClassicLand("BOW STREET", 16, this.bank, Color.ORANGE));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_13.getPos(), new ClassicLand("MARLBOROUGH STREET", 18, this.bank, Color.ORANGE));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_14.getPos(), new ClassicLand("VINE STREET", 19, this.bank, Color.ORANGE));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_15.getPos(), new ClassicLand("STRAND", 21, this.bank, Color.RED));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_16.getPos(), new ClassicLand("FLEET STREET", 23, this.bank, Color.RED));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_17.getPos(), new ClassicLand("TRAFALGAR SQUARE", 24, this.bank, Color.RED));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_18.getPos(), new Station("FENCHURCH ST. STATION", 25, this.bank));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_19.getPos(), new ClassicLand("LEICESTER SQUARE", 26, this.bank, Color.YELLOW));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_20.getPos(), new ClassicLand("COVENTRY STREET", 27, this.bank, Color.YELLOW));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_21.getPos(), new Company("WATER COMPANY", 28, this.bank));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_22.getPos(), new ClassicLand("PICCADILLY", 29, this.bank, Color.YELLOW));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_23.getPos(), new ClassicLand("REGENT STREET", 31, this.bank, Color.GREEN));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_24.getPos(), new ClassicLand("OXFORD STREET", 32, this.bank, Color.GREEN));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_25.getPos(), new ClassicLand("BOND STREET", 34, this.bank, Color.GREEN));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_26.getPos(), new Station("LIVERPOOL ST. STATION", 35, this.bank));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_27.getPos(), new ClassicLand("PARK LANE", 37, this.bank, Color.BLUE));
        this.ownerships.add(BoxesPositions.OWNERSHIP_N_28.getPos(), new ClassicLand("MAYFAIR", 39, this.bank, Color.BLUE));
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

    @Override
    public List<Deck> getDecks() {
        // TODO Auto-generated method stub
        return null;
    }

    private void inizializesBoxes() {
        final List<Box> temp = new LinkedList<>();
//        final Box[] boxes = new Box[40];
//        this.ownerships.stream().forEach(o -> {
//            boxes[o.getID()] = o;
//        });
        temp.add(new Start("GO", BoxesPositions.START_POSITION.getPos()));
        final PrisonOrTransit prison = new PrisonOrTransit("IN JAIL OR JUST VISITING", BoxesPositions.PRISON_POSITION.getPos());
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
//        this.allBoxes.addAll(Arrays.asList(boxes));
        this.allBoxes = temp.stream().sorted(new Comparator<Box>() {

            @Override
            public int compare(Box o1, Box o2) {
                return o1.getID() - o2.getID();
            }
            
        }).collect(Collectors.toList());
    }

    private void inizializesDecks() {
        final Chance chance = new Chance();
        chance.addCard(new ClassicCard("TAKE 3 STEPS BACK (WITH BEST WISHES)", CardsId.CARD1.getValue(), MoveUpTo.takeSteps(-3)));
        chance.addCard(new ClassicCard("GO TO PARK LANE: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(), CardsId.CARD2.getValue()));
        chance.addCard(new ClassicCard(
                "ADVANCE TO THE NEAREST STATION: IF IT'S FREE, YOU CAN BUY IT;"
                        + "IF IT IS OWNED BY ANOTHER PLAYER, PAY HIM TWICE THE PRICE THAT MATTER",
                CardsId.CARD3.getValue(), MoveUpTo.theNearestStation()));
        chance.addCard(new ClassicCard(
                "GET OUT FREE OF JAIL. YOU CAN KEEP THIS CARD AND USE IT WHEN YOU WANT TO, OR YOU CAN SELL IT", CardsId.CARD4.getValue()));
        chance.addCard(new ClassicCard("FINE FOR SPEEDING. PAY $20", CardsId.CARD5.getValue()));
        chance.addCard(new ClassicCard("THE BANK WILL YOU PAY A BONUS OF $50", CardsId.CARD6.getValue(), new ToBePaid(50)));
        chance.addCard(new ClassicCard("GO DIRECTLY TO JAIL", CardsId.CARD7.getValue()));
        chance.addCard(new ClassicCard(
                "PERFORM MAINTENANCE WORK ON ALL OUR BUILDINGS. YOU HAVE TO PAY $25 FOR EACH HOME AND $100 FOR EACH HOTEL THAT YOU OWN",
                CardsId.CARD8.getValue()));
        chance.addCard(new ClassicCard(
                "YOU HAVE BEEN PROMOTED TO THE PRESIDENCY OF THE BOARD OF DIRECTORS. YOU HAVE TO PAY 50 TO ANY PLAYER",
                CardsId.CARD9.getValue()));
        chance.addCard(new ClassicCard("GO TO BOX 'GO' AND TAKE $" + Start.getMuchToPick(), CardsId.CARD10.getValue()));
        chance.addCard(new ClassicCard(
                "GO TO THE FENCHURCH ST. STATION: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(), CardsId.CARD11.getValue()));
        chance.addCard(new ClassicCard(
                "ADVANCE TO THE NEAREST STATION: IF IT'S FREE, YOU CAN BUY IT;"
                        + "IF IT IS OWNED BY ANOTHER PLAYER, PAY HIM TWICE THE PRICE THAT MATTER",
                CardsId.CARD12.getValue(), MoveUpTo.theNearestStation()));
        chance.addCard(new ClassicCard("GO TO THE TRAFALGAR SQUARE: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(),
                CardsId.CARD13.getValue()));
        chance.addCard(
                new ClassicCard("GO TO THE WHITEHALL: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(), CardsId.CARD14.getValue()));
        chance.addCard(new ClassicCard(
                "ADVANCE TO THE NEAREST STATION: IF IT'S FREE, YOU CAN BUY IT;"
                        + "IF IT IS OWNED BY ANOTHER PLAYER, LAUNCHING THE DICES AND PAY THE OWNER 10 TIMES THE NUMBER RELEASED",
                CardsId.CARD15.getValue(), MoveUpTo.theNearestStation(), new ToRollDices(new ClassicDicesStrategy())));
        chance.addCard(new ClassicCard("MATURANO THE COUPONS OF YOUR REAL ESTATE FUNDS: COLLECT $150", CardsId.CARD16.getValue(),
                new ToBePaid(150)));
        final CommunityChest chest = new CommunityChest();
        chest.addCard(new ClassicCard("RECEIVE DOCTOR'S BILL, PAY $50", CardsId.CARD17.getValue()));
        chest.addCard(new ClassicCard("PAY SCHOOL FEES OF OUR CHILDREN: $50", CardsId.CARD18.getValue()));
        chest.addCard(new ClassicCard("INHERITED $100 FROM A UNCLE", CardsId.CARD19.getValue(), new ToBePaid(50)));
        chest.addCard(new ClassicCard(
                "GET OUT FREE OF JAIL. YOU CAN KEEP THIS CARD AND USE IT WHEN YOU WANT TO, OR YOU CAN SELL IT",
                CardsId.CARD20.getValue()));
        chest.addCard(new ClassicCard("PAY HOSPITAL'S BILL. PAY $100", CardsId.CARD21.getValue()));
        chest.addCard(new ClassicCard("FOR THE SALE OF A STOCK OF PRODUCTS YOU OBTAIN $50", CardsId.CARD22.getValue(), new ToBePaid(50)));
        chest.addCard(new ClassicCard("GO DIRECTLY TO JAIL", CardsId.CARD23.getValue()));
        chest.addCard(new ClassicCard(
                "PAY CONTRIBUTIONS TO IMPROVE THE ROADS. YOU HAVE TO PAY $40 FOR EACH HOME AND $115 FOR EACH HOTEL THAT YOU OWN",
                CardsId.CARD24.getValue()));
        chest.addCard(new ClassicCard("IS YOUR BIRTHDAY. EACH PLAYER GIVES YOU $10", CardsId.CARD25.getValue()));
        chest.addCard(new ClassicCard("GO TO BOX 'GO' AND TAKE $" + Start.getMuchToPick(), CardsId.CARD26.getValue()));
        chest.addCard(new ClassicCard("GET $25 FOR YOUR ADVICE", CardsId.CARD27.getValue(), new ToBePaid(25)));
        chest.addCard(
                new ClassicCard("BANK RECOGNIZES AN ERROR IN YOUR STATEMENT. COLLECT $200", CardsId.CARD28.getValue(), new ToBePaid(200)));
        chest.addCard(
                new ClassicCard("YOU WON THE SECOND PRIZE IN A BEAUTY CONTEST. COLLECT $10", CardsId.CARD29.getValue(), new ToBePaid(10)));
        chest.addCard(
                new ClassicCard("MATURANO THE COUPONS OF YOUR ACTITONS. COLLECT $100", CardsId.CARD30.getValue(), new ToBePaid(100)));
        chest.addCard(new ClassicCard("FEE INCOME WAS REFUNDED. GET $20", CardsId.CARD31.getValue(), new ToBePaid(20)));
        chest.addCard(
                new ClassicCard("MATURANO INTEREST ON YOUR LIFE INSURANCE: COLLECT $100", CardsId.CARD32.getValue(), new ToBePaid(100)));

        this.decks.add(0, chance);
        this.decks.add(1, chest);
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

//    @Override
//    public List<Action> getNextCardsActions(final Box box, final Card card, final Player player) {
//        final List<Action> actions = new LinkedList<>();
//        switch (card.getID()) {
//        case CardsId.CARD2.getValue():
//            actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(37)));
//        case CardsId.CARD3.getValue():
//            if (((Ownership) box).getOwner().equals(player)) {
//                actions.addAll(this.getNextBoxsActions(box, player));
//            } else {
//                final int amount = 2
//                        * ((Ownership) box).getContract().getIncome(new StationIncomeStrategy(((Ownership) box)));
//                actions.add(new ToPay(amount, player));
//                new ToBePaid(amount);
//            }
//            break;
//        case CardsId.CARD4.getValue():
//            player.addCard(card);
//            break;
//        case CACardsId.CARD5.getValue():
//            actions.add(new ToPay(20, player));
//            break;
//        case CARD7:
//            actions.add(new GoToPrison(this.allBoxes.get(BoxesPositions.PRISON_POSITION.getPos())));
//        case CardsId.CARD8.getValue():
//            player.getOwnerships().get().stream().filter(o -> o instanceof Land)
//                    .filter(o -> !((LandGroup) o.getGroup()).getBuildings().isEmpty())
//                    .map(o -> ((LandGroup) o.getGroup()).getBuildings()).forEach(l -> {
//                        l.forEach(b -> {
//                            actions.add(new ToPay(b instanceof Home ? 25 : 100, player));
//                        });
//                    });
//            break;
//        case CardsId.CARD9.getValue():
//            this.players.stream().filter(p -> !p.equals(player)).forEach(p -> {
//                try {
//                    new ToPay(CARD_TAX, player).play(player);
//                    new ToBePaid(CARD_TAX).play(p);
//                } catch (IllegalArgumentException i) {
//                    this.notMuchMoney(player, actions);
//                }
//            });
//            break;
//        case CARCardsId.CARD10.getValue()D10:
//            actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(BoxesPositions.START_POSITION.getPos())));
//        case CARD11:
//            actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(OWNERSHIP_N_18)));
//        case CARD12:
//            if (((Ownership) box).getOwner().equals(player)) {
//                actions.addAll(this.getNextBoxsActions(box, player));
//            } else {
//                final int amount = 2
//                        * ((Ownership) box).getContract().getIncome(new StationIncomeStrategy(((Ownership) box)));
//                actions.add(new ToPay(amount, player));
//                new ToBePaid(amount);
//            }
//            break;
//        case CARD13:
//            actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(OWNERSHIP_N_17)));
//        case CARD14:
//            actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(OWNERSHIP_N_9)));
//        case CARD15:
//            if (((Ownership) box).getOwner().equals(player)) {
//                actions.addAll(this.getNextBoxsActions(box, player));
//            } else {
//                final int amount = (player.lastDicesNumber().stream().reduce((d, d1) -> d + d1).get() * 10);
//                actions.add(new ToPay(amount, player));
//                new ToBePaid(amount);
//            }
//            break;
//        case CARD17:
//            actions.add(new ToPay(50, player));
//            break;
//        case CARD18:
//            actions.add(new ToPay(50, player));
//            break;
//        case CARD20:
//            player.addCard(card);
//            break;
//        case CARD21:
//            actions.add(new ToPay(100, player));
//            break;
//        case CARD23:
//            actions.add(new GoToPrison(this.allBoxes.get(BoxesPositions.PRISON_POSITION.getPos())));
//        case CARD24:
//            player.getOwnerships().get().stream().filter(o -> o instanceof Land)
//                    .filter(o -> !((LandGroup) o.getGroup()).getBuildings().isEmpty())
//                    .map(o -> ((LandGroup) o.getGroup()).getBuildings()).forEach(l -> {
//                        l.forEach(b -> {
//                            actions.add(new ToPay(b instanceof Home ? 40 : 115, player));
//                        });
//                    });
//            break;
//        case CARD25:
//            this.players.stream().filter(p -> !p.equals(player)).forEach(p -> {
//                try {
//                    new ToPay(10, p).play(p);
//                    new ToBePaid(10).play(player);
//                } catch (IllegalArgumentException i) {
//                    this.notMuchMoney(player, actions);
//                }
//            });
//            break;
//        case CARD26:
//            actions.add(MoveUpTo.moveUpToBox(this.allBoxes.get(BoxesPositions.START_POSITION.getPos())));
//        default:
//            break;
//        }
//        return actions;
//    }
//    private void notMuchMoney(final Player player, final List<Action> actions) {
//        if (player.getOwnerships().isPresent()) {
//            player.getOwnerships().get().stream().filter(o -> o.getGroup() instanceof LandGroup)
//                    .filter(o -> ((LandGroup) o.getGroup()).getBuildings().size() > 0).forEach(o -> {
//                        ((LandGroup) o.getGroup()).getBuildings()
//                                .forEach(b -> actions.add(ToSellProperties.sellABuilding(((Land) o), b, this.bank)));
//                    });
//            if (actions.isEmpty()) {
//                player.getOwnerships().get().stream().forEach(o -> {
//                    actions.add(ToSellProperties.sellAOwnership(o, this.bank));
//                });
//            }
//        } else if (player.getCards().isPresent()) {
//            player.getCards().get().forEach(c -> actions.add(ToAuction.cards(this.players, new ClassicAuction(), c)));
//        } else {
//            // FINE DEL GIOCO -> interfaccia funzionale (Action ?) + classe
//            // anonima: rimuove il giocatore con il play)
//            actions.add(p -> {
//                this.players.remove(p);
//            });
//        }
//    }
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
    
    public static void main(String[] args) {
        List<Player> l = new LinkedList<>();
        l.add(new ClassicPlayer("v", new ClassicPawn(3), true));
        ClassicStrategy c = new ClassicStrategy(l);
        List<Box> b = c.getBoxes();
        for(int i = 0; i < 40 ; i++) {
            System.out.println(b.get(i).getName());
            System.out.println(b.get(i).getID());
        }
        
        List<Integer> n = new LinkedList<>();
        n.add(3);
        n.add(5);
        n.add(1);
        n.add(8);
        n.add(2);
        n.add(6);
        
        List<Integer> v = n.stream().sorted((i, i1) -> (i - i1)).collect(Collectors.toList());
        
        System.out.println(v);
    }
}
