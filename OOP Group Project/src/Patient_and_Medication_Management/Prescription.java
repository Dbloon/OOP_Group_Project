package Patient_and_Medication_Management;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.Scanner;

public class Prescription {

	 private MedicationStock medicationStock;
	
	private static final int sizeOfRecord = 123;
	private static final int numberOfRecords = 10;
	private static final Scanner INPUT = new Scanner(System.in);
	private static int filePointerPosition = 0;
	
	private int patientNumber = 0;	
	private int doctorNumber = 0;
	private String lName = "ABCDEFGHIJKLMNO";
	private String medication = "ABCDEFGHIJ";
	private String instructions = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZAAAAAAAA";
	private int refills = 0;
	private double dosage = 0.0;
	private String prescriptionDate = "ABCDEFGHIJ";
	private LocalDate currentDay = LocalDate.now();
	
	public Prescription() {
		this.patientNumber = 0;
		this.doctorNumber = 0;
		this.lName = "Unknow";
		this.medication = "Unknown";
		this.instructions = "Nothing";
		this.refills = 0;
		this.dosage = 0.0;
		this.prescriptionDate = "Unknown";
	}
	
	public Prescription(MedicationStock medicationStock,int patientNumber, int doctorNumber, String lName, String medication, String instructions, int refills, double dosage, String prescriptionDate) {
		this.medicationStock = medicationStock;
		this.patientNumber = patientNumber;
		this.doctorNumber = doctorNumber;
		this.lName = lName;
		this.medication = medication;
		this.instructions = instructions;
		this.refills = refills;
		this.dosage = dosage;
		this.prescriptionDate = prescriptionDate;
	}

