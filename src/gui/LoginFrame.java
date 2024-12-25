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
        // Create the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        // GridLayout for 2 columns and 3 rows
        // Create labels and text fields
        panel.setBackground(customColor);

        //labels
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordField = new JTextField(15);

        JLabel messageLabel = new JLabel(" ");
        messageLabel.setForeground(Color.RED); //make message RED
        //the message will only change if the user is refused to log in

        JButton loginButton = new JButton("Log in");
        loginButton.addActionListener(e -> {    //This action will be taken if "Log in" is pressed
            boolean check1=Access.login(usernameField.getText(),passwordField.getText(),"client.csv");
            boolean check2=Access.login(usernameField.getText(),passwordField.getText(),"manager.csv");
            if (check1) {
                Person user = new Person(usernameField.getText(), passwordField.getText());
                //A user of type Person is created with parameters the contents of the fields
                LogOutFrame.OutFrame(loginButton); //Closes the login frame
                ClientLoggedFrame.StartFrame(user);
            }
            else if (check2) {
                Person user = new Person(usernameField.getText(), passwordField.getText());
                //A user of type Person is created with parameters the contents of the fields
                LogOutFrame.OutFrame(loginButton); //Closes the log in frame
                ManagerLoggedFrame.StartFrame(user);
            }
            else {
                messageLabel.setText("Wrong information");
                /*If check1 and check2 are both false the log in information does not match
                  a client or a manager and the message appears to inform the user*/
            }
        });

        // Add to the panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(messageLabel);
        panel.add(loginButton);
        loginFrame.add(panel); // Add the panel to the frame
        loginFrame.setVisible(true); // Make the frame visible
    }
}