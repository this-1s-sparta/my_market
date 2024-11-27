package gui;
import javax.swing.*;

public class SignUpFrame {
    // Method to open Sign Up frame
    public static void openSignUpFrame() {
        JFrame signUpFrame = new JFrame("Sign Up");
        signUpFrame.setSize(300, 200);
        signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signUpFrame.setVisible(true);
    }
}
