package bookmenu;

import isengard.db.Adapter;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class BookDetailsMenu extends JPanel{
  
  public int id;
  public BookPanel bookPanel;
  public JPanel list;
  private Dimension pSize = new Dimension(100, 100);
  public JScrollPane scrollablePanel;
  JTextField reviewText;
  JTextField reviewScore;
  Label reviewReturn;
  Button publishReviewButton;
  Button addToCart;
  /*
   * Przyjmuje id książki, i sam wyciąga z baz danych potrzebne informacje tj. nazwa i recenzje
   */
  public BookDetailsMenu(int id) {
    this.id = id;
    initBookPanel();
  }

  private void initBookPanel() {
    this.setLayout(new BorderLayout());
    //Create book panel
    bookPanel = new BookPanel(id,false);
    bookPanel.setVisible(true);
    
    setBookInfo(id);
    
    bookPanel.setBackground(new Color(159,155,119));
    bookPanel.setPreferredSize(pSize);
    bookPanel.setMinimumSize(pSize);
    this.add(bookPanel, BorderLayout.PAGE_START);
    //Do dodania do koszyka
    //Trzeba rozszerzyc
    addToCart = new Button("Dodaj do koszyka");
    addToCart.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          System.out.println("Add to cart button has been clicked");
          //Dodaj do koszyka id ksiazki
      }
    });
    bookPanel.add(addToCart, BorderLayout.EAST);
    
    //Panel do napisania recenzji
    JPanel pom = new JPanel();
    pom.setPreferredSize(pSize);
    pom.setLayout(new BorderLayout());
    publishReviewButton = new Button("Opublikuj recenzję");
    
    publishReviewButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          //System.out.println("Publish review button has been clicked");
          //Dodaj do bazy danych recenzje (jesli spelnia wymagania dot ilosci znakow)
          //To do
          //Odswiez recenzje
          publishReview();
          refreshReviews();
      }

      private void publishReview() {
        try {
          int iduzytkownika = 1; //TRZEBA ZMIENIC
          System.out.println("INSERT INTO recenzje VALUES ("+Integer.toString(id)+","+Integer.toString(iduzytkownika)+","+Integer.parseInt(reviewScore.getText())+",'"+reviewText.getText()+"')");
          Adapter.execute("INSERT INTO recenzje VALUES ("+Integer.toString(id)+","+Integer.toString(iduzytkownika)+","+Integer.parseInt(reviewScore.getText())+",'"+reviewText.getText()+"')");
          reviewReturn.setText("Pomyslnie dodano recenzje");
        } catch (Exception e) {
          reviewReturn.setText("Wprowadz poprawne dane");
          e.printStackTrace();
        }
        
      }
    });
    
    pom.add(publishReviewButton,BorderLayout.EAST);
    reviewText = new JTextField("Tu wpisz swoją recenzję książki");
    pom.add(reviewText,BorderLayout.CENTER);
    reviewReturn=new Label("Odpowiedz w spr. inserta");
    pom.add(reviewReturn,BorderLayout.SOUTH);
    reviewScore=new JTextField("1-5");
    pom.add(reviewScore,BorderLayout.WEST);

    this.add(pom, BorderLayout.PAGE_END);
    //Create list of object(recenzja)
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
    
    addReviews(id);
  }
  /*
   * Zapytanie baze danych o informacje o książce
   */
  private void setBookInfo(int id) {
      try {
        ResultSet rs = Adapter.execute(
        "SELECT ksiazki.id,tytul,cena,ilosc,imie,nazwisko,nazwa,AVG(iloscgwiazdek) AS sredniaOcena,kategorie.nazwa as kategoria FROM ksiazki" +
        " JOIN autorzy" +
        " JOIN kategorie" +
        " LEFT JOIN recenzje ON ksiazki.id=recenzje.id_ksiazka" +
        " WHERE " +
        " autorzy.id = ksiazki.autor AND" +
        " kategorie.id = ksiazki.kategoria AND" +
        " ksiazki.id =" + id +
        " GROUP BY ksiazki.id LIMIT 1");
        // :)
        rs.next();
        Float cena = rs.getFloat("cena");
        String tytul = rs.getString("tytul");
        String kategoria = rs.getString("kategoria");
        String autor = rs.getString("imie") + " " + rs.getString("nazwisko");
        Float ocena = rs.getFloat("sredniaOcena");
        bookPanel.setData(tytul, autor, ocena.toString(), kategoria, cena.toString());
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
  }

  /*
   * Zapytanie do bazy danych o recenzje książki, iteracja i dodanie do listy
   * Wzor w addReviews lub generateList
   */
  private void addReviews(int id) {
    
    try {
      ResultSet rs = Adapter.execute(
      "SELECT uzytkownicy.nazwa AS nazwa,iloscGwiazdek,tekstowaRecenzja FROM ksiazki" +
      " JOIN uzytkownicy" +
      " LEFT JOIN recenzje ON ksiazki.id=recenzje.id_ksiazka" +
      " WHERE " +
      " ksiazki.id=" + id +
      " AND id_uzytkownik=uzytkownicy.id"
      );
      // :)
      while(rs.next()) {
        String name = rs.getString("nazwa");
        int gwiazdki = rs.getInt("iloscGwiazdek");
        String tekst = rs.getString("tekstowaRecenzja");
        ReviewPanel rp = new ReviewPanel(this,Adapter.getRole()>0);
        rp.setData(name, tekst, Integer.toString(gwiazdki));
        list.add(rp);
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    scrollablePanel.revalidate();
    scrollablePanel.repaint();
  }
  
  public void refreshReviews() {
    removeAllListedItems();
    addReviews(id);
  }
  /*
   * Potrzebne do aktualizacji po dodaniu swojej recenzji
   */
  private void removeAllListedItems() {
    list.removeAll();
    scrollablePanel.revalidate();
    scrollablePanel.repaint();
  }
}
