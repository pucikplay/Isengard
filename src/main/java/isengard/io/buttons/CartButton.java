package isengard.io.buttons;

import bookmenu.CartMenu;
import isengard.db.Adapter;
import isengard.io.NewWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartButton extends JButton {

    public CartButton() {
        this.setText("Cart");

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewWindow(new CartMenu(), "Cart");
                System.out.println("Cart button has been clicked");
            }
        });
    }

}
