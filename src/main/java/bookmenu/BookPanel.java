package bookmenu;

import isengard.io.NewWindow;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BookPanel extends JPanel{

  public int bookDBid;
  public Label name;
  public Label author;
  public Button button;
  public Label rating;
  public Label count;
  public Label cena;
  public Label kategoria;
  
  public BookPanel(int id,boolean showDetailsButton) {
    this.bookDBid = id;
    initPanel(showDetailsButton);
  }
  
  public void setData(String name,String author,String rating,String kategoria,String cena,String ilosc) {
    this.name.setText("Tytuł: "+name);
    this.author.setText("Autor: "+author);
    this.rating.setText("Średnia ocena: "+rating);
    this.kategoria.setText("Kategoria: "+kategoria);
    this.cena.setText("Cena: "+cena + " zł");
    this.count.setText("Dostepne sztuki: " + ilosc);
  }
  
  private void initPanel(boolean showDetailsButton) {
    this.setLayout(new GridLayout(2,2));
    this.setBorder(BorderFactory.createLineBorder(Color.black,4));
    //Nazwa
    name = new Label("Test ksiazka");
    name.setFont(new Font("Verdana", Font.PLAIN, 30));
    this.add(name, BorderLayout.PAGE_START);
    //Autor
    author = new Label("Autor ksiazki");
    author.setFont(new Font("Verdana", Font.PLAIN, 15));
    this.add(author, BorderLayout.LINE_START);
    //Kategoria
    kategoria = new Label("kategoria");
    kategoria.setFont(new Font("Verdana", Font.PLAIN, 15));
    this.add(kategoria, BorderLayout.LINE_START);
    //Cena
    cena = new Label("cena");
    cena.setFont(new Font("Verdana", Font.PLAIN, 15));
    this.add(cena, BorderLayout.LINE_START);
    //Rating
    rating = new Label("Ocenka może byc/git");
    rating.setFont(new Font("Verdana", Font.PLAIN, 15));
    this.add(rating, BorderLayout.CENTER);
    //Number of books available
    count = new Label("Ilosc sztuk");
    count.setFont(new Font("Verdana", Font.PLAIN, 15));
    this.add(count, BorderLayout.CENTER);
    
    //Przycisk
    if(showDetailsButton) {
      button = new Button("Obejrzyj");
      button.setFont(new Font("Verdana", Font.PLAIN, 15));
      this.add(button, BorderLayout.LINE_END);
      button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Kliknięto i teraz trzeba pokazac menu z ta ksiazka "+bookDBid);
            new NewWindow(new BookDetailsMenu(bookDBid), "Book Details");
        }
      });
    }
  }

  public void hideAvailableBooks() {
    count.setText("");
  }
}
