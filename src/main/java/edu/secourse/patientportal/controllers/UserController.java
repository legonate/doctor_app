package edu.secourse.patientportal.controllers;

import edu.secourse.patientportal.services.UserService;
import edu.secourse.patientportal.models.*;

public class UserController {
    UserService us;

    public UserController() {
        us = new UserService();
    }

    public void createDoctor(String username, String email_address, String role, String name, String password) {
        this.us.addDoctor(username, email_address, role, name, password);
    }

    public void createPatient(String username, String email_address, String role, String name, String password) {
        this.us.addDoctor(username, email_address, role, name, password);
    }
}