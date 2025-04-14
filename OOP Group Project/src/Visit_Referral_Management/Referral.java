package Visit_Referral_Management;

public class Referral {
    private int referralId;
    private int patientNumber;
    private int referringDoctorNumber;
    private String specialistType;
    private String status; // "Pending", "Accepted", "Completed"
    private String feedback;

    public Referral(int referralId, int patientNumber, int referringDoctorNumber, String specialistType, String status, String feedback) {
        this.referralId = referralId;
        this.patientNumber = patientNumber;
        this.referringDoctorNumber = referringDoctorNumber;
        this.specialistType = specialistType;
        this.status = status;
        this.feedback = feedback;
    }

    public void displayReferralDetails() {
        System.out.println("Visit_Referral_Management.Referral ID: " + referralId);
        System.out.println("Patient_Management.Patient #: " + patientNumber);
        System.out.println("Referring Doctor #: " + referringDoctorNumber);
        System.out.println("Specialist: " + specialistType);
        System.out.println("Status: " + status);
        System.out.println("Feedback: " + feedback);
    }

    public int getReferralId() {
        return referralId;
    }

    public void setReferralId(int referralId) {
        this.referralId = referralId;
    }

    public int getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(int patientNumber) {
        this.patientNumber = patientNumber;
    }

    public int getReferringDoctorNumber() {
        return referringDoctorNumber;
    }

    public void setReferringDoctorNumber(int referringDoctorNumber) {
        this.referringDoctorNumber = referringDoctorNumber;
    }

    public String getSpecialistType() {
        return specialistType;
    }

    public void setSpecialistType(String specialistType) {
        this.specialistType = specialistType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

}
