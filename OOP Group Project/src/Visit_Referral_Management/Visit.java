package Visit_Referral_Management;

//import java.util.Date;

import Dashboardpak.Dashboard;

import javax.swing.*;
import java.awt.*;

public class Visit {
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

}
