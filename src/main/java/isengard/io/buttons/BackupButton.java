package isengard.io.buttons;

import isengard.db.Adapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackupButton extends JButton {

    public BackupButton() {
        this.setText("Backup");

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Adapter.backup();
                System.out.println("Backup button has been clicked");
            }
        });
    }

}
