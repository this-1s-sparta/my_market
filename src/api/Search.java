package api;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Search {

    public static void searchproduct(String title, String category, String subcategory) {
        try {
            File search = new File("search.txt");
            if (search.exists()) {
                search.delete();

            }
            if (!search.createNewFile()) {
                System.err.println("Unable to create best.txt file.");
                return;
            }
        } catch (IOException e) {
            System.err.println("Error creating or clearing best.txt: " + e.getMessage());
            return;
        }
        //String current,result="";
        if (!title.equals("")) {
            int j = 0;
            while (j <= FileManagement.LastLine("products.txt")) {
                int line = FileManagement.PartialSearchLine(j, "products.txt", "Τίτλος: " + title);
                if (line >= 1) {
                    try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
                        String targetLine = null;
                        for (int i = 1; i <= line; i++) {
                            targetLine = reader.readLine();
                        }
                        int k = FileManagement.LastLine("search.txt");
                        for (int i = k; i < k + 6; i++) {
                            FileManagement.Write("search.txt", i, false, targetLine + "\n");
                            targetLine = reader.readLine();

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else
                    return;
                j = j + line + 6;
            }
        }
        if (category != null) {
            if (subcategory != "-" && subcategory != null) {
                int j = 0;
                while (j <= FileManagement.LastLine("products.txt")) {
                    int line = FileManagement.PartialSearchLine(j, "products.txt", "Υποκατηγορία: " + subcategory);
                    if (line >= 1) {
                        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
                            String targetLine = null;
                            for (int i = 1; i <= line - 3; i++) {
                                targetLine = reader.readLine();
                            }
                            int k = FileManagement.LastLine("search.txt");
                            for (int i = k; i < k + 6; i++) {
                                FileManagement.Write("search.txt", i, false, targetLine);
                                targetLine = reader.readLine();

                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else
                        return;
                    j = j + line + 6;
                }
            } else {
                int j = 0;
                while (j <= FileManagement.LastLine("products.txt")) {
                    int line = FileManagement.PartialSearchLine(j, "products.txt", "Κατηγορία: " + category);
                    if (line >= 1) {
                        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
                            String targetLine = null;
                            for (int i = 1; i <= line - 2; i++) {
                                targetLine = reader.readLine();
                            }
                            int k = FileManagement.LastLine("search.txt");
                            for (int i = k; i < k + 6; i++) {
                                FileManagement.Write("search.txt", i, false, targetLine);
                                targetLine = reader.readLine();

                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else
                        return;
                    j = j + line + 6;
                }
            }
        }
        else if (title.equals(""))
        {
            int k=FileManagement.LastLine("products.txt");
            try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
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

        String[] categories= {"Φρέσκα τρόφιμα","Κατεψυγμένα τρόφιμα","Προϊόντα ψυγείου","Αλλαντικά","Αλκοολούχα ποτά","Μη αλκοολούχα ποτά","Καθαριστικά για το σπίτι","Απορρυπαντικά ρούχων","Καλλυντικά","Προϊόντα στοματικής υγιεινής","Πάνες","Δημητριακά","Ζυμαρικά","Σνακ","Έλαια","Κονσέρβες","Χαρτικά"};


        return categories;
    }
    public static String[] SubCategoryArray() {
        String[] sub="Φρούτα@Λαχανικά@Ψάρια@Κρέατα@Κατεψυγμένα λαχανικά@Κατεψυγμένα κρέατα@Κατεψυγμένες πίτσες@Κατεψυγμένα γεύματα@Τυριά@Γιαούρτια@Γάλα@Βούτυρο@Ζαμπόν@Σαλάμι@Μπέικον@Μπύρα@Κρασί@Ούζο@Τσίπουρο@Χυμοί@Αναψυκτικά@Νερό@Ενεργειακά ποτά@Καθαριστικά για το πάτωμα@Καθαριστικά για τα τζάμια@Καθαριστικά κουζίνας@Σκόνες πλυντηρίου@Υγρά πλυντηρίου@Μαλακτικά@Κρέμες προσώπου@Μακιγιάζ@Λοσιόν σώματος@Οδοντόκρεμες@Οδοντόβουρτσες@Στοματικά διαλύματα@Πάνες για μωρά@Πάνες ενηλίκων@Νιφάδες καλαμποκιού@Μούσλι@Βρώμη@Μακαρόνια@Κριθαράκι@Ταλιατέλες@Πατατάκια@Κράκερς@Μπάρες δημητριακών@Ελαιόλαδο@Ηλιέλαιο@Σογιέλαιο@Κονσέρβες ψαριών@Κονσέρβες λαχανικών@Κονσέρβες φρούτων@Χαρτί υγείας@Χαρτοπετσέτες@Χαρτομάντηλα".split("@");
        return sub;
    }
}
