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
            System.out.println("Welcome to Doctor App (tm)! \n Please enter a command to continue:");
            System.out.print("""
                    list patients
                    list doctors
                    create patient (implemented)
                    create doctor  (implemented)
                    exit
                    """);
            String input = sc.nextLine();
            if (input.equals("create patient")) {
                uc.createPatient();
            } else if (input.equals("create doctor")) {
                uc.createDoctor();
            } else if (input.equals("exit")) {
                continueProgram = false;
            }
        } while (continueProgram);
    }
}
