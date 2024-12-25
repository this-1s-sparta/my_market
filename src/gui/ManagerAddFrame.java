package gui;
import api.*;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ManagerAddFrame {
    // Class-level variables
    private static String selectedCategory = null;
    private static String selectedSub = null;
    private static String add = "τεμάχεια";

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

        JLabel TypemesLabel = new JLabel("Choose category/subcategory");
        TypemesLabel.setForeground(Color.WHITE); //make message RED

        JLabel messageLabel = new JLabel(" ");
        messageLabel.setForeground(Color.RED); //make message RED

        // Add ActionListener for comboBox1 to update subcategories
        comboBox1.addActionListener(ee -> {
            selectedCategory = (String) comboBox1.getSelectedItem();
            String[] sub = new String[0]; // Default empty subcategory array
            if (selectedCategory != null) {
                int line = FileManagement.PartialSearchLine(0, "categories.txt", selectedCategory);
                if (line >= 1) {
                    try (BufferedReader reader = new BufferedReader(new FileReader("categories.txt"))) {
                        String targetLine = null;
                        for (int i = 1; i <= line; i++) {
                            targetLine = reader.readLine();
                        }
                        //find the correct category line
                        //then split to get subcategories
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
            comboBox2.removeAllItems(); // Clear existing subcategories
            for (String subcategory : sub) {
                comboBox2.addItem(subcategory);
            }
            selectedSub = null; // check if selectedSub is reset
            TypemesLabel.setText("Σε " + add); // Update label text
        });

        comboBox2.addActionListener(ee -> {
            selectedSub = (String) comboBox2.getSelectedItem(); // Assign subcategory safely
            if (selectedSub != null && (selectedSub.equals("Φρούτα") || selectedSub.equals("Λαχανικά"))) {
                add = "kg";
            } else {
                add = "τεμάχεια";
            }
            TypemesLabel.setText("Σε " + add);
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
            if (title.isEmpty() || category == null || subcategory == null || description.isEmpty() || price.isEmpty() || quantity.isEmpty()) {
                messageLabel.setText("Information missing"); // Display message it fails
            } else {
                // Perform the add operation
                if (subcategory.equals("Φρούτα") || subcategory.equals("Λαχανικά")) {
                    add = "kg";
                }
                Products prod = new Products(title, category, subcategory, description, price+"€", quantity + " " + add);
                Products.Add(prod); // Add the product
                LogOutFrame.OutFrame(AddButton); // Close the frame if successful
            }
        });

        // Add components to the panel
        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(new JLabel("Category:")); // Label for category selection
        panel.add(comboBox1);
        panel.add(new JLabel("Subcategory:")); // Label for subcategory selection
        panel.add(comboBox2);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(messageLabel);
        panel.add(TypemesLabel);
        panel.add(AddButton);

        // Add the panel to the frame
        AddFrame.add(panel);
        AddFrame.setVisible(true);
    }
}
