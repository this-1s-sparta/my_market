package api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//MANAGER ONLY
public class Statistics {
    public static void Zero(String filename){
        int last=FileManagement.LastLine(filename);
        int line=FileManagement.FromThatLine(0,filename,"Ποσότητα: 0 τεμάχια");
        while (line!=-1 && line <= last){
            FileManagement.PrintLine(line-5,filename);
            line++;
            line=FileManagement.FromThatLine(line, filename,"Ποσότητα: 0 τεμάχια");
        }

        line=FileManagement.FromThatLine(0,filename,"Ποσότητα: 0kg");
        while (line!=-1 && line <= last){
            FileManagement.PrintLine(line-5,filename);
            line++;
            line=FileManagement.FromThatLine(line, filename,"Ποσότητα: 0kg");
        }
    }

    //NOT TESTED!!!!!!!!
    //Best will look in the history.txt file for the most used products
    //this will be done by searching for every product on "products.txt"
    //the Best method is called and a "best.cvs" file is created
    //if it already exists its contents will be deleted
    //for every product on "products.txt" a search will be done on "history.txt"
    //the contents of "best.cvs" will look like:
    //      title of product,times found on history
    public static void Best(String history,String products){
        File file = new File("best.csv");
        try {
            // Check if the file exists
            if (file.exists()) {
                // Delete the file
                if (!file.delete()) {
                    System.err.println("Failed to delete existing best.csv file.");
                    return; // Stop further execution
                }
            }
            // Create a new file
            if (!file.createNewFile()) {
                System.err.println("Failed to create new best.csv file.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred while handling best.csv: " + e.getMessage());
        }
        //now I am sure that I have a file called "best.csv"
        int Plines=FileManagement.LastLine(products);
        int Hlines=FileManagement.LastLine(history);
        //I check how many line products.txt and history.txt has
        //I know that line 1 has the title of a product
        int ProdStart=1;
        String search = "";
        for ( ; ProdStart <= Plines ; ProdStart += 7){
            //the for loop gives search the title of each product of products.txt
            try {
                search = Files.readAllLines(Paths.get(products)).get(ProdStart);
            } catch (IOException | IndexOutOfBoundsException e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
            //then the while loop searches of "search" on history.txt and counts how many times it is found
            int HistStart=1;
            int count=0;
            while (HistStart <= Hlines) {
                int nextLine = FileManagement.FromThatLine(HistStart, history, search);
                if (nextLine == -1) {
                    break; // No more occurrences
                }
                count++; // Increment the count for each occurrence
                HistStart = nextLine; // Start from the next line after the found content
            }

            int line=Position("best.csv",count);
            FileManagement.Write("best.csv",line,false ,search+","+count + "\n");
        }
        //after we exit the for loop the best.csv file has the form that we want it to have and it is shorted
        //to find the most shown products we only need to call it and check the "best.cvs" file
    }

    //Position will be called by Best() and is used to tell us where a new product should be put
    //according to the short that we have selected
    public static int Position(String filename, int count) {
        int position = 1; // Start at line 1
        try (BufferedReader best = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = best.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int existingCount = Integer.parseInt(parts[1]);
                    // If the new count is bigger, return the current position
                    if (count > existingCount) {
                        return position;
                    }
                }
                position++; // Move to the next line
            }
        } catch (IOException e) {
            System.err.println("Error reading the file " + filename + ": " + e.getMessage());
        }
        // If no larger count is found, the position is at the end
        return position;
    }
}
