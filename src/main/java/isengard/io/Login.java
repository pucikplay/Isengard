package isengard.io;

import isengard.db.Adapter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    public Label loginLabel = new Label("Login: ");
    public Label passwordLabel = new Label("Password: ");
    public Label errorLabel = new Label("Error: ");
    public final TextField loginText = new TextField(6);
    public final TextField passwordText = new TextField(6);
    public Button loginButton = new Button("Login");

    public Login() {
        loginLabel.setBounds(100, 100, 100, 20);
        passwordLabel.setBounds(100, 200, 100, 20);
        errorLabel.setBounds(100, 300, 100, 20);
        loginText.setBounds(220, 100, 200, 20);
        passwordText.setBounds(220, 200, 200, 20);
        loginButton.setBounds(655, 850, 150, 60);
        passwordText.setEchoChar('*');
    }

}
