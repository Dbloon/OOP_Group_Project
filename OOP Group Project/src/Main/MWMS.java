package Main;

import Dashboardpak.Dashboard;
import Login_and_Authentication.LoginManager;
import Login_and_Authentication.SessionManager;
import Login_and_Authentication.User;

import javax.swing.*;
import java.awt.*;

public class MWMS {
    public static int failedAttempts = 0;
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
        panel.add(Box.createHorizontalStrut(68));

        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Password:"));
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

    
            User user = LoginManager.authenticate(username,password,failedAttempts);

            if (user != null) {
                JOptionPane.showMessageDialog(loginFrame, "Welcome " + user.getUsername() + "!");
                loginFrame.dispose(); // Close login frame
                Dashboard.open(); // Open role-based dashboard
            } else {
                failedAttempts++;
                if(failedAttempts > 3){
                    JOptionPane.showMessageDialog(loginFrame,"Account Locked, consult an Admin");
                    failedAttempts = 0;
                    System.exit(1  );
                }
                JOptionPane.showMessageDialog(loginFrame, "Login Failed.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

