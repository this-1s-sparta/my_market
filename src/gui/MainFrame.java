package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    public static void OpenMainFrame() {
        //This is the first frame it gives the option of log in and sign up
        //Create the main Frame
        JFrame mainFrame = new JFrame("Welcome to My Market");
        mainFrame.setSize(300, 150); // Set the size of the frame
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // stops running if X is pressed
        mainFrame.setLayout(new GridBagLayout()); //GridBagLayout: centers components.
        Color customColor = new Color(100, 30, 200);
        mainFrame.getContentPane().setBackground(customColor); // color the frame
        // Create buttons
        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");

        // login button
        loginButton.addActionListener(e -> { //This action will be done if login is pressed
            LoginFrame.openLoginFrame();
        });

        signUpButton.addActionListener(e -> { //This action will be done if signup is pressed
            SignUpFrame.openSignUpFrame();
        });

        //Add to the frame
        mainFrame.add(loginButton); // Add the buttons to the frame
        mainFrame.add(signUpButton);
        mainFrame.setVisible(true); // Make the frame visible frame
    }
}
