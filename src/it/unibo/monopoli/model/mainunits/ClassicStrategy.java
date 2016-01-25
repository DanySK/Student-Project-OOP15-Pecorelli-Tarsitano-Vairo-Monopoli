package it.unibo.monopoli.model.mainunits;

import java.awt.Color;
import java.awt.Point;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.actions.AddCardToPlayer;
import it.unibo.monopoli.model.actions.ClassicDicesStrategy;
import it.unibo.monopoli.model.actions.GoToPrison;
import it.unibo.monopoli.model.actions.MoveUpTo;
import it.unibo.monopoli.model.actions.ToBePaid;
import it.unibo.monopoli.model.actions.ToPay;
import it.unibo.monopoli.model.actions.ToRollDices;
import it.unibo.monopoli.model.cards.Chance;
import it.unibo.monopoli.model.cards.ClassicCard;
import it.unibo.monopoli.model.cards.ClassicCardBelonging;
import it.unibo.monopoli.model.cards.CommunityChest;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.ClassicLand;
import it.unibo.monopoli.model.table.ClassicLandContract;
import it.unibo.monopoli.model.table.ClassicOwnership;
import it.unibo.monopoli.model.table.Contract;
import it.unibo.monopoli.model.table.DecksBox;
import it.unibo.monopoli.model.table.Group;
import it.unibo.monopoli.model.table.Home;
import it.unibo.monopoli.model.table.Hotel;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.NeutralArea;
import it.unibo.monopoli.model.table.Ownership;
import it.unibo.monopoli.model.table.Police;
import it.unibo.monopoli.model.table.PrisonOrTransit;
import it.unibo.monopoli.model.table.Start;
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

    private static final int N_MAX_OF_HOUSES = 32;
    private static final int N_MAX_OF_HOTELS = 12;
    private static final int AMOUNT_OF_FEES = 10;

    private final List<Player> players;
    private final List<Ownership> ownerships;
    private final List<Group> groups;
    private final List<Contract> contracts;
    private final List<Building> buildings;
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
        this.players = players;
        this.inizializesPlayers(players);
        this.inizializesOwnerships();
        this.inizializesGroups();
        this.inizializesBuildings();
        this.inizializesContracts();
        this.allBoxes = new LinkedList<>();
        this.decks = new LinkedList<>();
        this.inizializesDecks();
        this.decks.add(new CommunityChest());
        this.decks.add(new Chance());
        this.bank = new ClassicBank(this.ownerships, this.buildings);
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
        Random r = new Random();
        for (int i = 0; i < nOfOwnership; i++) {
            Ownership ow = this.ownerships.remove(r.nextInt(this.ownerships.size()));
            player.addOwnership(ow);
            ow.setOwner(player);
        }
    }
    
    private void inizializesGroups() {
        this.groups.add();
    }
    
    private void inizializesContracts() {
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(1)).);
    }

    private void inizializesOwnerships() {
        this.ownerships.add(new ClassicLand("OLD KENT ROAD", 1, this.bank, Color.DARK_GRAY));
        this.ownerships.add(new ClassicLand("WHITECHAPEL ROAD", 3, this.bank, Color.DARK_GRAY));
        this.ownerships.add(new ClassicOwnership("KINGS CROSS STATION", 5, this.bank));
        this.ownerships.add(new ClassicLand("THE ANGEL, ISLINGTON", 6, this.bank, Color.CYAN));
        this.ownerships.add(new ClassicLand("EUSTON ROAD", 8, this.bank, Color.CYAN));
        this.ownerships.add(new ClassicLand("PENTONVILLE ROAD", 9, this.bank, Color.CYAN));
        this.ownerships.add(new ClassicLand("PALL MALL", 11, this.bank, Color.MAGENTA));
        this.ownerships.add(new ClassicOwnership("ELECTRIC COMPANY", 12, this.bank));
        this.ownerships.add(new ClassicLand("WHITEHALL", 13, this.bank, Color.MAGENTA));
        this.ownerships.add(new ClassicLand("NORTHUMRL'D AVENUE", 14, this.bank, Color.MAGENTA));
        this.ownerships.add(new ClassicOwnership("MARYLEBONE STATION", 15, this.bank));
        this.ownerships.add(new ClassicLand("BOW STREET", 16, this.bank, Color.ORANGE));
        this.ownerships.add(new ClassicLand("MARLBOROUGH STREET", 18, this.bank, Color.ORANGE));
        this.ownerships.add(new ClassicLand("VINE STREET", 19, this.bank, Color.ORANGE));
        this.ownerships.add(new ClassicLand("STRAND", 21, this.bank, Color.RED));
        this.ownerships.add(new ClassicLand("FLEET STREET", 23, this.bank, Color.RED));
        this.ownerships.add(new ClassicLand("TRAFALGAR SQUARE", 24, this.bank, Color.RED));
        this.ownerships.add(new ClassicOwnership("FENCHURCH ST. STATION", 25, this.bank));
        this.ownerships.add(new ClassicLand("LEICESTER SQUARE", 26, this.bank, Color.YELLOW));
        this.ownerships.add(new ClassicLand("COVENTRY STREET", 27, this.bank, Color.YELLOW));
        this.ownerships.add(new ClassicOwnership("WATER COMPANY", 28, this.bank));
        this.ownerships.add(new ClassicLand("PICCADILLY", 29, this.bank, Color.YELLOW));
        this.ownerships.add(new ClassicLand("REGENT STREET", 31, this.bank, Color.GREEN));
        this.ownerships.add(new ClassicLand("OXFORD STREET", 32, this.bank, Color.GREEN));
        this.ownerships.add(new ClassicLand("BOND STREET", 34, this.bank, Color.GREEN));
        this.ownerships.add(new ClassicOwnership("LIVERPOOL ST. STATION", 35, this.bank));
        this.ownerships.add(new ClassicLand("PARK LANE", 37, this.bank, Color.BLUE));
        this.ownerships.add(new ClassicLand("MAYFAIR", 39, this.bank, Color.BLUE));
    }

    private void inizializesBuildings() {
        for (int i = 0; i < N_MAX_OF_HOUSES; i++) {
            this.buildings.add(new Home(i + 1));
        }
        for (int i = N_MAX_OF_HOUSES; i < N_MAX_OF_HOUSES + N_MAX_OF_HOTELS; i++) {
            this.buildings.add(new Hotel(i + 1));
        }
    }

