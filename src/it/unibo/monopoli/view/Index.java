package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import it.unibo.monopoli.controller.ControllerImpl;
import it.unibo.monopoli.model.mainunits.Player;

public class Index {
	
	

    public Index() {
        final MyFrame frame = new MyFrame("Monopoli", new BorderLayout(), new Dimension(1200, 700));
        List<Player> player = new ArrayList();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        // JPanelMain South
        final JPanel southP = new JPanel();
        JButton ok = new JButton("Ok");
        JButton quit = new JButton("Quit");
        JButton buildHouse = new JButton("Add House");
        southP.add(ok);
        southP.add(quit);
        southP.add(buildHouse);
        Dimension dimSouth = new Dimension(0, 50);
        southP.setPreferredSize(dimSouth);
        // Center
        JPanel centerP = new JPanel();
        JPanel tabellone = new ProvaTabellone(11, 11, new ControllerImpl(EVersion.CLASSIC,player)).initialize();
        centerP.add(tabellone);

        // East
        frame.getContentPane().add(new East(), BorderLayout.EAST);
        frame.getMainPanel().add(centerP, BorderLayout.CENTER);
        frame.getMainPanel().add(southP, BorderLayout.SOUTH);
        
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Index();
//		new Index();
    }

}
