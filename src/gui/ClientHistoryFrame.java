package gui;

import api.FileManagement;
import api.History;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ClientHistoryFrame {
    //this is called by ClientLoggedFrame
    public static void HistoryFrame(String name) throws FileNotFoundException {
        //here each client can view their own history
        JFrame historyFrame = new JFrame("History");
        historyFrame.setSize(400, 250);
        historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        historyFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        Color customColor = new Color(150, 0, 180); // colour the frame
        historyFrame.getContentPane().setBackground(customColor);


        JPanel panelHistory = new JPanel();
        panelHistory.setLayout(new GridLayout(5, 2, 10, 10));
        panelHistory.setBackground(customColor);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        History.ShowHistory(name);
        try (BufferedReader br = new BufferedReader(new FileReader("historyuser.txt"))) {
            String line;
            for(int i=1;i< FileManagement.LastLine("historyuser.txt");i++)
            {
                line = br.readLine();
                textArea.append(line + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        panelHistory.add(new JScrollPane(textArea));
        historyFrame.add(panelHistory);
        historyFrame.setVisible(true);


    }
}
