package edu.secourse.patientportal.controllers;

import edu.secourse.patientportal.models.Doctor;
import edu.secourse.patientportal.models.Patient;
import edu.secourse.patientportal.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Integration tests for the console-based AppointmentController.
 *
 * This test class works by:
 * 1. Simulating user input (System.in) with a ByteArrayInputStream.
 * 2. Capturing console output (System.out) with a ByteArrayOutputStream.
 *
 * It tests the controller and the real AppointmentService together.
 */
class AppointmentControllerTest {

    private AppointmentController appointmentController;

    // These are used to capture System.out
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    // These are used to simulate System.in
    private final InputStream originalIn = System.in;

    // Dummy data for tests
    private Patient testPatient;
    private Doctor testDoctor;
    private User genericUser; // For the listAppointmentsForUser test

    @BeforeEach
    void setUp() {
        // Redirect System.out to our stream
        System.setOut(new PrintStream(outContent));

        // Create dummy model objects
        testPatient = new Patient(1, "testPatient", "p@a.com", "patient", "Test Patient", "pass");
        testDoctor = new Doctor(2, "testDoctor", "d@a.com", "doctor", "Test Doctor", "pass");
        genericUser = new User(3, "genUser", "g@a.com", "user", "Generic User", "pass");
    }

    @AfterEach
    void tearDown() {
        // Restore original System.in and System.out
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    /**
     * Helper method to simulate user input.
     * @param input The string you want to "type" into the console.
     * @return A Scanner initialized with that string.
     */
    private Scanner createScanner(String input) {
        // Add a newline to simulate pressing 'Enter'
        ByteArrayInputStream inContent = new ByteArrayInputStream((input + "\n").getBytes());
        System.setIn(inContent);
        return new Scanner(System.in);
    }

    /**
     * Helper to create a scanner with multiple lines of input.
     */
    private Scanner createMultiLineScanner(String... lines) {
        String input = String.join("\n", lines) + "\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        return new Scanner(System.in);
    }

    @Test
    void createAppointment() {
        // --- Arrange ---
        Scanner testScanner = createScanner("2025-10-20 14:30");
        appointmentController = new AppointmentController(testScanner);

        // --- Act ---
        appointmentController.createAppointment(testPatient, testDoctor);

        // --- Assert ---
        String output = outContent.toString();
        assertTrue(output.contains("Enter appointment date and time"));
        assertTrue(output.contains("Appointment created successfully"));
    }

    @Test
    void createAppointment_handlesInvalidDateFormat() {
        // --- Arrange ---
        // Simulate user typing a bad date, then a good date
        Scanner testScanner = createMultiLineScanner("bad-date", "2025-10-20 14:30");
        appointmentController = new AppointmentController(testScanner);

        // --- Act ---
        appointmentController.createAppointment(testPatient, testDoctor);

        // --- Assert ---
        String output = outContent.toString();
        assertTrue(output.contains("Invalid date/time format. Please use yyyy-MM-dd HH:mm"));
        assertTrue(output.contains("Appointment created successfully"));
    }

    @Test
    void cancelAppointment_notFound() {
        // --- Arrange ---
        // Test cancelling an ID that doesn't exist
        Scanner testScanner = createScanner("999");
        appointmentController = new AppointmentController(testScanner);

        // --- Act ---
        appointmentController.cancelAppointment();

        // --- Assert ---
        String output = outContent.toString();
        assertTrue(output.contains("Enter appointment ID to cancel:"));
        assertTrue(output.contains("Appointment not found or already cancelled"));
    }

    @Test
    void cancelAppointment_invalidInput() {
        // --- Arrange ---
        // Test typing "abc" instead of a number
        Scanner testScanner = createScanner("abc");
        appointmentController = new AppointmentController(testScanner);

        // --- Act ---
        appointmentController.cancelAppointment();

        // --- Assert ---
        String output = outContent.toString();
        assertTrue(output.contains("Invalid appointment ID"));
    }

    // Note: A test for successful cancellation is brittle, as it depends on
    // an appointment existing first. We'll test the "not found" case instead.


    @Test
    void listAppointments() {
        // --- Arrange ---
        // This method doesn't take input, so an empty scanner is fine.
        Scanner testScanner = createScanner("");
        appointmentController = new AppointmentController(testScanner);

        // --- Act ---
        appointmentController.listAppointments();

        // --- Assert ---
        String output = outContent.toString();
        assertTrue(output.contains("All Appointments"));
        // We can't be sure any appointments exist, but we know the header should print.
    }

    @Test
    void listAppointmentsForPatient() {
        // --- Arrange ---
        Scanner testScanner = createScanner("");
        appointmentController = new AppointmentController(testScanner);

        // --- Act ---
        appointmentController.listAppointmentsForPatient(testPatient);

        // --- Assert ---
        String output = outContent.toString();
        assertTrue(output.contains("Appointments for Test Patient"));
    }

    @Test
    void listAppointmentsForDoctor() {
        // --- Arrange ---
        Scanner testScanner = createScanner("");
        appointmentController = new AppointmentController(testScanner);

        // --- Act ---
        appointmentController.listAppointmentsForDoctor(testDoctor);

        // --- Assert ---
        String output = outContent.toString();
        assertTrue(output.contains("Appointments for Test Doctor"));
    }

    @Test
    void modifyAppointment_notFound() {
        // --- Arrange ---
        // Try to modify an ID that doesn't exist
        Scanner testScanner = createMultiLineScanner("999", "2025-12-01 09:00");
        appointmentController = new AppointmentController(testScanner);

        // --- Act ---
        appointmentController.modifyAppointment();

        // --- Assert ---
        String output = outContent.toString();
        assertTrue(output.contains("Enter appointment ID to modify:"));
        assertTrue(output.contains("Enter new date and time"));
        assertTrue(output.contains("Appointment not found or already cancelled"));
    }

    @Test
    void modifyAppointment_invalidDateTime() {
        // --- Arrange ---
        // Try to modify with a bad date, then a good one
        Scanner testScanner = createMultiLineScanner("999", "bad-date", "2025-12-01 09:00");
        appointmentController = new AppointmentController(testScanner);

        // --- Act ---
        appointmentController.modifyAppointment();

        // --- Assert ---
        String output = outContent.toString();
        assertTrue(output.contains("Invalid date/time format. Please use yyyy-MM-dd HH:mm"));
        assertTrue(output.contains("Appointment not found or already cancelled"));
    }

    @Test
    void listAppointmentsForUser_asPatient() {
        // --- Arrange ---
        Scanner testScanner = createScanner("");
        appointmentController = new AppointmentController(testScanner);

        // --- Act ---
        appointmentController.listAppointmentsForUser(testPatient);

        // --- Assert ---
        String output = outContent.toString();
        // Check that it correctly identified the user as a Patient
        assertTrue(output.contains("Appointments for Test Patient"));
    }

    @Test
    void listAppointmentsForUser_asDoctor() {
        // --- Arrange ---
        Scanner testScanner = createScanner("");
        appointmentController = new AppointmentController(testScanner);

        // --- Act ---
        appointmentController.listAppointmentsForUser(testDoctor);

        // --- Assert ---
        String output = outContent.toString();
        // Check that it correctly identified the user as a Doctor
        assertTrue(output.contains("Appointments for Test Doctor"));
    }

    @Test
    void listAppointmentsForUser_asNeither() {
        // --- Arrange ---
        Scanner testScanner = createScanner("");
        appointmentController = new AppointmentController(testScanner);

        // --- Act ---
        appointmentController.listAppointmentsForUser(genericUser);

        // --- Assert ---
        String output = outContent.toString();
        // Check that it correctly identified the user as neither
        assertTrue(output.contains("User is neither a patient nor a doctor"));
    }
}