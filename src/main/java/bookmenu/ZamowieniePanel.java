package bookmenu;

import java.awt.Button;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ZamowieniePanel extends JPanel{
  int id;
  public ZamowieniePanel(int id,String adres,String status) {
    this.id = id;
    this.setLayout(new GridLayout(3,1));
    JLabel first = new JLabel("Zamowienie nr. " + id);
    JLabel second = new JLabel("Na adres: " + adres);
    JLabel third = new JLabel("Status zamowienia: " + status);
    
    first.setFont(new Font("Verdana", Font.PLAIN, 25));
    second.setFont(new Font("Verdana", Font.PLAIN, 25));
    third.setFont(new Font("Verdana", Font.PLAIN, 25));
    
    this.add(first);
    this.add(second);
    this.add(third);
  }
  
}
