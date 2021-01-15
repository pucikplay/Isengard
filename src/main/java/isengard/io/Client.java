package isengard.io;

import isengard.db.Adapter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Client {

    private final LoginWindow loginWindow;
    private Window window;

    public Client() {

        this.loginWindow = new LoginWindow();
        addWindowListener();
        loginWindow.setVisible(true);

    }

    private void addWindowListener() {
        loginWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent event) {
                System.out.println("I'm closed.");
                window = new Window();
            }
        });
    }


}