//    @Override
//    public List<Player> getPlayers() {
//        return Collections.unmodifiableList(this.players);
//    }

    @Override
    public Bank getBank() {
        return this.bank;
    }

    @Override
    public List<Box> getBoxes() {
        this.ownerships.stream().forEach(o -> {
            this.allBoxes.add(o.getID(), o);
        });
        this.allBoxes.add(START_POSITION, new Start("GO", START_POSITION));
        PrisonOrTransit prison = new PrisonOrTransit("IN JAIL OR JUST VISITING", PRISON_POSITION);
        this.allBoxes.add(PRISON_POSITION, prison);
        this.allBoxes.add(NEUTRAL_AREA_POSITION, new NeutralArea("FREE PARKING", NEUTRAL_AREA_POSITION));
        this.allBoxes.add(POLICE_POSITION, new Police("GO TO JAIL", POLICE_POSITION, prison));
        this.allBoxes.add(FIRST_CHANCE_POSITION, new DecksBox("CHANCE", FIRST_CHANCE_POSITION, this.decks.get(0)));
        this.allBoxes.add(SECOND_CHANCE_POSITION, new DecksBox("CHANCE", SECOND_CHANCE_POSITION, this.decks.get(0)));
        this.allBoxes.add(THIRD_CHANCE_POSITION, new DecksBox("CHANCE", THIRD_CHANCE_POSITION, this.decks.get(0)));
        this.allBoxes.add(FIRST_COMMUNITY_CHEST_POSITION,
                new DecksBox("COMMUNITY CHEST", FIRST_COMMUNITY_CHEST_POSITION, this.decks.get(1)));
        this.allBoxes.add(SECOND_COMMUNITY_CHEST_POSITION,
                new DecksBox("COMMUNITY CHEST", SECOND_COMMUNITY_CHEST_POSITION, this.decks.get(1)));
        this.allBoxes.add(THIRD_COMMUNITY_CHEST_POSITION,
                new DecksBox("COMMUNITY CHEST", THIRD_COMMUNITY_CHEST_POSITION, this.decks.get(1)));
        this.allBoxes.add(INCOME_TAX_POSITION, new TaxImpl("INCOME TAX", INCOME_TAX_POSITION, AMOUNT_OF_FEES));
        this.allBoxes.add(SUPER_TAX_POSITION, new TaxImpl("SUPER TAX", SUPER_TAX_POSITION, AMOUNT_OF_FEES));
        return this.allBoxes;
    }

    private void inizializesDecks() {
        Chance chance = new Chance();
        chance.addCard(new ClassicCard("TAKE 3 STEPS BACK (WITH BEST WISHES)", MoveUpTo.takeSteps(-3)));
        chance.addCard(new ClassicCard("GO TO PARK LANE: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(), MoveUpTo.moveUpToBox(this.allBoxes.get(37))));
        chance.addCard(new ClassicCard("ADVANCE TO THE NEAREST STATION: IF IT'S FREE, YOU CAN BUY IT;"
                + "IF IT IS OWNED BY ANOTHER PLAYER, PAY HIM TWICE THE PRICE THAT MATTER", MoveUpTo.theNearestStation(), new ToPay(amount)));
        ClassicCardBelonging card1 = new ClassicCardBelonging("GET OUT FREE OF JAIL. YOU CAN KEEP THIS CARD AND USE IT WHEN YOU WANT TO, OR YOU CAN SELL IT");
        card1.addItselfToPlayer();
        chance.addCard(card1);
        chance.addCard(new ClassicCard("FINE FOR SPEEDING. PAY $20", new ToPay(20)));
        chance.addCard(new ClassicCard("THE BANK WILL YOU PAY A BONUS OF $50", new ToBePaid(50)));
        chance.addCard(new ClassicCard("GO DIRECTLY TO JAIL", new GoToPrison(this.allBoxes.get(10))));
        chance.addCard(new ClassicCard("PERFORM MAINTENANCE WORK ON ALL OUR BUILDINGS. YOU HAVE TO PAY 25 FOR EACH HOME AND 100 FOR EACH HOTEL THAT YOU OWN", new ToPay()));
        chance.addCard(new ClassicCard("YOU HAVE BEEN PROMOTED TO THE PRESIDENCY OF THE BOARD OF DIRECTORS. YOU HAVE TO PAY 50 TO ANY PLAYER", new ToPay(), new ToBePaid(amount)));
        chance.addCard(new ClassicCard("GO TO BOX 'GO' AND TAKE $" + Start.getMuchToPick(), MoveUpTo.moveUpToBox(this.allBoxes.get(START_POSITION))));
        chance.addCard(new ClassicCard("GO TO THE FENCHURCH ST. STATION: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(), MoveUpTo.moveUpToBox(this.allBoxes.get()));
        chance.addCard(new ClassicCard("ADVANCE TO THE NEAREST STATION: IF IT'S FREE, YOU CAN BUY IT;"
                + "IF IT IS OWNED BY ANOTHER PLAYER, PAY HIM TWICE THE PRICE THAT MATTER", MoveUpTo.theNearestStation(), new ToPay(amount)));
        chance.addCard(new ClassicCard("GO TO THE TRAFALGAR SQUARE: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(), MoveUpTo.moveUpToBox(this.allBoxes.get()));
        chance.addCard(new ClassicCard("GO TO THE WHITEHALL: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(), MoveUpTo.moveUpToBox(this.allBoxes.get()));
        chance.addCard(new ClassicCard("ADVANCE TO THE NEAREST STATION: IF IT'S FREE, YOU CAN BUY IT;"
                + "IF IT IS OWNED BY ANOTHER PLAYER, LAUNCHING THE DICES AND PAY THE OWNER 10 TIMES THE NUMBER RELEASED", MoveUpTo.theNearestStation(), new ToRollDices(new ClassicDicesStrategy()), new ToPay(amount)));
        chance.addCard(new ClassicCard("MATURANO THE COUPONS OF YOUR REAL ESTATE FUNDS: COLLECT $150", new ToBePaid(50)));
        CommunityChest chest = new CommunityChest();
        chest.addCard(new );
        this.decks.add(0, chance);
        this.decks.add(1, chest);
    }
}
