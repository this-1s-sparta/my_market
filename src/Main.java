import api.*;
import gui.*;
import java.io.File;
import java.io.IOException;
import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {
        try {
            //if the client and manager files don't exist initialization must be done
            File client = new File("client.csv");
            File manager = new File("manager.csv");

            // createNewFile() returns true if the file was created, and false if it hasn't.
            if (client.createNewFile() && manager.createNewFile()) {
                //initialization
                FileManagement.Write("client.csv", 1, false, "user1,password1\nuser2,password2");
                FileManagement.Write("manager.csv", 1, false, "admin1,password1\nadmin2,password2");
            }

        } catch (IOException e) {
            // Handle the exception here, in case of an error!!!
            //for example a file can not be found
            out.println("An error occurred while working with the file.");
            out.println(e.getMessage());
        }
        MainFrame.OpenMainFrame(); //here starts the gui

        //TEST 3 checks that ProductInCart Product and Cart work
        //System.out.println("3rd test");
        //ProductInCart p=new ProductInCart("Νερό Μεταλλικό 1,5lt",1,0.5);
        //Cart mycart=new Cart();
        //mycart.AddToCart(p);
        //ProductInCart p1=new ProductInCart("Καρότα 1kg",4,4);
        //ProductInCart p2=new ProductInCart("Φιλέτο Σολομού 300g",2,24);
        //mycart.AddToCart(p1);
        //mycart.AddToCart(p2);
        //Cart cart2=new Cart();
        //cart2.AddToCart(p2);
        //double price1=p1.getPrice()/p1.getQuantity();
        //p1.setQuantity(6);
        //p1.setPrice(price1*p1.getQuantity());

        //mycart.ChangeCart(p1);
        //History.addToHistory(mycart,"user1");
        //History.addToHistory(cart2,"user1");
        //History.ShowHistory("user1");

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
        //price1=mycart.SumOfCart();
        //System.out.println(price1);

    }
}