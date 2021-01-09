package isengard.io;

import isengard.db.Adapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame{

    private Panel panel;

    private final int windowSide = 250;

    public LoginWindow() {
        initUI();
    }

    private void initUI() {
        setTitle("Login window");
        setSize(new Dimension(windowSide, windowSide));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init(windowSide);
    }

    public void init(int windowSide) {

        this.panel = new Panel(windowSide);
        this.add(panel);

        initLogin();

        this.setVisible(true);

    }

    private void initLogin() {
        final Login login = new Login();
        panel.add(login.loginLabel);
        panel.add(login.passwordLabel);
        panel.add(login.errorLabel);
        panel.add(login.loginText);
        panel.add(login.passwordText);
        login.loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String log = login.loginText.getText();
                String pass = login.passwordText.getText();
                if(Adapter.login(log, pass)) {
                    dispose();
                }
                else {
                    login.errorLabel.setText("Wrong credentials");
                }
            }
        });
        panel.add(login.loginButton);
    }
}
