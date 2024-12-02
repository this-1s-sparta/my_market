package gui;
import api.*;
import javax.swing.*;
import java.awt.*;

public class LoginFrame {
    //this is called by MainFrame
    public static void openLoginFrame() {
        // Create the login frame
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(300, 150);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setLayout(new GridBagLayout()); //center
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
        messageLabel.setForeground(Color.RED); //make message RED

        //Login button
        JButton loginButton = new JButton("Login");
        //this is the action that will be taken after login is pressed
        loginButton.addActionListener(e -> {
            boolean check1=Access.login(usernameField.getText(),passwordField.getText(),"client.csv");
            boolean check2=Access.login(usernameField.getText(),passwordField.getText(),"manager.csv");
            if (check1) {
                Person user = new Person(usernameField.getText(), passwordField.getText());
                LogOutFrame.OutFrame(loginButton); //this closes the login frame
                ClientLoggedFrame.StartFrame(user);
            }
            else if (check2) {
                Person user = new Person(usernameField.getText(), passwordField.getText());
                LogOutFrame.OutFrame(loginButton); //this closes the login frame
                ManagerLoggedFrame.StartFrame(user);
            }
            else {
                messageLabel.setText("Wrong information");
            }
        });

        // Add to the panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(messageLabel);  // this might be empty
        panel.add(loginButton);
        // Add the panel to the frame
        loginFrame.add(panel);
        // Make the frame visible
        loginFrame.setVisible(true);
    }
}