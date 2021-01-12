package isengard.io;

import isengard.db.Adapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegisterWindow extends JFrame {

    private JPanel panel;
    private Dimension pSize = new Dimension(400, 150);
    public Label loginLabel = new Label("Login: ");
    public Label passwordLabel = new Label("Password: ");
    public Label repeatPasswordLabel = new Label("Repeat password: ");
    public Label errorLabel = new Label();
    public final TextField loginText = new TextField();
    public final TextField passwordText = new TextField();
    public final TextField repeatPasswordText = new TextField();
    public Button registerButton = new Button("Register");

    public RegisterWindow() {
        setTitle("Register window");
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

        initRegister();

        this.setVisible(true);

    }

    private void initRegister() {
        panel.add(loginLabel);
        panel.add(loginText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(repeatPasswordLabel);
        panel.add(repeatPasswordText);
        passwordText.setEchoChar('*');
        repeatPasswordText.setEchoChar('*');
        panel.add(errorLabel);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String log = loginText.getText();
                String pass = passwordText.getText();
                String rPass = repeatPasswordText.getText();
                if(log.equals("") || pass.equals("")) {
                    errorLabel.setText("Fields cannot be empty");
                }
                else if(!pass.equals(rPass)) {
                    errorLabel.setText("Repeated password is different");
                }
                else {
                    try {
                        if (Adapter.register(log, pass)) {
                            dispose();
                        }
                        else {
                            errorLabel.setText("Registration failed");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        panel.add(registerButton);
    }
}
