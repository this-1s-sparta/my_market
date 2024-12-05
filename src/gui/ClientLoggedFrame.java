package gui;
import javax.swing.*;
import java.awt.*;
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
        //Create the buttons
        JButton Search = new JButton("Search");
        JButton Cart = new JButton("Cart");
        JButton History = new JButton("History");
        JButton LogOut = new JButton("Log Out");

        Search.addActionListener(e -> { //This action will be done if Search is pressed
            ClientSearchFrame.SearchFrame();
        });

        Cart.addActionListener(e -> { //This action will be done if Cart is pressed
            ClientCartFrame.CartFrame();
        });

        History.addActionListener(e -> { //This action will be done if History is pressed
            ClientHistoryFrame.HistoryFrame();
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
