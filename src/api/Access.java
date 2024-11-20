package api;

//API FILE
//The access file has the log in and sign up methods inside
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Access{
    public static boolean login(String username, String password, String filename) {
        //login(username,password,filename) checks if "username","password" exist in "filename"
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //read file line by line
                String[] fields = line.split(",");
                String fileUsername = fields[0].trim();
                String filePassword = fields[1].trim();
                // Split the line into username and password
                if (fileUsername.equals(username)) {
                    // Check if username matches
                    return filePassword.equals(password);
                    // Check if password matches
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            System.out.println(e.getMessage());
            //IOException is needed when working with BufferedReader.
        }
        //MESSAGE
        System.out.println("Wrong username or password.");
        return false;
        //false if username was not found or password doesn't match
    }

    // Method to handle user signup
    public static boolean signup(String username, String password) {
        try (
                BufferedReader check = new BufferedReader(new FileReader("client.csv"));
                BufferedReader check2 = new BufferedReader(new FileReader("manager.csv"))) {
            //we check if an other user already has that username (we need to check both files)
            String line;
            //check if username is in the client.csv file
            while ((line = check.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].trim().equals(username)) {
                    // Username already exists
                    //MESSAGE
                    System.out.println("Username already exists.");
                    return false;
                }
            }
            //check if username is in the manager.csv file
            while ((line = check2.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].trim().equals(username)) {
                    // Username already exists
                    //MESSAGE
                    System.out.println("Username already exists.");
                    return false;
                }
            }
            // If username doesn't exist, add new user to file
            int lastline = FileManagement.LastLine("client.csv");
            FileManagement.Write("manager.csv",lastline,false,username+","+password);
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while reading or writing to the file.");
            System.out.println(e.getMessage());
        }
        return false; // Return false if there's an error or if username already exists
    }
}

