package gui;

import api.FileManagement;
import api.Products;
import api.Search;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ManagerSearchFrame {
    //this is called by ManagerLoggedFrame
    public static void SearchFrame() {
       //creates search frame
        JFrame searchFrame = new JFrame("Search");
        searchFrame.setSize(400, 250);
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Color customColor = new Color(150, 0, 180); // colour the frame
        searchFrame.getContentPane().setBackground(customColor);
         //creates panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBackground(customColor);

        //label and textField for title in order for the user to write the title he wants to search


        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(20);
        panel.add(titleLabel);
        panel.add(titleField);

        //label and combobox with all the categories for category in order for the user to choose the category he wants to search


        JLabel categoryLabel = new JLabel("Category:");
        String[] categories = Search.CategoryArray();
        JComboBox<String> comboBox1 = new JComboBox<>(categories);
        comboBox1.insertItemAt(null, 0);
        comboBox1.setSelectedIndex(0);

        comboBox1.setBounds(50, 50, 150, categories.length);
        panel.add(categoryLabel);
        panel.add(comboBox1);

        //label for subcategory and combobox which includes the subcategories of the category that is chosen
        //if no category is chosen the combobox is empty


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
            //when search button is pressed it takes all the data from the textField and the comboboxes and it calls tha function searchproduct with these three parametres
            String givenTitle = titleField.getText();
            String givenCategory = (String) comboBox1.getSelectedItem();
            String givenSubcategory = (String) comboBox2.getSelectedItem();
            Search.searchproduct(givenTitle, givenCategory, givenSubcategory);
            JFrame resultFrame = new JFrame("Results");
            resultFrame.setSize(400, 250);
            JPanel panelSearch = new JPanel();
            String current;
            panelSearch.setLayout(new BoxLayout(panelSearch, BoxLayout.Y_AXIS));

            try (BufferedReader reader = new BufferedReader(new FileReader("search.txt"))) {
                current = reader.readLine();
                while(current!=null && !current.equals("")) {
                    String[] parts = new String[6];
                    int i = 0;
                    while (current != null && i < 6) {
                        String[] fields = current.split(":");//takes the part of the line after : which are the characteristics
                        if (fields.length > 1)  // Check if the line is properly split
                            parts[i] = fields[1];
                        i++;
                        current = reader.readLine();
                    }
                    //assign each part to the right value
                    String title = parts[0];
                    String des = parts[1];
                    String category = parts[2];
                    String subcategory = parts[3];
                    String price = parts[4];
                    String quantity = parts[5];

                    //creates textArea with th details of the product

                    JTextArea textArea = new JTextArea();
                    textArea.setEditable(false);
                    textArea.append(title + "\n" + des + "\n" + category + "\n" + subcategory + "\n" + price + "\n" + quantity + "\n");
                    panelSearch.add(textArea);


                    JButton changeButton = new JButton("Change");//creates change button
                    panelSearch.add(changeButton);//adds change button to panel serach

                    changeButton.addActionListener(ee -> {//when change button is pressed
                        //creates change frame
                        JFrame ChangeFrame = new JFrame("Change Product");
                        ChangeFrame.setSize(400, 300);
                        ChangeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        ChangeFrame.setLayout(new GridBagLayout()); // Center alignment
                        ChangeFrame.getContentPane().setBackground(customColor);

                        // Create the panel
                        JPanel changePanel = new JPanel();
                        changePanel.setLayout(new GridLayout(7, 2, 5, 5)); // 2 columns, 6 rows with padding
                        changePanel.setBackground(customColor);
                        // Create labels and text fields
                        changePanel.add(titleLabel);
                        changePanel.add(titleField);
                        titleField.setText(title);
                        JLabel descriptionLabel = new JLabel("Description:");
                        JTextField descriptionField = new JTextField(des);
                        changePanel.add(descriptionLabel);
                        changePanel.add(descriptionField);
                        changePanel.add(categoryLabel);
                        comboBox1.insertItemAt(category, 0);
                        comboBox1.setSelectedIndex(0);
                        changePanel.add(comboBox1);
                        changePanel.add(subcategoryLabel);
                        comboBox2.insertItemAt(subcategory, 0);
                        comboBox2.setSelectedIndex(0);
                        changePanel.add(comboBox2);
                        JLabel priceLabel = new JLabel("Price:");
                        JTextField priceField = new JTextField(price);
                        JLabel quantityLabel = new JLabel("Quantity:");
                        JTextField quantityField = new JTextField(quantity);
                        JButton enterButton = new JButton("Enter");
                        changePanel.add(priceLabel);
                        changePanel.add(priceField);
                        changePanel.add(quantityLabel);
                        changePanel.add(quantityField);
                        changePanel.add(enterButton);
                        enterButton.addActionListener(ec -> {//when enter button is pressed the changed data are taken and they create a new product which replace the old one
                            Products p = new Products(titleField.getText().trim(), ((String) comboBox1.getSelectedItem()).trim(), ((String) comboBox2.getSelectedItem()).trim(), descriptionField.getText().trim(), priceField.getText().trim(), quantityField.getText().trim());
                            Products.Change("Τίτλος:" + title, p);// the change methon is called
                            ChangeFrame.dispose();
                        });
                        ChangeFrame.add(changePanel);//panel ia added to frame
                        ChangeFrame.setVisible(true);
                    });
                }} catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            //frame add scroll pane
            JScrollPane scrollPane = new JScrollPane(panelSearch);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            resultFrame.add(scrollPane);
            resultFrame.revalidate();
            resultFrame.repaint();
            resultFrame.setVisible(true);
        });
        //panel add searchbutton
        //frame add panel
        panel.add(searchButton);
        searchFrame.add(panel);
        searchFrame.setVisible(true);

    }
}
//the manager can search for products if he wants he has the ability
//to change the characteristics of a product



