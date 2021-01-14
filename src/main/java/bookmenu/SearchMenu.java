package bookmenu;

import isengard.db.Adapter;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class SearchMenu extends JPanel{
  
  public SearchPanel searchPanel;
  public JPanel list;
  private Dimension pSize = new Dimension(100, 100);
  public JScrollPane scrollablePanel;
  
  public SearchMenu() {
    initBookPanel();
  }

  private void initBookPanel() {
    this.setLayout(new BorderLayout());
    //Create search panel
    searchPanel = new SearchPanel();
    /*
     * Przycisk dodaje poza searchPanelem for some reason idk now
     */
    Button button = new Button("SZUKAJ");
    searchPanel.add(button);
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          System.out.println("Search button has been clicked");
          generateList(false);
      }
    });
    this.add(searchPanel, BorderLayout.PAGE_START);
    
    //Create list of object
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
    
    generateList(true);
  }
  /*
   * Wewnetrzna klasa panela wyszukiwującego
   */
  class SearchPanel extends JPanel{
    
    private JTextField titleField;
    private JTextField authorField;
    private JTextField categoryField;
    private JComboBox<String> ratingSort;
    private JComboBox<String> priceSort;
    
    public SearchPanel() {
      
      this.setVisible(true);
      this.setBackground(new Color(159,155,119));
      this.setLayout(new FlowLayout());
      this.setPreferredSize(pSize);
      this.setMinimumSize(pSize);
      
      this.setLayout(new GridLayout(2,5));
      
      this.init();
    }

    private void init() {

      String[] sortOptions = {"brak","Rosnąco","Malejąco"};
      //Ocena
      this.add(new Label("Sortuj ocenę"));
      ratingSort = new JComboBox<String>(sortOptions);
      this.add(ratingSort);
      //Cena
      this.add(new Label("Sortuj cenę"));
      priceSort = new JComboBox<String>(sortOptions);
      this.add(priceSort);
      
      this.add(new Label("Tytul"));
      titleField = new JTextField();
      this.add(titleField);
      this.add(new Label("Autor"));
      authorField = new JTextField();
      this.add(authorField);
      this.add(new Label("Kategoria"));
      categoryField = new JTextField();
      this.add(categoryField);
      
      titleField.setPreferredSize(new Dimension(200,25));
      authorField.setPreferredSize(new Dimension(200,25));
      categoryField.setPreferredSize(new Dimension(200,25));
    }
    
    public String getNameSearch() {
      return titleField.getText();
    }
    public String getAuthorSearch() {
      return authorField.getText();
    }
    public String getCategorySearch() {
      return categoryField.getText();
    }
    public int getPriceSort() {
      if(priceSort.getSelectedItem().toString()=="Rosnąco") {
        return 1;
      }
      if(priceSort.getSelectedItem().toString()=="Malejąco") {
        return -1;
      }
      return 0;
    }
    public int getRatingSort() {
      if(ratingSort.getSelectedItem().toString()=="Rosnąco") {
        return 1;
      }
      if(ratingSort.getSelectedItem().toString()=="Malejąco") {
        return -1;
      }
      return 0;
    }
  }
  
  public void removeAllListedItems() {
    list.removeAll();
    /*
     * Nwm czy potrzebne ale nie zaszkodzi
     */
    scrollablePanel.revalidate();
    scrollablePanel.repaint();
  }

  /*
   *  Generacja random rzeczy żeby coś było widac
   */
  private void generateList(boolean justOpened) {
    removeAllListedItems();
    try {
      ResultSet rs;
      if(justOpened) {
        rs = Adapter.execute("CALL GetSearchResults('','','',0,0)");
      }else {
        //System.out.println("CALL GetSearchResults('" + searchPanel.getNameSearch() + "','" + searchPanel.getAuthorSearch() +  "','" + searchPanel.getCategorySearch() +"',0,0)");
        rs = Adapter.execute("CALL GetSearchResults('" + searchPanel.getNameSearch() + "','" + searchPanel.getAuthorSearch() +  "','" + searchPanel.getCategorySearch() +"',"+searchPanel.getPriceSort()+","+searchPanel.getRatingSort()+")");
      }
      while(rs.next()) {
        int id = rs.getInt("id");
        Float cena = rs.getFloat("cena");
        String tytul = rs.getString("tytul");
        String kategoria = rs.getString("kategoria");
        String autor = rs.getString("imie") + " " + rs.getString("nazwisko");
        int ilosc = rs.getInt("ilosc");
        Float ocena = rs.getFloat("sredniaOcena");
        //Ocena i cena do wyswietlenia
        String ocenaString;
        if(ocena==0) {
          ocenaString = "Brak ocen uzytkownikow";
        }else {
          ocenaString = Float.toString(ocena);
        }
        String cenaString = cena.toString();
        //Tworzenie
        BookPanel test = new BookPanel(id,true);
        test.setPreferredSize(pSize);
        test.setMinimumSize(pSize);
        test.setData(tytul,autor,ocenaString,kategoria,cenaString,Integer.toString(ilosc));
        list.add(test);
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    //Skalowanie wygląda tragicznie jest jest mało obiektów 
    // I tak nie działa.......
//    JPanel test = new JPanel();
//    test.setPreferredSize(pSize);
//    test.setMinimumSize(pSize);
//    list.add(test);
//
//    scrollablePanel.revalidate();
//    scrollablePanel.repaint();
  }

}
