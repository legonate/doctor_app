package edu.secourse.patientportal.services;

import edu.secourse.patientportal.models.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

class UserServiceTest {

    private UserService us;

    @BeforeEach
    void setUp() {
        us = new UserService();
    }

    @org.junit.jupiter.api.Test
    void getUsers() {
        us.addDoctor("doc", "doctor@mail.com", "doctor", "Dr. John", "1234");
        us.addPatient("pat", "patient@mail.com", "patient", "John Smith", "5678");
        ArrayList<User> users = us.getUsers();
        assertEquals(2, users.size());
        assertEquals("doc", users.get(0).getUsername());
        assertEquals("pat", users.get(1).getUsername());
        assertEquals("Dr. John", users.get(0).getName());
        assertEquals("John Smith", users.get(1).getName());
    }

    @org.junit.jupiter.api.Test
    void addDoctor() {
        User doctor = us.addDoctor("doc", "doc@mail.com", "doctor", "Dr. Eve", "pass3");
        assertNotNull(doctor);
        assertEquals("doc", doctor.getUsername());
        assertEquals(1, us.getUsers().size());
        assertInstanceOf(Doctor.class, us.getUsers().getFirst());
    }

    @org.junit.jupiter.api.Test
    void addPatient() {
        User patient = us.addPatient("pat", "pat@mail.com", "patient", "John Doe", "pass4");
        assertNotNull(patient);
        assertEquals("pat", patient.getUsername());
        assertEquals(1, us.getUsers().size());
        assertInstanceOf(Patient.class, us.getUsers().getFirst());
    }

    @org.junit.jupiter.api.Test
    void deleteUser() {
        User doctor = us.addDoctor("doc3", "doc@mail.com", "doctor", "Dr. Mike", "pass5");
        assertEquals(1, us.getUsers().size());
        boolean deleted = us.deleteUser(doctor);
        assertTrue(deleted);
        assertEquals(0, us.getUsers().size());
        User fake = new Doctor(99, "fake", "fake@email.com", "doctor", "fake doc", "pass");
        boolean deleted2 = us.deleteUser(fake);
        assertFalse(deleted2);
    }

    @org.junit.jupiter.api.Test
    void modifyUser() {
        User patient = us.addPatient("pat", "pat@email.com", "patient", "John Smith", "pass6");
        int accountNum = patient.getAccountNumber();
        User updated = new Patient(accountNum, "patup", "patup@email.com", "patient", "John S.", "newpass");
        boolean modified = us.modifyUser(updated);
        assertTrue(modified);
        ArrayList<User> users = us.getUsers();
        assertEquals("patup", users.getFirst().getUsername());
        assertEquals("John S.", users.getFirst().getName());
        User updated2 = new Patient(100, "fake", "fake@mail.com", "patient", "fake pat", "pass");
        boolean modified2 = us.modifyUser(updated2);
        assertFalse(modified2);
    }
}
