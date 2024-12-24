package gui;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

import api.*;

public class ClientLoggedFrame {
    //this is called by LoginFrame or SignUpFrame
    public static void StartFrame(Person user) {
        // Create the Client Start Frame
        JFrame startFrame = new JFrame("Welcome Client "+ user.getUsername() );
        startFrame.setSize(300, 200);
        startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startFrame.setVisible(true);
        //Create the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 9, 10, 10));
        Color customColor = new Color(0, 49, 4);
        panel.setBackground(customColor);

        //Create the buttons
        JButton Search = new JButton("Search");
        JButton Cart = new JButton("Cart");
        JButton History = new JButton("History");
        JButton LogOut = new JButton("Log Out");

        Color buttonBackground = new Color(88, 2, 101); // Forest green
        Color buttonForeground = Color.WHITE;           // White text

        Search.setBackground(buttonBackground);
        Search.setForeground(buttonForeground);

        Cart.setBackground(buttonBackground);
        Cart.setForeground(buttonForeground);

        History.setBackground(buttonBackground);
        History.setForeground(buttonForeground);

        LogOut.setBackground(buttonBackground);
        LogOut.setForeground(buttonForeground);
        Cart c= new Cart();

        Search.addActionListener(e -> { //This action will be done if Search is pressed
            ClientSearchFrame.SearchFrame(c);
            //this method gives the client the ability to
            //search for products and add them to his cart
        });

        Cart.addActionListener(e -> { //This action will be done if Cart is pressed
            ClientCartFrame.CartFrame();
            //this method gives the client the ability to
            //view his cart and change or finalize it
        });

        History.addActionListener(e -> { //This action will be done if History is pressed
            try {
                ClientHistoryFrame.HistoryFrame(user.getUsername());
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            //this method gives the cliet the ability to
            //view his history
        });

        LogOut.addActionListener(e -> { //This action will be done if Logout is pressed
            //done
            LogOutFrame.OutFrame(LogOut);
        });

        panel.add(Search);
        panel.add(Cart);
        panel.add(History);
        panel.add(LogOut);
        startFrame.add(panel);
        // Make the frame visible
        startFrame.setVisible(true);
    }
}
