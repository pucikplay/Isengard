package bookmenu;

import java.util.ArrayList;

public class Cart {
  public static ArrayList<Integer> listOfItems = new ArrayList<Integer>();
  
  public static void addItem(int id) {
    listOfItems.add(id);
  }
  public static void removeItem(int id) {
    listOfItems.remove(id); // Usuwa index a nie wartosc...
  }
  public static void removeAllItems() {
    listOfItems.clear();
  }
}
