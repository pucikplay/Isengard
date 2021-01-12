package isengard.io.buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReviewButton extends JButton {

    public ReviewButton() {
        this.setText("Add review");

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //open review menu
                System.out.println("Review button has been clicked");
            }
        });
    }
}
