package isengard.io;

import isengard.db.Adapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackupButton extends JButton {

    public BackupButton() {
        initButton();
    }

    private void initButton() {
        this.setText("Backup");
        this.setBounds(655, 850, 150, 60);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Adapter.backup();
                System.out.println("Backup button has been clicked");
            }
        });
    }
}
