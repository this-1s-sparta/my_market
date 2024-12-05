package api;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Search {

    public static String searchproduct(String title) {
        String current,result="";
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            current = reader.readLine();
            while (current != null) {
                if (current.equals("Τίτλος: " + title)) {
                    result=current+"\n"+reader.readLine()+"\n"+reader.readLine()+"\n"+reader.readLine()+"\n"+reader.readLine()+"\n"+reader.readLine()+"\n";
                    reader.readLine();
                    current = null;
                } else {
                    String line=current+"\n"+reader.readLine()+"\n";
                    current=reader.readLine();
                    if (current.equals("Κατηγορία: " + title)) {
                        result=result+line+current+"\n"+reader.readLine()+"\n"+reader.readLine()+"\n"+reader.readLine()+"\n"+"\n";
                        reader.readLine();

                    }
                    else {
                        reader.readLine();
                        reader.readLine();
                        reader.readLine();
                        reader.readLine();
                    }
                    current=reader.readLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return result;
    }

    public static String searchproduct(String category, String subcategory) {
        String current,result="";
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            while ((current = reader.readLine()) != null) {
                String line=current+"\n"+reader.readLine()+"\n";
                current=reader.readLine();
                if (current.equals("Κατηγορία: " + category)) {
                    line=line+current+"\n";
                    current = reader.readLine();
                    if (current.equals("Υποκατηγορία: " + subcategory)) {
                        result=result+line+current+"\n"+reader.readLine()+"\n"+reader.readLine()+"\n";
                        reader.readLine();

                    }else{
                        reader.readLine();
                        reader.readLine();
                    }
                }else{
                    reader.readLine();
                    reader.readLine();
                    reader.readLine();

                }
                current=reader.readLine();


            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return result;

    }

    public static String searchproduct() {
        String current,result="";
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            while ((current = reader.readLine()) != null) {
                result+=current+"\n";

            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return result;
    }


    public static String[] TitleArray() {
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
    public static String[] CategoryArray() {

        String[] categories= {"Φρέσκα τρόφιμα","Kατεψυγμένα τρόφιμα","Προϊόντα ψυγείου","Αλλαντικά","Αλκοολούχα ποτά","Μη αλκοολούχα ποτά","Καθαριστικά για το σπίτι","Απορρυπαντικά ρούχων","Καλλυντικά","Προϊόντα στοματικής υγιεινής","Πάνες","Δημητριακά","Ζυμαρικά","Σνακ","Έλαια","Κονσέρβες","Χαρτικά"};


        return categories;
    }
}