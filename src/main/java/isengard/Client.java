package isengard;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Client {

    private final Window window;

    public Client() {

        this.window = new Window();
        addWindowListener();
        window.setVisible(true);

    }

    private void addWindowListener() {
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                System.out.println("I'm closing.");
            }
        });
    }


}
