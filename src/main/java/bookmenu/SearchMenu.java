package bookmenu;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class SearchMenu extends JPanel{
  
  public SearchPanel searchPanel;
  public JPanel topPanel;
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
          searchButtonClicked();
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
    
    generateList();
  }
  /*
   * Wewnetrzna klasa panela wyszukiwującego
   */
  class SearchPanel extends JPanel{
    
    private JTextField textField;
    private JComboBox<String> ratingSort;
    private JComboBox<String> priceSort;
    
    public SearchPanel() {
      
      this.setVisible(true);
      this.setBackground(Color.GRAY);
      this.setLayout(new FlowLayout());
      this.setPreferredSize(pSize);
      this.setMinimumSize(pSize);
      
      this.setLayout(new GridBagLayout());
      
      this.init();
    }

    private void init() {
      textField = new JTextField();
      this.add(textField);
      textField.setPreferredSize(new Dimension(400,25));

      String[] sortOptions = {"brak","Rosnąco","Malejąco"};
      //Ocena
      this.add(new Label("Sortuj ocenę"));
      ratingSort = new JComboBox<String>(sortOptions);
      this.add(ratingSort);
      //Cena
      this.add(new Label("Sortuj cenę"));
      priceSort = new JComboBox<String>(sortOptions);
      this.add(priceSort);
    }
    
    public String getNameSearch() {
      return textField.getText();
    }
    public String getPriceSort() {
      return (String) priceSort.getSelectedItem();
    }
    public String getRatingSort() {
      return (String) ratingSort.getSelectedItem();
    }
  }
  
  public void addBook(String name,String author,String rating) {
    BookPanel test = new BookPanel(0,true);
    
    test.setPreferredSize(pSize);
    test.setMinimumSize(pSize);
    test.setData(name,author,rating);
    
    test.setPreferredSize(pSize);
    test.setMinimumSize(pSize);
    
    list.add(test);

    scrollablePanel.revalidate();
    scrollablePanel.repaint();
  }
  
  public void removeAllListedItems() {
    list.removeAll();
    /*
     * Nwm czy potrzebne ale nie zaszkodzi
     */
    scrollablePanel.revalidate();
    scrollablePanel.repaint();
  }

  protected void searchButtonClicked() {
    removeAllListedItems();
    /*
     * Delete
     */
    this.addBook("a","a","a");
    this.addBook("a","a","a");
    this.addBook("a","a","a");
    this.addBook("a","a","a");
    this.addBook("a","a","a");
    this.addBook("a","a","a");
    this.addBook("a","a","a");
    this.addBook("a","a","a");
    this.addBook("a","a","a");
    this.addBook("a","a","a");
    this.addBook("a","a","a");
    /*
     * Zapytanie do bazy o książki spełniające kryteria wpisane w SearchPanel
     * a następnie iterując się dodajemy do metodą addBook
     */
    this.searchPanel.getRatingSort();
    this.searchPanel.getPriceSort();
  }
  /*
   *  Generacja random rzeczy żeby coś było widac
   */
  private void generateList() {
    for(int i=0;i<25;i++) {
      BookPanel test = new BookPanel(0,true);
      
      test.setPreferredSize(pSize);
      test.setMinimumSize(pSize);
      list.add(test);
      test.setData("TITLE_" + i,"AUTHOR_"+i,"10/"+i);
    }
  }

}
