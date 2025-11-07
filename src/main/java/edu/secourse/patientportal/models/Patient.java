package edu.secourse.patientportal.models;

public class Patient extends User {
    public Patient(int account_number, String username, String email_address, String role, String name, String password) {
        super(account_number, username, email_address, role, name, password);  // Call parent constructor
    }
}