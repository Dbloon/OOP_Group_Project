package Patient_Management;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PatientManagement {
    String fileName = "Patients.txt";
    ArrayList<Patient> patients = new ArrayList<>();

    public String readPatientData(String pN) {
        try (Scanner scan = new Scanner(new File(fileName))) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.startsWith(pN + ":")) {
                    return line; // Return the matching patient data
                }
            }
            return "Patient_Management.Patient " + pN + " not found in " + fileName;
        } catch (FileNotFoundException e) {
            System.out.println(fileName + " not found");
            return "Error: File not found";
        }
    }

    public void logVisit(String patientName, String doctorAssigned, String purpose, String diagnosis, String treatment, String notes) {
        for (Patient patient : patients) {
            if (patient.name.equals(patientName)) {
                //Visit_Referral_Management.Visit visit = new Visit_Referral_Management.Visit(doctorAssigned, purpose, diagnosis, treatment, notes);
                //patient.addVisit(visit);
                System.out.println("Visit_Referral_Management.Visit logged for patient: " + patientName);
                return;
            }
        }
        System.out.println("Patient_Management.Patient " + patientName + " not found. Please add the patient first.");
    }

    public void addPatient(String name, String id) {
        patients.add(new Patient(name, id));
        System.out.println("Patient_Management.Patient added: " + name);
    }
}