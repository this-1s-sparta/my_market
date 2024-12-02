package gui;
import javax.swing.*;
import java.awt.*;
import api.*;

public class ManagerLoggedFrame {
    //this is called by LoginFrame
    public static void StartFrame(Person user) {
        JFrame startFrame = new JFrame("Welcome Manager "+ user.getUsername() );
        startFrame.setSize(300, 200);
        startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startFrame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 9, 10, 10));

        JButton Search = new JButton("Search");
        JButton Add = new JButton("Add");
        JButton Statistics = new JButton("Statistics");
        JButton LogOut = new JButton("Log out");
        //this is the action that will be taken after login is pressed
        Search.addActionListener(e -> {
            ManagerSearchFrame.SearchFrame();
        });

        Statistics.addActionListener(e -> {
            //done
            ManagerStatisticsFrame.StatisticsFrame();
        });

        Add.addActionListener(e -> {
            ManagerAddFrame.AddFrame();
        });

        LogOut.addActionListener(e -> {
            //done
            LogOutFrame.CloseAll();
        });

        panel.add(Search);
        panel.add(Add);
        panel.add(Statistics);
        panel.add(LogOut);
        startFrame.add(panel);
        // Make the frame visible
        startFrame.setVisible(true);
    }
}
