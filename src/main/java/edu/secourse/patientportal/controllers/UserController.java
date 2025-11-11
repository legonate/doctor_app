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
                System.out.println(user.getAccountNumber() + ": " + user.getName());
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
                System.out.println(user.getAccountNumber() + ": " + user.getName());
            }
        }
    }

    /**
     * Deletes a user by prompting for their account number.
     * Displays a success or failure message based on the result.
     */
    public void deleteUser() {
        System.out.print("Enter account number of user to delete: ");
        int accountNumber = Integer.parseInt(sc.nextLine());

        // Find the user first
        User userToDelete = null;
        for (User user : this.us.getUsers()) {
            if (user.getAccountNumber() == accountNumber) {
                userToDelete = user;
                break;
            }
        }

        if (userToDelete != null) {
            boolean success = this.us.deleteUser(userToDelete);
            if (success) {
                System.out.println("User deleted successfully");
            } else {
                System.out.println("Failed to delete user");
            }
        } else {
            System.out.println("User not found");
        }
    }

    /**
     * Modifies an existing user by prompting for updated information.
     * User must provide the account number, then updates all fields.
     */
    public void modifyUser() {
        System.out.print("Enter account number of user to modify: ");
        int accountNumber = Integer.parseInt(sc.nextLine());

        // Find the user first
        User existingUser = null;
        for (User user : this.us.getUsers()) {
            if (user.getAccountNumber() == accountNumber) {
                existingUser = user;
                break;
            }
        }

        if (existingUser == null) {
            System.out.println("User not found");
            return;
        }

        System.out.println("Modifying user: " + existingUser.getName());
        System.out.print("Enter new username (current: " + existingUser.getUsername() + "): ");
        String username = sc.nextLine();
        System.out.print("Enter new email (current: " + existingUser.getEmailAddress() + "): ");
        String email_address = sc.nextLine();
        System.out.print("Enter new role (current: " + existingUser.getRole() + "): ");
        String role = sc.nextLine();
        System.out.print("Enter new name (current: " + existingUser.getName() + "): ");
        String name = sc.nextLine();
        System.out.print("Enter new password (leave blank to keep current): ");
        String password = sc.nextLine();

        String hashedPassword;
        if (password.isEmpty()) {
            hashedPassword = existingUser.getPassword(); // Keep existing password
        } else {
            hashedPassword = hashPassword(password);
        }

        // Create updated user object of the same type
        User updatedUser;
        if (existingUser instanceof Patient) {
            updatedUser = new Patient(accountNumber, username, email_address, role, name, hashedPassword);
        } else {
            updatedUser = new Doctor(accountNumber, username, email_address, role, name, hashedPassword);
        }

        boolean success = this.us.modifyUser(updatedUser);
        if (success) {
            System.out.println("User modified successfully");
        } else {
            System.out.println("Failed to modify user");
        }
    }

    /**
     * Finds a doctor by their account number.
     *
     * @param accountNumber the account number to search for
     * @return the Doctor object if found, null otherwise
     */
    public Doctor findDoctorByAccountNumber(int accountNumber) {
        for (User user : this.us.getUsers()) {
            if (user instanceof Doctor && user.getAccountNumber() == accountNumber) {
                return (Doctor) user;
            }
        }
        return null;
    }

    /**
     * Finds a patient by their account number.
     *
     * @param accountNumber the account number to search for
     * @return the Patient object if found, null otherwise
     */
    public Patient findPatientByAccountNumber(int accountNumber) {
        for (User user : this.us.getUsers()) {
            if (user instanceof Patient && user.getAccountNumber() == accountNumber) {
                return (Patient) user;
            }
        }
        return null;
    }

    /**
     * Creates an appointment by prompting for patient and doctor account numbers.
     * Handles the lookup and delegates to the AppointmentController.
     *
     * @param ac the AppointmentController to use for creating the appointment
     */
    public void createAppointmentMenu(AppointmentController ac) {
        listPatients();
        System.out.print("Enter patient account number: ");
        try {
            int patientAccountNum = Integer.parseInt(sc.nextLine());
            Patient patient = findPatientByAccountNumber(patientAccountNum);

            if (patient == null) {
                System.out.println("Patient not found");
                return;
            }

            listDoctors();
            System.out.print("Enter doctor account number: ");
            int doctorAccountNum = Integer.parseInt(sc.nextLine());
            Doctor doctor = findDoctorByAccountNumber(doctorAccountNum);

            if (doctor == null) {
                System.out.println("Doctor not found");
                return;
            }

            ac.createAppointment(patient, doctor);
        } catch (NumberFormatException e) {
            System.out.println("Invalid account number");
        }
    }
}
