package bookmenu;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import isengard.db.Adapter;

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
  JLabel stanKonta;
  private float koszt;
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
          orderButtonPressed();
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
    //Wyswietlenie stanu konta
    stanKonta = new JLabel("Stan konta: " + getStanKonta());
    topPanel.add(stanKonta,BorderLayout.WEST);
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
    generateCartList();
  }

  private Float getStanKonta() {
    ResultSet rs;
    try {
      rs = Adapter.execute(
          "SELECT id,stanKonta FROM uzytkownicy" +
          " WHERE " +
          " id = " + 1 + " LIMIT 1"); ///////////////////////////////////////////////////////////////TU ZMIENIC ID
      rs.next();
      Float stanKonta = rs.getFloat("stanKonta");
      return stanKonta;
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return 0f;
    }
  }

  private void orderButtonPressed() {
    // TODO Auto-generated method stub
    Float aktStanKonta = getStanKonta();
    if(aktStanKonta < koszt) {
      System.out.println(stanKonta + "    " + koszt);
      returnValue.setText("Niewystarczająca ilosc funduszy");
      return;
    }
    //CALL CreateNewOrder(2,"Ulica sezamkowa");
    try {
      Adapter.connection.setAutoCommit(false);
      ResultSet rs = Adapter.execute("CALL CreateNewOrder("+1+",'"+Adres.getText()+"')"); ///////////////////////////////////////////////////////////////TU ZMIENIC ID
//      ResultSet rs = Adapter.execute("SELECT AUTO_INCREMENT AS id" +
//      " FROM information_schema.TABLES" +
//      " WHERE TABLE_SCHEMA = 'isengardbookdb'" +
//      " AND TABLE_NAME = 'zamowienia'");
      rs.next();
      int orderId = rs.getInt("id");
//      
//      Adapter.execute("INSERT INTO zamowienia (id_uzytkownik,stan_zamowienia,adres,koszt) VALUES (" + 1 + ",'Zamowione','"+Adres.getText()+"',0)");
      
      for(int i=0;i<Cart.listOfItems.size();i++) {
        ResultSet rs2 = Adapter.execute("CALL AddToOrder("+1+","+orderId+","+Cart.listOfItems.get(i)+")"); //tu tez
        rs2.next();
        int result = rs2.getInt("result");
        if(result==0) {
          returnValue.setText("Jedna z ksiazek jest aktualnie nie dostepna w sklepie");
          System.out.println("tutaj rollback");
          Adapter.connection.rollback();
          return;
        }
      }
      //Tu juz sie wszystko udalo
      removeAllListedItems();
      stanKonta.setText("Stan konta: " + Float.toString(getStanKonta()));
      returnValue.setText("Zamowiono pomyslnie");
      Adapter.connection.commit();
      Adapter.connection.setAutoCommit(true);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void removeAllListedItems() {
    list.removeAll();
    scrollablePanel.revalidate();
    scrollablePanel.repaint();
  }
  public void generateCartList() {
    removeAllListedItems();
    koszt = 0;
    /*
     * Bierze id książek w jakiejś liście i bierze info od bazy danych
     * aktualizuje cenę 
     */
    try {
      for(int i=0;i<Cart.listOfItems.size();i++) {
        BookPanel test = new BookPanel(Cart.listOfItems.get(i),false);
        ResultSet rs = Adapter.execute(
            "SELECT ksiazki.id,tytul,cena,ilosc,imie,nazwisko,nazwa,AVG(iloscgwiazdek) AS sredniaOcena,kategorie.nazwa as kategoria FROM ksiazki" +
            " JOIN autorzy" +
            " JOIN kategorie" +
            " LEFT JOIN recenzje ON ksiazki.id=recenzje.id_ksiazka" +
            " WHERE " +
            " autorzy.id = ksiazki.autor AND" +
            " kategorie.id = ksiazki.kategoria AND" +
            " ksiazki.id =" + Cart.listOfItems.get(i) +
            " GROUP BY ksiazki.id LIMIT 1");
          rs.next();
          Float cena = rs.getFloat("cena");
          String tytul = rs.getString("tytul");
          String kategoria = rs.getString("kategoria");
          int ilosc = rs.getInt("ilosc");
          String autor = rs.getString("imie") + " " + rs.getString("nazwisko");
          Float ocena = rs.getFloat("sredniaOcena");
          test.setData(tytul, autor, ocena.toString(), kategoria, cena.toString(),Integer.toString(ilosc));
          test.setPreferredSize(pSize);
          test.setMinimumSize(pSize);
          
          //Dodanie przycisku do usunięcia z koszyka #Dobry styl programowania
          DeleteFromCartButton b = new DeleteFromCartButton("Usun z koszyka",i,this); 
          test.add(b);
          
          list.add(test);
          koszt = koszt + cena;
      }
      kosztText.setText("Całkowity koszt: "+Float.toString(koszt)+" zł");
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    kosztText.setText("Całkowity koszt: " + koszt);
  }

}
