package api;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Cart {
    ArrayList<productinCart> cart;
    Cart()
    {
        cart = new ArrayList<>();
    }

    public void AddToCart(productinCart p)
    {
        int a;
        AtomicInteger flag=new AtomicInteger();
        a=AvailableQuantity(p.name,flag);
        if(p.quantity<=a)
        {
            cart.add(p);
            int newq;
            int i;
            newq=a-p.quantity;
            i=FileManagement.ThatLine("products.txt","Τίτλος: "+p.name);
            if (flag.get()==0) {
                FileManagement.Write("products.txt", i+5, true, "Ποσότητα: " + newq + "kg");
            }
            else
                FileManagement.Write("products.txt", i+5, true, "Ποσότητα: " + newq + " pieces");

        }
        else
            System.out.println("Available quantity not enough");
    }

    public void DeleteFromCart(productinCart p)
    {
        AtomicInteger flag=new AtomicInteger();
        int newq=p.quantity+AvailableQuantity(p.name,flag),i;
        cart.remove(p);
        i=FileManagement.ThatLine("products.txt","Τίτλος: "+p.name);
        if (flag.get()==0) {
            FileManagement.Write("products.txt", i+5, true, "Ποσότητα: " + newq + "kg");
        }
        else
            FileManagement.Write("products.txt", i+5, true, "Ποσότητα: " + newq + " pieces");

    }

    public void ChangeCart(productinCart p)
    {
        for(productinCart p1 : cart)
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
                        FileManagement.Write("products.txt", i, true, "Ποσότητα: " + newq + " pieces");

                }
                else
                    System.out.println("Available quantity not enough");
            }
        }
    }

    public double SumOfCart()
    {
        double sum = 0;
        for(productinCart p1 : cart)
        {
            sum += p1.price;
        }
        return sum;
    }

    //returns available quantity in int cast for the product with the given name from product.txt file
    //if flag==1 product in pieces
    //if flag==0 product in kilograms
    public int AvailableQuantity(String name,AtomicInteger flag)
    {
        int i;
        i=FileManagement.ThatLine("products.txt","Τίτλος: "+name);
        String filePath = "products.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String currentLine;
            int currentLineNumber = 1;
            while ((currentLine = br.readLine()) != null || currentLineNumber <= i+5) {
                if (currentLineNumber == i+5) {
                    String[] words = currentLine.split("\\s+");
                    if (words.length>=3){
                        flag.set(1);
                        String quantityword = words[words.length - 2];
                        return Integer.parseInt(quantityword);
                    }
                    else if(words.length<=2){
                        flag.set(0);
                        String quantityword = words[words.length-1];
                        char let[]=quantityword.toCharArray();
                        char[] arr;
                        int count=0;
                        for(int j = 0; let[j]!='k'; j++)
                        {
                            count++;
                        }
                        arr=new char[count];
                        for(int j = 0; let[j]!='k'; j++)
                        {
                            arr[j]=let[j];
                        }
                        quantityword=new String(arr);
                        return Integer.parseInt(quantityword);
                    }
                }
                currentLineNumber++;
            }
        }catch (IOException e) {
            e.printStackTrace();

        }
        return -1;
    }












}
