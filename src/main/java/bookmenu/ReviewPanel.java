package bookmenu;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import isengard.db.Adapter;

public class ReviewPanel extends JPanel{

  public Label name;
  public Label text;
  public Label rating;
  private BookDetailsMenu menu;
  private Button deleteButton;
  
  public ReviewPanel(BookDetailsMenu menu,boolean showDeleteButton) {
    this.menu = menu;
    initPanel(showDeleteButton);
  }
  
  public void setData(String name,String text, String rating) {
    this.name.setText(name);
    this.text.setText(text);
    this.rating.setText("Wystawiona ocena " + rating + "/5");
  }
  
  private void initPanel(boolean showDeleteButton) {
    this.setLayout(new BorderLayout());
    this.setBorder(BorderFactory.createLineBorder(Color.black,4));
    //Nazwa
    name = new Label("Xxx_CzytaczKsiążek_xxX");
    name.setFont(new Font("Verdana", Font.PLAIN, 15));
    this.add(name, BorderLayout.PAGE_START);
    //Ocena
    text = new Label("0/10");
    text.setFont(new Font("Verdana", Font.PLAIN, 15));
    this.add(text, BorderLayout.CENTER);
    //Tekst recenzji
    rating = new Label("Nawet spoko ale w sumie nwm Nawet spoko ale w sumie nwm nwm Nawet spoko ale w sumie nwm nwm Nawet spoko ale w sumie nwm nwm Nawet spoko ale w sumie nwm");
    rating.setFont(new Font("Verdana", Font.PLAIN, 15));
    rating.setPreferredSize(new Dimension(50,50));
    this.add(rating, BorderLayout.PAGE_END);
    //Przycisk do usunięcia recenzji
    if(showDeleteButton) {
      deleteButton = new Button("Usun recenzje");
      this.add(deleteButton,BorderLayout.EAST);
      deleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Pressed delete button");
            try {
              System.out.println("DELETE FROM recenzje WHERE tekstowaRecenzja='"+ text.getText() +"')");
              Adapter.execute("DELETE FROM recenzje WHERE tekstowaRecenzja='"+ text.getText() +"'");
              menu.refreshReviews();
            } catch (SQLException e1) {
              // TODO Auto-generated catch block
              e1.printStackTrace();
            }
        }
      });
    }
    
  }

}
