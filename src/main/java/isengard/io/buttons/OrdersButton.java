package isengard.io.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import bookmenu.OrderMenu;
import isengard.io.NewWindow;

public class OrdersButton extends JButton {

  public OrdersButton() {
      this.setText("Orders");

      this.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              new NewWindow(new OrderMenu(), "OrderMenu");
              System.out.println("Order button has been clicked");
          }
      });
  }

}