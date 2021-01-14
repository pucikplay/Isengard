package bookmenu;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteFromCartButton extends Button {
  public int index;
  private CartMenu menu;
  
  public DeleteFromCartButton(String string,int a,CartMenu menuToRefresh) {
    super(string);
    this.index=a;
    menu = menuToRefresh;
    this.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          System.out.println("Pressed delete button");
          Cart.removeItem(index);
          menu.generateCartList();
      }
    });
  }

}
