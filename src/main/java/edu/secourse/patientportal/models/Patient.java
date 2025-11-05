package edu.secourse.patientportal.models;

public class Patient extends User {
    public Patient(int sessionID, String username, String address, String dob) {
        super(sessionID, username, address, dob);  // Call parent constructor
    }
}