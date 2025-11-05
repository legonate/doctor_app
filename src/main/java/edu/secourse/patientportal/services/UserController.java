package edu.secourse.patientportal.services;

public class UserController {
    UserService us;

    public UserController() {
        us = new UserService();
    }

    public User createUser(String username, String address, String dob) {
        User u = new User(username, address, dob);
        this.us.addUser(u);
        return u;
    }
}