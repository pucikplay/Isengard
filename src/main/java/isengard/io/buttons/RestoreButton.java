package isengard.io.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import isengard.db.Adapter;

public class RestoreButton extends JButton {

  public RestoreButton() {
      this.setText("Restore");

      this.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              Adapter.restore();
              System.out.println("Backup button has been clicked");
          }
      });
  }

}