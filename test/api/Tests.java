package api;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import static org.junit.Assert.*;

//Make sure that the tests run more than one time!!!
public class Tests {
    //to run the 4 login and sign up test we must first check
    //if the program has already been initialized
    //to do that we check if "clients.txt" already exists
    @Test
    public void testLoginTrue() {
        File file = new File("client.txt");
        // Check if the file exists
        if (file.exists()) {
            boolean result = Access.login("user1", "password1", "client.csv");
            boolean expResult = true;
            assertEquals(expResult, result);
        }
    }

    @Test
    public void testLoginFalse() {
        File file = new File("client.txt");
        if (file.exists()) {
            boolean result = Access.login("nadia", "password2", "client.csv");
            boolean expResult = false;
            assertEquals(expResult, result);
        }
    }

    @Test
    public void testSignupTrue() {
        File file = new File("client.txt");
        // Check if the file exists
        if (file.exists()) {
            boolean result = Access.signup("nadia", "password");
            boolean expResult = true;
            assertEquals(expResult, result);
            try {
                FileManagement.deleteLines("client.csv", 1); // Call the helper method
            } catch (IOException e) {
                System.err.println("Error deleting lines: " + e.getMessage());
            }
        }
    }

    @Test
    public void testSignupFalse() {
        File file = new File("client.txt");
        if (file.exists()) {
            boolean result = Access.signup("user1", "password1");
            boolean expResult = false;
            assertEquals(expResult, result);
        }
    }

    @Test
    public void AddingChangingProducts() {
        Products product = new Products("Τίτλος", "Περιγραφή", "Κατηγορία", "Υποκατηγορία", "Τιμή", "Ποσότητα");
        Products.Add(product);
        int check=FileManagement.LastLine("products.txt");
        int result=FileManagement.ThatLine("products.txt","Ποσότητα: Ποσότητα");
        assertEquals(result,check-1);//I check if the last line is what it was supposed to be

        Products.Change("Τίτλος: Τίτλος", new Products("Τ", "Π", "Κ", "Υ", "Τ", "Π"));
        result=FileManagement.ThatLine("products.txt","Ποσότητα: Ποσότητα");
        //the line is not found so it returns -1
        assertNotEquals(result,check-1);
        //If the first test didn't fail after changing them they must NOT be equal

        //After checking remember to delete the lines that where only written to test
        //this is done so that the test will always work and not only on the initialization
        try {
            FileManagement.deleteLines("products.txt", 7); // Call the helper method
        } catch (IOException e) {
            System.err.println("Error deleting lines: " + e.getMessage());
        }
    }

    @Test
    public void SearchingProductsByTitle() {
        Products p = new Products("Κασέρι","Αγελαδινό","Κίτρινο Τυρί","Κίτρινο τυρί","2,00","10");
        Products.Add(p);
        Search.searchproduct("Κασέρι",null,null);
        int result=FileManagement.ThatLine("search.txt","Τίτλος: Κασέρι");
        assertEquals(result,1);
        result=FileManagement.ThatLine("search.txt","Ποσότητα: 10");
        assertEquals(result,6);
        try {
            FileManagement.deleteLines("products.txt", 7); // Call the helper method
        } catch (IOException e) {
            System.err.println("Error deleting lines: " + e.getMessage());
        }

    }

    @Test
    public void SearchingProductsByCategory() {
        Products p = new Products("Τίτλος1", "Κατηγορία1", "Υποκατηγορία1","Περιγραφή1", "Τιμή1", "Ποσότητα1");
        Products.Add(p);
        Search.searchproduct("","Κατηγορία1",null);
        int result=FileManagement.ThatLine("search.txt","Τίτλος: Τίτλος1");
        assertEquals(result,1);
        result=FileManagement.ThatLine("search.txt","Κατηγορία: Κατηγορία1");
        assertEquals(result,3);
        result=FileManagement.ThatLine("search.txt","Ποσότητα: Ποσότητα1");
        assertEquals(result,6);
        try {
            FileManagement.deleteLines("products.txt", 7); // Call the helper method
        } catch (IOException e) {
            System.err.println("Error deleting lines: " + e.getMessage());
        }

    }

    @Test
    public void SearchingProductsBySubcategory() {
        Products p = new Products("Τίτλος1", "Κατηγορία1", "Υποκατηγορία1","Περιγραφή1", "Τιμή1", "Ποσότητα1");
        Products.Add(p);
        Search.searchproduct("","Κατηγορία1","Υποκατηγορία1");
        int result=FileManagement.ThatLine("search.txt","Τίτλος: Τίτλος1");
        assertEquals(result,1);
        result=FileManagement.ThatLine("search.txt","Κατηγορία: Κατηγορία1");
        assertEquals(result,3);
        result=FileManagement.ThatLine("search.txt","Υποκατηγορία: Υποκατηγορία1");
        assertEquals(result,4);
        result=FileManagement.ThatLine("search.txt","Ποσότητα: Ποσότητα1");
        assertEquals(result,6);
        try {
            FileManagement.deleteLines("products.txt", 7); // Call the helper method
        } catch (IOException e) {
            System.err.println("Error deleting lines: " + e.getMessage());
        }

    }

    @Test
    public void SearchByNothing(){
        Products p = new Products("Τίτλος1", "Κατηγορία1", "Υποκατηγορία1","Περιγραφή1", "Τιμή1", "Ποσότητα1");
        Products.Add(p);
        Search.searchproduct("",null,null);
        try(BufferedReader br=new BufferedReader(new FileReader("products.txt"))){
            String current=br.readLine();
            int result=FileManagement.ThatLine("search.txt",current);
            assertEquals(result,1);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int i=FileManagement.LastLine("search.txt");
        int result=FileManagement.ThatLine("search.txt","Ποσότητα: Ποσότητα1");
        assertEquals(result,i-1);
        try {
            FileManagement.deleteLines("products.txt", 7); // Call the helper method
        } catch (IOException e) {
            System.err.println("Error deleting lines: " + e.getMessage());
        }



    }


}
