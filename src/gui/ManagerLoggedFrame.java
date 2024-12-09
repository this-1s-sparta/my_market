package gui;
import javax.swing.*;
import java.awt.*;
import api.*;

public class ManagerLoggedFrame {
    //this is called by LoginFrame
    public static void StartFrame(Person user) {
        // Create the Managers Start Frame
        JFrame startFrame = new JFrame("Welcome Manager "+ user.getUsername() );
        startFrame.setSize(300, 200);
        startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create and color the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        Color customColor = new Color(0, 49, 4);
        panel.setBackground(customColor);

        JButton Search = new JButton("Search");
        JButton Add = new JButton("Add");
        JButton Statistics = new JButton("Statistics");
        JButton LogOut = new JButton("Log out");

        Color buttonBackground = new Color(88, 2, 101); // Forest green
        Color buttonForeground = Color.WHITE;           // White text

        Search.setBackground(buttonBackground);
        Search.setForeground(buttonForeground);

        Add.setBackground(buttonBackground);
        Add.setForeground(buttonForeground);

        Statistics.setBackground(buttonBackground);
        Statistics.setForeground(buttonForeground);

        LogOut.setBackground(buttonBackground);
        LogOut.setForeground(buttonForeground);

        Search.addActionListener(e -> { //This action will be taken if Search is pressed
            ManagerSearchFrame.SearchFrame();
        });

        Statistics.addActionListener(e -> { //This action will be taken if Statistics is pressed
            ManagerStatisticsFrame.StatisticsFrame();
        });

        Add.addActionListener(e -> { //This action will be taken if Add is pressed
            ManagerAddFrame.AddFrame();
        });

        LogOut.addActionListener(e -> { //This action will be taken if Log Out is pressed
            LogOutFrame.CloseAll();
        });

        panel.add(Search);
        panel.add(Add);
        panel.add(Statistics);
        panel.add(LogOut);
        startFrame.add(panel);
        startFrame.setVisible(true); // Make the frame visible
    }
}
