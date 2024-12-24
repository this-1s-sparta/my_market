package gui;
import javax.swing.*;
import java.awt.Window;
public class LogOutFrame {

    public static void OutFrame(JButton button) {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(button);
        if (parentFrame != null) {
            parentFrame.dispose();
            // Close the frame that triggered the logout
        }
    }

    public static void CloseAll()
    {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            window.dispose();
        }
        MainFrame.OpenMainFrame();
        //this closes all frames and reopens the Main Frame
        //this does NOT stop the program from running...
        //only logs the user out and gives the ability to any user to log in
    }
}
