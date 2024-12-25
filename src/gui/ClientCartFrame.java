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
        panelcart.setBackground(customColor);
        JTextArea textArea = new JTextArea();
        if (c==null){
            textArea.setText("No cart found");
        }else{

        for (ProductInCart p : c.getCart()) {
            textArea.append(p.getName()+" "+p.getQuantity()+" "+p.getPrice()+"\n");
            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(e -> {
                panelcart.remove(textArea);
                c.getCart().remove(p);

            });
            panelcart.add(deleteButton);
            JButton changeButton = new JButton("Change");
            changeButton.addActionListener(e -> {
                JPanel panelchange = new JPanel();
                JLabel label = new JLabel("Give new quantity:");
                JTextField textField = new JTextField();
                panelchange.add(label);
                panelchange.add(textField);
                if(!textField.getText().equals("")){
                int k = Integer.parseInt(textField.getText());
                for(ProductInCart prod:c.getCart()){
                    if(prod.getName().equals(p.getName())){
                        p.setQuantity(k);
                    }
                }}
                cartFrame.add(panelchange);




            });
            JButton sumButton = new JButton("Sum");
            sumButton.addActionListener(e -> {
                JPanel panelsum = new JPanel();
                JTextArea textArea2 = new JTextArea();
                textArea2.setEditable(false);
                textArea2.setText(c.SumOfCart() + "");
                panelsum.add(textArea2);
            });
            JButton endButton = new JButton("Finish Order");
            endButton.addActionListener(e -> {
                for (ProductInCart pro: c.getCart()){
                    c.AddToCart(pro);
                }
                History.addToHistory(c, name);
                cartFrame.dispose();

            });
            cartFrame.add(deleteButton);
            cartFrame.add(changeButton);
            cartFrame.add(sumButton);
            cartFrame.add(endButton);




        }}
        cartFrame.add(panelcart);
        cartFrame.add(textArea);
        cartFrame.setVisible(true);






    }
}
