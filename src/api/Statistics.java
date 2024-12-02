package api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//MANAGER ONLY
public class Statistics {
    /*zero creates zero.txt and puts the name of every product with zero quantity available inside
    when called it deletes the previous zero.txt file id ti exists else it creates a new one
    then it checks the products.txt file and if something has zero quantity
    it puts the title of it in the zero.txt file */
    public static void Zero(){
        String filename = "products.txt";
        try {
            String zeroFile = "zero.txt";
            File zero = new File(zeroFile);
            if (zero.exists()) {
                if (!zero.delete()) {
                    System.err.println("Unable to clear the existing best.txt file.");
                    return;
                }
            }
            if (!zero.createNewFile()) {
                System.err.println("Unable to create zero.txt file.");
                return;
            }
        } catch (IOException e) {
            System.err.println("Error creating or clearing zero.txt: " + e.getMessage());
            return;
        }
        String content=null;
        int last=FileManagement.LastLine(filename);
        int line=FileManagement.FromThatLine(0,filename,"Ποσότητα: 0 τεμάχια");
        while (line!=-1 && line <= last){
            content=FileManagement.GetLine(line-5,filename);
            FileManagement.Write("zero.txt",FileManagement.LastLine("zero.txt"),false,content);
            line++;
            line=FileManagement.FromThatLine(line, filename,"Ποσότητα: 0 τεμάχια");
        }
        line=FileManagement.FromThatLine(0,filename,"Ποσότητα: 0kg");
        while (line!=-1 && line <= last){
            content=FileManagement.GetLine(line-5,filename);
            FileManagement.Write("zero.txt",FileManagement.LastLine("zero.txt"),false,content);
            line++;
            line=FileManagement.FromThatLine(line, filename,"Ποσότητα: 0kg");
        }
    }
    /*Best will look in the history.txt file for the most used products
    this will be done by searching for every product on "products.txt"
    the Best method is called and a "best.txt" file is created
    if it already exists its contents will be deleted
    for every product on "products.txt" a search will be done on "history.txt"
    the contents of "best.cvs" will look like:
          title of product,times found on history */
    public static void Best() {
        String products = "products.txt";
        String history = "History.txt";
        String bestFile = "best.txt";
        // Create or clear the best.txt file at the beginning
        try {
            File best = new File(bestFile);
            if (best.exists()) {
                if (!best.delete()) {
                    System.err.println("Unable to clear the existing best.txt file.");
                    return;
                }
            }
            if (!best.createNewFile()) {
                System.err.println("Unable to create best.txt file.");
                return;
            }
        } catch (IOException e) {
            System.err.println("Error creating or clearing best.txt: " + e.getMessage());
            return;
        }
        try (BufferedReader readerprod = new BufferedReader(new FileReader(products))) {
            //this leaves and empty line so that I can write on line-1 at any time without an error
            FileManagement.Write(bestFile, 1, false, " ");
            String productLine;
            int times = 0;
            int Last = FileManagement.LastLine(products);
            // Reading each line of products.txt
            while (times <= Last) {
                productLine = readerprod.readLine();
                if (productLine == null) {
                    break; // Exit if there are no more lines to process
                }
                times++;
                if (times % 7 == 1) { // Process only every 7th line
                    try (BufferedReader readerhist = new BufferedReader(new FileReader(history))) {
                        String historyLine;
                        int count = 0; // Counter for occurrences of the product in History.txt
                        // Count occurrences in History.txt
                        while ((historyLine = readerhist.readLine()) != null) {
                            if (productLine.equals(historyLine)) {
                                count++; // Increment the count for each match
                            }
                        }
                        // Write the product and its count to best.txt
                        int line = Position(bestFile, count);
                        //what was written on "line" before is now saved in previous
                        FileManagement.Write(bestFile, line-1, false, "\n"+productLine + "@" + count+"\n");
                    } catch (IOException e) {
                        System.err.println("Error reading History.txt: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading products.txt: " + e.getMessage());
        }
    }

    /*Position will be called by Best() and is used to tell us where a new product should be put
    according to the short that we have selected (from most bought to least*/
    public static int Position(String filename, int count) {
        int position = 1; // Start at line 1
        try (BufferedReader best = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = best.readLine()) != null) {
                String[] parts = line.split("@");
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
