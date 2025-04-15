package Patient_and_Medication_Management;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class PrescriptionForm extends JDialog {
    public PrescriptionForm(DefaultTableModel tableModel, JTable table, int selectedRow) {
        setTitle(selectedRow == -1 ? "Add Prescription" : "Edit Prescription");
        setSize(400, 400);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        JLabel patientIdLabel = new JLabel("Patient ID#: ");
        JTextField patientIdField = new JTextField();
        JLabel doctorIdLabel = new JLabel("Doctor ID#: ");
        JTextField doctorIdField = new JTextField();
        JLabel medicationLabel = new JLabel("Medication: ");
        JTextField medicationField = new JTextField();
        JLabel instructionsLabel = new JLabel("Instructions: ");
        JTextField instructionsField = new JTextField();
        JLabel refillsLabel = new JLabel("Refills: ");
        JTextField refillsField = new JTextField();
        JLabel dosageLabel = new JLabel("Dosage: ");
        JTextField dosageField = new JTextField();
        JLabel dateLabel = new JLabel("Date: ");
        JTextField dateField = new JTextField();

        inputPanel.add(patientIdLabel);
        inputPanel.add(patientIdField);
        inputPanel.add(doctorIdLabel);
        inputPanel.add(doctorIdField);
        inputPanel.add(medicationLabel);
        inputPanel.add(medicationField);
        inputPanel.add(instructionsLabel);
        inputPanel.add(instructionsField);
        inputPanel.add(refillsLabel);
        inputPanel.add(refillsField);
        inputPanel.add(dosageLabel);
        inputPanel.add(dosageField);
        inputPanel.add(dateLabel);
        inputPanel.add(dateField);

        // Pre-fill fields if editing
        if (selectedRow != -1) {
            patientIdField.setText(table.getValueAt(selectedRow, 0).toString());
            doctorIdField.setText(table.getValueAt(selectedRow, 1).toString());
            medicationField.setText(table.getValueAt(selectedRow, 2).toString());
            instructionsField.setText(table.getValueAt(selectedRow, 3).toString());
            refillsField.setText(table.getValueAt(selectedRow, 4).toString());
            dosageField.setText(table.getValueAt(selectedRow, 5).toString());
            dateField.setText(table.getValueAt(selectedRow, 6).toString());
        }

        // Save button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            try {
                int patientId = Integer.parseInt(patientIdField.getText());
                int doctorId = Integer.parseInt(doctorIdField.getText());
                String medication = medicationField.getText();
                String instructions = instructionsField.getText();
                int refills = Integer.parseInt(refillsField.getText());
                double dosage = Double.parseDouble(dosageField.getText());
                String date = dateField.getText();

                if (selectedRow == -1) {
                    // Add new record
                    tableModel.addRow(new Object[]{patientId, doctorId, medication, instructions, refills, dosage, date});
                } else {
                    // Edit existing record
                    tableModel.setValueAt(patientId, selectedRow, 0);
                    tableModel.setValueAt(doctorId, selectedRow, 1);
                    tableModel.setValueAt(medication, selectedRow, 2);
                    tableModel.setValueAt(instructions, selectedRow, 3);
                    tableModel.setValueAt(refills, selectedRow, 4);
                    tableModel.setValueAt(dosage, selectedRow, 5);
                    tableModel.setValueAt(date, selectedRow, 6);
                }
                dispose(); // Close the form
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input! Please ensure numeric fields are correct.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            dispose();
            MainDashboard.main(null);
        });

        add(inputPanel, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
        setModal(true);
        setVisible(true);
    }
}