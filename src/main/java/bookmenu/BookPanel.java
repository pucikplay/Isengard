package bookmenu;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
  
  public BookPanel(int id,boolean showDetailsButton) {
    this.bookDBid = id;
    initPanel(showDetailsButton);
  }
  
  public void setData(String name,String author,String rating) {
    this.name.setText(name);
    this.author.setText(author);
    this.rating.setText(rating);
  }
  
  private void initPanel(boolean showDetailsButton) {
    this.setLayout(new BorderLayout());
    this.setBorder(BorderFactory.createLineBorder(Color.black,4));
    //Nazwa
    name = new Label("Test ksiazka");
    name.setFont(new Font("Verdana", Font.PLAIN, 30));
    this.add(name, BorderLayout.PAGE_START);
    //Autor
    author = new Label("Autor ksiazki");
    author.setFont(new Font("Verdana", Font.PLAIN, 15));
    this.add(author, BorderLayout.LINE_START);
    //Przycisk
    //Trzeba zmienic na zewnetrzny przekierowywujacy 
    if(showDetailsButton) {
      button = new Button("Obejrzyj");
      button.setFont(new Font("Verdana", Font.PLAIN, 15));
      this.add(button, BorderLayout.LINE_END);
      button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Kliknięto i teraz trzeba pokazac menu z ta ksiazka"+bookDBid);
            //Wyswietl BookDetailsMenu(id)
        }
      });
    }
    //Autor
    rating = new Label("Ocenka może byc/git");
    rating.setFont(new Font("Verdana", Font.PLAIN, 15));
    this.add(rating, BorderLayout.CENTER);
  }

}
