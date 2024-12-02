package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    public static void OpenMainFrame() {
        //this is the first frame
        //it gives the option of log in and sign up
        JFrame mainFrame = new JFrame("Welcome to My Market");
        mainFrame.setSize(300, 150); // Set the size of the frame
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // stops running when X is pressed
        mainFrame.setLayout(new GridBagLayout()); //Center
        //GridBagLayout: This layout manager automatically centers components.
        Color customColor = new Color(100, 30, 200); // color the frame
        mainFrame.getContentPane().setBackground(customColor);

        // Create buttons
        JButton loginButton = new JButton("Login");
        // loginButton.setBackground(new Color(80, 60, 100)); //to put colour on buttons
        JButton signUpButton = new JButton("Sign Up");

        // for login button
        loginButton.addActionListener(e -> {
            LoginFrame.openLoginFrame();
            //when "log in" is pressed the LoginFrame is opened
        });
        //for signup button
        signUpButton.addActionListener(e -> {
            SignUpFrame.openSignUpFrame();
            //when "sign up" is pressed SignUpFrame is opened
        });
        // Add the buttons to the frame
        mainFrame.add(loginButton);
        mainFrame.add(signUpButton);
        // visible frame
        mainFrame.setVisible(true);
    }
}
