package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import api.*;

public class ClientCartFrame {
    //this is called by ClientLoggedFrame
    public static void CartFrame(Cart c,String name) {
        //here we can see the contents of the cart
        JFrame cartFrame = new JFrame("Cart\uD83D\uDED2");
        cartFrame.setSize(400, 250);
        cartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cartFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        Color customColor = new Color(150, 0, 180); // colour the frame
        cartFrame.getContentPane().setBackground(customColor);


        JPanel panelcart = new JPanel();
        panelcart.setLayout(new GridLayout(5, 2, 10, 10));
        panelcart.setPreferredSize(new Dimension(380, 1000));
        panelcart.setBackground(customColor);
        if (c.getCart().size()==0){
            JTextArea textArea = new JTextArea();
            textArea.setText("Empty cart!");
        }else{

        for (ProductInCart p : c.getCart()) {
            JTextArea textArea = new JTextArea();
            textArea.append(p.getName()+" "+p.getQuantity()+" "+p.getPrice()+"\n");
            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(e -> {
                textArea.setText("");
                c.getCart().remove(p);


            });
            panelcart.add(deleteButton);
            JButton changeButton = new JButton("Change");
            changeButton.addActionListener(e -> {
                String input = JOptionPane.showInputDialog(null, "Enter a number:", "Number Input", JOptionPane.QUESTION_MESSAGE);
                try {
                    // Convert the input to a number
                    int number = Integer.parseInt(input);
                    for(ProductInCart prod:c.getCart()) {
                        if (prod.getName().equals(p.getName())) {
                            p.setQuantity(number);
                            p.setPrice(prod.getPrice());
                        }
                    }
                    textArea.setText(p.getName()+" "+p.getQuantity()+" "+p.getPrice()+"\n");
                } catch (NumberFormatException ee) {
                    // Handle invalid input
                    JOptionPane.showMessageDialog(null, "Invalid number entered. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            });

            cartFrame.add(textArea);
            cartFrame.add(deleteButton);
            cartFrame.add(changeButton);




        }}
        JButton sumButton = new JButton("Sum");
        sumButton.addActionListener(e -> {
            JTextArea textArea2 = new JTextArea();
            textArea2.setEditable(false);
            textArea2.setText(c.SumOfCart() + "");
            panelcart.add(textArea2);
        });
        JButton endButton = new JButton("Finish Order");
        endButton.addActionListener(e -> {
            for (ProductInCart pro: c.getCart()){
                c.AddToCart(pro);
            }
            History.addToHistory(c, name);
            cartFrame.dispose();

        });
        cartFrame.add(sumButton);
        cartFrame.add(endButton);
        cartFrame.add(panelcart);
        cartFrame.setVisible(true);






    }
}
