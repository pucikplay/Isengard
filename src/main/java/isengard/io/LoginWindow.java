package isengard.io;

import isengard.db.Adapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame{

    private JPanel panel;
    private Dimension pSize = new Dimension(300, 150);
    public Label loginLabel = new Label("Login: ");
    public Label passwordLabel = new Label("Password: ");
    public Label errorLabel = new Label("Error: ");
    public final TextField loginText = new TextField(6);
    public final TextField passwordText = new TextField(6);
    public Button loginButton = new Button("Login");
    public Button registerButton = new Button("Register");

    public LoginWindow() {
        initUI();
    }

    private void initUI() {
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
                if(Adapter.login(log, pass)) {
                    dispose();
                }
                else {
                    errorLabel.setText("Wrong credentials");
                }
            }
        });
        panel.add(loginButton);
        panel.add(registerButton);
    }
}
