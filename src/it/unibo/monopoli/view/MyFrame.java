package it.unibo.monopoli.view;

import java.awt.*;
import javax.swing.*;

public class MyFrame extends JFrame {

	public MyFrame(String title, LayoutManager lm, Dimension dim) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(dim);
		this.getContentPane().add(new JPanel(lm));
		this.setBackground(Color.RED);

	}

	public JPanel getMainPanel() {

		return (JPanel) this.getContentPane().getComponent(0);
	}

}
