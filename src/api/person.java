package api;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class person {
    String username;
    int password;
//constructor με παραμέτρους το username και το password
    public person(String username, int password) {
        this.username = username;
        this.password = password;
    }
//getter για το username
    public String getUsername() {
        return username;
    }
//getter για το password
    public int getPassword() {
        return password;
    }
//setter για το username
    public void setUsername(String username) {
        this.username = username;
    }
//setter για το password
    public void setPassword(int password) {
        this.password = password;
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
