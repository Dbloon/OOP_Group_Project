package Visit_Referral_Management;

import Dashboardpak.Dashboard;

import javax.swing.*;
import java.awt.*;


public class AddVisit {
        public static void open() {
            JFrame frame = new JFrame("Add Visit");
            frame.setSize(800, 700);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Set vertical layout

            panel.add(new JLabel("Visit Number:"));
            JTextField visitNumberField = new JTextField(15);
            panel.add(visitNumberField);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            panel.add(new JLabel("Patient Name:"));
            JTextField patientNumberField = new JTextField(15);
            panel.add(patientNumberField);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            panel.add(new JLabel("Doctor Name:"));
            JTextField doctorNumberField = new JTextField(15);
            panel.add(doctorNumberField);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            panel.add(new JLabel("Visit Date - Day:"));
            JTextField dayField = new JTextField(5);
            panel.add(dayField);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            panel.add(new JLabel("Visit Date - Month:"));
            JTextField monthField = new JTextField(5);
            panel.add(monthField);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            panel.add(new JLabel("Visit Date - Year:"));
            JTextField yearField = new JTextField(5);
            panel.add(yearField);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            panel.add(new JLabel("Purpose of Visit:"));
            JTextField purposeField = new JTextField(15);
            panel.add(purposeField);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            panel.add(new JLabel("Diagnosis:"));
            JTextField diagnosisField = new JTextField(15);
            panel.add(diagnosisField);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            panel.add(new JLabel("Treatment:"));
            JTextField treatmentField = new JTextField(15);
            panel.add(treatmentField);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            panel.add(new JLabel("Doctor Notes:"));
            JTextArea notesArea = new JTextArea(5, 20);
            panel.add(new JScrollPane(notesArea));


            JButton submitButton = new JButton("Add Visit");
            submitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            submitButton.addActionListener(e -> {
                try {
                    // Validate inputs
                    int visitNumber = Integer.parseInt(visitNumberField.getText().trim());
                    int patientNumber = Integer.parseInt(patientNumberField.getText().trim());
                    int doctorNumber = Integer.parseInt(doctorNumberField.getText().trim());
                    int day = Integer.parseInt(dayField.getText().trim());
                    int month = Integer.parseInt(monthField.getText().trim());
                    int year = Integer.parseInt(yearField.getText().trim());

                    Date visitDate = new Date(day, month, year);
                    String purposeOfVisit = purposeField.getText().trim();
                    String diagnosis = diagnosisField.getText().trim();
                    String treatment = treatmentField.getText().trim();
                    String doctorNotes = notesArea.getText().trim();

                    Visit newVisit = new Visit(visitNumber, patientNumber, doctorNumber, visitDate, purposeOfVisit, diagnosis, treatment, doctorNotes);
                    JOptionPane.showMessageDialog(frame, "Visit added successfully!");
                    frame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: Visit, Patient, Doctor Numbers & Date fields must be numeric!", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            JButton returnToMenuButton = new JButton("Return to Menu");
            returnToMenuButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            returnToMenuButton.addActionListener(e -> {
                frame.dispose();
                Dashboard.open();
            });

            panel.add(submitButton);
            panel.add(returnToMenuButton);
            frame.add(panel);
            frame.setVisible(true);
        }

}
