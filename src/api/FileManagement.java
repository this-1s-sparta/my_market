package api;
import java.io.*;
//the FileManagement class has:
//the Write(...) method, the LastLine(...) method,ThatLine(...) method ...
//those methods are used to make file management easier

public class FileManagement {
    //Write(filename,line,overwrite,content)
    //filename is the name of the file
    //line is the line I want to write on
    //overwrite if true I overwrite
    //          if false I add on that line (text will be added next to the already written text)
    //content is the text I want to written on the file
    public static void Write(String filename, int line, boolean overwrite, String content) {
        File inputFile = new File(filename);
        File tempFile = new File("temp_" + filename);
        // try-catch for exceptions
        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String currentLine;
            int currentLineNumber = 1;
            while ((currentLine = reader.readLine()) != null || currentLineNumber <= line) {
                if (currentLineNumber == line) {
                    // Write to the specified line
                    if (overwrite) {
                        writer.write(content); // Overwrite the line
                    } else {
                        if (currentLine != null) {
                            writer.write(currentLine + content); // if the line is not empty just add
                        } else {
                            writer.write(content); // If the line is empty, write the content
                        }
                    }
                } else {
                    // Write the original content of other lines
                    if (currentLine != null) {
                        writer.write(currentLine);
                    }
                }
                writer.newLine(); // Add a new line
                currentLineNumber++;
                // If at the end of the file we haven't reached the wanted line,
                // we will fill the file with empty lines until the wanted line.
                if (currentLine == null && currentLineNumber <= line) {
                    for (int i = currentLineNumber; i < line; i++) {
                        writer.newLine(); // Write blank lines
                        currentLineNumber++;
                    }
                }
            }
        } catch (IOException e) {
            // Handle any IOExceptions that occur
            System.out.println("An error occurred: " + e.getMessage());
            System.out.println(e.getMessage());
        }
        // Replace the original file with the edited.
        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            System.out.println("Error replacing the original file.");
        }
    }

    //LastLine finds the last non-written line of "filename"
    //it returns where we will not overwrite
    public static int LastLine(String filename) {
        int last = 1;
        String current;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while ((current = reader.readLine()) != null) {
                last += 1;
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return last;
    }

    //ThatLine returns in what line "content" is if it  exists.
    //if it does not exist it returns -1.
    public static int ThatLine(String filename, String content) {
        int line = 0;
        String current;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while ((current = reader.readLine()) != null) {
                line++;
                if (content.equals(current)) { // Compare the current line to content
                    return line; // Return the line number if content is found
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return -1; // Return -1 if not found
    }

}