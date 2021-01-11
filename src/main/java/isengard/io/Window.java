package isengard.io;


import isengard.db.Adapter;
import isengard.io.buttons.BackupButton;
import isengard.io.buttons.CartButton;
import isengard.io.buttons.SearchButton;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    private JPanel panel;

    private Dimension pSize = new Dimension(300, 150);

    private final int role = -1;

    public Window() {
        initUI();
    }


    private void initUI() {
        setTitle("Isengard client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init();
    }

    public void init() {

        this.panel = new JPanel();
        panel.setLayout(new FlowLayout());
        this.setPreferredSize(pSize);
        this.setMinimumSize(pSize);
        this.add(panel);

        initButtons(Adapter.getRole());

        this.setVisible(true);

    }

    private void initButtons(int role) {

        CartButton cartButton = new CartButton();
        SearchButton searchButton = new SearchButton();
        panel.add(cartButton);
        panel.add(searchButton);

        if (role == 2) {
            BackupButton backupButton = new BackupButton();
            panel.add(backupButton);
        }
    }



}
