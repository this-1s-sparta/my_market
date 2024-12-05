package gui;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import api.*;

public class ManagerStatisticsFrame {
    // This is called by ManagerLoggedFrame
    public static void StatisticsFrame() {
        //Create the main frame
        JFrame StatFrame = new JFrame("Statistics");
        StatFrame.setSize(500, 250); // Set the size of the frame
        StatFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only close this frame
        StatFrame.setLayout(new GridBagLayout()); // Center components
        //Set the background color
        Color customColor = new Color(75, 150, 100);
        StatFrame.getContentPane().setBackground(customColor);
        //Create a panel with GridLayout
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10)); // 1 row, 2 columns
        panel.setBackground(customColor); // Match panel color with frame
        //Create buttons
        JButton Unavailable = new JButton("Unavailable Products");
        Unavailable.setBackground(new Color(150, 0, 180));
        JButton Best = new JButton("Most Ordered Products");
        Best.setBackground(new Color(150, 0, 180));

        Unavailable.addActionListener(e -> { //This action will be taken if Unavailable Products is pressed
            Statistics.Zero();
            JFrame ZeroFrame = new JFrame("Unavailable Products");
            ZeroFrame.setSize(400, 300);
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            ZeroFrame.add(scrollPane);
            try (BufferedReader reader = new BufferedReader(new FileReader("zero.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(":");
                    String title = fields[1].trim();
                    textArea.append(title + "\n");
                }
            } catch (IOException ex) {
                textArea.setText("Error reading file: " + ex.getMessage());
            }
            ZeroFrame.setVisible(true);
        });

        Best.addActionListener(e -> { //This action will be taken if Best Products is pressed
            Statistics.Best();
            JFrame BestFrame = new JFrame("Most Ordered Products");
            BestFrame.setSize(400, 300);
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            BestFrame.add(scrollPane);
            try (BufferedReader reader = new BufferedReader(new FileReader("best.txt"))) {
                String line;
                int count = 0;
                while ((line = reader.readLine()) != null && count < 5) {  // Only show top 5 products
                    String[] fields = line.split("@");
                    if (fields.length > 1) {  // Check if the line is properly split
                        String title = fields[0].trim();
                        String times = fields[1].trim();
                        if (!times.equals("0")) {
                            String[] fields2 = title.split(":");
                            title = fields2[1].trim();
                            textArea.append(title +"\n"+"\n");
                            count++;
                        }
                    }
                }
            } catch (IOException ex) {
                textArea.setText("Error reading file: " + ex.getMessage());
            }
            BestFrame.setVisible(true);
        });

        // Add buttons to the panel
        panel.add(Unavailable);
        panel.add(Best);
        // Add the panel to the main frame
        StatFrame.add(panel);
        StatFrame.setVisible(true); //Make Frame visible
    }
}
