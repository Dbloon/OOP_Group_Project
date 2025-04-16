package Dashboardpak;

import Main.MWMS;
import Login_and_Authentication.SessionManager;
import Login_and_Authentication.User;
import Patient_and_Medication_Management.MainDashboard;
import Patient_and_Medication_Management.PatientManager;
import Staff_Record_Management.EmployeeInfo;
import Staff_Record_Management.ManageUsers;
import Visit_Referral_Management.AddVisit;
import AppointmentScheduling.Appointment;

import javax.swing.*;
import java.awt.*;

public class Dashboard {
    public static void open() {
        // Fetch authenticated user from Session Manager
        User user = SessionManager.getAuthenticatedUser();
        if (user == null) {
            System.out.println("No authenticated user found. Redirecting to login...");
            return; // Prevent opening dashboard without a user
        }

        // Create the dashboard frame
        JFrame dashboardFrame = new JFrame("Prescription_and_Medication.MWMS Menu - " + user.getRole());
        dashboardFrame.setSize(600, 400);
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up the main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1)); // Single-column layout

        // Buttons
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
            dashboardFrame.setVisible(false);
            AddVisit.open();
        });

        JButton AppointmentScheduling = new JButton("Appointment Scheduling");
        AppointmentScheduling.addActionListener(e -> {
            dashboardFrame.setVisible(false);
            Appointment.main(null);
        });

        JButton ManageMedication = new JButton("Manage Medication");
        ManageMedication.addActionListener(e -> {
            dashboardFrame.setVisible(false);
            MainDashboard.main(null);
        });

        JButton patientRecords = new JButton("View Patient Records");
        patientRecords.addActionListener(e -> {
            dashboardFrame.setVisible(false);
            PatientManager.managePatientRecords();
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
                panel.add(AppointmentScheduling);
                panel.add(ManageMedication);
                panel.add(logoutButton);
                break;
            case "nurse":
                panel.add(patientRecords);
                panel.add(ManageMedication);
                panel.add(logoutButton);
                break;
            case "patient":
                panel.add(new JButton("View Medical History"));
                panel.add(AppointmentScheduling);
                panel.add(logoutButton);
                break;
            default:
                panel.add(new JLabel("No specific options available for this role."));
        }

        // Add panel to frame and make it visible
        dashboardFrame.add(panel);
        dashboardFrame.setVisible(true);
    }
}
