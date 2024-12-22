package api;
import org.junit.Before;
import org.junit.Test; // Correct JUnit import

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*; // Correct static import for assertions

public class Tests {
    @Test
    public void testLogin() {
        boolean result = Access.login("user1", "password1", "client.csv");
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    @Test
    public void testSignup() {
        boolean result = Access.signup("user1", "password1");
        boolean expResult = false;
        assertEquals(expResult, result);
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
}
