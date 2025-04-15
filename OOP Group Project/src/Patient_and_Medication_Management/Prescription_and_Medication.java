package Patient_and_Medication_Management;

import javax.swing.*;
import java.awt.*;

public class Prescription_and_Medication {

	public static void MedicalHistory() {
		JFrame frame = new JFrame("Patient Records Management");
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JLabel titleLabel = new JLabel("Manage Patient Records", JLabel.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());

		JLabel patientIdLabel = new JLabel("Enter Patient ID#: ");
		JTextField patientIdField = new JTextField(10);
		JButton viewMedicationButton = new JButton("View Medication History");

		inputPanel.add(patientIdLabel);
		inputPanel.add(patientIdField);
		inputPanel.add(viewMedicationButton);

		JTextArea outputArea = new JTextArea();
		outputArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(outputArea);

		// Button action listener
		viewMedicationButton.addActionListener(e -> {
			try {
				int patientID = Integer.parseInt(patientIdField.getText());
				Prescription precs = new Prescription();
				//precs.medicationHistory();
				precs.viewMedicationHistory(patientID);
				outputArea.setText("Displaying medication history for Patient ID: " + patientID);
			} catch (NumberFormatException ex) {
				outputArea.setText("Invalid input! Please enter a numeric Patient ID.");
			}
		});

		frame.add(titleLabel, BorderLayout.NORTH);
		frame.add(inputPanel, BorderLayout.CENTER);
		frame.add(scrollPane, BorderLayout.SOUTH);

		frame.setVisible(true);
	}
}

/*import java.util.Scanner;

@SuppressWarnings("unused")
public class Prescription_and_Medication {
	public static void main(String[] args) {
		
		PatientRecords recs = new PatientRecords();
		Prescription precs = new Prescription();
		
		System.out.print("Please Enter the Patient's ID#: ");
		try (Scanner input = new Scanner(System.in)) {
			int patientID = Integer.parseInt(input.nextLine());
			
			precs.medicationHistory();
//			precs.initiallizeprescription();
//			precs.enterPrescription();
	
				
//			recs.initiallizeRec();
//			recs.enterData();
			
//			recs.displayRecords(patientID);
//			precs.displayPrescriptoins(patientID);
			
			precs.viewMedicationHistory(patientID);
			
		} catch (NumberFormatException e) {

			e.printStackTrace();
		}
	}*/