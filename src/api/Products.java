package api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//API FILE
//Add(...),Change(...) methods using InTheFile
//these are manager only methods!
public class Products {
    //those 2 methods are used to add and change products from the products.txt file
    //to have the following format:
            //Τίτλος: Πορτοκάλια 1kg
            //Περιγραφή: Φρέσκα πορτοκάλια, ιδανικά για χυμό ή κατανάλωση.
            //Κατηγορία: Φρέσκα τρόφιμα
            //Υποκατηγορία: Φρούτα
            //Τιμή: 1,20€
            //Ποσότητα: 200kg
    //we must ensure that the String will be given in the form:
    //NewContent = "Πορτοκάλια 1kg"\n"Φρέσκα πορτοκάλια, ιδανικά για χυμό ή κατανάλωση."\n"..."\n"
    //DONT forget "/n" these are used to split the String
    public static void Add(String NewContent) {
        //LastLine from FileManagement is used to find where the product will be added
        int line=FileManagement.LastLine("products.txt");
        line++;
        InTheFile(NewContent,line,false,"products.txt");
    }

    //OldTitle must be in the form :"Τίτλος: Πορτοκάλια 1kg"
    //NewContent must be in the form :
    //"Πορτοκάλια 1kg\nΦρέσκα πορτοκάλια, ιδανικά για χυμό ή κατανάλωση.\n...\n"
    public static void Change(String OldTitle, String NewContent) {
        int line=FileManagement.ThatLine("products.txt",OldTitle);
        //find the line of the title in the file and overwrite with the new content
        InTheFile(NewContent,line,true,"products.txt");
    }

    //this method is called to make changes in the file in the wanted format
    //if overwrite is false it was called by Add, else it was called by Change
    //this is done to reuse the same code for both methods.
    public static void InTheFile(String NewContent,int line,boolean overwrite,String filename){
        //split is used to separate what will go in each line
        String[] fields = NewContent.split("\n");
        String title = fields[0].trim();
        String description = fields[1].trim();
        String category = fields[2].trim();
        String subcategory = fields[3].trim();
        String price = fields[4].trim();
        String quantity = fields[5].trim();
        //write from FileManagement is used to add the product on "products.txt"
        FileManagement.Write(filename, line, overwrite, "Τίτλος: "+title);
        FileManagement.Write(filename, line+1, overwrite, "Περιγραφή: "+description);
        FileManagement.Write(filename, line+2, overwrite, "Κατηγορία: "+category);
        FileManagement.Write(filename, line+3, overwrite, "Υποκατηγορία: "+subcategory);
        FileManagement.Write(filename , line+4, overwrite, "Τιμή: "+price);
        FileManagement.Write(filename, line+5, overwrite, "Ποσότητα: "+quantity);
    }

    public static void searchproduct(String title)
    {
        String current;
        int line = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            while ((current = reader.readLine()) != null) {
                line++;
                if (current.equals("Τίτλος: " + title)) { // Compare the current line to content
                    for (int i = line; i < line + 6; i++) {
                        System.out.println(current);
                        current = reader.readLine();
                    }
                    current = null;
                }
                else if (current.equals("Κατηγορία: " + title)) { // Compare the current line to content
                    for (line=line-2; line< line + 4; line++) {
                        System.out.println(current);
                        current = reader.readLine();
                    }
                }

            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static void searchproduct(String category, String subcategory)
    {
        String current;
        int line = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            while ((current = reader.readLine()) != null) {
                line++;
                if (current.equals("Κατηγορία: " + category)) {
                    current = reader.readLine();
                        if (current.equals("Υποκατηγορία: " + subcategory)) {
                            for (line = line - 2; line < line + 4; line++) {
                                System.out.println(current);
                                current = reader.readLine();
                            }
                        }
                }

            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static void searchproduct() {
        int line = 0;
        String current;
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            while ((current = reader.readLine()) != null) {
                line++;
                System.out.println(current);

            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }




}
