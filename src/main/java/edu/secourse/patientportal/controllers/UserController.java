package edu.secourse.patientportal.controllers;

import edu.secourse.patientportal.services.UserService;
import java.util.Scanner;
import edu.secourse.patientportal.models.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

/**
 * Controller class responsible for managing user-related operations
 * including creating doctors, patients, and listing users.
 */
public class UserController {
    UserService us;
    Scanner sc;
    
    /**
     * Constructs a UserController with the specified Scanner for input.
     *
     * @param sc the Scanner object used for reading user input
     */
    public UserController(Scanner sc) {
        us = new UserService();
        this.sc = sc;
    }
    
    /**
     * Hashes a plain text password using the SHA-256 algorithm.
     *
     * @param plainPassword the plain text password to be hashed
     * @return the hexadecimal string representation of the hashed password
     * @throws RuntimeException if the SHA-256 algorithm is not available
     */
    public static String hashPassword(String plainPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(plainPassword.getBytes());
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }
    
    /**
     * Creates a new doctor by prompting the user for required information.
     * Collects username, email, role, name, and password, then hashes the
     * password before storing the doctor in the system.
     */
    public void createDoctor() {
        System.out.print("enter Username: ");
        String username = sc.nextLine();
        System.out.print("enter email: ");
        String email_address = sc.nextLine();
        System.out.print("enter role: ");
        String role = sc.nextLine();
        System.out.print("enter name: ");
        String name = sc.nextLine();
        System.out.print("enter password: ");
        String password = sc.nextLine();

        // Hash the password before storing
        String hashedPassword = hashPassword(password);
        this.us.addDoctor(username, email_address, role, name, hashedPassword);
        System.out.println("Doctor Created");
    }
    
    /**
     * Creates a new patient by prompting the user for required information.
     * Collects username, email, role, name, and password, then hashes the
     * password before storing the patient in the system.
     */
    public void createPatient() {
        System.out.print("enter Username: ");
        String username = sc.nextLine();
        System.out.print("enter email: ");
        String email_address = sc.nextLine();
        System.out.print("enter role: ");
        String role = sc.nextLine();
        System.out.print("enter name: ");
        String name = sc.nextLine();
        System.out.print("enter password: ");
        String password = sc.nextLine();

        // Hash the password before storing
        String hashedPassword = hashPassword(password);
        this.us.addPatient(username, email_address, role, name, hashedPassword);
        System.out.println("Patient Created");
    }
    
    /**
     * Lists all patients in the system by printing their names to the console.
     * Only displays users who are instances of the Patient class.
     */
    public void listPatients() {
        System.out.println("Patients:");
        for (User user : this.us.getUsers()) {
            if (user instanceof Patient) {
                System.out.println(user.getName());
            }
        }
    }
    
    /**
     * Lists all doctors in the system by printing their names to the console.
     * Only displays users who are instances of the Doctor class.
     */
    public void listDoctors() {
        System.out.println("Doctors:");
        for (User user : this.us.getUsers()) {
            if (user instanceof Doctor) {
                System.out.println(user.getName());
            }
        }
    }
}
