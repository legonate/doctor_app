package edu.secourse.patientportal.services;

import edu.secourse.patientportal.models.*;
import java.util.ArrayList;

/**
 * Provides user management services for the patient portal system.
 * Handles creation and retrieval of users such as doctors and patients.
 */
public class UserService {
    /** 
     * A list that stores all users managed by this service. 
     */
    ArrayList<User> userArr;

    /**
     * Constructs a new UserService with an empty user list.
     */
    public UserService() {
        userArr = new ArrayList<>();
    }

    /**
     * Returns a copy of the list of all registered users.
     *
     * @return a cloned ArrayList containing all users
     */
    public ArrayList<User> getUsers() {
        return (ArrayList<User>) userArr.clone();
    }

    /**
     * Adds a new doctor to the user list.
     *
     * @param username the username of the doctor
     * @param email_address the email address of the doctor
     * @param role the role assigned to the user (typically "doctor")
     * @param name the full name of the doctor
     * @param password the password for the doctor's account
     * @return the newly created User object representing the doctor
     */
    public User addDoctor(String username, String email_address, String role, String name, String password) {
        User user = new Doctor(userArr.size(), username, email_address, role, name, password);
        userArr.add(user);
        return user;
    }

    /**
     * Adds a new patient to the user list.
     *
     * @param username the username of the patient
     * @param email_address the email address of the patient
     * @param role the role assigned to the user (typically "patient")
     * @param name the full name of the patient
     * @param password the password for the patient's account
     * @return the newly created User object representing the patient
     */
    public User addPatient(String username, String email_address, String role, String name, String password) {
        User user = new Patient(userArr.size(), username, email_address, role, name, password);
        userArr.add(user);
        return user;
    }

    /**
     * Removes a user from the user list.
     *
     * @param user the User object to remove
     * @return true if the user was successfully removed, false if not found
     */
    public boolean deleteUser(User user) {
        for (int i = 0; i < userArr.size(); i++) {
            if (userArr.get(i).getAccountNumber() == user.getAccountNumber()) {
                userArr.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Modifies an existing user in the user list.
     * Replaces the user with the same account number with the provided user object.
     *
     * @param updatedUser the User object with updated information
     * @return true if the user was successfully modified, false if not found
     */
    public boolean modifyUser(User updatedUser) {
        for (int i = 0; i < userArr.size(); i++) {
            if (userArr.get(i).getAccountNumber() == updatedUser.getAccountNumber()) {
                userArr.set(i, updatedUser);
                return true;
            }
        }
        return false;
    }
}

