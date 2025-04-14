package Staff_Record_Management;

import Dashboardpak.Dashboard;
import javax.swing.*;
import java.awt.*;

public class ManageUsers {
    public static void open() {
        JFrame MUframe = new JFrame("Manage Users");
        MUframe.setSize(600, 400);
        MUframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("Welcome to the Medical and Wellness Management System", JLabel.CENTER);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(welcomeLabel);
        mainPanel.add(Box.createVerticalStrut(20)); // Add spacing below the label

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        buttonPanel.add(Box.createVerticalStrut(10));

        // **Add Employees Button**
        JButton addEmployees = new JButton("Add Employees");
        addEmployees.setAlignmentX(Component.CENTER_ALIGNMENT);
        addEmployees.addActionListener(e -> {
            MUframe.dispose();
            EmployeeInfo.add();
        });
        buttonPanel.add(addEmployees);
        buttonPanel.add(Box.createVerticalStrut(10));

        // **Remove Employees Button**
        JButton removeEmployees = new JButton("Remove Employees");
        removeEmployees.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeEmployees.addActionListener(e -> {
            EmployeeInfo.open();
        });
        buttonPanel.add(removeEmployees);
        buttonPanel.add(Box.createVerticalStrut(10));

        // **Edit Employees Button**
        JButton editEmployees = new JButton("Edit Employees");
        editEmployees.setAlignmentX(Component.CENTER_ALIGNMENT);
        editEmployees.addActionListener(e -> {
            JOptionPane.showMessageDialog(MUframe, "Feature for editing employees coming soon.");
            // Implement edit functionality here
        });
        buttonPanel.add(editEmployees);
        buttonPanel.add(Box.createVerticalStrut(10));

        // **Return to Previous Frame Button**
        JButton returnButton = new JButton("Return");
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnButton.addActionListener(e -> {
            MUframe.dispose(); // Close current frame
            Dashboard.open(); // Open Dashboardpak.Dashboard again
        });
        buttonPanel.add(returnButton);
        buttonPanel.add(Box.createVerticalGlue());

        mainPanel.add(buttonPanel);
        MUframe.add(mainPanel);

        MUframe.setVisible(true);
    }
}