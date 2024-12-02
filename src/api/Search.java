package api;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Search {

    public static void searchproduct(String title) {
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
                } else {
                    String l1 = current;
                    line++;
                    String l2 = reader.readLine();
                    line++;
                    current = reader.readLine();
                    line++;
                    if (current.equals("Κατηγορία: " + title)) {
                        System.out.println(l1);
                        System.out.println(l2);
                        for (int i = line; i <= line + 3; i++) {
                            System.out.println(current);
                            current = reader.readLine();
                        }
                        line += 3;

                    }

                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static void searchproduct(String category, String subcategory) {
        String current;
        int line = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            while ((current = reader.readLine()) != null) {
                line++;
                String l1 = current;
                line++;
                String l2 = reader.readLine();
                line++;
                String l3 = reader.readLine();
                line++;
                if (l3.equals("Κατηγορία: " + category)) {
                    current = reader.readLine();
                    if (current.equals("Υποκατηγορία: " + subcategory)) {
                        System.out.println(l1);
                        System.out.println(l2);
                        System.out.println(l3);
                        for (int i = line; i <= line + 2; i++) {
                            System.out.println(current);
                            current = reader.readLine();
                        }
                        line += 2;
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
}