package edu.secourse.patientportal.models;

/**
 * Represents a patient user in the patient portal system.
 * Inherits user account properties and behavior from the User class.
 */
public class Patient extends User {
    /**
     * Constructs a new Patient object with the specified account details.
     *
     * @param account_number the unique account number for the patient
     * @param username the username used for patient login
     * @param email_address the patient's email address
     * @param role the role assigned to this user (typically "patient")
     * @param name the full name of the patient
     * @param password the patient's account password
     */
    public Patient(int account_number, String username, String email_address, String role, String name, String password) {
        super(account_number, username, email_address, role, name, password);  // Call parent constructor
    }
}
