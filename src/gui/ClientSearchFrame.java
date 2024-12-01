package gui;
import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientSearchFrame {
    //this is called by ClientLoggedFrame
    public static void SearchFrame() {
        //here a client can search for a product
        //for each product give the ability to view details, select quantity (if available)
        //or put to cart
        JFrame searchFrame = new JFrame("Search");
        searchFrame.setSize(400, 250);
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        Color customColor = new Color(150, 0, 180); // colour the frame
        searchFrame.getContentPane().setBackground(customColor);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBackground(customColor);

        JButton titleButton = new JButton("Search By Title");
        titleButton.addActionListener(e->{
            JFrame searchTitleFrame = new JFrame("SearchByTitle");
            searchTitleFrame.setSize(400, 250);
            searchTitleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            searchTitleFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
            searchTitleFrame.getContentPane().setBackground(customColor);
            JPanel panel1 = new JPanel();
            panel1.setLayout(new GridLayout(5, 2, 10, 10));
            panel1.setBackground(customColor);
            String[] titles=Search.TitleArray();
            JComboBox<String> comboBox = new JComboBox<>(titles);
            comboBox.setBounds(50, 50, 150, titles.length);
            panel1.add(comboBox);
            comboBox.addActionListener(ee -> {
                String selectedItem = (String) comboBox.getSelectedItem();
            });
            searchTitleFrame.add(panel1);
            searchTitleFrame.setVisible(true);




        });
        JButton searchButton = new JButton("Search");
        panel.add(new JLabel("Search"));
        panel.add(searchButton);


        //panel.add(new JTextField());
       // panel.add(new JComboBox<>());

        panel.add(titleButton);
        searchFrame.add(panel);
        searchFrame.setVisible(true);



    }
}
