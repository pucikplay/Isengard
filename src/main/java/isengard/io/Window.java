package isengard.io;


import isengard.db.Adapter;
import isengard.io.buttons.BackupButton;
import isengard.io.buttons.CartButton;
import isengard.io.buttons.OrdersButton;
import isengard.io.buttons.ReviewButton;
import isengard.io.buttons.SearchButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Window extends JFrame{

    private JPanel panel;

    private Dimension pSize = new Dimension(400, 100);

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
        ReviewButton reviewButton = new ReviewButton();
        OrdersButton orderButton = new OrdersButton();
        panel.add(cartButton);
        panel.add(searchButton);
        panel.add(orderButton);
        //panel.add(reviewButton);

        if (role == 2) {
            BackupButton backupButton = new BackupButton();
            final TextField console = new TextField();
            console.setColumns(25);
            JButton executeButton = new JButton("Execute");
            executeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Adapter.execute(console.getText());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
            panel.add(backupButton);
            panel.add(console);
            panel.add(executeButton);
        }
    }



}
