package edu.secourse;

import edu.secourse.patientportal.controllers.UserController;

import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        // Setup scanner & controllers
        Scanner sc = new Scanner(System.in);
        UserController uc = new UserController(sc);

        boolean continueProgram = true;

        // TODO: Add login logic

        // Main loop
        do {
            // Admin view
            System.out.println("Welcome to Doctor App! \n Please enter a command to continue");
            System.out.print("""
                    list patients | list doctors | create patient | create doctor| exit
                    > """);
            String input = sc.nextLine();
            switch (input) {
                case "create patient" -> uc.createPatient();
                case "create doctor" -> uc.createDoctor();
                case "list patients" -> uc.listPatients();
                case "list doctors" -> uc.listDoctors();
                case "exit" -> continueProgram = false;
            }
        } while (continueProgram);
    }
}
