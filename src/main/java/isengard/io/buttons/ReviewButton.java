package isengard.io.buttons;

import bookmenu.BookDetailsMenu;
import bookmenu.ReviewPanel;
import isengard.io.NewWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReviewButton extends JButton {

    public ReviewButton() {
        this.setText("Review");

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new NewWindow(new ReviewPanel(new BookDetailsMenu(), ), "Review");
                System.out.println("Review button has been clicked");
            }
        });
    }
}
