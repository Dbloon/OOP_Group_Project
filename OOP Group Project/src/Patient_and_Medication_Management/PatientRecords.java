package Patient_and_Medication_Management;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class PatientRecords {
	
	private static final int sizeOfRecord = 92;
	private static final int numberOfRecords = 10;
	private static final Scanner INPUT = new Scanner(System.in);
	private static int filePointerPosition = 0;
	
	private int patientNumber = 0;
	private String patientName = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String patientDob = "dd/mm/yyyy";
	private String gender = "ABCDE";
	private int phoneNumber = 0;
	private String address = "ABCDEFGHIJKLMNOPQRSTUVWXYZAAAAAAAAA";
	
	public PatientRecords() {
		this.patientNumber = 0;
		this.patientName = " Unknown ";
		this.patientDob = " Unknown ";
		this.gender = " Unknown ";
		this.phoneNumber = 0;
		this.address = " Unknown";
	}
	
	public PatientRecords(int patientNumber, String patientName, String patientDob, String gender, int phoneNumber, String address) {
		this.patientNumber = patientNumber;
		this.patientName = patientName;
		this.patientDob = patientDob;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	public void initializeRec() {
		
		try {
			RandomAccessFile OUT = new RandomAccessFile("PatientData.txt", "rw");
			
			for(int i=0; i < numberOfRecords ; i++) {
				OUT.writeInt(0);
				OUT.writeUTF(patientName);
				OUT.writeUTF(patientDob);
				OUT.writeUTF(gender);
				OUT.writeInt(0);
				OUT.writeUTF(address);
			}
			
			OUT.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void enterData() {
		String TEMP = " ";
		
		try {
			RandomAccessFile OUT = new RandomAccessFile("PatientData.txt", "rw");
			
			System.out.print("Patient ID#: ");			
			try {
				patientNumber = Integer.parseInt(INPUT.nextLine());
			} catch (NumberFormatException e) {				
				e.printStackTrace();
			}
			
			System.out.print("Patient Name: ");
			TEMP = INPUT.nextLine();
			
			if (TEMP.length() > patientName.length()) {
				patientName = TEMP.substring(0,26);
			}else if(TEMP.length() < patientName.length()) {
				int DIFFERENCE = patientName.length() - TEMP.length();
				
				for (int x = 0; x < DIFFERENCE; x++) {
					TEMP = TEMP + " ";
					
					patientName = TEMP;
				}
			}
			else {
				patientName = TEMP;
			}
			
			
			System.out.print("Date of Birth: ");
			TEMP = INPUT.nextLine();
			
			if (TEMP.length() > patientDob.length()) {
				patientDob = TEMP.substring(0,10);
			}else if(TEMP.length() < patientDob.length()) {
				int DIFFERENCE = patientDob.length() - TEMP.length();
				
				for (int x = 0; x < DIFFERENCE; x++) {
					TEMP = TEMP + " ";
					
					patientDob = TEMP;
				}
			}
			else {
				patientDob = TEMP;
			}
			
			
			System.out.print("Gender: ");
			TEMP = INPUT.nextLine();
			
			if (TEMP.length() > gender.length()) {
				gender = TEMP.substring(0,5);
			}else if(TEMP.length() < gender.length()) {
				int DIFFERENCE = gender.length() - TEMP.length();
				
				for (int x = 0; x < DIFFERENCE; x++) {
					TEMP = TEMP + " ";
					
					gender = TEMP;
				}
			}
			else {
				gender = TEMP;
			}
			
			
			System.out.print("Patient Phone#: ");			
			try {
				phoneNumber = Integer.parseInt(INPUT.nextLine());
			} catch (NumberFormatException e) {				
				e.printStackTrace();
			}
			
			
			System.out.print("Address: ");
			TEMP = INPUT.nextLine();
			
			if (TEMP.length() > address.length()) {
				address = TEMP.substring(0,46);
			}else if(TEMP.length() < address.length()) {
				int DIFFERENCE = address.length() - TEMP.length();
				
				for (int x = 0; x < DIFFERENCE; x++) {
					TEMP = TEMP + " ";
					
					address = TEMP;
				}
			}
			else {
				address = TEMP;
			}
			
			filePointerPosition = patientNumber - 1;
			OUT.seek((long) filePointerPosition * sizeOfRecord);
			
			OUT.writeInt(patientNumber);
			OUT.writeUTF(patientName);
			OUT.writeUTF(patientDob);
			OUT.writeUTF(gender);
			OUT.writeInt(phoneNumber);
			OUT.writeUTF(address);			
			
			OUT.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void displayRecords(int pid) {
		JFrame frame = new JFrame("Patient Record Viewer");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JTextArea recordsArea = new JTextArea();
		recordsArea.setEditable(false);

		try {
			RandomAccessFile OUT = new RandomAccessFile("PatientData.txt", "r");

			long filePointerPosition = pid - 1;
			OUT.seek(filePointerPosition * sizeOfRecord);

			int patientNumber = OUT.readInt();
			String patientName = OUT.readUTF();
			String patientDob = OUT.readUTF();
			String gender = OUT.readUTF();
			int phoneNumber = OUT.readInt();
			String address = OUT.readUTF();

			StringBuilder details = new StringBuilder();
			details.append("Patient ID#: ").append(patientNumber).append("\n");
			details.append("Patient Name: ").append(patientName).append("\n");
			details.append("Date of Birth: ").append(patientDob).append("\n");
			details.append("Gender: ").append(gender).append("\n");
			details.append("Phone Number: ").append(phoneNumber).append("\n");
			details.append("Address: ").append(address).append("\n");

			recordsArea.setText(details.toString());

			OUT.close();
		} catch (IOException e) {
			recordsArea.setText("An error occurred while retrieving patient records.");
		}

		JScrollPane scrollPane = new JScrollPane(recordsArea);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	/*public void displayRecords(int pid) {

		try {
			RandomAccessFile OUT = new RandomAccessFile("PatientData.txt", "r");


			filePointerPosition = pid - 1;
			OUT.seek((long) filePointerPosition * sizeOfRecord);

			patientNumber = OUT.readInt();
			patientName = OUT.readUTF();
			patientDob = OUT.readUTF();
			gender = OUT.readUTF();
			phoneNumber = OUT.readInt();
			address = OUT.readUTF();

			display();
			OUT.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}*/

	void display() {
		System.out.println("\tPatientRecords \n"
				+ "\nPatient ID#: " + patientNumber + "\nPatient Name: " + patientName + "\nDate of Birth: "
				+ patientDob + "\nGender: " + gender + "\nPhone Number: " + phoneNumber + "\nAddress: " + address );
	}
	
	
	public static int getFilePointerPosition() { return filePointerPosition; }
	public static void setFilePointerPosition(int filePointerPosition) { PatientRecords.filePointerPosition = filePointerPosition; }

	public int getPatientNumber() { return patientNumber; }
	public void setPatientNumber(int patientNumber) { this.patientNumber = patientNumber; }

	public String getPatientName() { return patientName; }
	public void setPatientName(String patientName) { this.patientName = patientName; }

	public String getPatientDob() { return patientDob; }
	public void setPatientDob(String patientDob) { this.patientDob = patientDob; }

	public String getGender() { return gender; }
	public void setGender(String gender) { this.gender = gender; }

	public int getPhoneNumber() { return phoneNumber; }
	public void setPhoneNumber(int phoneNumber) { this.phoneNumber = phoneNumber; }

	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }

	public static int getSizeofrecord() { return sizeOfRecord; }
	public static int getNumberofrecords() { return numberOfRecords; }
	
	public static Scanner getInput() { return INPUT; }

	
}