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
        startFrame.setVisible(true);
        //Create the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 9, 10, 10));
        //Create the Buttons
        JButton Search = new JButton("Search");
        JButton Add = new JButton("Add");
        JButton Statistics = new JButton("Statistics");
        JButton LogOut = new JButton("Log out");

        Search.addActionListener(e -> { //This action will be taken if Search is pressed
            ManagerSearchFrame.SearchFrame();
        });

        Statistics.addActionListener(e -> { //This action will be taken if Statistics is pressed
            //done
            ManagerStatisticsFrame.StatisticsFrame();
        });

        Add.addActionListener(e -> { //This action will be taken if Add is pressed
            ManagerAddFrame.AddFrame();
        });

        LogOut.addActionListener(e -> { //This action will be taken if Log Out is pressed
            //done
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
