package Patient_and_Medication_Management;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class PatientManager {
    private static final String FILE_NAME = "PatientData.txt";
    private static final int RECORD_SIZE = 92;

    public static void managePatientRecords() {
        JFrame frame = new JFrame("Patient Records Management");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Manage Patient Records", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        JButton viewRecordsButton = new JButton("View Patient Records");
        viewRecordsButton.addActionListener(e -> viewPatientRecords());

        JButton enterDataButton = new JButton("Enter New Patient Record");
        enterDataButton.addActionListener(e -> enterPatientData());

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            frame.dispose();
            MainDashboard.main(null);
        });

        buttonPanel.add(viewRecordsButton);
        buttonPanel.add(enterDataButton);
        buttonPanel.add(exitButton);

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void viewPatientRecords() {
        String patientIdStr = JOptionPane.showInputDialog(null, "Enter Patient ID#: ");
        try {
            int patientId = Integer.parseInt(patientIdStr);

            JFrame frame = new JFrame("Patient Record Viewer");
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JTextArea recordsArea = new JTextArea();
            recordsArea.setEditable(false);

            try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
                long filePointerPosition = (long) (patientId - 1) * RECORD_SIZE;
                file.seek(filePointerPosition);

                int patientNumber = file.readInt();
                String patientName = file.readUTF();
                String patientDob = file.readUTF();
                String gender = file.readUTF();
                int phoneNumber = file.readInt();
                String address = file.readUTF();

                StringBuilder details = new StringBuilder();
                details.append("Patient ID#: ").append(patientNumber).append("\n");
                details.append("Patient Name: ").append(patientName).append("\n");
                details.append("Date of Birth: ").append(patientDob).append("\n");
                details.append("Gender: ").append(gender).append("\n");
                details.append("Phone Number: ").append(phoneNumber).append("\n");
                details.append("Address: ").append(address).append("\n");

                recordsArea.setText(details.toString());
            } catch (IOException ex) {
                recordsArea.setText("Error retrieving patient records.");
            }

            JScrollPane scrollPane = new JScrollPane(recordsArea);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setVisible(true);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a numeric Patient ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void enterPatientData() {
        JFrame frame = new JFrame("Enter New Patient Record");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        JLabel patientIdLabel = new JLabel("Patient ID#: ");
        JTextField patientIdField = new JTextField();
        JLabel patientNameLabel = new JLabel("Patient Name: ");
        JTextField patientNameField = new JTextField();
        JLabel dobLabel = new JLabel("Date of Birth (DD-MM-YYYY): ");
        JTextField dobField = new JTextField();
        JLabel genderLabel = new JLabel("Gender: ");
        JTextField genderField = new JTextField();
        JLabel phoneNumberLabel = new JLabel("Phone Number: ");
        JTextField phoneNumberField = new JTextField();
        JLabel addressLabel = new JLabel("Address: ");
        JTextField addressField = new JTextField();

        JButton saveButton = new JButton("Save Record");

        inputPanel.add(patientIdLabel);
        inputPanel.add(patientIdField);
        inputPanel.add(patientNameLabel);
        inputPanel.add(patientNameField);
        inputPanel.add(dobLabel);
        inputPanel.add(dobField);
        inputPanel.add(genderLabel);
        inputPanel.add(genderField);
        inputPanel.add(phoneNumberLabel);
        inputPanel.add(phoneNumberField);
        inputPanel.add(addressLabel);
        inputPanel.add(addressField);

        JTextArea statusArea = new JTextArea();
        statusArea.setEditable(false);

        saveButton.addActionListener(e -> {
            try {
                int patientNumber = Integer.parseInt(patientIdField.getText());
                String patientName = patientNameField.getText();
                String patientDob = dobField.getText();
                String gender = genderField.getText();
                int phoneNumber = Integer.parseInt(phoneNumberField.getText());
                String address = addressField.getText();

                try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
                    long filePointerPosition = (long) (patientNumber - 1) * RECORD_SIZE;
                    file.seek(filePointerPosition);

                    file.writeInt(patientNumber);
                    file.writeUTF(patientName);
                    file.writeUTF(patientDob);
                    file.writeUTF(gender);
                    file.writeInt(phoneNumber);
                    file.writeUTF(address);

                    statusArea.setText("Patient record saved successfully!");
                } catch (IOException ex) {
                    statusArea.setText("Error saving patient record.");
                }
            } catch (NumberFormatException ex) {
                statusArea.setText("Invalid input! Please ensure numeric fields are correct.");
            }
            int option = JOptionPane.showConfirmDialog(
                    frame,
                    "Do you wish to enter another?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );
            if (option == JOptionPane.YES_OPTION) {
                frame.dispose();
                enterPatientData();
            } else if (option == JOptionPane.NO_OPTION) {
                frame.dispose();

            }

        });

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(statusArea), BorderLayout.CENTER);
        frame.add(saveButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
