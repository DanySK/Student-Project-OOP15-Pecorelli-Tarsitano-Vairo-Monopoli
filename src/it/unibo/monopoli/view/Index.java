package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.controller.Actions;
import it.unibo.monopoli.controller.Controller;
import it.unibo.monopoli.controller.ControllerImpl;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Home;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.LandGroup;
import it.unibo.monopoli.model.table.Ownership;
import it.unibo.monopoli.view.cards.IBoxGraphic;
import it.unibo.monopoli.view.cards.LandGraphic;
import it.unibo.monopoli.view.listener.StartPlay;

public class Index {
    private static final int FIRST_USEFUL_POSITION = 28;
    private static final int LAST_USEFUL_POSITION = 11;

    private final Controller controller;
    private final List<JButton> buttonList;
    private East eastP;
    private int position;
    HashMap<Integer, IBoxGraphic> tessere;

    public Index() {

        this.controller = new ControllerImpl();
        this.buttonList = new LinkedList<>();

    }

    public void build() {

        final MyFrame frame = new MyFrame("Monopoli", new BorderLayout(), new Dimension(1200, 720));
        List<Player> player = new ArrayList();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        ProvaTabellone tabellone = new ProvaTabellone(11, 11, this.controller);
        tessere = tabellone.getCardsGraphic();
        // JPanelMain South
        final JPanel southP = new JPanel();
        southP.setPreferredSize(new Dimension(frame.getWidth(), 65));
        southP.setLayout(new FlowLayout());
        JButton rollDices = new JButton(Actions.ROLL_DICES.getText());
        JButton END_OF_TURN = new JButton(Actions.END_OF_TURN.getText());
        JButton buy = new JButton(Actions.BUY.getText());
        JButton sell = new JButton(Actions.SELL.getText());
        JButton build = new JButton(Actions.BUILD.getText());
        JButton sellBuilding = new JButton(Actions.SELL_BUILDING.getText());
        JButton mortgage = new JButton(Actions.MORTGAGE.getText());
        JButton revoke = new JButton(Actions.REVOKE_MORTGAGE.getText());
        JButton endGame = new JButton(Actions.END_OF_THE_GAME.getText());

        southP.add(rollDices);
        southP.add(END_OF_TURN);
        southP.add(buy);
        southP.add(sell);
        southP.add(build);
        southP.add(sellBuilding);
        southP.add(mortgage);
        southP.add(revoke);
        southP.add(endGame);

        buttonList.add(rollDices);
        buttonList.add(END_OF_TURN);
        buttonList.add(buy);
        buttonList.add(sell);
        buttonList.add(build);
        buttonList.add(sellBuilding);
        buttonList.add(mortgage);
        buttonList.add(revoke);
        buttonList.add(endGame);

        rollDices.setEnabled(true);
        END_OF_TURN.setEnabled(false);
        buy.setEnabled(false);
        sell.setEnabled(false);
        build.setEnabled(false);
        sellBuilding.setEnabled(false);
        mortgage.setEnabled(false);
        revoke.setEnabled(false);
        endGame.setEnabled(false);

        rollDices.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Player Roll Dicies: " + controller.getActualPlayer().getName());
                Player p = controller.getActualPlayer();

                tessere.get(controller.getActualPlayer().getPawn()
                        .getActualPos()/* controller.getActualPosition() */).removePawn(p);
                int prePos = controller.getActualPlayer().getPawn().getActualPos();
                position = controller.toRollDices();
                int passi = position - prePos;
                if (isPassedFromStartBox()) {
                    passi = passFromStart();
                }
                new Dialog(new JFrame(), "Roll Dieces", "Number: " + (passi));
                System.out.println("new pos: " + position);
                tessere.get(position).addPawn(controller.getActualPlayer());
                final String contratto = controller.getActualBox().getName();
                new Dialog(new JFrame(), "Actual", "You are in box " + contratto);
                if (controller.getActualBox() instanceof Ownership
                        && !((Ownership) controller.getActualBox()).getOwner().equals(controller.getActualPlayer())
                        && !((Ownership) controller.getActualBox()).getOwner().equals(controller.getBank())) {
                    new Dialog(new JFrame(), "Pay",
                            "This Box is owned by Player "
                                    + ((Player) ((Ownership) controller.getActualBox()).getOwner()).getName()
                                    + " so you have to pay to him it's income value");
                }
                buttonList.forEach(b -> b.setEnabled(false));
                final List<Actions> l = StartPlay.getInPlay().getButtons();
                System.out.println(l);
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        if (bu.getText().equals(but.getText())) {
                            but.setEnabled(true);
                            if (but.getText().equals(Actions.BUY.getText())) {
                                String nome = controller.getActualPlayer().getName();
                                int cost = ((Ownership) controller.getActualBox()).getContract().getCost();
                                new Dialog(new JFrame(), "", "Il contratto " + contratto + " ha un costo di " + cost);
                            }
                        }
                    });
                });
            }
        });

        END_OF_TURN.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateLabelTurn();
                updateInfoPlayer();
                controller.endTurn();
                new Dialog(new JFrame(), "Next Player", "Next Player is: " + controller.getActualPlayer().getName());
                updateLabelTurn();

                buttonList.forEach(b -> b.setEnabled(false));
                List<Actions> l = StartPlay.getInPlay().getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        // System.out.println("bu.getText: " + bu.getText());
                        // System.out.println("but.getName: " + but.getText());
                        if (bu.getText().equals(but.getText())) {
                            // System.out.println("*****IF******: " +
                            // but.getText());
                            but.setEnabled(true);
                        }
                    });
                });
            }
        });

        buy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buyOwnership();

                String nome = controller.getActualPlayer().getName();
                String contratto = controller.getActualBox().getName();
                int pos = controller.getActualBox().getID();
                int cost = ((Ownership) controller.getActualBox()).getContract().getCost();
                new Dialog(new JFrame(), "Buy",
                        "" + nome + " hai comprato " + contratto + " in posizione " + pos + " al costo di: " + cost);
                updateInfoPlayer();
                buttonList.forEach(b -> b.setEnabled(false));
                List<Actions> l = StartPlay.getInPlay().getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        if (bu.getText().equals(but.getText())) {
                            but.setEnabled(true);
                        }
                    });
                });
            }
        });

        sell.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.sellOwnership();

                String nome = controller.getActualPlayer().getName();
                String contratto = controller.getActualBox().getName();
                int pos = controller.getActualBox().getID();
                int cost = ((Ownership) controller.getActualBox()).getContract().getCost();
                new Dialog(new JFrame(), "Sell",
                        "" + nome + " hai venduto " + contratto + " in posizione " + pos + " al costo di: " + cost);
                updateInfoPlayer();
                buttonList.forEach(b -> b.setEnabled(false));
                List<Actions> l = StartPlay.getInPlay().getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {

                        if (bu.getText().equals(but.getText())) {

                            but.setEnabled(true);
                        }
                    });
                });
            }
        });

        build.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.build();
                new Dialog(new JFrame(), "build", "Prova build");
                List<Building> building = ((LandGroup) ((Land) controller.getActualBox()).getGroup()).getBuildings();
                String s;
                if (building.get(building.size() - 1) instanceof Home) {
                    s = "a Home";
                } else {
                    s = "an Hotel";
                }

                new Dialog(new JFrame(), "Build", "You bilt: " + s);

                HashMap<Integer, IBoxGraphic> tessere = tabellone.getCardsGraphic();
                ((LandGraphic) tessere.get(position)).addHouse(controller.getActualPlayer());

                buttonList.forEach(b -> b.setEnabled(false));
                List<Actions> l = StartPlay.getInPlay().getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        // System.out.println("bu.getText: " + bu.getText());
                        // System.out.println("but.getName: " + but.getText());
                        if (bu.getText().equals(but.getText())) {
                            // System.out.println("*****IF******: " +
                            // but.getText());
                            but.setEnabled(true);
                        }
                    });
                });
            }
        });

        sellBuilding.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.sellBuilding();

                HashMap<Integer, IBoxGraphic> tessere = tabellone.getCardsGraphic();
                ((LandGraphic) tessere.get(position)).removeHouse(controller.getActualPlayer());

                new Dialog(new JFrame(), "Build", "You sell biuld: ");

                buttonList.forEach(b -> b.setEnabled(false));
                List<Actions> l = StartPlay.getInPlay().getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        // System.out.println("bu.getText: " + bu.getText());
                        // System.out.println("but.getName: " + but.getText());
                        if (bu.getText().equals(but.getText())) {
                            // System.out.println("*****IF******: " +
                            // but.getText());
                            but.setEnabled(true);
                        }
                    });
                });
            }
        });

        mortgage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.mortgageOwnership();

                buttonList.forEach(b -> b.setEnabled(false));
                List<Actions> l = StartPlay.getInPlay().getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        // System.out.println("bu.getText: " + bu.getText());
                        // System.out.println("but.getName: " + but.getText());
                        if (bu.getText().equals(but.getText())) {
                            // System.out.println("*****IF******: " +
                            // but.getText());
                            but.setEnabled(true);
                        }
                    });
                });
            }
        });

        revoke.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.revokeMortgageOwnership();

                buttonList.forEach(b -> b.setEnabled(false));
                List<Actions> l = StartPlay.getInPlay().getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        // System.out.println("bu.getText: " + bu.getText());
                        // System.out.println("but.getName: " + but.getText());
                        if (bu.getText().equals(but.getText())) {
                            // System.out.println("*****IF******: " +
                            // but.getText());
                            but.setEnabled(true);
                        }
                    });
                });
            }
        });

        endGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.endGame();

                buttonList.forEach(b -> b.setEnabled(false));
                List<Actions> l = StartPlay.getInPlay().getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        // System.out.println("bu.getText: " + bu.getText());
                        // System.out.println("but.getName: " + but.getText());
                        if (bu.getText().equals(but.getText())) {
                            // System.out.println("*****IF******: " +
                            // but.getText());
                            but.setEnabled(true);
                        }
                    });
                });
            }
        });

        Dimension dimSouth = new Dimension(0, 50);

        // Center
        JPanel centerP = new JPanel();
        centerP.add(tabellone.initialize(), BorderLayout.CENTER);

        // East
        eastP = new East(this.controller);
        frame.getContentPane().add(eastP, BorderLayout.EAST);
        frame.getMainPanel().add(centerP, BorderLayout.CENTER);
        frame.getMainPanel().add(southP, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

    public Controller getController() {
        return this.controller;

    }

    public void updateLabelTurn() {
        // eastP.getMap().
        //
        // PlayerGraphic.getMapPlayers().get(controller.getActualPlayer().getName()).updateLabel();

    }

    public void updateInfoPlayer() {
        System.out.println("updateInfoPlayer(): ");
        for (int i = 0; i < eastP.getMap().size(); i++) {
            eastP.getMap().entrySet().forEach(p -> p.getValue().setLAbelContract());
        }
    }

    private boolean isPassedFromStartBox() {
        return controller.getActualPlayer().getPawn().getPreviousPos() >= FIRST_USEFUL_POSITION
                && controller.getActualPlayer().getPawn().getActualPos() <= LAST_USEFUL_POSITION;
    }

    private int passFromStart() {
        int prePos = controller.getActualPlayer().getPawn().getPreviousPos();
        int actPos = controller.getActualPlayer().getPawn().getActualPos();

        return (39 - prePos) + (actPos + 1);
    }

    public void computerTurn(Player p) {

        tessere.get(p.getPawn().getPreviousPos()).removePawn(p);
//        int prePos = p.getPawn().getPreviousPos();
        position = p.getPawn().getActualPos();
        tessere.get(position).addPawn(p);

    }

}
