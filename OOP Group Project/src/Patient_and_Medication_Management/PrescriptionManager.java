package Patient_and_Medication_Management;

import Dashboardpak.Dashboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.RandomAccessFile;
import java.io.IOException;

public class PrescriptionManager {
    private static final String FILE_NAME = "Prescriptions.txt";
    private static final int RECORD_SIZE = 128;

    public static void displayPrescriptionPanel() {
        JFrame frame = new JFrame("Prescription Management");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Manage Prescriptions", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Table setup
        String[] columns = {"Patient ID", "Doctor ID", "Medication", "Instructions", "Refills", "Dosage", "Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable prescriptionTable = new JTable(tableModel);
        loadPrescriptions(tableModel);

        JScrollPane tableScrollPane = new JScrollPane(prescriptionTable);

        // Buttons for add, edit, delete
        JPanel buttonPanel = getJPanel(tableModel, prescriptionTable,frame);

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(tableScrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private static JPanel getJPanel(DefaultTableModel tableModel, JTable prescriptionTable, JFrame parentframe) {
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addPrescription(tableModel));

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> editPrescription(prescriptionTable, tableModel));

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deletePrescription(prescriptionTable, tableModel));

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            parentframe.dispose();
            MainDashboard.main(null);
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);
        return buttonPanel;
    }

    private static void loadPrescriptions(DefaultTableModel model) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
            while (file.getFilePointer() < file.length()) {
                int patientId = file.readInt();
                int doctorId = file.readInt();
                String medication = file.readUTF().trim();
                String instructions = file.readUTF().trim();
                int refills = file.readInt();
                double dosage = file.readDouble();
                String date = file.readUTF().trim();
                model.addRow(new Object[]{patientId, doctorId, medication, instructions, refills, dosage, date});
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading prescriptions.");
        }
    }

    private static void addPrescription(DefaultTableModel model) {
        // Open input dialog to add a new prescription
        new PrescriptionForm(model, null, -1);
    }

    private static void editPrescription(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to edit.");
            return;
        }
        // Open the form for editing with current data
        new PrescriptionForm(model, table, selectedRow);
    }

    private static void deletePrescription(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
            return;
        }
        model.removeRow(selectedRow);
        saveToFile(model);
    }

    private static void saveToFile(DefaultTableModel model) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            file.setLength(0); // Clear the file
            for (int row = 0; row < model.getRowCount(); row++) {
                file.writeInt((Integer) model.getValueAt(row, 0));
                file.writeInt((Integer) model.getValueAt(row, 1));
                file.writeUTF(String.format("%-15s", model.getValueAt(row, 2)));
                file.writeUTF(String.format("%-50s", model.getValueAt(row, 3)));
                file.writeInt((Integer) model.getValueAt(row, 4));
                file.writeDouble((Double) model.getValueAt(row, 5));
                file.writeUTF(String.format("%-10s", model.getValueAt(row, 6)));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving prescriptions.");
        }
    }
}