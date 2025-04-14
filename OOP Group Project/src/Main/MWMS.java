package Main;

import Dashboardpak.Dashboard;
import Login_and_Authentication.LoginManager;
import Login_and_Authentication.SessionManager;
import Login_and_Authentication.User;

import javax.swing.*;
import java.awt.*;

public class MWMS {
    public static void main(String[] args) {
        // Login Frame
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 300);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //components
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> {
            System.exit(0);
        });

        //Layout
        panel.add(new JLabel("Welcome to the Medical and Wellness Management System", JLabel.CENTER));
        panel.add(Box.createVerticalStrut(50));

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(Box.createHorizontalStrut(68)); // Adds 10px horizontal space

        panel.add(Box.createVerticalStrut(10)); //Adds 10px vertical space
        panel.add(new JLabel("\nPassword:"));
        panel.add(passwordField);

        panel.add(Box.createVerticalStrut(10));
        panel.add(loginButton);
        panel.add(exit);

        loginFrame.add(panel);
        loginFrame.setVisible(true);
//not done
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = String.valueOf(passwordField.getPassword()).trim();

            User attemptedUser = new User(username, password, "Unknown"); // Temporary user tracking

            //User authenticatedUser = Login_and_Authentication.LoginManager.authenticate(username, password);
            //Login_and_Authentication.User authenticatedUser = LoginManager.authenticate("Daren", "bryan");
            Login_and_Authentication.User authenticatedUser = LoginManager.authenticate("Joshua", "kal");

            if (authenticatedUser != null) {
                if (!authenticatedUser.isLocked()) {
                    JOptionPane.showMessageDialog(loginFrame, "Welcome " + authenticatedUser.getUsername() + "!");
                    loginFrame.dispose(); // Close login frame
                    SessionManager.setAuthenticatedUser(authenticatedUser); // Store authenticated user
                    Dashboard.open(); // Open role-based dashboard
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Account is locked.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                attemptedUser.incrementFailedAttempts();

                if (attemptedUser.getFailedAttempts() >= 3) {
                    attemptedUser.setLocked(true);
                    JOptionPane.showMessageDialog(loginFrame, "Account locked due to multiple failed attempts.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

