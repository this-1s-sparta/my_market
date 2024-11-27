package gui;
import api.*;
import javax.swing.*;
import java.awt.*;

public class ClientLoggedFrame {
    //this is called by LoginFrame or SignUpFrame
    public static void StartFrame() {
        JFrame startFrame = new JFrame("Welcome Client");
        startFrame.setSize(300, 200);
        startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startFrame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 9, 10, 10));

        JButton Search = new JButton("Search");
        JButton Cart = new JButton("Cart");
        JButton History = new JButton("History");
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

        panel.add(Search);
        panel.add(Cart);
        panel.add(History);
        startFrame.add(panel);
        // Make the frame visible
        startFrame.setVisible(true);
    }
}
