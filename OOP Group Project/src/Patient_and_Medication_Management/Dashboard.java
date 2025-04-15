package Patient_and_Medication_Management;

import javax.swing.*;
import java.awt.*;

public class Dashboard {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Dashboard");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Healthcare Management Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        // Button for managing prescriptions
        JButton prescriptionButton = new JButton("Manage Prescriptions");
        prescriptionButton.addActionListener(e -> {
            PrescriptionManager.displayPrescriptionPanel();
        });

        // Button for managing patient records
        JButton patientButton = new JButton("Manage Patient Records");
        patientButton.addActionListener(e -> {
            PatientManager.managePatientRecords();
        });

        // Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(prescriptionButton);
        buttonPanel.add(patientButton);
        buttonPanel.add(exitButton);

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
