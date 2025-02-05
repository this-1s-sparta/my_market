package api;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
//the FileManagement class has:
//the Write(...) method, the LastLine(...) method,ThatLine(...) method ...
//those methods are used to make file management easier

public class FileManagement {
    //Write(filename,line,overwrite,content)
    //filename is the name of the file
    //line is the line I want to write on
    //overwrite if true I overwrite
    //          if false I add on that line (text will be added next to the already written text)
    //content is the text I want written on the file
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

        try (
                BufferedReader reader = new BufferedReader(new FileReader(tempFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))
        ) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                writer.write(currentLine);
                writer.newLine(); // Add a newline after each line
            }
        } catch (IOException e) {
            System.out.println("An error occurred while copying tempFile to inputFile: " + e.getMessage());
        }
        // delete tempFile after copying
        if (!tempFile.delete()) {
            System.out.println("Failed to delete temporary file.");
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

    //this method does exactly what the ThatLine method does
    //but it can start from a certain "line"...
    public static int FromThatLine(int startLine,String filename, String content) {
        int line = 0;
        String current;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (line < startLine && reader.readLine() != null) {
                line++;
            }
            while ((current = reader.readLine()) != null) {
                line++;
                if (content.equals(current)) {
                    return line; // Return the line number if content is found
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return -1; // Return -1 if content is not found
    }
    //this is a quick way to print a line ("lineToPrint") of a file ("filename")
    public static String GetLine(int lineToPrint,String filename) {
        int line = 0;
        String current;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Read lines until we reach the desired line
            while ((current = reader.readLine()) != null) {
                line++;
                if (line == lineToPrint) {
                    return current;  // Return after printing the line
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        // If the loop finishes and the line is not found, print a message and return
        return null;
    }

    public static int PartialSearchLine(int startline, String filename, String content) {
        int line = 0;
        String current;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while ((current = reader.readLine()) != null) {
                line++; //skip until startline
                if (line >= startline && current.startsWith(content)) {
                    return line;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return -1; // no match found
    }

    //this is used for the testing methods
    public static void deleteLines(String filename, int n) throws IOException {
        //this method deletes the last n lines of a file
        File inputFile = new File(filename);
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        // If there are more lines than N, remove the last N lines
        if (lines.size() > n) {
            lines = lines.subList(0, lines.size() - n);
        } else {
            lines.clear(); // Clear the file if it has fewer than N lines
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}