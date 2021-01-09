package isengard.io;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private final int windowSide;


    public Panel(int windowSide) {
        this.windowSide = windowSide;

        this.setBackground(new Color(194, 187, 169));
        this.setLayout(null);

        MouseHandler handler = new MouseHandler(windowSide);
        this.addMouseListener(handler);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

}
