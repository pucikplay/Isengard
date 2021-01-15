package isengard.io;

import isengard.db.Adapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginWindow extends JFrame{

    private JPanel panel;
    private Dimension pSize = new Dimension(300, 150);
    public Label loginLabel = new Label("Login: ");
    public Label passwordLabel = new Label("Password: ");
    public Label errorLabel = new Label();
    public final TextField loginText = new TextField();
    public final TextField passwordText = new TextField();
    public Button loginButton = new Button("Login");
    public Button registerButton = new Button("Register");

    public LoginWindow() {
        setTitle("Login window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init();
    }

    public void init() {

        this.panel = new JPanel();
        panel.setLayout(new GridLayout(0,2));
        this.setPreferredSize(pSize);
        this.setMinimumSize(pSize);
        this.add(panel);

        initLogin();

        this.setVisible(true);

    }

    private void initLogin() {
        panel.add(loginLabel);
        panel.add(loginText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        passwordText.setEchoChar('*');
        panel.add(errorLabel);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String log = loginText.getText();
                String pass = passwordText.getText();
                try {
                    if(Adapter.login(log, pass)) {
                        dispose();
                    }
                    else {
                        errorLabel.setText("Wrong credentials");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        panel.add(loginButton);
        panel.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegisterWindow();
            }
        });
    }
}
