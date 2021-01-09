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
        loginLabel.setBounds(20, 20, 80, 20);
        passwordLabel.setBounds(20, 50, 80, 20);
        errorLabel.setBounds(20, 80, 50, 20);
        loginText.setBounds(100, 20, 100, 20);
        passwordText.setBounds(100, 50, 100, 20);
        loginButton.setBounds(100, 150, 60, 30);
        passwordText.setEchoChar('*');
    }

}
