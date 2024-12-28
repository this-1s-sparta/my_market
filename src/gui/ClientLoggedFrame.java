package gui;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import api.*;

public class ClientLoggedFrame {
    //this is called by LoginFrame or SignUpFrame
    public static void StartFrame(Person user) {
        // Create the Client Start Frame
        JFrame startFrame = new JFrame("Welcome Client "+ user.getUsername() );
        startFrame.setSize(300, 200);
        startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        Color customColor = new Color(0, 49, 4);
        panel.setBackground(customColor);

        //Create the buttons
        JButton Search = new JButton("Search");
        JButton Cart = new JButton("Cart");
        JButton History = new JButton("History");
        JButton LogOut = new JButton("Log Out");

        Color buttonBackground = new Color(88, 2, 101);
        Color buttonForeground = Color.WHITE; // White text

        Search.setBackground(buttonBackground);
        Search.setForeground(buttonForeground);

        Cart.setBackground(buttonBackground);
        Cart.setForeground(buttonForeground);

        History.setBackground(buttonBackground);
        History.setForeground(buttonForeground);

        LogOut.setBackground(buttonBackground);
        LogOut.setForeground(buttonForeground);

        final Cart[] c = new Cart[1];

        Search.addActionListener(e -> { //This action will be done if Search is pressed
            c[0]=ClientSearchFrame.SearchFrame();
            //this method gives the client the ability to
            //search for products and add them to his cart
        });

        Cart.addActionListener(e -> {//This action will be done if Cart is pressed
            if(c[0]!=null)
              ClientCartFrame.CartFrame(c[0],user.getUsername());
            else
                JOptionPane.showMessageDialog(
                        null,
                        "Add products to your cart!",
                        "Empty Cart",
                        JOptionPane.ERROR_MESSAGE
                );
            //this method gives the client the ability to
            //view his cart and change or finalize it
        });

        History.addActionListener(e -> {
            String filePath = "History.txt";
            File file = new File(filePath);
            // Check if the file exists
            if (!file.exists()){
                JOptionPane.showMessageDialog(
                        null,
                        "Make your first purchase!",
                        "No History",
                        JOptionPane.ERROR_MESSAGE
                );//This action will be done if History is pressed

            }
            else{

            try {
                ClientHistoryFrame.HistoryFrame(user.getUsername());
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }}
            //this method gives the client the ability to
            //view his history
        });

        LogOut.addActionListener(e -> { //This action will be done if Logout is pressed
            LogOutFrame.OutFrame(LogOut);
            //this gives the manager the ability to log out
            //it then reopens the Mainframe
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
