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
            // Check if the file exists, if it does not it creates a new one
            if (!file.exists()) {file.createNewFile();}
            int i=1;
            int j=FileManagement.LastLine("History.txt");//number of last line of products.txt
            FileManagement.Write("History.txt",j,false,"@"+username);//writes in the last available line of products.txt @ followed by the current username of the user
            j=j+1;//moves the indicator of the last line
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            String formattedDate = currentDate.format(formatter);
            FileManagement.Write("History.txt",j,false,"Date:"+formattedDate);//writes the date tha the cart was purchased in a specific format
            for(ProductInCart p:c.cart)//writes the title, quantity and price of each product in the cart
            {
                FileManagement.Write("History.txt",j+i,false,"Τίτλος:"+p.name);
                FileManagement.Write("History.txt",j+i+1,false,"Ποσότητα: "+p.quantity);
                FileManagement.Write("History.txt",j+i+2,false,"Τιμή: "+p.price);
                i+=3;
            }
            FileManagement.Write("History.txt",j+i,false,"Σύνολο Καλαθιού: "+c.SumOfCart());//writes sum of the cart at the end
            FileManagement.Write("History.txt",j+i+1,false,"\n");//leaves a space line
        } catch (IOException e) {
            // Handle exceptions
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void ShowHistory(String username)//transfers the history of a specific user in the file historyuser.txt
    {
        try {
            File historyuser = new File("historyuser.txt");
            if (historyuser.exists()) {//checks if file exists and deletes it
                historyuser.delete();

            }
            if (!historyuser.createNewFile()) {//chech for error
                System.err.println("Unable to create best.txt file.");
                return;
            }
        } catch (IOException e) {//check for error
            System.err.println("Error creating or clearing best.txt: " + e.getMessage());
            return;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader("History.txt")))//opens file for reading
        {
            String line;
            int i=FileManagement.LastLine("History.txt");//finds numberr of last line of History.txt
            int k=1;//number of available line of historyuser.txt
            for(int j=0;j<i;j++)//loop until we reach last line
            {
                line = reader.readLine();//reads current line and stores it
                if (line != null && line.equals("@" + username)) {//check if line does not equal the username or if it is not null
                    while ((line = reader.readLine()) != null) {  // check that it's not null
                        if (!line.equals("")) {//if it is not a space line
                            FileManagement.Write("historyuser.txt",k,false,line);//copies each cart of the logged user line by line from the file History.txt to the temporary file historyuser.txt
                            k++;

                        }
                        else{
                            FileManagement.Write("historyuser.txt",k,false,"");//writes a space line between each order
                            k++;
                            break;//exits the while loop
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
