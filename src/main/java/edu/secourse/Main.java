package edu.secourse;

import edu.secourse.patientportal.controllers.AppointmentController;
import edu.secourse.patientportal.controllers.UserController;

import java.util.Scanner;

/**
 * Main entry point for the Doctor App application.
 * Provides a command-line interface for managing patients, doctors, and appointments.
 */
public class Main {
    /**
     * Main method that runs the Doctor App application.
     * Initializes the scanner and controllers, then enters a loop to process user commands.
     * Supports operations for managing users (patients/doctors) and appointments.
     */
    static void main() {
        // Setup scanner & controllers
        Scanner sc = new Scanner(System.in);
        UserController uc = new UserController(sc);
        AppointmentController ac = new AppointmentController(sc);

        boolean continueProgram = true;

        // TODO: Add login logic

        System.out.println("Welcome to Doctor App!"); 
        // Main loop
        do {
            // Admin view
            System.out.println("\nPlease enter a command to continue");
            System.out.print("""
                    list patients | list doctors | create patient | create doctor | delete user | modify user | create appointment | modify appointment | cancel appointment | list appointments | exit
                    > """);
            String input = sc.nextLine();
            switch (input) {
                case "create patient" -> uc.createPatient();
                case "create doctor" -> uc.createDoctor();
                case "list patients" -> uc.listPatients();
                case "list doctors" -> uc.listDoctors();
                case "delete user" -> uc.deleteUser();
                case "modify user" -> uc.modifyUser();
                case "create appointment" -> uc.createAppointmentMenu(ac);
                case "modify appointment" -> ac.modifyAppointment();
                case "cancel appointment" -> ac.cancelAppointment();
                case "list appointments" -> uc.listAppointmentsForUser(ac);
                case "exit" -> continueProgram = false;
                default -> System.out.println("Invalid input. Please choose from the options above");
            }
        } while (continueProgram);
    }
}
