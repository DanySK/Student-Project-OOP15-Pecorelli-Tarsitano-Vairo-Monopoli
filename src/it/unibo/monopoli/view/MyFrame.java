package it.unibo.monopoli.view;

import java.awt.*;
import javax.swing.*;

/**
 * 
 * class used to create the frames needed for the game.
 *
 */
public class MyFrame extends JFrame {
    private final JPanel content;

    /**
     * builder.
     * 
     * @param title
     * @param lm
     * @param dim
     */
    public MyFrame(final String title, final LayoutManager lm, final Dimension dim) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = new JPanel();
        content.setLayout(lm);
        this.getContentPane().add(content);
        this.setBackground(Color.RED);

        if (dim == null) {
            pack();
        } else {
            this.setSize(dim);
        }
    }

    public JPanel getMainPanel() {
        return content;
    }

}
