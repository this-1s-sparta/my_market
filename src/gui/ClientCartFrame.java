package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

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
        panelcart.setPreferredSize(new Dimension(380, 1000));
        panelcart.setBackground(customColor);
        panelcart.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        cartFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        if (c.getCart().size()==0){
            JTextArea textArea = new JTextArea();
            textArea.setText("Empty cart!");
        }else{

        for (ProductInCart p : c.getCart()) {
            JTextArea textArea = new JTextArea();
            textArea.append(p.getName()+" "+p.getQuantity()+" "+p.getPrice()+"\n");
            JButton deleteButton = new JButton("Delete");
            JButton changeButton = new JButton("Change");
            cartFrame.add(textArea);
            cartFrame.add(deleteButton);
            cartFrame.add(changeButton);
            deleteButton.addActionListener(e -> {
                textArea.setText("");
                c.cart.remove(p);
                cartFrame.remove(textArea);
                cartFrame.remove(deleteButton);
                cartFrame.remove(changeButton);


            });
            changeButton.addActionListener(e -> {
                String input = JOptionPane.showInputDialog(null, "Enter a number:", "Number Input", JOptionPane.QUESTION_MESSAGE);
                try {
                    // Convert the input to a number
                    int number = Integer.parseInt(input);
                    AtomicInteger flag=new AtomicInteger();
                    int a=c.AvailableQuantity(p.getName(),flag);
                    if(a>=number){
                    for(ProductInCart prod:c.getCart()) {
                        if (prod.getName().equals(p.getName())) {
                            p.setQuantity(number);
                            p.setPrice(prod.getPriceOfOne());
                        }
                    }}
                    else {
                        JOptionPane.showMessageDialog(null, "Not enough available quantity", "Error", JOptionPane.INFORMATION_MESSAGE);

                    }
                    textArea.setText(p.getName()+" "+p.getQuantity()+" "+p.getPrice()+"\n");
                } catch (NumberFormatException ee) {
                    // Handle invalid input
                    JOptionPane.showMessageDialog(null, "Invalid number entered. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            });





        }}
        JTextArea textArea2 = new JTextArea();
        JButton sumButton = new JButton("Sum");
        sumButton.addActionListener(e -> {
            double number=c.SumOfCart();
            JOptionPane.showMessageDialog(null, "The sum of yor cart is: " + number, "Sum of Cart", JOptionPane.INFORMATION_MESSAGE);
        });
        JButton endButton = new JButton("Finish Order");
        endButton.addActionListener(e -> {
            for (ProductInCart pro: c.cart){
               // textArea2.append(pro.getName()+" "+pro.getQuantity()+" "+pro.getPrice()+"\n");
                c.AddToCart(pro);
            }
            History.addToHistory(c, name);
            cartFrame.dispose();

        });
        //cartFrame.add(textArea2);
        cartFrame.add(sumButton);
        cartFrame.add(endButton);
        cartFrame.add(panelcart);
        cartFrame.setVisible(true);






    }
}
