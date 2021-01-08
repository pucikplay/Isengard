package isengard;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    private Panel panel;

    private final int windowSide = 1000;

    public Window() {
        initUI();
    }


    private void initUI() {
        setTitle("Isengard client");
        setSize(new Dimension(windowSide, windowSide));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init(windowSide);
    }

    public void init(int windowSide) {

        this.panel = new Panel(windowSide);
        this.add(panel);

        initButtons();

        this.setVisible(true);

    }

    private void initButtons() {
        Button button = new Button();
        panel.add(button);
    }

}
