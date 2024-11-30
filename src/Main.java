import api.*;
import gui.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import static java.lang.System.*;

import static java.lang.System.*;
//* = to import all the files of the package
public class Main {
    public static void main(String[] args) {
        try {
            //if the client and manager files don't exist initialization must be done
            File client = new File("client.csv");
            File manager = new File("manager.csv");
            File products = new File("products.txt");
            //File categories = new File ("categories_subcategories.txt");

            // The createNewFile() returns true if the file was created, and false if it hasn't.
            if (client.createNewFile() && manager.createNewFile()) {
                FileManagement.Write("client.csv", 1, false, "user1,password1\nuser2,password2");
                FileManagement.Write("manager.csv", 1, false, "admin1,password\nadmin2,password2");
            }

        } catch (IOException e) {
            // Handle the exception here, in case of an error!!!
            //for example a file can not be found
            out.println("An error occurred while working with the file.");
            out.println(e.getMessage());
        }

        //this is the first frame
        //it gives the option of log in and sign up
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Welcome to My Market");
            mainFrame.setSize(300, 150); // Set the size of the frame
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // stops running when X is pressed
            mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center
            Color customColor = new Color(100, 30, 200); // color the frame
            mainFrame.getContentPane().setBackground(customColor);

            // Create buttons
            JButton loginButton = new JButton("Login");
            // loginButton.setBackground(new Color(80, 60, 100)); //to put colour on buttons
            JButton signUpButton = new JButton("Sign Up");

            // for login button
            loginButton.addActionListener(e -> {
                LoginFrame.openLoginFrame();
                //when "log in" is pressed the LoginFrame is opened
            });
            //for signup button
            signUpButton.addActionListener(e -> {
                SignUpFrame.openSignUpFrame();
                //when "sign up" is pressed SignUpFrame is opened
            });
            // Add the buttons to the frame
            mainFrame.add(loginButton);
            mainFrame.add(signUpButton);
            // visible frame
            mainFrame.setVisible(true);
        });


        //TESTS 1 checks that the Access methods all work
        //System.out.println("1st test");
        //boolean x=Access.signup("nadia","password123"); //-> works
        //System.out.println(x);
        //x = Access.signup("nadia", "password123"); //-> works
        //System.out.println(x);
        //x = Access.login("nadia", "password123", "client.csv"); //->works
        //System.out.println(x);
        //x=Access.login("nadia","password123","manager.csv"); //->works
        //System.out.println(x);

        //TEST 2 checks that Products (Add and Change) work
        //System.out.println("2nd test");
        //Products.Add("Τίτλος"+"\n"+"Περιγραφή"+"\n"+"Κατηγορία"+"\n"+"Υποκατηγορία"+"\n"+"Τιμή"+"\n"+"Ποσότητα"); //->works
        //Products.Change("Τίτλος: Τίτλος","Τ"+"\n"+"Π"+"\n"+"Κ"+"\n"+"Υ"+"\n"+"Τ"+"\n"+"Π"); //->works

        //TEST 3 checks that ProductInCart Product and Cart work
        //System.out.println("3rd test");
        ProductInCart p=new ProductInCart("Νερό Μεταλλικό 1,5lt",1,0.5);
        Cart mycart=new Cart();
        mycart.AddToCart(p);
        ProductInCart p1=new ProductInCart("Καρότα 1kg",4,4);
        ProductInCart p2=new ProductInCart("Φιλέτο Σολομού 300g",2,24);
        mycart.AddToCart(p1);
        mycart.AddToCart(p2);
        Cart cart2=new Cart();
       cart2.AddToCart(p2);
        double price1=p1.getPrice()/p1.getQuantity();
        p1.setQuantity(6);
        p1.setPrice(price1*p1.getQuantity());

       mycart.ChangeCart(p1);
        History.addToHistory(mycart,"user1");
        History.addToHistory(cart2,"user1");
        History.ShowHistory("user1");

        //TEST 4 checks that Search works
        //System.out.println("4th test");
        //Search.searchproduct("Καρότα 1kg");
        //Search.searchproduct();
        //** for(productinCart p3: mycart.cart)
        // {
        //    System.out.println(p3.name);
        //    System.out.println(p3.quantity);
        //    System.out.println(p3.price);
        // }
        price1=mycart.SumOfCart();
        System.out.println(price1);

        //TEST 5 checks that Statistics (Zero and Best) work
        //System.out.println("5th test");
        Statistics.Zero("products.txt"); //->works
        Statistics.Best();

    }
}