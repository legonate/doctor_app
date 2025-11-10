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
        System.out.print("Enter appointment date and time: ");
        String dateTimeStr = sc.nextLine();

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
            Appointment appointment = us.createAppointment(patient, doctor, dateTime);
            System.out.println("Appointment created successfully: " + appointment.getAppointmentId());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date/time. Please use yyyy-MM-dd HH:mm");
        }
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
}