package gui;

import api.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ManagerAddFrame {
    // Class-level variables
    private static String selectedCategory = null;
    private static String selectedSub = null;
    private static String add = null;

    public static void AddFrame() {
        // Create the main frame
        JFrame AddFrame = new JFrame("Add Product");
        AddFrame.setSize(400, 300);
        AddFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        AddFrame.setLayout(new GridBagLayout()); // Center alignment
        Color customColor = new Color(150, 0, 180); // Custom background color
        AddFrame.getContentPane().setBackground(customColor);

        // Create the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 5, 5)); // 2 columns, 6 rows with padding
        panel.setBackground(customColor);

        // Create labels and text fields
        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(15);

        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField(100);

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(5);

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField(5);

        // Create the first combo box for categories
        String[] categories = Search.CategoryArray();
        JComboBox<String> comboBox1 = new JComboBox<>(categories);
        comboBox1.setBounds(50, 50, 150, categories.length);

        // Create the second combo box for subcategories
        JComboBox<String> comboBox2 = new JComboBox<>();
        comboBox2.setBounds(50, 50, 150, 10);

        JLabel messageLabel = new JLabel(" ");
        messageLabel.setForeground(Color.RED); //make message RED

        //chose for "temaxia" or "kila" to be written on "products.txt" for quantity
        JRadioButton pieces = new JRadioButton("pieces");
        JRadioButton kilos = new JRadioButton("kilos");
        ButtonGroup group = new ButtonGroup();
        group.add(pieces);
        group.add(kilos);
        //add is a class level variable so that I will be able to use it when I call Products.Add(..)
        pieces.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add=" kg";
            }
        });
        kilos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add=" τεμάχια";
            }
        });

        // Add ActionListener for comboBox1 to update subcategories
        comboBox1.addActionListener(ee -> {
            selectedCategory = (String) comboBox1.getSelectedItem();
            // Update the subcategories based on the selected category
            String[] sub = new String[0];
            if (selectedCategory != null) {
                int line = FileManagement.PartialSearchLine(0, "categories.txt", selectedCategory);
                if (line >= 1) { // Ensure a valid line was found
                    try (BufferedReader reader = new BufferedReader(new FileReader("categories.txt"))) {
                        //this is done to only return the subcategories of each category and not all the existing subcategories
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
            }
            // Update comboBox2 with new subcategories
            comboBox2.removeAllItems(); // Clear existing items
            for (String subcategory : sub) {
                comboBox2.addItem(subcategory);
            }
        });

        // Add ActionListener for comboBox2 to handle subcategory selection
        comboBox2.addActionListener(ee -> {
            selectedSub = (String) comboBox2.getSelectedItem();
        });

        JButton AddButton = new JButton("Add");
        AddButton.addActionListener(e -> {
            //get the written text on variables
            String title = titleField.getText();
            String category = selectedCategory;
            String subcategory = selectedSub;
            String description = descriptionField.getText();
            String price = priceField.getText();
            String quantity = quantityField.getText();
            //make sure that no variable is null (all of them are required)
            if (!(title == null || category == null || subcategory == null || description == null || price == null || quantity == null || add==null)) {
                //Make a Product variable and call the wanted method
                Products prod = new Products(title, category, subcategory, description, price, quantity+add);
                Products.Add(prod);
                LogOutFrame.OutFrame(AddButton); //Close the add window if adding was successful
            } else {
                messageLabel.setText("Information missing"); //print message if not successful
            }
        });

        // Add components to the panel
        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(kilos);
        panel.add(pieces);
        panel.add(new JLabel("Category:")); // Label for category selection
        panel.add(comboBox1);
        panel.add(new JLabel("Subcategory:")); // Label for subcategory selection
        panel.add(comboBox2);
        panel.add(messageLabel);
        panel.add(AddButton);

        // Add the panel to the frame
        AddFrame.add(panel);
        AddFrame.setVisible(true);
    }
}
