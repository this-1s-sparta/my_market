package api;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class History{



    public static void addToHistory(Cart c,String username)
    {
        String filePath = "History.txt";
        File file = new File(filePath);

        try {
            // Check if the file exists
            if (!file.exists()) {file.createNewFile();}
            int i=1;
            int j=FileManagement.LastLine("History.txt");
            FileManagement.Write("History.txt",j,false,"@"+username);
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            String formattedDate = currentDate.format(formatter);
            FileManagement.Write("History.txt",j,false,"Date:"+formattedDate);
            for(ProductInCart p:c.cart)
            {
                FileManagement.Write("History.txt",j+i,false,"Τίτλος: "+p.name);
                FileManagement.Write("History.txt",j+i+1,false,"Ποσότητα: "+p.quantity);
                FileManagement.Write("History.txt",j+i+2,false,"Τιμή: "+p.price);
                i+=3;
            }
            FileManagement.Write("History.txt",j+i,false,"Σύνολο Καλαθιού: "+c.SumOfCart());
        } catch (IOException e) {
            // Handle exceptions
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void ShowHistory(String username)
    {


    }




}
