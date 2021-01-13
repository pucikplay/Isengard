package isengard.io.buttons;

import bookmenu.SearchMenu;
import isengard.db.Adapter;
import isengard.io.NewWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchButton extends JButton {

    public SearchButton() {
        this.setText("Search");

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewWindow(new SearchMenu(), "Search Menu");
                System.out.println("Search button has been clicked");
            }
        });
    }

}
