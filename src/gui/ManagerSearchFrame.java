package gui;

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
                String[] titles= Search.TitleArray();
                JComboBox<String> comboBox = new JComboBox<>(titles);
                comboBox.setBounds(50, 50, 150, titles.length);
                panel1.add(comboBox);
                JTextArea textArea = new JTextArea();
                textArea.setEditable(false);
                comboBox.addActionListener(ee -> {
                    panel1.remove(textArea);
                    String selectedItem = (String) comboBox.getSelectedItem();
                    textArea.setText(Search.searchproduct(selectedItem));
                    panel1.add(textArea);


                });
                searchTitleFrame.add(panel1);
                searchTitleFrame.setVisible(true);




            });

            JButton categoryButton = new JButton("Search By Category");
            categoryButton.addActionListener(e1->{
                JFrame searchCategoryFrame = new JFrame("SearchByCategory");
                searchCategoryFrame.setSize(400, 250);
                searchCategoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                searchCategoryFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
                searchCategoryFrame.getContentPane().setBackground(customColor);
                JPanel panel2 = new JPanel();
                panel2.setLayout(new GridLayout(5, 2, 10, 10));
                panel2.setBackground(customColor);
                String[] categories=Search.CategoryArray();
                JComboBox<String> comboBox1 = new JComboBox<>(categories);
                comboBox1.setBounds(50, 50, 150, categories.length);
                panel2.add(comboBox1);
                JTextArea textArea1 = new JTextArea();
                textArea1.setEditable(false);
                comboBox1.addActionListener(ee -> {
                    panel2.remove(textArea1);
                    String selectedItem = (String) comboBox1.getSelectedItem();
                    textArea1.setText(Search.searchproduct(selectedItem));
                    panel2.add(textArea1);


                });

                searchCategoryFrame.add(panel2);
                searchCategoryFrame.setVisible(true);




            });

            JButton searchButton = new JButton("Search");
            searchButton.addActionListener(e1->{
                JFrame searchAllFrame = new JFrame("Search");
                searchAllFrame.setSize(400, 250);
                searchAllFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                searchAllFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
                searchAllFrame.getContentPane().setBackground(customColor);
                JPanel panel3 = new JPanel();
                panel3.setLayout(new GridLayout(5, 2, 10, 10));
                panel3.setBackground(customColor);
                JTextArea textArea2 = new JTextArea();
                textArea2.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea2);
                searchAllFrame.add(scrollPane);
                try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
                    String line;
                    int count = 0;
                    while ((line = reader.readLine()) != null) {
                        textArea2.append(line + "\n");

                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                textArea2.setText(Search.searchproduct());
                panel3.add(textArea2);

                searchAllFrame.add(panel3);
                searchAllFrame.setVisible(true);

            });
            JButton subcategoryButton = new JButton("Search By Category And Subcategory");
            subcategoryButton.addActionListener(e1->{
                JFrame searchSubategoryFrame = new JFrame("SearchBySubcategory");
                searchSubategoryFrame.setSize(400, 250);
                searchSubategoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                searchSubategoryFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
                searchSubategoryFrame.getContentPane().setBackground(customColor);
                JPanel panel4 = new JPanel();
                panel4.setLayout(new GridLayout(5, 2, 10, 10));
                panel4.setBackground(customColor);
                String[] categories=Search.CategoryArray();
                String[] sub=Search.SubCategoryArray();
                JComboBox<String> comboBox1 = new JComboBox<>(categories);
                comboBox1.setBounds(50, 50, 150, categories.length);
                panel4.add(comboBox1);
                JTextArea textArea4 = new JTextArea();
                textArea4.setEditable(false);
                comboBox1.addActionListener(ee -> {
                    panel4.remove(textArea4);
                    String selectedItem = (String) comboBox1.getSelectedItem();
                    JComboBox<String> comboBox2 = new JComboBox<>(sub);
                    comboBox2.setBounds(50, 50, 150, categories.length);
                    panel4.add(comboBox2);
                    comboBox1.addActionListener(e2 -> {
                        panel4.remove(textArea4);
                        String selectedItem2 = (String) comboBox2.getSelectedItem();
                        textArea4.setText(Search.searchproduct(selectedItem,selectedItem2));
                        panel4.add(textArea4);
                    });



                });

                searchSubategoryFrame.add(panel4);
                searchSubategoryFrame.setVisible(true);




            });


            panel.add(new JLabel("Search"));
            panel.add(searchButton);

            //panel.add(new JTextField());
            //panel.add(new JComboBox<>());


            panel.add(titleButton);
            panel.add(categoryButton);
            panel.add(subcategoryButton);
            searchFrame.add(panel);
            searchFrame.setVisible(true);



        }
        //the manager can search for products if he wants he has the ability
        //to change the characteristics of a product

    }

