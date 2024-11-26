import java.io.File;
import java.io.IOException;
import api.*;

import static java.lang.System.*;
//* = to import all the files of the package
public class Main {
    public static void main(String[] args) {
        try {
            //if the client and manager files don't exist initialization must be done
            File client = new File("client.csv");
            File manager = new File ("manager.csv");
            File products = new File("products.txt");
            //File categories = new File ("categories_subcategories.txt");

            // The createNewFile() returns true if the file was created, and false if it hasn't.
            if (client.createNewFile() && manager.createNewFile()) {
                FileManagement.Write("client.csv", 1, false, "user1,password1\nuser2,password2");
                FileManagement.Write("manager.csv",1,false,"admin1,password\nadmin2,password2");
            }

        } catch (IOException e) {
            // Handle the exception here, in case of an error!!!
            //for example a file can not be found
            out.println("An error occurred while working with the file.");
            out.println(e.getMessage());
        }

        //gui is needed for this to work
        //if the user chooses to sign up:
        //  input   name
        //          lastname
        //          username
        //          password
        //  if (boolean check=Access.signup == true)
        //      give CLIENT access
        //if the user chooses to log in:
        //  input   username
        //          password
        //check in both files
        //boolean check1= Access.login(username,password."manger.cvs")
        //if true give  MANAGER access
        //else
        //boolean check2=Access.login(username,password,"client.cvs")
        //if true give CLIENT access
        //if check1 AND ckeck2 == false
        //print wrong input message


        //for now only allow to login

        //name and password will be input from the gui
        //for now change manually here :)
        String username="user1";
        String password="password1";
        boolean check1=Access.login(username,password,"client.csv");
        boolean check2=Access.login(username,password,"manager.csv");
        if (check1) {
            client user = new client(username, password);
        }
        else if (check2) {
            manager user = new manager(username, password);
        }
        else {
            out.println("Wrong username or password");
        }

        //TESTS 1-4 check that the Access methods all work
        //boolean x=Access.signup("nadia","password123"); //-> works
        // test 2 boolean x=Access.signup("nadia","password123"); //-> works
        // test 3 boolean x=Access.login("nadia","password123","client.csv"); //->works
        // test 4 boolean x=Access.login("nadia","password123","manager.csv"); //->works

        //TEST 5 checks that Products.Add and Products.Change work
        //TEST 5 checks that Products methods work
        //Products.Add("Τίτλος"+"\n"+"Περιγραφή"+"\n"+"Κατηγορία"+"\n"+"Υποκατηγορία"+"\n"+"Τιμή"+"\n"+"Ποσότητα"); //->works
        //Products.Change("Τίτλος: Τίτλος","Τ"+"\n"+"Π"+"\n"+"Κ"+"\n"+"Υ"+"\n"+"Τ"+"\n"+"Π"); //->works

        //test 6
        //TEST 6 checks that Statistics.Zero works
        Statistics.Zero("products.txt"); //->works
    }
}