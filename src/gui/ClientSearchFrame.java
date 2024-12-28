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
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientSearchFrame {
    //this is called by ClientLoggedFrame
    public static Cart SearchFrame() {
        //here a client can search for a product
        //for each product give the ability to view details, select quantity (if available)
        //or put to cart
        Cart cart = new Cart();//creates object cart
        //creates the search frame
        JFrame searchFrame = new JFrame("Search");
        searchFrame.setSize(400, 250);
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
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
        JButton searchButton = new JButton("Search");//creates search button
        searchButton.addActionListener(e -> {
              //when search button is pressed it takes all the data from the textField and the comboboxes and it calls tha function searchproduct with these three parametres
            String givenTitle = titleField.getText();
            String givenCategory = (String) comboBox1.getSelectedItem();
            String givenSubcategory = (String) comboBox2.getSelectedItem();
            Search.searchproduct(givenTitle, givenCategory, givenSubcategory);
            //creates result frame
            JFrame resultFrame = new JFrame("Results");
            resultFrame.setSize(400, 250);
            JPanel panelSearch = new JPanel();//creates panel search
            panelSearch.setLayout(new BoxLayout(panelSearch, BoxLayout.Y_AXIS));
            String current;
            ArrayList<ProductInCart> list=new ArrayList<>();//creates new list

            try (BufferedReader reader = new BufferedReader(new FileReader("search.txt"))) {//opens search.txt file for reading
                current = reader.readLine();//read line from file
                while(current!=null && !current.equals("")) {//if line is not null nor empty
                    String[] parts = new String[6];
                    int i = 0;
                    while (current != null && i < 6) {
                        String[] fields = current.split(":");//takes the data after teh : which are the characteristics of the product
                        if (fields.length > 1)  // Check if the line is properly split
                            parts[i] = fields[1];
                        i++;
                        current = reader.readLine();
                    }
                    //assigns the table to each value
                    String title = parts[0];
                    String des = parts[1];
                    String category = parts[2];
                    String subcategory = parts[3];
                    String price = parts[4];
                    String quantity = parts[5];

                    //creates text area with the details of the product
                    JTextArea textArea = new JTextArea();
                    textArea.setEditable(false);
                    textArea.append(title + "\n" + des + "\n" + category + "\n" + subcategory + "\n" + price + "\n" + quantity + "\n");
                    panelSearch.add(textArea);
                    final int[] quantitygiven = {0};
                    //adds quantity label

                        JLabel quantityLabel = new JLabel("Quantity: " + quantitygiven[0]);
                        //quantityLabel.setBounds(110, 50, 150, 30);  // Set position and size
                    panelSearch.add(quantityLabel);

                        // Create the "Minus" button
                        JButton minusButton = new JButton("-");
                        //minusButton.setBounds(50, 100, 60, 30);  // Set position and size
                    panelSearch.add(minusButton);

                        // Create the "Plus" button
                        JButton plusButton = new JButton("+");
                        //plusButton.setBounds(180, 100, 60, 30);  // Set position and size
                    panelSearch.add(plusButton);

                        // Action for "Minus" button
                        minusButton.addActionListener(eq-> {
                                if (quantitygiven[0] > 0) {
                                    quantitygiven[0]--;  // Decrease the quantity
                                    quantityLabel.setText("Quantity: " + quantitygiven[0]);
                                }

                        });

                        // Action for "Plus" button
                        plusButton.addActionListener(eq1-> {
                                if (quantitygiven[0] < 100) {
                                    quantitygiven[0]++;  // Increase the quantity
                                    quantityLabel.setText("Quantity: " + quantitygiven[0]);
                                }

                        });
                        //fixes price to make it float
                        price=price.replace("€","");
                        String priceF=price.replace(",",".");
                        //creates add to cart button
                        JButton addtocart=new JButton("Add to Cart");
                        panelSearch.add(addtocart);

                        addtocart.addActionListener(eq1-> {//when add to cart button is pressed
                            //checks if available quantity is enough
                            //if it is not a pop-up message appears
                            //if it is the product is added to the cart
                            AtomicInteger flag=new AtomicInteger();
                            int a=cart.AvailableQuantity(title,flag);
                            if(quantitygiven[0]!=0 && quantitygiven[0]<=a){
                            ProductInCart p= new ProductInCart(title, quantitygiven[0],Double.parseDouble(priceF));
                            cart.cart.add(p);}
                            else if(quantitygiven[0]>a)
                            {
                                JOptionPane.showMessageDialog(null, "Not enough available quantity", "Error", JOptionPane.INFORMATION_MESSAGE);

                            }

                        });






                }} catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            //creates vertical scroll pane and adds it to the panel

            JScrollPane scrollPane = new JScrollPane(panelSearch);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            resultFrame.add(scrollPane);
            resultFrame.revalidate();
            resultFrame.repaint();
            resultFrame.setVisible(true);
        });
        //adds the buttons/panel to the frame
        panel.add(searchButton);
        searchFrame.add(panel);
        searchFrame.setVisible(true);

    return cart;
    }
}
