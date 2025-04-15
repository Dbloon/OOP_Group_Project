package Patient_and_Medication_Management;

import javax.swing.*;
import java.awt.*;

public class MainDashboard {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Dashboard");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Healthcare Management Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        // Button for Saving Prescriptions
        JButton savePrescriptionButton = new JButton("Save Prescriptions");
        savePrescriptionButton.addActionListener(e -> {
            Prescription prescription = new Prescription();
            prescription.enterPrescription(); // Opens the prescription-saving GUI
        });

        // Button for Managing Medication History
        JButton medicationHistoryButton = new JButton("Manage Medication History");
        medicationHistoryButton.addActionListener(e -> {
            Prescription prescription = new Prescription();
            prescription.medicationHistory(); // Opens medication history management GUI
        });

        // Button for Viewing Patient Records
        JButton viewPatientRecordsButton = new JButton("View Patient Records");
        viewPatientRecordsButton.addActionListener(e -> {
            PatientRecords patientRecords = new PatientRecords();
            String patientIdStr = JOptionPane.showInputDialog(frame, "Enter Patient ID#: ");
            try {
                int patientId = Integer.parseInt(patientIdStr);
                patientRecords.displayRecords(patientId); // Opens the patient records viewer GUI
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a numeric Patient ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add buttons to the panel
        buttonPanel.add(savePrescriptionButton);
        buttonPanel.add(medicationHistoryButton);
        buttonPanel.add(viewPatientRecordsButton);

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
