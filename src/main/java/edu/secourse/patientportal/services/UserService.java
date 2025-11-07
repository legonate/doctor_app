package edu.secourse.patientportal.services;

import edu.secourse.patientportal.models.*;
import java.util.ArrayList;

public class UserService {
    ArrayList<User> userArr;

    public UserService() {
        userArr = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return (ArrayList<User>)userArr.clone();
    }

    public User addDoctor(String username, String email_address, String role, String name, String password) {
        User user = new User(userArr.size(), username, email_address, role, name, password);
        userArr.add(user);
        return user;
    }

    public User addPatient(String username, String email_address, String role, String name, String password) {
        User user = new User(userArr.size(), username, email_address, role, name, password);
        userArr.add(user);
        return user;
    }
}