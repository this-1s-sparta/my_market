package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

import api.*;

public class ClientCartFrame {
    //this is called by ClientLoggedFrame
    //here we can see the contents of the cart
    public static void CartFrame(Cart c,String name) {
        //creates cart frame
        JFrame cartFrame = new JFrame("Cart\uD83D\uDED2");
        cartFrame.setSize(400, 250);
        cartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cartFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        Color customColor = new Color(150, 0, 180); // colour the frame
        cartFrame.getContentPane().setBackground(customColor);

          //creates cart panel
        JPanel panelcart = new JPanel();
        panelcart.setPreferredSize(new Dimension(380, 1000));
        panelcart.setBackground(customColor);
        panelcart.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        cartFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //if cart is empty shows empty cart message
        if (c.getCart().size()==0){
            JTextArea textArea = new JTextArea();
            textArea.setText("Empty cart!");
        }else{//if cart is not empty

        for (ProductInCart p : c.getCart()) {//pass through the list for each product one by one
            JTextArea textArea = new JTextArea();
            textArea.append(p.getName()+" "+p.getQuantity()+" "+p.getPrice()+"\n");//adds the details of each product in a textArea
            JButton deleteButton = new JButton("Delete");//creates delete button
            JButton changeButton = new JButton("Change");//creates change button
            cartFrame.add(textArea);//adds textarea to frame
            cartFrame.add(deleteButton);//adds delete button to frame
            cartFrame.add(changeButton);//adds change button to frame
            deleteButton.addActionListener(e -> {
                //when delete button is pressed textarea is deleted, the product is removed from the cart and all the buttons relating the product are removed
                textArea.setText("");
                c.cart.remove(p);
                cartFrame.remove(textArea);
                cartFrame.remove(deleteButton);
                cartFrame.remove(changeButton);


            });
            changeButton.addActionListener(e -> {
                //when change button is pressed it gives the opportunity to the user to change the wanted quantity of the product with a pop-up message
                String input = JOptionPane.showInputDialog(null, "Enter a number:", "Number Input", JOptionPane.QUESTION_MESSAGE);
                try {
                    // Convert the input to a number
                    int number = Integer.parseInt(input);
                    AtomicInteger flag=new AtomicInteger();
                    int a=c.AvailableQuantity(p.getName(),flag);
                    if(a>=number){//checks if available quantity is enough to cover the new wanted quantity
                    for(ProductInCart prod:c.getCart()) {//if it is, it finds the product and change the quantity
                        if (prod.getName().equals(p.getName())) {
                            p.setQuantity(number);
                            p.setPrice(prod.getPriceOfOne());
                        }
                    }}
                    else {//if it is not available a pop-up message not enough appears
                        JOptionPane.showMessageDialog(null, "Not enough available quantity", "Error", JOptionPane.INFORMATION_MESSAGE);

                    }
                    textArea.setText(p.getName()+" "+p.getQuantity()+" "+p.getPrice()+"\n");//edits the text area
                } catch (NumberFormatException ee) {
                    // Handle invalid input
                    JOptionPane.showMessageDialog(null, "Invalid number entered. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            });





        }}
        JButton sumButton = new JButton("Sum");//creates button for sum of cart
        sumButton.addActionListener(e -> {//when sum buttons is pressed a pop-up message with the current sum of the cart appears
            double number=c.SumOfCart();
            JOptionPane.showMessageDialog(null, "The sum of yor cart is: " + number, "Sum of Cart", JOptionPane.INFORMATION_MESSAGE);
        });
        JButton endButton = new JButton("Finish Order");//creates button for finishing order
        endButton.addActionListener(e -> {
            //when end button is pressed the AddToCart function is called in order to adjust the quantities of the product and the cart is added to the History.txt
            for (ProductInCart pro: c.cart){
                c.AddToCart(pro);
            }
            History.addToHistory(c, name);
            cartFrame.dispose();//the cart frame is disposed

        });
        //the buttons/panel are added to the frame
        cartFrame.add(sumButton);
        cartFrame.add(endButton);
        cartFrame.add(panelcart);
        cartFrame.setVisible(true);






    }
}
