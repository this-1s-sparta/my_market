package gui;

import javax.swing.*;
import java.awt.*;
import api.*;

public class ClientCartFrame {
    //this is called by ClientLoggedFrame
    public static void CartFrame() {
        //here we can see the contents of the cart
        JFrame cartFrame = new JFrame("Cart\uD83D\uDED2");
        cartFrame.setSize(400, 250);
        cartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cartFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        Color customColor = new Color(150, 0, 180); // colour the frame
        cartFrame.getContentPane().setBackground(customColor);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBackground(customColor);

        //for()
       // {
           // JButton plusButton = new JButton("+");
        //}






    }
}
