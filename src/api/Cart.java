package api;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class Cart {
    public ArrayList<ProductInCart> cart;

    public Cart()
    {
        cart = new ArrayList<>();

    }

    public ArrayList<ProductInCart> getCart() {
        return cart;
    }

    public void AddToCart(ProductInCart p)
    {
        int a;
        AtomicInteger flag=new AtomicInteger();
        a=AvailableQuantity(p.name,flag);
        if(p.quantity<=a)
        {
            int newq;
            int i;
            newq=a-p.quantity;
            i=FileManagement.ThatLine("products.txt","Τίτλος: "+p.name);
            if (flag.get()==0) {
                FileManagement.Write("products.txt", i+5, true, "Ποσότητα: " + newq + "kg");
            }
            else
                FileManagement.Write("products.txt", i+5, true, "Ποσότητα: " + newq + " τεμάχια");

        }


    }

    /*public void DeleteFromCart(ProductInCart p)
    {
        AtomicInteger flag=new AtomicInteger();
        int newq=p.quantity+AvailableQuantity(p.name,flag),i;
        //cart.remove(p);
        i=FileManagement.ThatLine("products.txt","Τίτλος: "+p.name);
        if (flag.get()==0) {
            FileManagement.Write("products.txt", i+5, true, "Ποσότητα: " + newq + "kg");
        }
        else
            FileManagement.Write("products.txt", i+5, true, "Ποσότητα: " + newq + " pieces");

    }

    public int ChangeCart(ProductInCart p)
    {
        for(ProductInCart p1 : cart)
        {
            if(p1.name.equals(p.name))
            {
                int a;
                AtomicInteger flag=new AtomicInteger();
                a=AvailableQuantity(p.name,flag);
                if(p.quantity<=a+p1.quantity)
                {
                    p1.price = p.price;
                    int newq=a-p.quantity+p1.quantity;
                    p1.quantity = p.quantity;
                    int i=FileManagement.ThatLine("products.txt",p.name);
                    if (flag.get()==0)
                        FileManagement.Write("products.txt", i, true, "Ποσότητα: " + newq + "kg");
                    else
                        FileManagement.Write("products.txt", i, true, "Ποσότητα: " + newq + " τεμάχια");

                }
                else
                return 0;
            }
        }
        return 1;
    }*/

    public double SumOfCart()
    {
        double sum = 0;
        for(ProductInCart p1 : cart)
        {
            sum += p1.price;
        }
        return sum;
    }

    //returns available quantity in int cast for the product with the given name from product.txt file
    //if flag==1 product in pieces
    //if flag==0 product in kilograms
        public int AvailableQuantity(String name, AtomicInteger flag) {
            int i;
            i = FileManagement.ThatLine("products.txt", "Τίτλος: " + name); // Locate the product title line
            String filePath = "products.txt";

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String currentLine;
                int currentLineNumber = 1;

                // Read file line by line
                while ((currentLine = br.readLine()) != null) {
                    if (currentLineNumber == i + 5) { // Look for the specific line (i + 5)
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
                            flag.set(0); // Line with minimal words
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










