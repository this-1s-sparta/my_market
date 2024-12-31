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
        File file=new File("search.txt");
        file.delete();

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
        File file=new File("search.txt");
        file.delete();

    }

    @Test
    public void SearchingProductsBySubcategory() {
        Products p = new Products("Τίτλος2", "Κατηγορία2", "Υποκατηγορία2","Περιγραφή2", "Τιμή2", "Ποσότητα2");
        Products.Add(p);
        Search.searchproduct("","Κατηγορία2","Υποκατηγορία2");
        int result=FileManagement.ThatLine("search.txt","Τίτλος: Τίτλος2");
        assertEquals(result,1);
        result=FileManagement.ThatLine("search.txt","Κατηγορία: Κατηγορία2");
        assertEquals(result,3);
        result=FileManagement.ThatLine("search.txt","Υποκατηγορία: Υποκατηγορία2");
        assertEquals(result,4);
        result=FileManagement.ThatLine("search.txt","Ποσότητα: Ποσότητα2");
        assertEquals(result,6);
        try {
            FileManagement.deleteLines("products.txt", 7); // Call the helper method
        } catch (IOException e) {
            System.err.println("Error deleting lines: " + e.getMessage());
        }
        File file=new File("search.txt");
        file.delete();

    }

    @Test
    public void SearchByNothing(){
        Products p = new Products("Τίτλος3", "Κατηγορία3", "Υποκατηγορία3","Περιγραφή3", "Τιμή3", "Ποσότητα3");
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
        int result=FileManagement.ThatLine("search.txt","Ποσότητα: Ποσότητα3");
        assertEquals(result,i-1);
        try {
            FileManagement.deleteLines("products.txt", 7); // Call the helper method
        } catch (IOException e) {
            System.err.println("Error deleting lines: " + e.getMessage());
        }
        File file=new File("search.txt");
        file.delete();



    }

    @Test
    public void CartTest(){
        Products pro = new Products("Τίτλος4", "Κατηγορία4", "Υποκατηγορία4","Περιγραφή4", "3", "150");
        Products.Add(pro);
        int priorquantity=Integer.parseInt(pro.quantity);
        ProductInCart p=new ProductInCart(" Τίτλος4",2,3);
        Cart c=new Cart();
        c.cart.add(p);
        c.AddToCart(p);
        assertEquals(c.cart.size(),1);
        assertEquals(p.quantity,2);
        double sum=0;
        for(ProductInCart prod:c.cart)
        {
            sum+=prod.price;
        }
        assertEquals(sum,c.SumOfCart(),0);
        try {
            FileManagement.deleteLines("products.txt", 7); // Call the helper method
        } catch (IOException e) {
            System.err.println("Error deleting lines: " + e.getMessage());
        }
        History.addToHistory(c,"abcd");



    }

    @Test
    public void HistoryTest(){
        History.ShowHistory("abcd");
        int i=FileManagement.ThatLine("historyuser.txt","Τίτλος: Τίτλος4");
        assertEquals(i,2);
        i= FileManagement.PartialSearchLine(1,"historyuser.txt","Σύνολο Καλαθιού:");
        int j=FileManagement.LastLine("historyuser.txt");
        assertEquals(j-2,i);
        try {
            FileManagement.deleteLines("History.txt", 8); // Call the helper method
        } catch (IOException e) {
            System.err.println("Error deleting lines: " + e.getMessage());
        }
        File file=new File("historyuser.txt");
        file.delete();
    }

    @Test
    public void StatisticsZero(){
        Products product = new Products("Τίτλος5", "Περιγραφή5", "Κατηγορία5", "Υποκατηγορία5", "Τιμή5", "0 τεμάχια");
        Products.Add(product);
        Statistics.Zero();
        int check=FileManagement.ThatLine("zero.txt","Τίτλος: Τίτλος5");
        assertEquals(check,1);
        try{
            String zeroFile = "zero.txt";
            File zero = new File(zeroFile);
            if (zero.exists()) {
                if (!zero.delete()) {
                    System.err.println("Unable to clear the existing zero.txt file.");
                    return;
                }
            }
            FileManagement.deleteLines("products.txt",7);
        } catch (IOException e) {
            System.err.println("Error deleting lines: " + e.getMessage());
        }
    }

    @Test
    public void StatisticsBest(){
        if(FileManagement.ThatLine("products.txt","Τίτλος: Τίτλος6")==-1) {
            Products pro = new Products("Τίτλος6", "Κατηγορία6", "Υποκατηγορία6", "Περιγραφή6", "3", "150");
            Products.Add(pro);
            ProductInCart p = new ProductInCart(" Τίτλος6", 2, 3);
            Cart c = new Cart();
            c.cart.add(p);
            c.AddToCart(p);
            History.addToHistory(c, "user");
            Statistics.Best();
            int check = FileManagement.ThatLine("best.txt", "Τίτλος: Τίτλος6@1");
            assertEquals(2, check);
            try {
                FileManagement.deleteLines("products.txt", 7);
                FileManagement.deleteLines("History.txt",8);
                String zeroFile = "best.txt";
                File zero = new File(zeroFile);
                if (zero.exists()) {
                    if (!zero.delete()) {
                        System.err.println("Unable to clear the existing best.txt file.");
                    }
                }
            } catch (IOException e) {
                System.err.println("Error deleting lines: " + e.getMessage());
            }

        }

    }

}
