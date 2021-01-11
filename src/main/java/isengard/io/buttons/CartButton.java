package isengard.io.buttons;

import isengard.db.Adapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartButton extends JButton {

    public CartButton() {
        this.setText("Cart");

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //open cart widow
                System.out.println("Cart button has been clicked");
            }
        });
    }

}
