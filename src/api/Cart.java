package api;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class Cart {
    public ArrayList<ProductInCart> cart;

    public Cart()//constructor for object cart
    {
        cart = new ArrayList<>();

    }

    public ArrayList<ProductInCart> getCart() {//returns the list of cart
        return cart;
    }

    public void AddToCart(ProductInCart p)//when order is finished proceeds to make the right adjusts to products.txt
    {
        int a;
        AtomicInteger flag=new AtomicInteger();
        a=AvailableQuantity(p.name,flag);//a is the current available quantity, flag=0 if quantity is in kg, flag=1 if quantity is in pieces
        if(p.quantity<=a) //if available quantity is enough
        {
            int newq;
            int i;
            newq=a-p.quantity;//new quantity = available quantity - quantity that is purchased
            i=FileManagement.ThatLine("products.txt","Τίτλος:"+p.name);//finds the first line of the product the one with its title
            if (flag.get()==0) {
                FileManagement.Write("products.txt", i+5, true, "Ποσότητα: " + newq + "kg");//edits the available quantity(kg) in products.txt
            }
            else
                FileManagement.Write("products.txt", i+5, true, "Ποσότητα: " + newq + " τεμάχια");//edits the available quantity(pieces) in products.txt

        }


    }

    public double SumOfCart()
    {
        double sum = 0;
        for(ProductInCart p1 : cart)//goes through the list of the cart and adds all the prices
        {
            sum += p1.price;
        }
        return sum;//returns the sum of cart
    }

    //returns available quantity in int cast for the product with the given name from product.txt file
    //if flag==1 product in pieces
    //if flag==0 product in kilograms
        public int AvailableQuantity(String name, AtomicInteger flag) {
            int i;
            i = FileManagement.ThatLine("products.txt", "Τίτλος:" + name); // Locate the product title line
            String filePath = "products.txt";

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String currentLine;
                int currentLineNumber = 1;

                // Read file line by line
                while ((currentLine = br.readLine()) != null) {
                    if (currentLineNumber == i +5) {
                        if (currentLine.trim().isEmpty()) {
                            flag.set(5); // Empty line case
                            return -1;  // Indicate error or empty value
                        }

                        String[] words = currentLine.trim().split("\\s+");
                        if (words.length >= 3) {
                            flag.set(1); // Multi-word line with a clear quantity
                            String quantityWord = words[words.length - 2]; // Second last word
                            return Integer.parseInt(quantityWord);
                        } else if (words.length == 2) {
                            flag.set(0); // Line with minimal words->quantity in kgs
                            String quantityWord = words[words.length - 1].replace("kg", ""); // Handle "kg"
                            return Integer.parseInt(quantityWord);
                        } else {
                            flag.set(0); // Unexpected format
                            return -1;
                        }
                    }
                    currentLineNumber++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format in line: " + e.getMessage());
            }

            return -1; // Default return value for errors or not found
        }
    }










