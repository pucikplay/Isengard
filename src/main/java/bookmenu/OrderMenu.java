package bookmenu;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import isengard.db.Adapter;

public class OrderMenu extends JPanel{
  
  public int id;
  public JPanel topPanel;
  public JPanel list;
  private Dimension pSize = new Dimension(100, 100);
  public JScrollPane scrollablePanel;
  public OrderMenu() {
    initOrderMenu();
  }

  private void initOrderMenu() {
    this.setLayout(new BorderLayout());
    //Create book panel
    topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    topPanel.setVisible(true);
    
    topPanel.setBackground(new Color(159,155,119));
    topPanel.setPreferredSize(pSize);
    topPanel.setMinimumSize(pSize);
    this.add(topPanel, BorderLayout.PAGE_START);
    Label title = new Label("Zamowienia");
    title.setFont(new Font("Verdana", Font.PLAIN, 50));
    topPanel.add(title, BorderLayout.CENTER);
    
    //Panel na zamowienia
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

  private void removeAllListedItems() {
    list.removeAll();
    scrollablePanel.revalidate();
    scrollablePanel.repaint();
  }
  public void generateCartList() {
    int userid = Adapter.getId();
    int previousid = -1;
    try {
      ResultSet rs = Adapter.execute("SELECT * FROM zamowienia"
          + " LEFT JOIN produktyzamowien ON zamowienia.id=produktyzamowien.id_zamowienie"
          + " WHERE id_uzytkownik = " + userid
          + " ORDER BY zamowienia.id");
      while(rs.next()) {
        int id = rs.getInt("id");
        if(id!=previousid) {
          previousid=id;
          System.out.println(id);
          String status = rs.getString("stan_zamowienia");
          String adres = rs.getString("adres");
          ZamowieniePanel panel = new ZamowieniePanel(id,adres,status);
          list.add(panel);
        }
        int bookid = rs.getInt("id_ksiazka");
        //get book info
        BookPanel bp = new BookPanel(bookid,false);
        ResultSet rs2 = Adapter.execute(
          "SELECT ksiazki.id,tytul,cena,ilosc,imie,nazwisko,nazwa,AVG(iloscgwiazdek) AS sredniaOcena,kategorie.nazwa as kategoria FROM ksiazki" +
          " JOIN autorzy" +
          " JOIN kategorie" +
          " LEFT JOIN recenzje ON ksiazki.id=recenzje.id_ksiazka" +
          " WHERE " +
          " autorzy.id = ksiazki.autor AND" +
          " kategorie.id = ksiazki.kategoria AND" +
          " ksiazki.id =" + bookid +
          " GROUP BY ksiazki.id LIMIT 1");
          // :)
        rs2.next();
        Float cena = rs2.getFloat("cena");
        String tytul = rs2.getString("tytul");
        String kategoria = rs2.getString("kategoria");
        String autor = rs2.getString("imie") + " " + rs2.getString("nazwisko");
        Float ocena = rs2.getFloat("sredniaOcena");
        int ilosc = rs2.getInt("ilosc");
        bp.setData(tytul, autor, ocena.toString(), kategoria, cena.toString(), Integer.toString(ilosc));
        bp.hideAvailableBooks();
        list.add(bp);
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
