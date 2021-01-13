package isengard.io;

import javax.swing.*;
import java.awt.*;

public class NewWindow extends JFrame{

    private JPanel panel;

    public NewWindow(JPanel panel, String title) {
        this.panel = panel;
        setTitle(title);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1000,1000));
        this.add(panel);
        this.setVisible(true);
    }
}
