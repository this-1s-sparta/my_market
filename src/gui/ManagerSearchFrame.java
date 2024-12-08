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


            JLabel titleLabel=new JLabel("Title:");
            JTextField titleField = new JTextField(20);
            panel.add(titleLabel);
            panel.add(titleField);

            JLabel categoryLabel=new JLabel("Category:");
            String[] categories=Search.CategoryArray();
            JComboBox<String> comboBox1 = new JComboBox<>(categories);
            comboBox1.insertItemAt(null, 0);
            comboBox1.setSelectedIndex(0);

            comboBox1.setBounds(50, 50, 150, categories.length);
            panel.add(categoryLabel);
            panel.add(comboBox1);


            JLabel subcategoryLabel=new JLabel("Subcategory:");
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

                String givenTitle=titleField.getText();
                String givenCategory=(String) comboBox1.getSelectedItem();
                String givenSubcategory=(String) comboBox2.getSelectedItem();
                Search.searchproduct(givenTitle, givenCategory, givenSubcategory);
                JFrame resultFrame = new JFrame("Results");
                resultFrame.setSize(400, 250);
                JPanel panelSearch = new JPanel();
                String current;
                try (BufferedReader reader = new BufferedReader(new FileReader("search.txt"))){
                    while((current=reader.readLine())!=null){
                        JTextArea textArea = new JTextArea();
                        textArea.setEditable(false);
                        textArea.append(current+"\n");
                        String[] fields = current.split(":");
                        String title =current;
                        current=reader.readLine();
                        textArea.append(current+"\n");
                        fields = current.split(":");
                        String des = fields[1].trim();
                        current=reader.readLine();
                        textArea.append(current+"\n");
                        fields = current.split(":");
                        String category = fields[1].trim();
                        current=reader.readLine();
                        textArea.append(current+"\n");
                        fields = current.split(":");
                        String subcategory = fields[1].trim();
                        current=reader.readLine();
                        textArea.append(current+"\n");
                        fields = current.split(":");
                        String price = fields[1].trim();
                        current=reader.readLine();
                        textArea.append(current+"\n");
                        fields = current.split(":");
                        String quantity = fields[1].trim();
                        panelSearch.add(textArea);
                        JButton changeButton = new JButton("Change");
                        panelSearch.add(changeButton);
                        changeButton.addActionListener(ee -> {
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
                            enterButton.addActionListener(ec->{
                                Products p=new Products(titleField.getText(),descriptionField.getText(),(String)comboBox1.getSelectedItem(),(String)comboBox2.getSelectedItem(),priceField.getText(),quantityField.getText());
                                Products.Change("Τίτλος: "+title,p);
                                ChangeFrame.dispose();
                            });
                            ChangeFrame.add(changePanel);
                            ChangeFrame.setVisible(true);



                        });


                    }
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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
        //the manager can search for products if he wants he has the ability
        //to change the characteristics of a product



