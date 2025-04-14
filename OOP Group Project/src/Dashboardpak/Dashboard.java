package Dashboardpak;

import Main.MWMS;
import Login_and_Authentication.SessionManager;
import Login_and_Authentication.User;
import Staff_Record_Management.EmployeeInfo;
import Staff_Record_Management.ManageUsers;
import Visit_Referral_Management.AddVisit;

import javax.swing.*;
import java.awt.*;

public class Dashboard {
    public static void open() {
        User user = SessionManager.getAuthenticatedUser(); //fetches authenticated user from Session Manager
        if (user == null) {
            System.out.println("No authenticated user found. Redirecting to login...");
            return; // Prevent opening dashboard without a user
        }

        JFrame dashboardFrame = new JFrame("Main.MWMS Menu - " + user.getRole());
        dashboardFrame.setSize(600, 400);
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1)); // Single-column layout

        //Buttons
        JButton manageUsers = new JButton("Manage Users");
        manageUsers.addActionListener(e -> {
            dashboardFrame.dispose();
            ManageUsers.open();
        });

        JButton viewEmpRecs = new JButton("View Employee Record");
        viewEmpRecs.addActionListener(e -> {
            dashboardFrame.dispose();
            EmployeeInfo.viewEmployees();
        });

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            ((JFrame) SwingUtilities.getWindowAncestor(logoutButton)).dispose();
            MWMS.main(null);
        });

        JButton addVisitButton = new JButton("Add New Visit");
        addVisitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addVisitButton.addActionListener(e -> {
            AddVisit.open();
        });

        // Role-Based Options
        switch (user.getRole().toLowerCase()) {
            case "admin":
                panel.add(manageUsers);
                panel.add(viewEmpRecs);
                panel.add(logoutButton);
                break;
            case "doctor":
                panel.add(addVisitButton);
                panel.add(new JButton("View Appointments"));
                panel.add(new JButton("View Patient_Management.Patient Records"));
                panel.add(new JButton("Prescriptions"));
                panel.add(logoutButton);;
                break;
            case "nurse":
                panel.add(new JButton("Assist with Patient_Management.Patient Records"));
                panel.add(new JButton("Manage Medications"));
                panel.add(logoutButton);
                break;
            case "patient":
                panel.add(new JButton("View Medical History"));
                panel.add(new JButton("Book Appointment"));
                panel.add(logoutButton);
                break;
            default:
                panel.add(new JLabel("No specific options available for this role."));
        }

        dashboardFrame.add(panel);
        dashboardFrame.setVisible(true);
    }
}
