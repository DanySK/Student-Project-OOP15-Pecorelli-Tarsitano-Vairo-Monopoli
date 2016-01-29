package it.unibo.monopoli.view;

import java.awt.*;
import javax.swing.*;

public class MyFrame extends JFrame {
	private JPanel content;

	public MyFrame(String title, LayoutManager lm, Dimension dim) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content = new JPanel();
		content.setLayout(lm);
		this.getContentPane().add(content);
		this.setBackground(Color.RED);

		if (dim == null)
			pack();
		else
			this.setSize(dim);
	}

	public JPanel getMainPanel() {
		return content;
	}

}
