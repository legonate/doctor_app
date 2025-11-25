package edu.secourse.patientportal.controllers;

import edu.secourse.patientportal.models.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    // Setup environment
    private Scanner sc;
    private UserController uc;
    private AppointmentController ac;
    private final PrintStream originalOut = System.out;
    private final InputStream originalSystemIn = System.in;
    private final ByteArrayOutputStream testOut = new ByteArrayOutputStream();

    @AfterEach
    void restoreSystemIn() {
        // Restore the original System.in after each test
        System.setIn(originalSystemIn);
        System.setOut(originalOut);
    }

    @Test
    void hashPassword() {

    }

    @Test
    void createDoctor() {
        String simInput = "create doctor\njohnPork\njohnpork@gmail.com\nDoctor\nJohn Pork\nham1234";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simInput.getBytes());
        Scanner sc = new Scanner(testIn);
        uc = new UserController(sc);
        ac = new AppointmentController(sc);
        System.setOut(new PrintStream(testOut));
        System.setIn(testIn);

        uc.createDoctor();

        assertTrue(testOut.toString().contains("Doctor Created"));
    }

    @Test
    void createPatient() {
        String simInput = "create doctor\njohnPork\njohnpork@gmail.com\nPatient\nJohn Pork\nham1234";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simInput.getBytes());
        Scanner sc = new Scanner(testIn);
        uc = new UserController(sc);
        ac = new AppointmentController(sc);
        System.setOut(new PrintStream(testOut));
        System.setIn(testIn);

        uc.createPatient();
        assertTrue(testOut.toString().contains("Patient Created"));
    }

    @Test
    void listPatients() {
        String simInput = "create doctor\njohnPork\njohnpork@gmail.com\nPatient\nJohn Pork\nham1234";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simInput.getBytes());
        Scanner sc = new Scanner(testIn);
        uc = new UserController(sc);
        ac = new AppointmentController(sc);
        System.setOut(new PrintStream(testOut));
        System.setIn(testIn);

        uc.createPatient();
        uc.listPatients();

        assertTrue(testOut.toString().contains("John Pork"));
    }

    @Test
    void listDoctors() {
        String simInput = "create doctor\njohnPork\njohnpork@gmail.com\nPatient\nJohn Pork\nham1234";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simInput.getBytes());
        Scanner sc = new Scanner(testIn);
        uc = new UserController(sc);
        ac = new AppointmentController(sc);
        System.setOut(new PrintStream(testOut));
        System.setIn(testIn);

        uc.createPatient();
        uc.listPatients();

        assertTrue(testOut.toString().contains("John Pork"));
    }

    @Test
    void deleteUser() {
        String simInput = "create doctor\njohnPork\njohnpork@gmail.com\nPatient\nJohn Pork\nham1234\n0";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simInput.getBytes());
        Scanner sc = new Scanner(testIn);
        uc = new UserController(sc);
        ac = new AppointmentController(sc);
        System.setOut(new PrintStream(testOut));
        System.setIn(testIn);

        uc.createPatient();
        uc.deleteUser();
        uc.listDoctors();

        assertFalse(testOut.toString().contains("0")); // test that user no longer exists
    }

    @Test
    void modifyUser() {
        System.setIn(new ByteArrayInputStream("BobJon\nbobjon@gmail.com\npatient\nBob Jonson\n123456 \n0\nBobJon\nbobjon2@gmail.com\npatient\nBob Parker\n123456".getBytes()));
        uc = new UserController(new Scanner(System.in));
        System.setOut(new PrintStream(testOut));


        uc.createPatient();
        uc.modifyUser();
        uc.listPatients();
        assertTrue(testOut.toString().contains("0: Bob Parker"));
    }

    @Test
    void findDoctorByAccountNumber() {
        System.setIn(new ByteArrayInputStream("BobJon\nbobjon@gmail.com\nDoctor\nBob Jonson\n123456".getBytes()));
        uc = new UserController(new Scanner(System.in));
        System.setOut(new PrintStream(testOut));


        uc.createDoctor();
        Patient t = uc.findPatientByAccountNumber(0);
        assertTrue(t.getName().equals("Bob Jonson"));
    }

    @Test
    void findPatientByAccountNumber() {
        System.setIn(new ByteArrayInputStream("BobJon\nbobjon@gmail.com\npatient\nBob Jonson\n123456".getBytes()));
        uc = new UserController(new Scanner(System.in));
        System.setOut(new PrintStream(testOut));


        uc.createPatient();
        Patient t = uc.findPatientByAccountNumber(0);
        assertTrue(t.getName().equals("Bob Jonson"));
    }

    @Test
    void createAppointmentMenu() {
        System.setIn(new ByteArrayInputStream(("BobJon\nbobjon@gmail.com\npatient\nBob Jonson\n123456\nBillyDoctor\n" +
                "billyDoc@gmail.com\nDoctor\nWilliam Doctor\n123456w\n0\n1\n2025-11-29 11:00").getBytes()));
        sc = new Scanner(System.in);
        uc = new UserController(sc);
        ac = new AppointmentController(sc);
        System.setOut(new PrintStream(testOut));


        uc.createPatient();
        uc.createDoctor();
        uc.createAppointmentMenu(ac);
        assertTrue(testOut.toString().contains("Appointment created successfully: 1"));
    }

    @Test
    void listAppointmentsForUser() {
        System.setIn(new ByteArrayInputStream(("BobJon\nbobjon@gmail.com\npatient\nBob Jonson\n123456\nBillyDoctor\n" +
                "billyDoc@gmail.com\nDoctor\nWilliam Doctor\n123456w\n0\n1\n2025-11-29 11:00\n0").getBytes()));
        sc = new Scanner(System.in);
        uc = new UserController(sc);
        ac = new AppointmentController(sc);
        System.setOut(new PrintStream(testOut));


        uc.createPatient();
        uc.createDoctor();
        uc.createAppointmentMenu(ac);
        uc.listAppointmentsForUser(ac);
        assertTrue(testOut.toString().contains("Appointments for Bob Jonson"));
    }
}