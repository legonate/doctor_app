package edu.secourse.patientportal.controllers;

import edu.secourse.patientportal.services.AppointmentService;
import edu.secourse.patientportal.models.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AppointmentController {
    private AppointmentService us;
    private Scanner sc;
    private DateTimeFormatter dateTimeFormatter;

    public AppointmentController(Scanner sc) {
        this.us = new AppointmentService();
        this.sc = sc;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    public void createAppointment(Patient patient, Doctor doctor) {
        LocalDateTime dateTime = null;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter appointment date and time (yyyy-MM-dd HH:mm): ");
            String dateTimeStr = sc.nextLine();

            try {
                dateTime = LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
                validInput = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date/time format. Please use yyyy-MM-dd HH:mm");
            }
        }

        Appointment appointment = us.createAppointment(patient, doctor, dateTime);
        System.out.println("Appointment created successfully: " + appointment.getAppointmentId());
    }

    /**
     * Cancels an existing appointment
     */
    public void cancelAppointment() {
        System.out.print("Enter appointment ID to cancel: ");
        try {
            int appointmentId = Integer.parseInt(sc.nextLine());

            if (us.cancelAppointment(appointmentId)) {
                System.out.println("Appointment cancelled successfully");
            } else {
                System.out.println("Appointment not found or already cancelled");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid appointment ID");
        }
    }

    /**
     * Lists all appointments in the system
     */
    public void listAppointments() {
        System.out.println("\nAll Appointments");
        for (Appointment appointment : us.getAllAppointments()) {
            displayAppointment(appointment);
        }
    }

    /**
     * Lists appointments for a specific patient
     */
    public void listAppointmentsForPatient(Patient patient) {
        System.out.println("\nAppointments for " + patient.getName());
        for (Appointment appointment : us.getAppointmentsForPatient(patient)) {
            displayAppointment(appointment);
        }
    }

    /**
     * Lists appointments for a specific doctor
     */
    public void listAppointmentsForDoctor(Doctor doctor) {
        System.out.println("\nAppointments for " + doctor.getName());
        for (Appointment appointment : us.getAppointmentsForDoctor(doctor)) {
            displayAppointment(appointment);
        }
    }

    /**
     *displays appointment details
     */
    private void displayAppointment(Appointment appointment) {
        System.out.println("ID: " + appointment.getAppointmentId());
        System.out.println("Patient: " + appointment.getPatient().getName());
        System.out.println("Doctor: " + appointment.getDoctor().getName());
        System.out.println("Date/Time: " + appointment.getDateTime().format(dateTimeFormatter));
        System.out.println("Status: " + appointment.getStatus());
    }

    /**
     * Modifies an existing appointment's date and time
     */
    public void modifyAppointment() {
        System.out.print("Enter appointment ID to modify: ");
        try {
            int appointmentId = Integer.parseInt(sc.nextLine());
            
            LocalDateTime newDateTime = null;
            boolean validInput = false;

            while (!validInput) {
                System.out.print("Enter new date and time (yyyy-MM-dd HH:mm): ");
                String dateTimeStr = sc.nextLine();

                try {
                    newDateTime = LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
                    validInput = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date/time format. Please use yyyy-MM-dd HH:mm");
                }
            }

            if (us.modifyAppointment(appointmentId, newDateTime)) {
                System.out.println("Appointment modified successfully");
            } else {
                System.out.println("Appointment not found or already cancelled");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid appointment ID");
        }
    }

    /**
     * Lists appointments for a specific user by account number.
     * Works for both patients and doctors.
     */
    public void listAppointmentsForUser(User user) {
        if (user instanceof Patient) {
            listAppointmentsForPatient((Patient) user);
        } else if (user instanceof Doctor) {
            listAppointmentsForDoctor((Doctor) user);
        } else {
            System.out.println("User is neither a patient nor a doctor");
        }
    }
}
