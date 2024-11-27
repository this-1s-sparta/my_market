package gui;
import api.*;
import javax.swing.*;
import java.awt.*;

public class SignUpFrame {
    // Method to open Sign Up frame
    public static void openSignUpFrame() {
        JFrame signupFrame = new JFrame("Sign Up");
        signupFrame.setSize(400, 250);
        signupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signupFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        Color customColor = new Color(150, 0, 180); // colour the frame
        signupFrame.getContentPane().setBackground(customColor);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        // GridLayout for 2 columns and 3 rows
        // Create labels and text fields
        panel.setBackground(customColor);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);

        JLabel lastnameLabel = new JLabel("Lastname:");
        JTextField lastnameField = new JTextField(15);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordField = new JTextField(15);

        JLabel messageLabel = new JLabel(" ");

        //Login button
        JButton signupButton = new JButton("Sign Up");
        //this is the action that will be taken after login is pressed
        signupButton.addActionListener(e -> {
            String username = usernameField.getText(); //save to username
            String password = passwordField.getText(); //save to username
            if (username != null && !username.trim().isEmpty() &&
                    password != null && !password.trim().isEmpty() &&
                    nameField.getText() != null && !nameField.getText().trim().isEmpty() &&
                    lastnameField.getText() != null && !lastnameField.getText().trim().isEmpty()) {
                boolean check1 = Access.signup(usernameField.getText(), passwordField.getText());
                if (check1) {
                    client user = new client(username, password);
                    ClientLogged.Start();
                } else {
                    messageLabel.setText("Username already exists");
                }

            } else {
                messageLabel.setText("All fields are required");
            }

        });
        // Add to the panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(lastnameLabel);
        panel.add(lastnameField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(messageLabel);  // this might be empty
        panel.add(signupButton);
        // Add the panel to the frame
        signupFrame.add(panel);
        // Make the frame visible
        signupFrame.setVisible(true);
    }
}
