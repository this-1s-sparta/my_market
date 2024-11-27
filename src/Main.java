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
            Color customColor = new Color(100, 30, 200); // colour the frame
            mainFrame.getContentPane().setBackground(customColor);

            // Create buttons
            JButton loginButton = new JButton("Login");
            // loginButton.setBackground(new Color(80, 60, 100)); //to put colour on buttons
            JButton signUpButton = new JButton("Sign Up");

            // for login button
            loginButton.addActionListener(e -> {
                LoginFrame.openLoginFrame(); // Open Login frame or perform Login action
                // this is located on the gui file
            });
            //for signup button
            signUpButton.addActionListener(e -> {
                SignUpFrame.openSignUpFrame(); // Open Sign Up frame or perform Sign Up action
                //this is located on the gui file
            });
            // Add the buttons to the frame
            mainFrame.add(loginButton);
            mainFrame.add(signUpButton);
            // visible frame
            mainFrame.setVisible(true);
        });

        //TESTS 1-4 check that the Access methods all work
        //boolean x=Access.signup("nadia","password123"); //-> works
        // test 2
        //boolean x = Access.signup("nadia", "password123"); //-> works
        // test 3 boolean y = Access.login("nadia", "password123", "client.csv"); //->works
        // test 4 boolean x=Access.login("nadia","password123","manager.csv"); //->works
        //System.out.println(y);

        //TEST 5 checks that Products.Add and Products.Change work
        //TEST 5 checks that Products methods work
        //Products.Add("Τίτλος"+"\n"+"Περιγραφή"+"\n"+"Κατηγορία"+"\n"+"Υποκατηγορία"+"\n"+"Τιμή"+"\n"+"Ποσότητα"); //->works
        //Products.Change("Τίτλος: Τίτλος","Τ"+"\n"+"Π"+"\n"+"Κ"+"\n"+"Υ"+"\n"+"Τ"+"\n"+"Π"); //->works

        //test 6
        //TEST 6 checks that Statistics.Zero works
        //Statistics.Zero("products.txt"); //->works
    }
}