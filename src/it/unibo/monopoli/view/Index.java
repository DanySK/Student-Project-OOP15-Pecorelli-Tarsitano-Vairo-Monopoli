package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.controller.Actions;
import it.unibo.monopoli.controller.Controller;
import it.unibo.monopoli.controller.ControllerImpl;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.view.listener.StartPlay;

public class Index {
    private final Controller controller;
    private final List<JButton> buttonList;

    public Index() {

        this.controller = new ControllerImpl();
        this.buttonList = new LinkedList<>();

    }

    public void build() {

        final MyFrame frame = new MyFrame("Monopoli", new BorderLayout(), new Dimension(1200, 720));
        List<Player> player = new ArrayList();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        // JPanelMain South
        final JPanel southP = new JPanel();
        southP.setPreferredSize(new Dimension(frame.getWidth(), 65));
        southP.setLayout(new FlowLayout());
        JButton rollDices = new JButton(Actions.ROLL_DICES.getText());
        JButton endTurn = new JButton(Actions.END_OF_TURN.getText());
        JButton buy = new JButton(Actions.BUY.getText());
        JButton sell = new JButton(Actions.SELL.getText());
        JButton auction = new JButton(Actions.AUCTION.getText());
        JButton build = new JButton(Actions.BUILD.getText());
        JButton sellBuilding = new JButton(Actions.SELL_BUILDING.getText());
        JButton mortgage = new JButton(Actions.MORTGAGE.getText());
        JButton revoke = new JButton(Actions.REVOKE_MORTGAGE.getText());
        JButton endGame = new JButton(Actions.END_OF_THE_GAME.getText());

        southP.add(rollDices);
        southP.add(endTurn);
        southP.add(buy);
        southP.add(sell);
        southP.add(auction);
        southP.add(build);
        southP.add(sellBuilding);
        southP.add(mortgage);
        southP.add(revoke);
        southP.add(endGame);

        buttonList.add(rollDices);
        buttonList.add(endTurn);
        buttonList.add(buy);
        buttonList.add(sell);
        buttonList.add(auction);
        buttonList.add(build);
        buttonList.add(sellBuilding);
        buttonList.add(mortgage);
        buttonList.add(revoke);
        buttonList.add(endGame);

        rollDices.setEnabled(true);
        endTurn.setEnabled(false);
        buy.setEnabled(false);
        sell.setEnabled(false);
        auction.setEnabled(false);
        build.setEnabled(false);
        sellBuilding.setEnabled(false);
        mortgage.setEnabled(false);
        revoke.setEnabled(false);
        endGame.setEnabled(false);

        rollDices.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int pos = controller.toRollDices();
                System.out.println("pos: " + pos);

                buttonList.forEach(b -> b.setEnabled(false));
                List<String> l = StartPlay.getInPlay().getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        if (bu.equals(but)) {
                            but.setEnabled(true);
                        }
                    });
                });

                // TODO
                /*
                 * 1 prendere posizione attuale int oldpos =
                 * actualPlayer.getPawn().getActualPos() prendo la tessera
                 * corrispondente alla posizione e invoco
                 * 
                 * tessera.removePawn(actualPlayer) 2 prendere prossima
                 * posizione int new pos = controller.toRollDices(); prendo la
                 * nuova tessera e ivoco tesseta.addPawn(actualPlater)
                 */

                System.out.println("pos: " + pos);
                System.out.println(MonopoliIterator.getPointById(pos));
                Set<IBoxGraphic> tessere = ProvaTabellone.getCardsGraphic();
                
                if (tessere != null) {
                  int i1 = 0;
                  tessere.forEach(card -> {
                      int riga = 0, colonna = 0;
                      System.out.println("forEach: Position: " + card.getPosition());
                      System.out.println("forEach: pos: " + pos);
                      if (card.getPosition().equals(pos)) {
                          System.out.println("getPosition: " + card.getPosition());
//                         card.addPawn(p);
      
                      }
                  });
      
              }
                
                // tessera in posizione pos...
                // newCard.addPawn(p);

            }
        });

        endTurn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.endTurn();

                buttonList.forEach(b -> b.setEnabled(false));
                List<String> l = StartPlay.getInPlay().getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        if (bu.equals(but)) {
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

                buttonList.forEach(b -> b.setEnabled(false));
                List<String> l = StartPlay.getInPlay().getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        if (bu.equals(but)) {
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

                buttonList.forEach(b -> b.setEnabled(false));
                List<String> l = StartPlay.getInPlay().getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        if (bu.equals(but)) {
                            but.setEnabled(true);
                        }
                    });
                });
            }
        });

        auction.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.auction();

                buttonList.forEach(b -> b.setEnabled(false));
                List<String> l = StartPlay.getInPlay().getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        if (bu.equals(but)) {
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

                buttonList.forEach(b -> b.setEnabled(false));
                List<String> l = StartPlay.getInPlay().getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        if (bu.equals(but)) {
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

                buttonList.forEach(b -> b.setEnabled(false));
                List<String> l = StartPlay.getInPlay().getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        if (bu.equals(but)) {
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
                List<String> l = StartPlay.getInPlay().getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        if (bu.equals(but)) {
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
                List<String> l = StartPlay.getInPlay().getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        if (bu.equals(but)) {
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
                List<String> l = StartPlay.getInPlay().getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        if (bu.equals(but)) {
                            but.setEnabled(true);
                        }
                    });
                });
            }
        });

        Dimension dimSouth = new Dimension(0, 50);
        // southP.setPreferredSize(dimSouth);
        // Center
        JPanel centerP = new JPanel();
        JPanel tabellone = new ProvaTabellone(11, 11, this.controller).initialize();
        centerP.add(tabellone, BorderLayout.CENTER);

        // East
        frame.getContentPane().add(new East(this.controller), BorderLayout.EAST);
        frame.getMainPanel().add(centerP, BorderLayout.CENTER);
        frame.getMainPanel().add(southP, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

    public Controller getController() {
        return this.controller;

    }

}
