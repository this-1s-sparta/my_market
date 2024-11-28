package api;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class History{



        public static void addToHistory(Cart c,String username)
        {
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

        }




}
