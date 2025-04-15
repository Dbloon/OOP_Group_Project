package Visit_Referral_Management;

import Dashboardpak.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Visit {
    private static final String FILE_NAME = "Visits.dat";
    private static final int RECORD_SIZE = 2048;

    private int visitNumber;
    private int patientNumber;
    private int doctorNumber;
    private Date visitDate;
    private String purposeOfVisit;
    private String diagnosis;
    private String treatment;
    private String doctorNotes;

    public Visit(int visitNumber, int patientNumber, int doctorNumber, Date visitDate, String purposeOfVisit, String diagnosis, String treatment, String doctorNotes) {
        this.visitNumber = visitNumber;
        this.patientNumber = patientNumber;
        this.doctorNumber = doctorNumber;
        this.visitDate = visitDate;
        this.purposeOfVisit = purposeOfVisit;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.doctorNotes = doctorNotes;
    }

    public void displayVisitDetails() {
        System.out.println("Visit_Referral_Management.Visit #: " + visitNumber);
        System.out.println("Patient_Management.Patient #: " + patientNumber);
        System.out.println("Doctor #: " + doctorNumber);
        System.out.println("Date: " + visitDate);
        System.out.println("Purpose: " + purposeOfVisit);
        System.out.println("Diagnosis: " + diagnosis);
        System.out.println("Treatment: " + treatment);
        System.out.println("Notes: " + doctorNotes);
    }

    public int getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(int visitNumber) {
        this.visitNumber = visitNumber;
    }

    public int getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(int patientNumber) {
        this.patientNumber = patientNumber;
    }

    public int getDoctorNumber() {
        return doctorNumber;
    }

    public void setDoctorNumber(int doctorNumber) {
        this.doctorNumber = doctorNumber;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getPurposeOfVisit() {
        return purposeOfVisit;
    }

    public void setPurposeOfVisit(String purposeOfVisit) {
        this.purposeOfVisit = purposeOfVisit;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }

    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }

    // Write a Visit object at a specific position based on patient number
    public void writeVisit(int position, Visit visit) {
        try (RandomAccessFile raf = new RandomAccessFile(FILE_NAME, "rw")) {
            raf.seek((long) position * RECORD_SIZE); // Navigate to specific record
            raf.writeInt(visit.getPatientNumber());
            raf.writeInt(visit.getVisitNumber());
            raf.writeInt(visit.getDoctorNumber());
            raf.writeInt(visit.getVisitDate().getDay());
            raf.writeInt(visit.getVisitDate().getMonth());
            raf.writeInt(visit.getVisitDate().getYear());
            raf.writeUTF(visit.getPurposeOfVisit());
            raf.writeUTF(visit.getDiagnosis());
            raf.writeUTF(visit.getTreatment());
            raf.writeUTF(visit.getDoctorNotes());
        } catch (IOException e) {
            System.out.println("Exception Caught");
            e.printStackTrace();
        }
    }

    // Read a specific Visit object based on position
    public Visit readVisit(int position) {
        Visit visit = null;
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
            file.seek(position * RECORD_SIZE); // Navigate to specific record
            int patientNumber = file.readInt();
            int visitNumber = file.readInt();
            int doctorNumber = file.readInt();
            int visitDay = file.readInt();
            int visitMonth = file.readInt();
            int visitYear = file.readInt();
            String purposeOfVisit = file.readUTF();
            String diagnosis = file.readUTF();
            String treatment = file.readUTF();
            String doctorNotes = file.readUTF();

            visit = new Visit(
                    visitNumber,
                    patientNumber,
                    doctorNumber,
                    new Date(visitDay,visitMonth,visitYear),
                    purposeOfVisit,
                    diagnosis,
                    treatment,
                    doctorNotes
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return visit;
    }
}
