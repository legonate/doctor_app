package edu.secourse.patientportal.controllers;

import edu.secourse.patientportal.services.UserService;
import edu.secourse.patientportal.models.*;

public class UserController {
    UserService us;

    public UserController() {
        us = new UserService();
    }

    public Doctor createDoctor(String username, String address, String dob) {
        Doctor d = new Doctor(1, username, address, dob);
        this.us.addUser(d);
        return d;
    }
    public Patient createPatient(String username, String address, String dob) {
        Patient p = new Patient(1, username, address, dob);
        this.us.addUser(p);
        return p;
    }
}