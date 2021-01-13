package bookmenu;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CartMenu extends JPanel{
  
  public int id;
  public JPanel topPanel;
  public JPanel list;
  private Dimension pSize = new Dimension(100, 100);
  public JScrollPane scrollablePanel;
  JLabel kosztText;
  JTextField Adres;
  Button orderButton;
  JLabel returnValue;

  public CartMenu() {
    initBookPanel();
  }

  private void initBookPanel() {
    this.setLayout(new BorderLayout());
    //Create book panel
    topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    topPanel.setVisible(true);
    
    topPanel.setBackground(new Color(159,155,119));
    topPanel.setPreferredSize(pSize);
    topPanel.setMinimumSize(pSize);
    this.add(topPanel, BorderLayout.PAGE_START);
    //Zamawianie
    orderButton = new Button("Zamów");
    orderButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          System.out.println("Order button has been clicked");
          //Zamow i jesli udane wyczysc koszyk
          returnValue.setText("Niewystarczaja ilosc srodkow");
      }
    });
    topPanel.add(orderButton, BorderLayout.EAST);
    //Pole na wpisanie adresu
    Adres = new JTextField("Tu wpisz adres");
    topPanel.add(Adres,BorderLayout.CENTER);
    //Wyswietlanie stanu konta
    kosztText = new JLabel("Calkowity koszt: INFINITY");
    topPanel.add(kosztText,BorderLayout.NORTH);
    //Wyswietlenie wiadomosci zwrotnej
    returnValue = new JLabel("Wiadomosc zwrotna: ");
    topPanel.add(returnValue,BorderLayout.SOUTH);
    //Panel na zamowione ksiazki
    JPanel pom = new JPanel();
    pom.setPreferredSize(pSize);
    pom.setLayout(new BorderLayout());
    
    //Create list of object(ksiazki)
    list = new JPanel();
    list.setVisible(true);
    list.setBackground(Color.white);
    list.setLayout(new BoxLayout(list, BoxLayout.PAGE_AXIS));

    //Create ScrollPane
    scrollablePanel = new JScrollPane(list);
    scrollablePanel.getVerticalScrollBar().setBlockIncrement(1);
    scrollablePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollablePanel.setPreferredSize(new Dimension(400,300));
    this.add(scrollablePanel, BorderLayout.CENTER);
    
    generateList(); // do usunięcia
  }

  private void removeAllListedItems() {
    list.removeAll();
    scrollablePanel.revalidate();
    scrollablePanel.repaint();
  }
  private void generateCartList() {
    int koszt = 0;
    /*
     * Bierze id książek w jakiejś liście i bierze info od bazy danych
     * aktualizuje cenę 
     */
    kosztText.setText("Całkowity koszt: " + koszt);
  }
  /*
   *  Generacja random rzeczy żeby coś było widac
   */
  private void generateList() {
    for(int i=0;i<5;i++) {
        BookPanel test = new BookPanel(i,true);
        test.setData("Adam madA", "Mgła we mgle", "średnia ocen 5/5");
        
        test.setPreferredSize(pSize);
        test.setMinimumSize(pSize);
        list.add(test);
        //test.setData("TITLE_" + i,"AUTHOR_"+i,"10/"+i);
    }
  }

}
