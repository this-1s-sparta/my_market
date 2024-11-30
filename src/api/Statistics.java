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
            String productLine;
            int times = 0;
            int Last = FileManagement.LastLine(products);
            // Reading each line of products.txt
            while (times <= Last) {
                productLine = readerprod.readLine();
                if (productLine == null) {
                    break; // Exit if there are no more lines to process
                }
                times++; // Increment the counter
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
                        FileManagement.Write(bestFile, line, false, productLine + "@" + count + "\n");
                    } catch (IOException e) {
                        System.err.println("Error reading History.txt: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading products.txt: " + e.getMessage());
        }
    }

    //Position will be called by Best() and is used to tell us where a new product should be put
    //according to the short that we have selected
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
