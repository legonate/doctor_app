package edu.secourse.patientportal.services;

import java.util.ArrayList;

public class UserService {
    ArrayList<User> userArr;
    UserService us = new UserService();

    public UserService() {
        userArr = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return (ArrayList<User>)userArr.clone();
    }

    public void addUser(User user) {
        userArr.add(user);
    }
}