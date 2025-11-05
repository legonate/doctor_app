package edu.secourse.patientportal.models;

public class Doctor extends User {

    public Doctor(int sessionID, String username, String address, String dob) {
        super(sessionID, username, address, dob);  // Call parent constructor
    }
}