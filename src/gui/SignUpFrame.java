package gui;
import api.*;
import javax.swing.*;
import java.awt.*;

public class SignUpFrame {
    // this is called by MainFrame
    public static void openSignUpFrame() {
        // Create the sign up Frame
        JFrame signupFrame = new JFrame("Sign Up");
        signupFrame.setSize(400, 250);
        signupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signupFrame.setLayout(new GridBagLayout());
        Color customColor = new Color(150, 0, 180); // colour the frame
        signupFrame.getContentPane().setBackground(customColor);
        //Create the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBackground(customColor);

        //labels
        //Name and Lastname are needed in the form but are never used
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);
        JLabel lastnameLabel = new JLabel("Lastname:");
        JTextField lastnameField = new JTextField(15);
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordField = new JTextField(15);

        JLabel messageLabel = new JLabel(" ");
        messageLabel.setForeground(Color.RED); //make message red
        //this message will only change if the client is refused from signing up

        //Login button
        JButton signupButton = new JButton("Sign Up");
        signupButton.addActionListener(e -> {  //This action will be taken if login is pressed
            String username = usernameField.getText(); //save the field content to username
            String password = passwordField.getText(); //save the fields content to password
            if (username != null && !username.trim().isEmpty() &&
                    password != null && !password.trim().isEmpty() &&
                    nameField.getText() != null && !nameField.getText().trim().isEmpty() &&
                    lastnameField.getText() != null && !lastnameField.getText().trim().isEmpty()) {
                boolean check1 = Access.signup(usernameField.getText(), passwordField.getText());
                if (check1) {
                    Person user = new Person(username, password);
                    LogOutFrame.OutFrame(signupButton); //Closes the sign up frame
                    ClientLoggedFrame.StartFrame(user);
                } else
                    messageLabel.setText("Username already exists");
                    /*If the information does not match the info of a client
                      a message will appear to inform the user */
            } else
                messageLabel.setText("All fields are required");
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
        panel.add(messageLabel);
        panel.add(signupButton);
        signupFrame.add(panel);  // Add the panel to the frame
        signupFrame.setVisible(true); // Make the frame visible
    }
}
