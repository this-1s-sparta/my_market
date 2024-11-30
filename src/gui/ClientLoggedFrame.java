package gui;
import javax.swing.*;
import java.awt.*;
import api.*;

public class ClientLoggedFrame {
    //this is called by LoginFrame or SignUpFrame
    public static void StartFrame(Person user) {
        JFrame startFrame = new JFrame("Welcome Client "+ user.getUsername() );
        startFrame.setSize(300, 200);
        startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startFrame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 9, 10, 10));

        JButton Search = new JButton("Search");
        JButton Cart = new JButton("Cart");
        JButton History = new JButton("History");
        JButton LogOut = new JButton("Log Out");
        //this is the action that will be taken after login is pressed
        Search.addActionListener(e -> {
            ClientSearchFrame.SearchFrame();
        });

        Cart.addActionListener(e -> {
            ClientCartFrame.CartFrame();
        });

        History.addActionListener(e -> {
            ClientHistoryFrame.HistoryFrame();
        });

        LogOut.addActionListener(e -> {
            LogOutFrame.OutFrame();
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
