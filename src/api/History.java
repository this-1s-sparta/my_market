package api;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
            j=j+1;
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            String formattedDate = currentDate.format(formatter);
            FileManagement.Write("History.txt",j,false,"Date:"+formattedDate);
            for(ProductInCart p:c.cart)
            {
                FileManagement.Write("History.txt",j+i,false,"Τίτλος:"+p.name);
                FileManagement.Write("History.txt",j+i+1,false,"Ποσότητα: "+p.quantity);
                FileManagement.Write("History.txt",j+i+2,false,"Τιμή: "+p.price);
                i+=3;
            }
            FileManagement.Write("History.txt",j+i,false,"Σύνολο Καλαθιού: "+c.SumOfCart());
            FileManagement.Write("History.txt",j+i+1,false,"\n");
        } catch (IOException e) {
            // Handle exceptions
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void ShowHistory(String username)
    {
        try {
            File historyuser = new File("historyuser.txt");
            if (historyuser.exists()) {
                historyuser.delete();

            }
            if (!historyuser.createNewFile()) {
                System.err.println("Unable to create best.txt file.");
                return;
            }
        } catch (IOException e) {
            System.err.println("Error creating or clearing best.txt: " + e.getMessage());
            return;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader("History.txt")))
        {
            String line;
            int i=FileManagement.LastLine("History.txt");
            int k=1;
            for(int j=0;j<i;j++)
            {
                line = reader.readLine();
                if (line != null && line.equals("@" + username)) {
                    while ((line = reader.readLine()) != null) {  // check that it's not null
                        if (!line.equals("")) {
                            FileManagement.Write("historyuser.txt",k,false,line);
                            k++;

                        }
                        else{
                            FileManagement.Write("historyuser.txt",k,false,"");
                            k++;
                            break;
                        }


                    }

                }
            }
        }

         catch (IOException e) {
            e.printStackTrace();
        }



    }




}
