package isengard.io.buttons;

import isengard.db.Adapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchButton extends JButton {

    public SearchButton() {
        this.setText("Search");

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //open search menu
                System.out.println("Search button has been clicked");
            }
        });
    }

}
