package api;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Search {

    public static void searchproduct(String title, String category, String subcategory) {
        try {
            File search = new File("search.txt");
            if (search.exists()) {//checks if file exists
                search.delete();//if exists it deletes the file to create new

            }
            if (!search.createNewFile()) {//check if there is error in creating file
                System.err.println("Unable to create best.txt file.");
                return;
            }
        } catch (IOException e) {
            System.err.println("Error creating or clearing best.txt: " + e.getMessage());
            return;
        }

        if (!title.equals("")) {//check if the user has given a title for search if it has it enters the if loop
            int j = 0;
            while (j <= FileManagement.LastLine("products.txt")) {//the while loop continues until the last line of the file
                int line = FileManagement.PartialSearchLine(j, "products.txt", "Τίτλος: " + title);//find the number of the first line that the title is present even partially
                if (line >= 1) {
                    try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
                        String targetLine = null;
                        for (int i = 1; i <= line; i++) {//ignores the lines before the one we are interested
                            targetLine = reader.readLine();
                        }
                        int k = FileManagement.LastLine("search.txt");//finds last line of search.txt so it can continue writing without overwriting
                        for (int i = k; i < k + 6; i++) {
                            FileManagement.Write("search.txt", i, false, targetLine + "\n");//writes in the file all the lines/characteristics tha belong to the specific product
                            targetLine = reader.readLine();

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else
                    return;
                j = line+1;//moves the indicator to the line of the next line in order to check again
            }
        }
        if (category != null) {//checks if user has given a category
            if (subcategory != "-" && subcategory != null) {//check if user has given both category and subcategory input
                int j = 0;
                while (j <= FileManagement.LastLine("products.txt")) {//finds last line of file
                    int line = FileManagement.PartialSearchLine(j, "products.txt", "Υποκατηγορία: " + subcategory);//finds the first line with the given subcategory
                    if (line >= 1) {
                        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {//opens file for reading
                            String targetLine = null;
                            for (int i = 1; i <= line - 3; i++) {//ignores the previous lines
                                targetLine = reader.readLine();
                            }
                            int k = FileManagement.LastLine("search.txt");// find the last line of the file
                            for (int i = k; i < k + 6; i++) {
                                FileManagement.Write("search.txt", i, false, targetLine);//adds to the search.txt file all the lines/characteristics of the product with the given subcategory
                                targetLine = reader.readLine();

                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else
                        return;
                    j = line+1;//moves the indicator to the line of the next line in order to check again
                }
            } else {//the user has only given category and not subcategory
                int j = 0;
                while (j <= FileManagement.LastLine("products.txt")) {//finds last line
                    int line = FileManagement.PartialSearchLine(j, "products.txt", "Κατηγορία: " + category);//finds the first line with the given category
                    if (line >= 1) {
                        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
                            String targetLine = null;
                            for (int i = 1; i <= line - 2; i++) {//ignores the previous lines
                                targetLine = reader.readLine();
                            }
                            int k = FileManagement.LastLine("search.txt");
                            for (int i = k; i < k + 6; i++) {//adds to the search.txt file all the lines/characteristics of the product with the given subcategory
                                FileManagement.Write("search.txt", i, false, targetLine);
                                targetLine = reader.readLine();

                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else
                        return;
                    j = line+1;//moves the indicator to the line of the next line in order to check again
                }
            }
        }
        else if (title.equals(""))//if title category and subcategory are all blank
        {
            int k=FileManagement.LastLine("products.txt");
            try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {//open file products.txt and copy it in search.txt without the blank lines
                String targetLine = reader.readLine();
                for(int i=1;targetLine!=null;i+=6)
                {
                    FileManagement.Write("search.txt", i, false, targetLine);
                    for(int j=1;j<6;j++){
                        targetLine = reader.readLine();
                    FileManagement.Write("search.txt", i+j, false, targetLine);
                    }
                    reader.readLine();
                    targetLine = reader.readLine();
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public static String[] TitleArray() {//returns all the titles of products.txt in an array
        String filePath = "products.txt";
        int j = 0;
        int k = FileManagement.LastLine(filePath);
        String titles[] = new String[k / 7];

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            int currentLineNumber = 1;
            while ((currentLine = br.readLine()) != null) {
                String[] words = currentLine.split("\\s+");
                String[] arr = new String[words.length - 1];
                for (int i = 1; i < words.length; i++) {
                    arr[i - 1] = words[i];
                }
                titles[j] = String.join(" ", arr);
                j++;
                for (int i = 0; i < 6; i++)
                    currentLine = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return titles;
    }
    public static String[] CategoryArray() {//makes array of categories

        String[] categories= {"Φρέσκα τρόφιμα","Κατεψυγμένα τρόφιμα","Προϊόντα ψυγείου","Αλλαντικά","Αλκοολούχα ποτά","Μη αλκοολούχα ποτά","Καθαριστικά για το σπίτι","Απορρυπαντικά ρούχων","Καλλυντικά","Προϊόντα στοματικής υγιεινής","Πάνες","Δημητριακά","Ζυμαρικά","Σνακ","Έλαια","Κονσέρβες","Χαρτικά"};


        return categories;
    }
    public static String[] SubCategoryArray() {//makes array of subcategories
        String[] sub="Φρούτα@Λαχανικά@Ψάρια@Κρέατα@Κατεψυγμένα λαχανικά@Κατεψυγμένα κρέατα@Κατεψυγμένες πίτσες@Κατεψυγμένα γεύματα@Τυριά@Γιαούρτια@Γάλα@Βούτυρο@Ζαμπόν@Σαλάμι@Μπέικον@Μπύρα@Κρασί@Ούζο@Τσίπουρο@Χυμοί@Αναψυκτικά@Νερό@Ενεργειακά ποτά@Καθαριστικά για το πάτωμα@Καθαριστικά για τα τζάμια@Καθαριστικά κουζίνας@Σκόνες πλυντηρίου@Υγρά πλυντηρίου@Μαλακτικά@Κρέμες προσώπου@Μακιγιάζ@Λοσιόν σώματος@Οδοντόκρεμες@Οδοντόβουρτσες@Στοματικά διαλύματα@Πάνες για μωρά@Πάνες ενηλίκων@Νιφάδες καλαμποκιού@Μούσλι@Βρώμη@Μακαρόνια@Κριθαράκι@Ταλιατέλες@Πατατάκια@Κράκερς@Μπάρες δημητριακών@Ελαιόλαδο@Ηλιέλαιο@Σογιέλαιο@Κονσέρβες ψαριών@Κονσέρβες λαχανικών@Κονσέρβες φρούτων@Χαρτί υγείας@Χαρτοπετσέτες@Χαρτομάντηλα".split("@");
        return sub;
    }
}
