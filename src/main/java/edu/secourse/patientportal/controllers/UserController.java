package edu.secourse.patientportal.controllers;

import edu.secourse.patientportal.services.UserService;
import java.util.Scanner;
import edu.secourse.patientportal.models.*;

public class UserController {
    UserService us;
    Scanner sc;

    public UserController(Scanner sc) {
        us = new UserService();
        this.sc = sc;
    }

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
        this.us.addDoctor(username, email_address, role, name, password);
        System.out.println("Doctor Created");
    }

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
        this.us.addPatient(username, email_address, role, name, password);
        System.out.println("Patient Created");
    }

    public void listPatients() {
        System.out.println("Patients:");
        for (User user : this.us.getUsers()) {
            if (user instanceof Patient) {
                System.out.println(user.getName());
            }
        }
    }
    public void listDoctors() {
        System.out.println("Doctors:");
        for (User user : this.us.getUsers()) {
            if (user instanceof Doctor) {
                System.out.println(user.getName());
            }
        }
    }
}