package isengard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton {

    public Button() {
        initButton();
    }

    private void initButton() {
        this.setText("Backup");
        this.setBounds(655, 850, 150, 60);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Backup button has been clicked");
            }
        });
    }
}
