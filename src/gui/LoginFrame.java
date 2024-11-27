package gui;
import api.*;
import javax.swing.*;
import java.awt.*;

import static java.lang.System.out;

public class LoginFrame {

    public static void openLoginFrame() {
        // Create the login frame
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 150);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        Color customColor = new Color(150, 0, 180); // colour the frame
        loginFrame.getContentPane().setBackground(customColor);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        // GridLayout for 2 columns and 3 rows
        // Create labels and text fields
        panel.setBackground(customColor);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordField = new JTextField(15);

        JLabel messageLabel = new JLabel(" ");

        // Login button
        JButton loginButton = new JButton("Login");
        String username = usernameField.getText(); //save to username
        String password = passwordField.getText(); //save to username
        System.out.println(username);
        System.out.println(password);
        //this is the action that will be taken after login is pressed
        loginButton.addActionListener(e -> {
            boolean check1=Access.login(usernameField.getText(),passwordField.getText(),"client.csv");
            boolean check2=Access.login(usernameField.getText(),passwordField.getText(),"manager.csv");
            boolean message=false;
            if (check1) {
                client user = new client(username, password);
                ClientLogged.Start();
            }
            else if (check2) {
                manager user = new manager(username, password);
                ManagerLogged.Start();
            }
            else {
                messageLabel.setText("Wrong Username or Password");
            }
        });


        // Add to the panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(messageLabel);  // this is empty
        panel.add(loginButton);
        // Add the panel to the frame
        loginFrame.add(panel);
        // Make the frame visible
        loginFrame.setVisible(true);
    }
}

