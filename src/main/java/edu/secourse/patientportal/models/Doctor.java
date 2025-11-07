package edu.secourse.patientportal.models;

public class Doctor extends User {

    public Doctor(int account_number, String username, String email_address, String role, String name, String password) {
        super(account_number, username, email_address, role, name, password);  // Call User constructor
    }
}