	public void saveMedicationHistory() {
		JFrame frame = new JFrame("Medication History");
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		JLabel patientNumberLabel = new JLabel("Patient Number:");
		JTextField patientNumberField = new JTextField(10);
		JLabel medicationLabel = new JLabel("Medication:");
		JTextField medicationField = new JTextField(10);
		JLabel dateLabel = new JLabel("Prescription Date (YYYY-MM-DD):");
		JTextField dateField = new JTextField(10);
		JButton saveButton = new JButton("Save");

		inputPanel.add(patientNumberLabel);
		inputPanel.add(patientNumberField);
		inputPanel.add(medicationLabel);
		inputPanel.add(medicationField);
		inputPanel.add(dateLabel);
		inputPanel.add(dateField);

		JTextArea statusArea = new JTextArea();
		statusArea.setEditable(false);

		saveButton.addActionListener(e -> {
			try {
				int patientNumber = Integer.parseInt(patientNumberField.getText());
				String medication = medicationField.getText();
				String prescriptionDate = dateField.getText();

				RandomAccessFile OUT = new RandomAccessFile("MedicationHistory.txt", "rw");
				OUT.seek(OUT.length());
				OUT.writeInt(patientNumber);
				OUT.writeUTF(medication);
				OUT.writeUTF(prescriptionDate);
				OUT.close();

				statusArea.setText("Medication history saved successfully for Patient #" + patientNumber);
			} catch (NumberFormatException ex) {
				statusArea.setText("Invalid Patient Number! Please enter a numeric value.");
			} catch (IOException ex) {
				statusArea.setText("An error occurred while saving the medication history.");
			}
		});

		frame.add(inputPanel, BorderLayout.NORTH);
		frame.add(new JScrollPane(statusArea), BorderLayout.CENTER);
		frame.add(saveButton, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	public void viewMedicationHistory(int searchPatientNumber) {
		JFrame frame = new JFrame("Medication History Viewer");
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JTextArea historyArea = new JTextArea();
		historyArea.setEditable(false);

		try {
			RandomAccessFile OUT = new RandomAccessFile("MedicationHistory.txt", "r");
			StringBuilder historyBuilder = new StringBuilder();

			while (OUT.getFilePointer() < OUT.length()) {
				int storedID = OUT.readInt();
				String med = OUT.readUTF();
				String date = OUT.readUTF();

				if (storedID == searchPatientNumber) {
					historyBuilder.append("Medication: ").append(med).append("\n");
					historyBuilder.append("Date: ").append(date).append("\n");
					historyBuilder.append("-----------------------\n");
				}
			}

			historyArea.setText(historyBuilder.toString());
			OUT.close();

			if (historyBuilder.isEmpty()) {
				historyArea.setText("No medication history found for Patient #" + searchPatientNumber);
			}

		} catch (IOException e) {
			historyArea.setText("An error occurred while retrieving medication history.");
		}

		JScrollPane scrollPane = new JScrollPane(historyArea);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	public void initiallizePrescription() {
		JFrame frame = new JFrame("Initialize Prescriptions");
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		JLabel recordsLabel = new JLabel("Number of Records to Initialize:");
		JTextField recordsField = new JTextField();
		JButton initializeButton = new JButton("Initialize");

		inputPanel.add(recordsLabel);
		inputPanel.add(recordsField);

		JTextArea statusArea = new JTextArea();
		statusArea.setEditable(false);

		initializeButton.addActionListener(e -> {
			try {
				int numberOfRecords = Integer.parseInt(recordsField.getText());
				String lName = "Doe"; // Example placeholder
				int refills = 0;

				RandomAccessFile OUT = new RandomAccessFile("Prescriptions.txt", "rw");
				for (int i = 0; i < numberOfRecords; i++) {
					OUT.writeInt(0);
					OUT.writeInt(0);
					OUT.writeUTF(" Unknown ");
					OUT.writeUTF(lName);
					OUT.writeUTF("Nothing");
					OUT.writeInt(refills);
					OUT.writeDouble(0.0);
					OUT.writeUTF(" Unknown");
				}
				OUT.close();

				statusArea.setText("Successfully initialized " + numberOfRecords + " prescription records.");
			} catch (NumberFormatException ex) {
				statusArea.setText("Invalid input! Please enter a numeric value.");
			} catch (IOException ex) {
				statusArea.setText("An error occurred while initializing prescriptions.");
			}
		});

		frame.add(inputPanel, BorderLayout.NORTH);
		frame.add(new JScrollPane(statusArea), BorderLayout.CENTER);
		frame.add(initializeButton, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	public void enterPrescription() {
		JFrame frame = new JFrame("Enter Prescription");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
		JLabel patientIdLabel = new JLabel("Patient ID#: ");
		JTextField patientIdField = new JTextField();
		JLabel doctorIdLabel = new JLabel("Doctor ID#: ");
		JTextField doctorIdField = new JTextField();
		JLabel doctorLastNameLabel = new JLabel("Doctor's Last Name: ");
		JTextField doctorLastNameField = new JTextField();
		JLabel medicationLabel = new JLabel("Medication: ");
		JTextField medicationField = new JTextField();
		JLabel instructionsLabel = new JLabel("Instructions: ");
		JTextField instructionsField = new JTextField();
		JLabel refillsLabel = new JLabel("Refills: ");
		JTextField refillsField = new JTextField();
		JLabel dosageLabel = new JLabel("Dosage: ");
		JTextField dosageField = new JTextField();

		JButton saveButton = new JButton("Save Prescription");
		JButton returnToMenuButton = new JButton("Return to Menu");

		inputPanel.add(patientIdLabel);
		inputPanel.add(patientIdField);
		inputPanel.add(doctorIdLabel);
		inputPanel.add(doctorIdField);
		inputPanel.add(doctorLastNameLabel);
		inputPanel.add(doctorLastNameField);
		inputPanel.add(medicationLabel);
		inputPanel.add(medicationField);
		inputPanel.add(instructionsLabel);
		inputPanel.add(instructionsField);
		inputPanel.add(refillsLabel);
		inputPanel.add(refillsField);
		inputPanel.add(dosageLabel);
		inputPanel.add(dosageField);

		JTextArea statusArea = new JTextArea();
		statusArea.setEditable(false);

		saveButton.addActionListener(e -> {
			try {
				// Input validation
				if (patientIdField.getText().isEmpty() || doctorIdField.getText().isEmpty() ||
						doctorLastNameField.getText().isEmpty() || medicationField.getText().isEmpty() ||
						instructionsField.getText().isEmpty() || refillsField.getText().isEmpty() ||
						dosageField.getText().isEmpty()) {
					statusArea.setText("All fields are required. Please fill them out.");
					return;
				}

				int patientNumber = Integer.parseInt(patientIdField.getText());
				int doctorNumber = Integer.parseInt(doctorIdField.getText());
				String lName = doctorLastNameField.getText();
				String medication = medicationField.getText();
				String instructions = instructionsField.getText();
				int refills = Integer.parseInt(refillsField.getText());
				double dosage = Double.parseDouble(dosageField.getText());
				String prescriptionDate = java.time.LocalDate.now().toString();

				RandomAccessFile OUT = new RandomAccessFile("Prescriptions.txt", "rw");
				long filePointerPosition = (long) (patientNumber - 1) * sizeOfRecord;
				OUT.seek(filePointerPosition);
				OUT.writeInt(patientNumber);
				OUT.writeInt(doctorNumber);
				OUT.writeUTF(String.format("%-15s", lName));
				OUT.writeUTF(String.format("%-10s", medication));
				OUT.writeUTF(String.format("%-60s", instructions));
				OUT.writeInt(refills);
				OUT.writeDouble(dosage);
				OUT.writeUTF(prescriptionDate);
				OUT.close();

				statusArea.setText("Prescription saved successfully for Patient ID#: " + patientNumber);

				// Prompt user to enter another prescription
				int option = JOptionPane.showConfirmDialog(
						saveButton.getParent(),
						"Do you want to enter another prescription?",
						"Continue?",
						JOptionPane.YES_NO_OPTION
				);

				if (option == JOptionPane.YES_OPTION) {
					JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(saveButton);
					currentFrame.dispose(); // Close current frame

					Prescription prescription = new Prescription();
					prescription.enterPrescription(); // Open new instance of the same frame
				}

			} catch (NumberFormatException ex) {
				statusArea.setText("Invalid input! Please ensure all numeric fields are correct.");
			} catch (IOException ex) {
				statusArea.setText("An error occurred while saving the prescription.");
			}
		});
		returnToMenuButton.addActionListener(e -> {
			frame.setVisible(false);
			MainDashboard.main(null);
		});

		frame.add(inputPanel, BorderLayout.NORTH);
		frame.add(new JScrollPane(statusArea), BorderLayout.CENTER);
		frame.add(saveButton,BorderLayout.WEST);
		frame.add(returnToMenuButton,BorderLayout.EAST);
		frame.setVisible(true);
	}

	public void displayPrescriptions(int pid) {
		JFrame frame = new JFrame("View Prescription Details");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JTextArea prescriptionDetailsArea = new JTextArea();
		prescriptionDetailsArea.setEditable(false);

		try {
			RandomAccessFile OUT = new RandomAccessFile("Prescriptions.txt", "r");

			long filePointerPosition = pid - 1;
			OUT.seek(filePointerPosition * sizeOfRecord);

			int patientNumber = OUT.readInt();
			int doctorNumber = OUT.readInt();
			String lName = OUT.readUTF();
			String medication = OUT.readUTF();
			String instructions = OUT.readUTF();
			int refills = OUT.readInt();
			double dosage = OUT.readDouble();
			String prescriptionDate = OUT.readUTF();

			StringBuilder details = new StringBuilder();
			details.append("Patient ID#: ").append(patientNumber).append("\n");
			details.append("Doctor ID#: ").append(doctorNumber).append("\n");
			details.append("Doctor's Last Name: ").append(lName).append("\n");
			details.append("Medication: ").append(medication).append("\n");
			details.append("Instructions: ").append(instructions).append("\n");
			details.append("Refills: ").append(refills).append("\n");
			details.append("Dosage: ").append(dosage).append("\n");
			details.append("Prescription Date: ").append(prescriptionDate).append("\n");

			prescriptionDetailsArea.setText(details.toString());

			OUT.close();
		} catch (IOException e) {
			prescriptionDetailsArea.setText("An error occurred while retrieving the prescription details.");
		}

		JScrollPane scrollPane = new JScrollPane(prescriptionDetailsArea);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	void display() {
		System.out.println("\n\tPrescriptions\n" + "Date: " +prescriptionDate+ "\n\nRx:\n\tPrescription: " +medication+ 
							"\n\tDosage: " +dosage+ "mg");
		
		System.out.println("\nPrescribing Doctor: Dr." +lName+
							"\nLicense# " +doctorNumber);
	}	

	public int getPatientNumber() { return patientNumber; }
	public void setPatientNumber(int patientNumber) { this.patientNumber = patientNumber; }

	public int getDoctorNumber() { return doctorNumber; }
	public void setDoctorNumber(int doctorNumber) { this.doctorNumber = doctorNumber; }

	public String getMedication() { return medication; }
	public void setMedication(String medication) { this.medication = medication; }

	public double getDosage() { return dosage; }
	public void setDosage(double dosage) { this.dosage = dosage; }

	public String getPrescriptionDate() { return prescriptionDate; }
	public void setPrescriptionDate(String prescriptionDate) { this.prescriptionDate = prescriptionDate; }
}
