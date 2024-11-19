import java.io.File;
import java.io.IOException;
import api.*;
//* = to import all the files of the package
/**
 * Το πρόγραμμά σας πρέπει να έχει μόνο μία main, η οποία πρέπει να είναι η παρακάτω.
 * <p>
 * <p>
 * ************* ΜΗ ΣΒΗΣΕΤΕ ΑΥΤΗ ΤΗΝ ΚΛΑΣΗ ************
 */
public class Main {
    public static void main(String[] args) {
        try {
            //if the pelates and diaxiristes files dont exist initialization must be done
            File pelates = new File("pelates.csv");
            File diaxiristes = new File ("diaxiristes.csv");
            // The createNewFile() returns true if the file was created, and false if it hasn't.
            if (pelates.createNewFile() && diaxiristes.createNewFile()) {
                FileManagement.Write("pelates.csv", 1, false, "user1,password1\nuser2,password2");
                FileManagement.Write("diaxiristes.csv",1,false,"admin1,password\nadmin2,password2");
            }
            //File prod = new File("products.txt");
            //File cat = new File ("categories_subcategories.txt");
            //
        } catch (IOException e) {
            // Handle the exception here, incase of an error!!!
            //for example a file can not be found
            System.out.println("An error occurred while working with the file.");
            System.out.println(e.getMessage());
        }

        //TESTS 1-4 check that the Access methods all work
        // test 1 boolean x=Access.signup("nadia","password123"); -> works
        // test 2 boolean x=Access.signup("nadia","password123"); -> works
        // test 3 boolean x=Access.login("nadia","password123","pelates.csv"); ->works
        // test 4 boolean x=Access.login("nadia","password123","diaxiristes.csv"); ->works
    }
}
