package Patient_Management;

import Visit_Referral_Management.Visit;

import java.util.ArrayList;

public class Patient {
    String name;
    String id;
    ArrayList<Visit> visits;

    public Patient(String name, String id) {
        this.name = name;
        this.id = id;
        this.visits = new ArrayList<>();
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
    }
}