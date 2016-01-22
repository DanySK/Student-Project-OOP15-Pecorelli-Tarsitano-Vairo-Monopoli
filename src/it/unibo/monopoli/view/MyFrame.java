package it.unibo.monopoli.view;

import java.awt.*;
import javax.swing.*;

public class MyFrame extends JFrame {

	public MyFrame(String title, LayoutManager lm) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 700);
		this.getContentPane().add(new JPanel(lm));

	}

	public JPanel getMainPanel() {

		return (JPanel) this.getContentPane().getComponent(0);
	}

}
