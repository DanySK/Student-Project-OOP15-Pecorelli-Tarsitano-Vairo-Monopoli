package it.unibo.monopoli.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;

import it.unibo.monopoli.controller.Controller;
import it.unibo.monopoli.model.mainunits.ClassicPawn;
import it.unibo.monopoli.view.C;
import it.unibo.monopoli.view.Dialog;
import it.unibo.monopoli.view.EVersion;
import it.unibo.monopoli.view.Go;
import it.unibo.monopoli.view.InPlay;
import it.unibo.monopoli.view.InPlayImpl;
import it.unibo.monopoli.view.Index;
import it.unibo.monopoli.view.InizializedPlayer;

public class StartPlay implements ActionListener {
    int count = 1;

    private static final InPlay inPlay = new InPlayImpl();

    public StartPlay() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        int j = 0;
        if (VersionSelected.getSelectedItem().equals(C.NOT_SELECTABLE_OPTION)) {
            new Dialog(new JFrame(), "Error", "Error! You have not selected the version");
        } else if (Go.getNumPlayers() <= 1) {
            new Dialog(new JFrame(), "Error", "Error! The minimum number of players is two");
        } else {
            Index i = new Index();
            Controller contr = i.getController();
            Set<Entry<String, Boolean>> s = InizializedPlayer.getMap().entrySet();
            s.forEach(g -> {
                contr.addPlayer(g.getKey(), new ClassicPawn(0), g.getValue());

            });
            switch (VersionSelected.getSelectedItem()) {
            case "Classic":
                contr.initializedVersion(EVersion.CLASSIC);
                break;
            default:
                break;
            }
            contr.addView(inPlay);
            i.build();
            contr.getPlayers().forEach(p -> {
                System.out.println(p.getName());
                System.out.println("" + p.isHuman());
                System.out.println("" + p.getPawn().getID());

            });

        }
        System.out.println("Version: " + VersionSelected.getSelectedItem());

    }

    public static InPlay getInPlay() {
        return inPlay;
    }

}
