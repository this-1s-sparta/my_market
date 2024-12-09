package gui;
import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ClientSearchFrame {
    //this is called by ClientLoggedFrame
    public static void SearchFrame(Cart c) {
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

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(20);
        panel.add(titleLabel);
        panel.add(titleField);

        JLabel categoryLabel = new JLabel("Category:");
        String[] categories = Search.CategoryArray();
        JComboBox<String> comboBox1 = new JComboBox<>(categories);
        comboBox1.insertItemAt(null, 0);
        comboBox1.setSelectedIndex(0);

        comboBox1.setBounds(50, 50, 150, categories.length);
        panel.add(categoryLabel);
        panel.add(comboBox1);


        JLabel subcategoryLabel = new JLabel("Subcategory:");
        JComboBox<String> comboBox2 = new JComboBox<>();
        comboBox1.setBounds(50, 50, 150, categories.length);
        panel.add(subcategoryLabel);
        panel.add(comboBox2);
        comboBox1.addActionListener(ee -> {
            String selectedCategory = (String) comboBox1.getSelectedItem();
            // Update the subcategories based on the selected category
            String[] sub = new String[0];
            if (selectedCategory != null) {
                int line = FileManagement.PartialSearchLine(0, "categories.txt", selectedCategory);
                if (line >= 1) { // Ensure a valid line was found
                    try (BufferedReader reader = new BufferedReader(new FileReader("categories.txt"))) {
                        String targetLine = null;
                        for (int i = 1; i <= line; i++) {
                            targetLine = reader.readLine();
                        }
                        if (targetLine != null && targetLine.contains("(") && targetLine.contains(")")) {
                            int start = targetLine.indexOf('(') + 1;
                            int end = targetLine.indexOf(')');
                            String subcategories = targetLine.substring(start, end);
                            sub = subcategories.split("@"); // Split by '@'
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Category not found in the file.");
                }
                comboBox2.removeAllItems(); // Clear existing items
                comboBox2.addItem(null);
                for (String subcategory : sub) {
                    comboBox2.addItem(subcategory);
                }
            }
        });
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {

            String givenTitle = titleField.getText();
            String givenCategory = (String) comboBox1.getSelectedItem();
            String givenSubcategory = (String) comboBox2.getSelectedItem();
            Search.searchproduct(givenTitle, givenCategory, givenSubcategory);
            JFrame resultFrame = new JFrame("Results");
            resultFrame.setSize(400, 250);
            JPanel panelSearch = new JPanel();
            panelSearch.setLayout(new GridLayout(5, 2, 10, 10));
            panelSearch.setPreferredSize(new Dimension(380, 1000));
            String current;

            try (BufferedReader reader = new BufferedReader(new FileReader("search.txt"))) {
                current = reader.readLine();
                while(current!=null && !current.equals("")) {
                    String[] parts = new String[6];
                    int i = 0;
                    while (current != null && i < 6) {
                        String[] fields = current.split(":");
                        if (fields.length > 1)  // Check if the line is properly split
                            parts[i] = fields[1];
                        i++;
                        current = reader.readLine();
                    }
                    String title = parts[0];
                    String des = parts[1];
                    String category = parts[2];
                    String subcategory = parts[3];
                    String price = parts[4];
                    String quantity = parts[5];

                    JTextArea textArea = new JTextArea();
                    textArea.setEditable(false);
                    textArea.append(title + "\n" + des + "\n" + category + "\n" + subcategory + "\n" + price + "\n" + quantity + "\n");
                    panelSearch.add(textArea);


                    JTextField addToCart = new JTextField(3);
                    JLabel addToCartLabel = new JLabel("Add Quantity To Cart");
                    panelSearch.add(addToCartLabel);
                    panelSearch.add(addToCart);
                    JButton addToCartButton = new JButton("Add To Cart");
                    addToCartButton.addActionListener(eadd->{
                        String priceW = price.replace("€","");
                        priceW = priceW.replace(",",".");
                        ProductInCart p=new ProductInCart(title,Integer.parseInt(addToCart.getText()),Double.parseDouble(priceW));
                        int k=c.AddToCart(p);
                        if(k==0) {
                            JLabel messageLabel = new JLabel(" ");
                            messageLabel.setForeground(Color.RED);
                            messageLabel.setText("Available quantity not enough");
                        }
                        resultFrame.dispose();
                    });
                    panelSearch.add(addToCartButton);






                }} catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JScrollPane scrollPane = new JScrollPane(panelSearch);
            //panelSearch.add(scrollPane);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            resultFrame.add(scrollPane);
            resultFrame.revalidate();
            resultFrame.repaint();
            resultFrame.add(panelSearch);
            resultFrame.setVisible(true);
        });
        panel.add(searchButton);
        //panel.add(new JTextField());
        //panel.add(new JComboBox<>());
        searchFrame.add(panel);
        searchFrame.setVisible(true);

    }
}
