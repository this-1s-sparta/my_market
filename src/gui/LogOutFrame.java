package gui;
import javax.swing.*;
import java.awt.Window;
public class LogOutFrame {

    public static void OutFrame(JButton button) {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(button);
        if (parentFrame != null) {
            parentFrame.dispose(); // Close the frame that triggered the logout
        }
    }

    public static void CloseAll()
    {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            window.dispose();
        }
        MainFrame.OpenMainFrame();
    }
}
