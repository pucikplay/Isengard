package bookmenu;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    
    bookPanel.setBackground(Color.red);
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
    
    //Panel na recenzje
    JPanel pom = new JPanel();
    pom.setPreferredSize(pSize);
    pom.setLayout(new BorderLayout());
    publishReviewButton = new Button("Opublikuj recenzję");
    
    publishReviewButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          System.out.println("Search button has been clicked");
          //Dodaj do bazy danych recenzje (jesli spelnia wymagania dot ilosci znakow)
          //To do
          //Odswiez recenzje
          refreshReviews();
      }
    });
    
    pom.add(publishReviewButton,BorderLayout.EAST);
    reviewText = new JTextField("Tu wpisz swoją recenzję książki");
    pom.add(reviewText,BorderLayout.CENTER);
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
    
    generateList(); // do usunięcia
    addReviews(id);
  }
  /*
   * Zapytanie baze danych o informacje o książce
   */
  private void setBookInfo(int id) {
    bookPanel.setData("NAZWA", "AUTOR", "Srednia ocena 0/5"); // remove this later
  }

  /*
   * Zapytanie do bazy danych o recenzje książki, iteracja i dodanie do listy
   * Wzor w addReviews lub generateList
   */
  private void addReviews(int id) {
//    ReviewPanel test = new ReviewPanel();
//    
//    test.setPreferredSize(pSize);
//    test.setMinimumSize(pSize);
//    test.setData(name,text,rating);
//    
//    test.setPreferredSize(pSize);
//    test.setMinimumSize(pSize);
//    
//    list.add(test);

    scrollablePanel.revalidate();
    scrollablePanel.repaint();
  }
  
  public void refreshReviews() {
    removeAllListedItems();
    addReviews(id);
  }
  /*
   * Może byc potrzebne do aktualizacji po dodaniu swojej recenzji, ale tak to useless
   */
  private void removeAllListedItems() {
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
  private void generateList() {
    for(int i=0;i<25;i++) {
        ReviewPanel test = new ReviewPanel(this,true);
        test.setData("Wojciech Maziarz", "Git polecam każdemu", "5");
        
        test.setPreferredSize(pSize);
        test.setMinimumSize(pSize);
        list.add(test);
        //test.setData("TITLE_" + i,"AUTHOR_"+i,"10/"+i);
    }
  }

}
